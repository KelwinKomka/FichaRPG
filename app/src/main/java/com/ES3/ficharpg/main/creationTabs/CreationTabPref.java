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
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

import com.ES3.ficharpg.main.SheetCreationActivity;
import com.ES3.ficharpg.R;

public class CreationTabPref extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_pref_layout, container, false);
        Spinner prefSpnEncumbrance = view.findViewById(R.id.prefSpnEncumbrance);

        prefSpnEncumbrance.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SheetCreationActivity.sheet.setEncumbranceRule(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                SheetCreationActivity.sheet.setEncumbranceRule(0);
            }
        });

        EditText prefTxtName = view.findViewById(R.id.prefEdtName);
        prefTxtName.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {}
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {}

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                SheetCreationActivity.sheet.setName(s.toString());
            }
        });

        Switch prefSwtCoinWeight = view.findViewById(R.id.prefSwtCoinWeight);
        prefSwtCoinWeight.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SheetCreationActivity.sheet.setIgnoreCoinWeight(isChecked);
            }
        });

        return view;
    }
}
