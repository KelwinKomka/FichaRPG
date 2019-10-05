package com.ES3.ficharpg.main.creationTabs;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.audiofx.DynamicsProcessing;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TabHost;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.ES3.ficharpg.Program;
import com.ES3.ficharpg.main.EquipmentEditActivity;
import com.ES3.ficharpg.main.SheetCreationActivity;
import com.ES3.ficharpg.main.SheetPickActivity;
import com.ES3.ficharpg.model.Armor;
import com.ES3.ficharpg.model.Equipment;
import com.ES3.ficharpg.R;
import com.ES3.ficharpg.model.Item;
import com.ES3.ficharpg.model.Sheet;
import com.ES3.ficharpg.model.Weapon;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CreationTabEquipment extends Fragment {

    private List<Equipment> selectedClassEquip;
    private List<Equipment> selectedBackgroundEquip;

    private EditText edtGold;
    private TableLayout tblClassEquip;
    private TableLayout tblBackgroundEquip;
    private Button btnAddEquip;
    private Button btnAddGold;

    private boolean equipmentChosed = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.tab_equipment_layout, container, false);

        selectedClassEquip = new ArrayList<>();
        selectedBackgroundEquip = new ArrayList<>();

        final TabHost tabHost = view.findViewById(R.id.tabHost);
        tabHost.setup();

        TabHost.TabSpec spec = tabHost.newTabSpec("equip");
        spec.setContent(R.id.tabEquip);
        spec.setIndicator(getString(R.string.equipment));
        tabHost.addTab(spec);

        spec = tabHost.newTabSpec("gold");
        spec.setContent(R.id.tabGold);
        spec.setIndicator(getString(R.string.gold));
        tabHost.addTab(spec);

        edtGold = view.findViewById(R.id.edtGold);
        tblClassEquip  = view.findViewById(R.id.tblClassEquip);
        tblBackgroundEquip = view.findViewById(R.id.tblBackgroundEquip);
        btnAddEquip = view.findViewById(R.id.btnAddEquip);
        btnAddGold = view.findViewById(R.id.btnAddGold);

        if (SheetCreationActivity.sheet.getCharClass() != null)
            fillOutTable(tblClassEquip, true);
        if (SheetCreationActivity.sheet.getBackground() != null)
            fillOutTable(tblBackgroundEquip, false);

        if (equipmentChosed)
            disableViews();

        if (SheetCreationActivity.sheet.getCharClass() == null) {
            final AlertDialog.Builder dialog = new AlertDialog.Builder(Objects.requireNonNull(getActivity()));
            dialog.setTitle("Atenção")
                    .setMessage("A ficha deve possuir uma classe selecionada!")
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialoginterface, int i) {
                            dialoginterface.cancel();
                        }
                    })
                    .show();
        }

        btnAddEquip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(Objects.requireNonNull(getActivity()));
                dialog.setTitle("Confirmação")
                        .setMessage("Deseja adicionar equipamento selecionado?")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialoginterface, int i) {
                                ArrayList<Equipment> equipment = new ArrayList<>();
                                equipment.addAll(selectedClassEquip);
                                equipment.addAll(selectedBackgroundEquip);

                                SheetCreationActivity.sheet.setEquipment(equipment);
                                equipmentChosed = true;
                                disableViews();
                            }
                        }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                                dialoginterface.cancel();
                        }})
                        .show();
            }
        });

        btnAddGold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(Objects.requireNonNull(getActivity()));
                dialog.setTitle("Confirmação")
                        .setMessage("Deseja adicionar ouro informado?")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialoginterface, int i) {
                                if (edtGold.getText().length() > 0) {
                                    try {
                                        SheetCreationActivity.sheet.setGold(Integer.parseInt(edtGold.getText().toString()) * 10);
                                    } catch (Exception ex) {
                                        SheetCreationActivity.sheet.setGold(0);
                                    }
                                }
                                equipmentChosed = true;
                                disableViews();
                            }
                        }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                        dialoginterface.cancel();
                    }}).show();
            }
        });

        return view;
    }

    @SuppressLint("ClickableViewAccessibility")
    private void fillOutTable(TableLayout table, boolean charEquip) {
        int count = 0;

        table.removeAllViews();
        if (charEquip) {
            for (ArrayList<Equipment> equipList: SheetCreationActivity.sheet.getCharClass().getStartingEquipment()) {
                TableRow tableRow = new TableRow(getContext());
                if (count % 2 == 0)
                    tableRow.setBackground(Objects.requireNonNull(getActivity()).getDrawable(R.drawable.layout_line_light));
                else
                    tableRow.setBackground(Objects.requireNonNull(getActivity()).getDrawable(R.drawable.layout_line));
                count++;

                RadioGroup radioGroup = new RadioGroup(getContext());
                radioGroup.setOrientation(RadioGroup.VERTICAL);
                for(final Equipment equip: equipList){
                    Item item;
                    if (equip.getWeapon() != null)
                        item = equip.getWeapon();
                    else if (equip.getArmor() != null)
                        item = equip.getArmor();
                    else
                        item = equip.getItem();

                    RadioButton radioButton  = new RadioButton(getContext());
                    String text = equip.getQuantity() + "x " + item.getName();
                    radioButton.setText(text);

                    radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            if (isChecked)
                                selectedClassEquip.add(equip);
                            else
                                selectedClassEquip.remove(equip);
                        }
                    });

                    radioGroup.addView(radioButton);
                }
                ((RadioButton) radioGroup.getChildAt(0)).setChecked(true);

                TableRow.LayoutParams radioGroupParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1f);
                radioGroup.setLayoutParams(radioGroupParams);
                tableRow.addView(radioGroup);

                TableLayout.LayoutParams tableParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT);
                tableParams.setMargins(2, 2, 2, 2); // left, top, right, bottom
                tableRow.setLayoutParams(tableParams);
                table.addView(tableRow);
            }

            TableRow tableRow = new TableRow(getActivity());
            if (count % 2 == 0)
                tableRow.setBackground(Objects.requireNonNull(getActivity()).getDrawable(R.drawable.layout_line_light));
            else
                tableRow.setBackground(Objects.requireNonNull(getActivity()).getDrawable(R.drawable.layout_line));

            TextView textView = new TextView(getActivity());
            textView.setText(R.string.createItem);
            textView.setTextAppearance(R.style.label);
            textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

            TableRow.LayoutParams textParams = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, 1f);
            textView.setLayoutParams(textParams);

            textView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        Intent intent = new Intent(getActivity(), EquipmentEditActivity.class);
                        startActivity(intent);
                    }
                    return false;
                }
            });
            tableRow.addView(textView);

            TableLayout.LayoutParams tableParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT);
            tableParams.setMargins(2, 2, 2, 2); // left, top, right, bottom
            tableRow.setLayoutParams(tableParams);
            table.addView(tableRow);

        } else {
            for (final Equipment equip: SheetCreationActivity.sheet.getBackground().getEquipment()) {
                Item item = equip.getItem();
                TableRow tableRow = new TableRow(getContext());
                if (count % 2 == 0)
                    tableRow.setBackground(Objects.requireNonNull(getActivity()).getDrawable(R.drawable.layout_line_light));
                else
                    tableRow.setBackground(Objects.requireNonNull(getActivity()).getDrawable(R.drawable.layout_line));
                count++;

                CheckBox checkBox = new CheckBox(getContext());
                checkBox.setText(item.getName());

                checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked)
                            selectedBackgroundEquip.add(equip);
                        else
                            selectedBackgroundEquip.remove(equip);
                    }
                });

                TableRow.LayoutParams checkBoxParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1f);
                checkBox.setLayoutParams(checkBoxParams);
                tableRow.addView(checkBox);

                TableLayout.LayoutParams tableParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT);
                tableParams.setMargins(2, 2, 2, 2); // left, top, right, bottom
                tableRow.setLayoutParams(tableParams);
                table.addView(tableRow);
            }
        }
    }

    private void disableViews(){
        btnAddEquip.setEnabled(false);
        btnAddGold.setEnabled(false);
        for (int j=0;j<tblClassEquip.getChildCount();j++){
            TableRow row = (TableRow) tblClassEquip.getChildAt(j);
            if (row.getChildAt(0) instanceof RadioGroup) {
                RadioGroup radioGroup = (RadioGroup) row.getChildAt(0);
                for (int k = 0; k < radioGroup.getChildCount(); k++) {
                    radioGroup.getChildAt(k).setEnabled(false);
                }
            }
        }
        for (int j=0;j<tblBackgroundEquip.getChildCount();j++){
            TableRow row = (TableRow) tblBackgroundEquip.getChildAt(j);
            row.getChildAt(0).setEnabled(false);
        }
        edtGold.setEnabled(false);
    }
}
