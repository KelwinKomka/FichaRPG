package com.ES3.ficharpg.main.creationTabs;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.ES3.ficharpg.main.SheetCreationActivity;
import com.ES3.ficharpg.model.CharClass;
import com.ES3.ficharpg.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CreationTabClass extends Fragment {

    private CharClass charClass;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_class_layout, container, false);

        final List<CharClass> classes = CharClass.getClassList(getContext());

        final Spinner spnClass = view.findViewById(R.id.spnClass);
        final EditText txtInfo = view.findViewById(R.id.txtInfo);
        final Button btnConfirm = view.findViewById(R.id.btnConfirm);

        List<String> spinnerArray =  new ArrayList<>();
        if (classes != null) {
            for (CharClass charClass : classes)
                spinnerArray.add(getString(getResources().getIdentifier(charClass.getName(), "string", Objects.requireNonNull(getContext()).getPackageName())));
        } else {
            spinnerArray.add("Clérigo");
            spinnerArray.add("Ladino");
            spinnerArray.add("Lutador");
            spinnerArray.add("Mago");
            spinnerArray.add("Paladino");
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(Objects.requireNonNull(getActivity()), android.R.layout.simple_spinner_item, spinnerArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnClass.setAdapter(adapter);

        spnClass.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try {
                    charClass = classes.get(position);

                    StringBuilder armors = new StringBuilder("Proficiência em armaduras: \n");
                    for (String armorProf : charClass.getArmorProficiencies())
                        if (!"".equals(armorProf))
                            armors.append(getString(getResources().getIdentifier(armorProf, "string", Objects.requireNonNull(getContext()).getPackageName()))).append("\n");

                    StringBuilder weapons = new StringBuilder("Proficiência em armas: \n");
                    for (String weaponProf : charClass.getWeaponProficiencies())
                        if (!"".equals(weaponProf))
                            weapons.append(getString(getResources().getIdentifier(weaponProf, "string", Objects.requireNonNull(getContext()).getPackageName()))).append("\n");

                    StringBuilder tools = new StringBuilder("Proficiência em ferramentas: \n");
                    for (String toolProf : charClass.getToolProficiencies())
                        if (!"".equals(toolProf))
                            tools.append(getString(getResources().getIdentifier(toolProf, "string", Objects.requireNonNull(getContext()).getPackageName()))).append("\n");

                    StringBuilder skills = new StringBuilder("Proficiência em habilidades: \n");
                    for (String skillProf : charClass.getSkills())
                        if (!"".equals(skillProf))
                            skills.append(getString(getResources().getIdentifier(skillProf, "string", Objects.requireNonNull(getContext()).getPackageName()))).append("\n");

                    StringBuilder savingThrows = new StringBuilder("Proficiência em testes de resistência: \n");
                    for (String savingProf : charClass.getSavingThrows())
                        if (!"".equals(savingProf))
                            savingThrows.append(getString(getResources().getIdentifier(savingProf, "string", Objects.requireNonNull(getContext()).getPackageName()))).append("\n");

                    String info = "Dado de vida: d" + charClass.getHitDice() +
                            "\nVida inicial: " + charClass.getStartHitPoints() + " + Modificador de constituição" +
                            "\n\n" + armors +
                            "\n" + weapons +
                            "\n" + tools +
                            "\n" + skills +
                            "\n" + savingThrows;

                    txtInfo.setText(info);
                } catch (Exception ex) {
                    txtInfo.setText("");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //
            }
        });

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SheetCreationActivity.sheet.getCharClass() == null) {
                    SheetCreationActivity.sheet.setCharClass(charClass);

                    AlertDialog.Builder dialog = new AlertDialog.Builder(Objects.requireNonNull(getActivity()));
                    dialog.setTitle("Confirmação")
                            .setMessage("Classe selecionada: " + getString(getResources().getIdentifier(charClass.getName(), "string", Objects.requireNonNull(getContext()).getPackageName())))
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialoginterface, int i) {
                                    dialoginterface.cancel();
                                }
                            }).show();
                }else {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(Objects.requireNonNull(getActivity()));
                    dialog.setTitle("Confirmação")
                            .setMessage("Deseja alterar a classe?")
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialoginterface, int i) {
                                    SheetCreationActivity.sheet.setCharClass(charClass);
                                }
                            }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialoginterface, int i) {
                            dialoginterface.cancel();
                        }
                    }).show();
                }
            }
        });

        return view;
    }
}
