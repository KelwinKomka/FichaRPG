package com.ES3.ficharpg.main.creationTabs;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.ES3.ficharpg.Program;
import com.ES3.ficharpg.main.MainActivity;
import com.ES3.ficharpg.main.SheetCreationActivity;
import com.ES3.ficharpg.model.DiceRollRegistry;
import com.ES3.ficharpg.model.Equipment;
import com.ES3.ficharpg.model.Race;
import com.ES3.ficharpg.R;
import com.ES3.ficharpg.model.Sheet;
import com.ES3.ficharpg.model.SheetSpells;
import com.ES3.ficharpg.model.Spell;
import com.ES3.ficharpg.model.Weapon;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CreationTabSummary extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_summary_layout, container, false);

        final TextView txtContName = view.findViewById(R.id.txtContName);
        final TextView txtContRace = view.findViewById(R.id.txtContRace);
        final TextView txtContClass = view.findViewById(R.id.txtContClass);
        final TextView txtContBackground = view.findViewById(R.id.txtContBackground);
        final Button btnFinish = view.findViewById(R.id.btnFinish);

        if (SheetCreationActivity.sheet.getName().length() > 0)
            txtContName.setText(SheetCreationActivity.sheet.getName());
        else {
            txtContName.setText(getString(R.string.noInfo));
            txtContName.setTextColor(Color.RED);
        }

        if (SheetCreationActivity.sheet.getRace() != null)
            txtContRace.setText(SheetCreationActivity.sheet.getRace().getName());
        else {
            txtContRace.setText(getString(R.string.noInfo));
            txtContRace.setTextColor(Color.RED);
        }

        if (SheetCreationActivity.sheet.getCharClass() != null)
            txtContClass.setText(getString(getResources().getIdentifier(SheetCreationActivity.sheet.getCharClass().getName(), "string", Objects.requireNonNull(getContext()).getPackageName())));
        else {
            txtContClass.setText(getString(R.string.noInfo));
            txtContClass.setTextColor(Color.RED);
        }

        if (SheetCreationActivity.sheet.getBackground() != null)
            txtContBackground.setText(getString(getResources().getIdentifier(SheetCreationActivity.sheet.getBackground().getName(), "string", Objects.requireNonNull(getContext()).getPackageName())));
        else {
            txtContBackground.setText(getString(R.string.noInfo));
            txtContBackground.setTextColor(Color.RED);
        }

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = checkInfo();
                if (msg.length() == 0) {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(Objects.requireNonNull(getActivity()));
                    dialog.setTitle("Confirmação")
                            .setMessage("Deseja finalizar a criação da ficha?")
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialoginterface, int i) {
                                    goToMain();
                                }
                            }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialoginterface, int i) {
                            dialoginterface.cancel();
                        }
                    }).show();
                } else {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(Objects.requireNonNull(getActivity()));
                    dialog.setTitle("Aviso")
                            .setMessage(msg)
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialoginterface, int i) {
                                    dialoginterface.cancel();
                                }
                            }).show();
                }
            }
        });

        return view;
    }

    private String checkInfo(){
        StringBuilder result = new StringBuilder();
        if (SheetCreationActivity.sheet.getName().length() == 0)
            result.append("\nNome não informado.");
        if (SheetCreationActivity.sheet.getRace() == null)
            result.append("\nRaça não escolhida.");
        if (SheetCreationActivity.sheet.getCharClass() == null)
            result.append("\nClasse não escolhida.");
        if (SheetCreationActivity.sheet.getBackground() == null)
            result.append("\nAtencedente não selecionado.");
        if ((SheetCreationActivity.sheet.getAttribute(Program.SCORE, Program.STRENGTH) == 0) ||
                (SheetCreationActivity.sheet.getAttribute(Program.SCORE, Program.DEXTERITY) == 0) ||
                (SheetCreationActivity.sheet.getAttribute(Program.SCORE, Program.CONSTITUTION) == 0) ||
                (SheetCreationActivity.sheet.getAttribute(Program.SCORE, Program.INTELLIGENCE) == 0) ||
                (SheetCreationActivity.sheet.getAttribute(Program.SCORE, Program.WISDOM) == 0) ||
                (SheetCreationActivity.sheet.getAttribute(Program.SCORE, Program.CHARISMA) == 0))
            result.append("\nPontos de habilidade não foram totalmente distribuídos.");
        return result.toString();
    }

    private void goToMain(){
        fillData();
        toJson();
        Intent intent = new Intent(getActivity(), MainActivity.class);
        intent.putExtra("Sheet", SheetCreationActivity.sheet);
        startActivity(intent);
        Objects.requireNonNull(getActivity()).finish();
    }

    private void toJson(){
        ArrayList<Sheet> sheets = (ArrayList<Sheet>) Program.readFile(getContext(), "sheet.json");
        if (sheets == null)
            sheets = new ArrayList<>();
        sheets.add(SheetCreationActivity.sheet);
        Program.createFile(Objects.requireNonNull(getActivity()), "sheet.json", sheets);
    }

    private void fillData(){
        Sheet sheet = SheetCreationActivity.sheet;
        sheet.setHitPoints(sheet.getCharClass().getHitDice() + sheet.getAttribute(Program.MODIFIER, Program.CONSTITUTION));
        sheet.setMaxHitPoints(sheet.getHitPoints());
        sheet.setExp(0);
        sheet.setSpeed(sheet.getRace().getSpeed());
        sheet.setInitiative(2);
        sheet.setArmor(10);
        sheet.setProficiency(2);

        sheet.setAttribute(Program.MODIFIER, Program.STRENGTH, Program.getModValue(sheet.getAttribute(Program.SCORE, Program.STRENGTH)));
        sheet.setAttribute(Program.MODIFIER, Program.DEXTERITY, Program.getModValue(sheet.getAttribute(Program.SCORE, Program.DEXTERITY)));
        sheet.setAttribute(Program.MODIFIER, Program.CONSTITUTION, Program.getModValue(sheet.getAttribute(Program.SCORE, Program.CONSTITUTION)));
        sheet.setAttribute(Program.MODIFIER, Program.INTELLIGENCE, Program.getModValue(sheet.getAttribute(Program.SCORE, Program.INTELLIGENCE)));
        sheet.setAttribute(Program.MODIFIER, Program.WISDOM, Program.getModValue(sheet.getAttribute(Program.SCORE, Program.WISDOM)));
        sheet.setAttribute(Program.MODIFIER, Program.CHARISMA, Program.getModValue(sheet.getAttribute(Program.SCORE, Program.CHARISMA)));

        sheet.setAttribute(Program.SAVING, Program.STRENGTH, Program.getModValue(sheet.getAttribute(Program.SCORE, Program.STRENGTH)));
        sheet.setAttribute(Program.SAVING, Program.DEXTERITY, Program.getModValue(sheet.getAttribute(Program.SCORE, Program.DEXTERITY)));
        sheet.setAttribute(Program.SAVING, Program.CONSTITUTION, Program.getModValue(sheet.getAttribute(Program.SCORE, Program.CONSTITUTION)));
        sheet.setAttribute(Program.SAVING, Program.INTELLIGENCE, Program.getModValue(sheet.getAttribute(Program.SCORE, Program.INTELLIGENCE)));
        sheet.setAttribute(Program.SAVING, Program.WISDOM, Program.getModValue(sheet.getAttribute(Program.SCORE, Program.WISDOM)));
        sheet.setAttribute(Program.SAVING, Program.CHARISMA, Program.getModValue(sheet.getAttribute(Program.SCORE, Program.CHARISMA)));

        sheet.setSkill(Program.ATHLETICS, Program.getModValue(sheet.getAttribute(Program.SCORE, Program.STRENGTH)));
        sheet.setSkillProficiency(Program.ATHLETICS, false);

        sheet.setSkill(Program.ACROBATICS, Program.getModValue(sheet.getAttribute(Program.SCORE, Program.DEXTERITY)));
        sheet.setSkillProficiency(Program.ACROBATICS, false);

        sheet.setSkill(Program.ARCANA, Program.getModValue(sheet.getAttribute(Program.SCORE, Program.INTELLIGENCE)));
        sheet.setSkillProficiency(Program.ARCANA, false);

        sheet.setSkill(Program.PERFORMANCE, Program.getModValue(sheet.getAttribute(Program.SCORE, Program.CHARISMA)));
        sheet.setSkillProficiency(Program.PERFORMANCE, false);

        sheet.setSkill(Program.DECEPTION, Program.getModValue(sheet.getAttribute(Program.SCORE, Program.CHARISMA)));
        sheet.setSkillProficiency(Program.DECEPTION, false);

        sheet.setSkill(Program.STEALTH, Program.getModValue(sheet.getAttribute(Program.SCORE, Program.DEXTERITY)));
        sheet.setSkillProficiency(Program.STEALTH, false);

        sheet.setSkill(Program.HISTORY, Program.getModValue(sheet.getAttribute(Program.SCORE, Program.INTELLIGENCE)));
        sheet.setSkillProficiency(Program.HISTORY, false);

        sheet.setSkill(Program.INSIGHT, Program.getModValue(sheet.getAttribute(Program.SCORE, Program.WISDOM)));
        sheet.setSkillProficiency(Program.INSIGHT, false);

        sheet.setSkill(Program.INTIMIDATION, Program.getModValue(sheet.getAttribute(Program.SCORE, Program.CHARISMA)));
        sheet.setSkillProficiency(Program.INTIMIDATION, false);

        sheet.setSkill(Program.INVESTIGATION, Program.getModValue(sheet.getAttribute(Program.SCORE, Program.INTELLIGENCE)));
        sheet.setSkillProficiency(Program.INVESTIGATION, false);

        sheet.setSkill(Program.ANIMALHANDLING, Program.getModValue(sheet.getAttribute(Program.SCORE, Program.WISDOM)));
        sheet.setSkillProficiency(Program.ANIMALHANDLING, false);

        sheet.setSkill(Program.MEDICINE, Program.getModValue(sheet.getAttribute(Program.SCORE, Program.WISDOM)));
        sheet.setSkillProficiency(Program.MEDICINE, false);

        sheet.setSkill(Program.NATURE, Program.getModValue(sheet.getAttribute(Program.SCORE, Program.INTELLIGENCE)));
        sheet.setSkillProficiency(Program.NATURE, false);

        sheet.setSkill(Program.PERCEPTION, Program.getModValue(sheet.getAttribute(Program.SCORE, Program.WISDOM)));
        sheet.setSkillProficiency(Program.PERCEPTION, false);

        sheet.setSkill(Program.PERSUASION, Program.getModValue(sheet.getAttribute(Program.SCORE, Program.CHARISMA)));
        sheet.setSkillProficiency(Program.PERSUASION, false);

        sheet.setSkill(Program.SLEIGHTOFHAND, Program.getModValue(sheet.getAttribute(Program.SCORE, Program.DEXTERITY)));
        sheet.setSkillProficiency(Program.SLEIGHTOFHAND, false);

        sheet.setSkill(Program.RELIGION, Program.getModValue(sheet.getAttribute(Program.SCORE, Program.INTELLIGENCE)));
        sheet.setSkillProficiency(Program.RELIGION, false);

        sheet.setSkill(Program.SURVIVAL, Program.getModValue(sheet.getAttribute(Program.SCORE, Program.WISDOM)));
        sheet.setSkillProficiency(Program.SURVIVAL, false);

        for (String proficiency: sheet.getCharClass().getSkills())
            sheet.setSkillProficiency(proficiency, true);

        for (String proficiency: sheet.getBackground().getSkillProficiencies())
            sheet.setSkillProficiency(proficiency, true);

        sheet.setPassivePerception(8 + sheet.getAttribute(Program.MODIFIER, Program.WISDOM));
        sheet.setPassiveInvestigation(8 + sheet.getAttribute(Program.MODIFIER, Program.INTELLIGENCE));
        sheet.setPassiveInsight(8 + sheet.getAttribute(Program.MODIFIER, Program.WISDOM));

        sheet.setDiceRollRegistry(new ArrayList<DiceRollRegistry>());

        SheetSpells sheetSpells = new SheetSpells();
        sheetSpells.setModifier(Program.INTELLIGENCE);
        sheetSpells.setSaveDC(10 + sheet.getAttribute(Program.MODIFIER, Program.INTELLIGENCE));
        sheetSpells.setSpellAttack(sheet.getAttribute(Program.MODIFIER, Program.INTELLIGENCE));

        sheetSpells.setCantrips(new ArrayList<Spell>());
        sheetSpells.setLevel1(new ArrayList<Spell>());
        sheetSpells.setLevel2(new ArrayList<Spell>());
        sheetSpells.setLevel3(new ArrayList<Spell>());
        sheetSpells.setLevel4(new ArrayList<Spell>());
        sheetSpells.setLevel5(new ArrayList<Spell>());
        sheetSpells.setLevel6(new ArrayList<Spell>());
        sheetSpells.setLevel7(new ArrayList<Spell>());
        sheetSpells.setLevel8(new ArrayList<Spell>());
        sheetSpells.setLevel9(new ArrayList<Spell>());

        if (Program.WIZARD.equals(sheet.getCharClass().getName())) {
            List<Spell> spells = Spell.getSpellList(getContext());

            if (spells != null)
                for (Spell spell: spells){
                    ArrayList<Spell> spellList;
                    switch (spell.getLevel()){
                        case 0:
                            spellList = sheetSpells.getCantrips();
                            spellList.add(spell);
                            sheetSpells.setCantrips(spellList);
                            break;
                        case 1:
                            spellList = sheetSpells.getLevel1();
                            spellList.add(spell);
                            sheetSpells.setLevel1(spellList);
                            break;
                        case 2:
                            spellList = sheetSpells.getLevel2();
                            spellList.add(spell);
                            sheetSpells.setLevel2(spellList);
                            break;
                        case 3:
                            spellList = sheetSpells.getLevel3();
                            spellList.add(spell);
                            sheetSpells.setLevel3(spellList);
                            break;
                        case 4:
                            spellList = sheetSpells.getLevel4();
                            spellList.add(spell);
                            sheetSpells.setLevel4(spellList);
                            break;
                        case 5:
                            spellList = sheetSpells.getLevel5();
                            spellList.add(spell);
                            sheetSpells.setLevel5(spellList);
                            break;
                        case 6:
                            spellList = sheetSpells.getLevel6();
                            spellList.add(spell);
                            sheetSpells.setLevel6(spellList);
                            break;
                        case 7:
                            spellList = sheetSpells.getLevel7();
                            spellList.add(spell);
                            sheetSpells.setLevel7(spellList);
                            break;
                        case 8:
                            spellList = sheetSpells.getLevel8();
                            spellList.add(spell);
                            sheetSpells.setLevel8(spellList);
                            break;
                        case 9:
                            spellList = sheetSpells.getLevel9();
                            spellList.add(spell);
                            sheetSpells.setLevel9(spellList);
                            break;
                    }
                    spellList = sheetSpells.getKnownSpells();
                    spellList.add(spell);
                    sheetSpells.setKnownSpells(spellList);
                }
        }
        sheet.setSpells(sheetSpells);

        if (sheet.getEquipment() == null)
            sheet.setEquipment(new ArrayList<Equipment>());
    }
}
