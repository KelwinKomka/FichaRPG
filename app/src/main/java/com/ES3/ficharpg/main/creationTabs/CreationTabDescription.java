package com.ES3.ficharpg.main.creationTabs;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.ES3.ficharpg.main.SheetCreationActivity;
import com.ES3.ficharpg.model.Background;
import com.ES3.ficharpg.model.LifeStyle;
import com.ES3.ficharpg.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CreationTabDescription extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_description_layout, container, false);

        final List<Background> backgrounds = Background.getBackgroundList(getContext());
        final List<LifeStyle> lifeStyles = LifeStyle.getLifeStyleList(getContext());

        final Spinner spnBackground = view.findViewById(R.id.spnBackground);
        final Spinner spnAlignment = view.findViewById(R.id.spnAlignment);
        final Spinner spnLifeStyle = view.findViewById(R.id.spnLifeStyle);

        EditText edtFaith = view.findViewById(R.id.edtFaith);
        EditText edtGender = view.findViewById(R.id.edtGender);
        EditText edtEyes = view.findViewById(R.id.edtEyes);
        EditText edtSize = view.findViewById(R.id.edtSize);
        EditText edtHeight = view.findViewById(R.id.edtHeight);
        EditText edtAge = view.findViewById(R.id.edtAge);
        EditText edtTraits = view.findViewById(R.id.edtTraits);

        List<String> spinnerArrayBackground =  new ArrayList<>();
        if (backgrounds != null) {
            for (Background background : backgrounds)
                spinnerArrayBackground.add(getString(getResources().getIdentifier(background.getName(), "string", Objects.requireNonNull(getContext()).getPackageName())));
        } else {
            spinnerArrayBackground.add("Acólito");
            spinnerArrayBackground.add("Criminoso");
            spinnerArrayBackground.add("Herói local");
            spinnerArrayBackground.add("Nobre");
            spinnerArrayBackground.add("Sábio");
            spinnerArrayBackground.add("Soldado");
        }

        ArrayAdapter<String> adapterBackground = new ArrayAdapter<>(Objects.requireNonNull(getActivity()), android.R.layout.simple_spinner_item, spinnerArrayBackground);
        adapterBackground.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnBackground.setAdapter(adapterBackground);

        List<String> spinnerArrayAlignment =  new ArrayList<>();
        spinnerArrayAlignment.add("Leal Bom");
        spinnerArrayAlignment.add("Leal Neutro");
        spinnerArrayAlignment.add("Leal Mau");
        spinnerArrayAlignment.add("Neutro Bom");
        spinnerArrayAlignment.add("Neutro");
        spinnerArrayAlignment.add("Neutro Mau");
        spinnerArrayAlignment.add("Caótico Bom");
        spinnerArrayAlignment.add("Caótico Neutro");
        spinnerArrayAlignment.add("Caótico Mau");

        ArrayAdapter<String> adapterAlignment = new ArrayAdapter<>(Objects.requireNonNull(getActivity()), android.R.layout.simple_spinner_item, spinnerArrayAlignment);
        adapterAlignment.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnAlignment.setAdapter(adapterAlignment);

        List<String> spinnerArrayLifeStyle =  new ArrayList<>();
        if (lifeStyles != null) {
            for (LifeStyle lifeStyle : lifeStyles) {
                String description = lifeStyle.getName() + " (" + lifeStyle.getCost() + ")";
                spinnerArrayLifeStyle.add(description);
            }
        } else {
            spinnerArrayLifeStyle.add("Miserável (-)");
            spinnerArrayLifeStyle.add("Esquálido (1 pp)");
            spinnerArrayLifeStyle.add("Pobre (2 pp)");
            spinnerArrayLifeStyle.add("Modesto (1 po)");
            spinnerArrayLifeStyle.add("Confortável (2 po)");
            spinnerArrayLifeStyle.add("Rico (4 po)");
            spinnerArrayLifeStyle.add("Aristocrático (min. 10 po)");
        }

        ArrayAdapter<String> adapterLifeStyle = new ArrayAdapter<>(Objects.requireNonNull(getActivity()), android.R.layout.simple_spinner_item, spinnerArrayLifeStyle);
        adapterLifeStyle.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnLifeStyle.setAdapter(adapterLifeStyle);

        spnBackground.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try {
                    Background background = Objects.requireNonNull(backgrounds).get(position);
                    SheetCreationActivity.sheet.setBackground(background);
                } catch (Exception ex) {
                    Toast.makeText(getContext(), "Erro ao gravar antecedente: \n" + ex.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //
            }
        });

        spnAlignment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SheetCreationActivity.sheet.setAlignment(parent.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //
            }
        });

        spnLifeStyle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try {
                    LifeStyle lifeStyle = Objects.requireNonNull(lifeStyles).get(position);
                    SheetCreationActivity.sheet.setLifestyle(lifeStyle);
                } catch (Exception ex) {
                    Toast.makeText(getContext(), "Erro ao gravar estilo de vida: \n" + ex.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //
            }
        });

        edtFaith.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {}
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {}

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                SheetCreationActivity.sheet.setFaith(s.toString());
            }
        });

        edtGender.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {}
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {}

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                SheetCreationActivity.sheet.setGender(s.toString());
            }
        });

        edtEyes.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {}
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {}

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                SheetCreationActivity.sheet.setEyes(s.toString());
            }
        });

        edtSize.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {}
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {}

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                SheetCreationActivity.sheet.setSize(s.toString());
            }
        });

        edtHeight.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {}
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {}

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                SheetCreationActivity.sheet.setHeight(s.toString());
            }
        });

        edtAge.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {}
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {}

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if (s.length() > 0)
                    SheetCreationActivity.sheet.setAge(Integer.parseInt(s.toString()));
                else
                    SheetCreationActivity.sheet.setAge(0);
            }
        });

        edtTraits.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {}
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {}

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                SheetCreationActivity.sheet.setTraits(s.toString());
            }
        });

        return view;
    }
}
