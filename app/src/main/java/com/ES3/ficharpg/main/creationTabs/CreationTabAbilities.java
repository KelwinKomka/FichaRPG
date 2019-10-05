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
import android.widget.Spinner;
import android.widget.TextView;

import com.ES3.ficharpg.Program;
import com.ES3.ficharpg.R;
import com.ES3.ficharpg.main.SheetCreationActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class CreationTabAbilities extends Fragment {

    private List<String> spinnerArray;
    private List<Spinner> spinners;
    private List<ArrayAdapter<String>> adapters;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_abilities_layout, container, false);

        final Spinner spnStr = view.findViewById(R.id.spnStr);
        final Spinner spnDex = view.findViewById(R.id.spnDex);
        final Spinner spnCon = view.findViewById(R.id.spnCon);
        final Spinner spnInt = view.findViewById(R.id.spnInt);
        final Spinner spnWis = view.findViewById(R.id.spnWis);
        final Spinner spnCha = view.findViewById(R.id.spnCha);

        final TextView txtContStr = view.findViewById(R.id.txtContStr);
        final TextView txtContStrMod = view.findViewById(R.id.txtContStrMod);
        final TextView txtContStrSave = view.findViewById(R.id.txtContStrSave);

        final TextView txtContDex = view.findViewById(R.id.txtContDex);
        final TextView txtContDexMod = view.findViewById(R.id.txtContDexMod);
        final TextView txtContDexSave = view.findViewById(R.id.txtContDexSave);

        final TextView txtContCon = view.findViewById(R.id.txtContCon);
        final TextView txtContConMod = view.findViewById(R.id.txtContConMod);
        final TextView txtContConSave = view.findViewById(R.id.txtContConSave);

        final TextView txtContInt = view.findViewById(R.id.txtContInt);
        final TextView txtContIntMod = view.findViewById(R.id.txtContIntMod);
        final TextView txtContIntSave = view.findViewById(R.id.txtContIntSave);

        final TextView txtContWis = view.findViewById(R.id.txtContWis);
        final TextView txtContWisMod = view.findViewById(R.id.txtContWisMod);
        final TextView txtContWisSave = view.findViewById(R.id.txtContWisSave);

        final TextView txtContCha = view.findViewById(R.id.txtContCha);
        final TextView txtContChaMod = view.findViewById(R.id.txtContChaMod);
        final TextView txtContChaSave = view.findViewById(R.id.txtContChaSave);

        spinnerArray = new ArrayList<>();
        spinnerArray.add("-");
        spinnerArray.add("15");
        spinnerArray.add("14");
        spinnerArray.add("13");
        spinnerArray.add("12");
        spinnerArray.add("10");
        spinnerArray.add("8");

        final ArrayAdapter<String> adapterStr = new ArrayAdapter<>(Objects.requireNonNull(getActivity()), android.R.layout.simple_spinner_item, new ArrayList<>(spinnerArray));
        adapterStr.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnStr.setAdapter(adapterStr);

        final ArrayAdapter<String> adapterDex = new ArrayAdapter<>(Objects.requireNonNull(getActivity()), android.R.layout.simple_spinner_item, new ArrayList<>(spinnerArray));
        adapterDex.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnDex.setAdapter(adapterDex);

        final ArrayAdapter<String> adapterCon = new ArrayAdapter<>(Objects.requireNonNull(getActivity()), android.R.layout.simple_spinner_item, new ArrayList<>(spinnerArray));
        adapterCon.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnCon.setAdapter(adapterCon);

        final ArrayAdapter<String> adapterInt = new ArrayAdapter<>(Objects.requireNonNull(getActivity()), android.R.layout.simple_spinner_item, new ArrayList<>(spinnerArray));
        adapterInt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnInt.setAdapter(adapterInt);

        final ArrayAdapter<String> adapterWis = new ArrayAdapter<>(Objects.requireNonNull(getActivity()), android.R.layout.simple_spinner_item, new ArrayList<>(spinnerArray));
        adapterWis.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnWis.setAdapter(adapterWis);

        final ArrayAdapter<String> adapterCha = new ArrayAdapter<>(Objects.requireNonNull(getActivity()), android.R.layout.simple_spinner_item, new ArrayList<>(spinnerArray));
        adapterCha.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnCha.setAdapter(adapterCha);

        spinners = new ArrayList<>();
        spinners.add(spnStr);
        spinners.add(spnDex);
        spinners.add(spnCon);
        spinners.add(spnInt);
        spinners.add(spnWis);
        spinners.add(spnCha);

        adapters = new ArrayList<>();
        adapters.add(adapterStr);
        adapters.add(adapterDex);
        adapters.add(adapterCon);
        adapters.add(adapterInt);
        adapters.add(adapterWis);
        adapters.add(adapterCha);

        spnStr.setEnabled(SheetCreationActivity.sheet.getRace() != null);
        spnDex.setEnabled(SheetCreationActivity.sheet.getRace() != null);
        spnCon.setEnabled(SheetCreationActivity.sheet.getRace() != null);
        spnInt.setEnabled(SheetCreationActivity.sheet.getRace() != null);
        spnWis.setEnabled(SheetCreationActivity.sheet.getRace() != null);
        spnCha.setEnabled(SheetCreationActivity.sheet.getRace() != null);

        if (SheetCreationActivity.sheet.getRace() == null) {
            final AlertDialog.Builder dialog = new AlertDialog.Builder(Objects.requireNonNull(getActivity()));
            dialog.setTitle("Atenção")
                    .setMessage("A ficha deve possuir uma raça selecionada!")
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialoginterface, int i) {
                            dialoginterface.cancel();
                        }
                    })
                    .show();
        }

        spnStr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (spnStr.getSelectedItem() != null && !"-".equals(spnStr.getSelectedItem().toString())) {
                    int score = Integer.parseInt(spnStr.getSelectedItem().toString());
                    if (SheetCreationActivity.sheet.getRace() != null)
                        score += SheetCreationActivity.sheet.getRace().getSkill(Program.STRENGTH);
                    txtContStr.setText(String.valueOf(score));
                    txtContStrMod.setText(String.valueOf(Program.getModValue(score)));
                    txtContStrSave.setText(String.valueOf(Program.getModValue(score)));
                    SheetCreationActivity.sheet.setAttribute(Program.SCORE, Program.STRENGTH, score);
                } else {
                    txtContStr.setText("0");
                    txtContStrMod.setText("0");
                    txtContStrSave.setText("0");
                }

                changeAdapterData();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                txtContStr.setText("0");
                txtContStrMod.setText("0");
                txtContStrSave.setText("0");
            }
        });

        spnDex.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (spnDex.getSelectedItem() != null && !"-".equals(spnDex.getSelectedItem().toString())) {
                    int score = Integer.parseInt(spnDex.getSelectedItem().toString());
                    if (SheetCreationActivity.sheet.getRace() != null)
                        score += SheetCreationActivity.sheet.getRace().getSkill(Program.DEXTERITY);
                    txtContDex.setText(String.valueOf(score));
                    txtContDexMod.setText(String.valueOf(Program.getModValue(score)));
                    txtContDexSave.setText(String.valueOf(Program.getModValue(score)));
                    SheetCreationActivity.sheet.setAttribute(Program.SCORE, Program.DEXTERITY, score);
                } else {
                    txtContDex.setText("0");
                    txtContDexMod.setText("0");
                    txtContDexSave.setText("0");
                }

                changeAdapterData();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                txtContDex.setText("0");
                txtContDexMod.setText("0");
                txtContDexSave.setText("0");
            }
        });

        spnCon.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (spnCon.getSelectedItem() != null && !"-".equals(spnCon.getSelectedItem().toString())) {
                    int score = Integer.parseInt(spnCon.getSelectedItem().toString());
                    if (SheetCreationActivity.sheet.getRace() != null)
                        score += SheetCreationActivity.sheet.getRace().getSkill(Program.CONSTITUTION);
                    txtContCon.setText(String.valueOf(score));
                    txtContConMod.setText(String.valueOf(Program.getModValue(score)));
                    txtContConSave.setText(String.valueOf(Program.getModValue(score)));
                    SheetCreationActivity.sheet.setAttribute(Program.SCORE, Program.CONSTITUTION, score);
                } else {
                    txtContCon.setText("0");
                    txtContConMod.setText("0");
                    txtContConSave.setText("0");
                }

                changeAdapterData();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                txtContCon.setText("0");
                txtContConMod.setText("0");
                txtContConSave.setText("0");
            }
        });

        spnInt.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (spnInt.getSelectedItem() != null && !"-".equals(spnInt.getSelectedItem().toString())) {
                    int score = Integer.parseInt(spnInt.getSelectedItem().toString());
                    if (SheetCreationActivity.sheet.getRace() != null)
                        score += SheetCreationActivity.sheet.getRace().getSkill(Program.INTELLIGENCE);
                    txtContInt.setText(String.valueOf(score));
                    txtContIntMod.setText(String.valueOf(Program.getModValue(score)));
                    txtContIntSave.setText(String.valueOf(Program.getModValue(score)));
                    SheetCreationActivity.sheet.setAttribute(Program.SCORE, Program.INTELLIGENCE, score);
                } else {
                    txtContInt.setText("0");
                    txtContIntMod.setText("0");
                    txtContIntSave.setText("0");
                }

                changeAdapterData();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                txtContInt.setText("0");
                txtContIntMod.setText("0");
                txtContIntSave.setText("0");
            }
        });

        spnWis.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (spnWis.getSelectedItem() != null && !"-".equals(spnWis.getSelectedItem().toString())) {
                    int score = Integer.parseInt(spnWis.getSelectedItem().toString());
                    if (SheetCreationActivity.sheet.getRace() != null)
                        score += SheetCreationActivity.sheet.getRace().getSkill(Program.WISDOM);
                    txtContWis.setText(String.valueOf(score));
                    txtContWisMod.setText(String.valueOf(Program.getModValue(score)));
                    txtContWisSave.setText(String.valueOf(Program.getModValue(score)));
                    SheetCreationActivity.sheet.setAttribute(Program.SCORE, Program.WISDOM, score);
                } else {
                    txtContWis.setText("0");
                    txtContWisMod.setText("0");
                    txtContWisSave.setText("0");
                }

                changeAdapterData();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                txtContWis.setText("0");
                txtContWisMod.setText("0");
                txtContWisSave.setText("0");
            }
        });

        spnCha.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (spnCha.getSelectedItem() != null && !"-".equals(spnCha.getSelectedItem().toString())) {
                    int score = Integer.parseInt(spnCha.getSelectedItem().toString());
                    if (SheetCreationActivity.sheet.getRace() != null)
                        score += SheetCreationActivity.sheet.getRace().getSkill(Program.CHARISMA);
                    txtContCha.setText(String.valueOf(score));
                    txtContChaMod.setText(String.valueOf(Program.getModValue(score)));
                    txtContChaSave.setText(String.valueOf(Program.getModValue(score)));
                    SheetCreationActivity.sheet.setAttribute(Program.SCORE, Program.CHARISMA, score);
                } else {
                    txtContCha.setText("0");
                    txtContChaMod.setText("0");
                    txtContChaSave.setText("0");
                }

                changeAdapterData();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                txtContCha.setText("0");
                txtContChaMod.setText("0");
                txtContChaSave.setText("0");
            }
        });

        return view;
    }

    private void changeAdapterData(){
        for (ArrayAdapter<String> adapter: adapters) {
            List<String> data = new ArrayList<>(spinnerArray);
            String selectedItem = "";
            for (Spinner spinner: spinners) {
                if (spinner.getAdapter() == adapter && spinner.getSelectedItem() != null) {
                    selectedItem = spinner.getSelectedItem().toString();
                } else if (spinner.getSelectedItem() != null && spinner.getSelectedItemPosition() > 0){
                    data.remove(spinner.getSelectedItem().toString());
                }
            }

            adapter.clear();
            adapter.addAll(data);
            adapter.notifyDataSetChanged();
            if (!"".equals(selectedItem))
                for (Spinner spinner: spinners)
                    if (spinner.getAdapter() == adapter) {
                        spinner.setSelection(adapter.getPosition(selectedItem));
                        break;
                    }
        }
    }
}
