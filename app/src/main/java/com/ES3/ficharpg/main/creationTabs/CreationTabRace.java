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
import com.ES3.ficharpg.model.Race;
import com.ES3.ficharpg.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class CreationTabRace extends Fragment {

    private Race race;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_race_layout, container, false);

        final List<Race> races = Race.getRaceList(getContext());

        final Spinner spnRace = view.findViewById(R.id.spnRace);
        final EditText txtInfo = view.findViewById(R.id.txtInfo);
        final Button btnConfirm = view.findViewById(R.id.btnConfirm);

        List<String> spinnerArray =  new ArrayList<>();
        if (races != null) {
            for (Race race : races)
                spinnerArray.add(race.getName());
        } else {
            spinnerArray.add("Anão");
            spinnerArray.add("Elfo");
            spinnerArray.add("Gnomo");
            spinnerArray.add("Halfling");
            spinnerArray.add("Humano");
            spinnerArray.add("Meio-Elfo");
            spinnerArray.add("Tielfing");
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(Objects.requireNonNull(getActivity()), android.R.layout.simple_spinner_item, spinnerArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnRace.setAdapter(adapter);

        spnRace.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try {
                    race = races.get(position);

                    Set<String> skillKey = race.getSkills().keySet();
                    StringBuilder skills = new StringBuilder();
                    for (String skill : skillKey) {
                        if (race.getSkill(skill) > 0) {
                            if (skills.length() > 0)
                                skills.append(", ");

                            skills.append(getString(getResources().getIdentifier(skill, "string", Objects.requireNonNull(getContext()).getPackageName())))
                                    .append(" ").append(race.getSkill(skill));
                        }
                    }

                    StringBuilder languages = new StringBuilder("Idiomas: \n");
                    for (String language : race.getLanguages())
                        if (!"".equals(language))
                            languages.append(getString(getResources().getIdentifier(language, "string", Objects.requireNonNull(getContext()).getPackageName()))).append("\n");

                    StringBuilder traits = new StringBuilder("Características: \n");
                    for (String trait : race.getTraits())
                        traits.append(trait).append("\n");

                    String info = "Pontos : \n" + skills + "\n\n" + languages + "\n" + traits;
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
                if (SheetCreationActivity.sheet.getRace() == null) {
                    SheetCreationActivity.sheet.setRace(race);

                    AlertDialog.Builder dialog = new AlertDialog.Builder(Objects.requireNonNull(getActivity()));
                    dialog.setTitle("Confirmação")
                            .setMessage("Raça selecionada: " + race.getName())
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialoginterface, int i) {
                                    dialoginterface.cancel();
                                }
                            }).show();
                } else {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(Objects.requireNonNull(getActivity()));
                    dialog.setTitle("Confirmação")
                            .setMessage("Deseja alterar a raça?")
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialoginterface, int i) {
                                    SheetCreationActivity.sheet.setRace(race);
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
