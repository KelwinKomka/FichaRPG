package com.ES3.ficharpg.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.ES3.ficharpg.R;
import com.ES3.ficharpg.model.DiceRollRegistry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

public class RollRegistryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roll_registry);

        TableLayout rollTable = findViewById(R.id.rollTable);

        ArrayList<DiceRollRegistry> diceRollRegistries = (ArrayList<DiceRollRegistry>) getIntent().getSerializableExtra("diceRegistry");

        Collections.reverse(diceRollRegistries);
        rollTable.removeAllViews();

        TableRow tableRow = new TableRow(this);
        tableRow.setOrientation(TableRow.HORIZONTAL);
        tableRow.setBackground(getDrawable(R.drawable.layout_line));

        TextView txtRoll = new TextView(this);
        txtRoll.setText(R.string.roll);
        txtRoll.setTextAppearance(R.style.tableHeader);
        txtRoll.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
        TableRow.LayoutParams textParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 2f);
        txtRoll.setLayoutParams(textParams);
        tableRow.addView(txtRoll);

        TextView txtDie = new TextView(this);
        txtDie.setText(R.string.die);
        txtDie.setTextAppearance(R.style.tableHeader);
        txtDie.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
        textParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1f);
        txtDie.setLayoutParams(textParams);
        tableRow.addView(txtDie);

        TextView txtMod = new TextView(this);
        txtMod.setText(R.string.mod);
        txtMod.setTextAppearance(R.style.tableHeader);
        txtMod.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
        textParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1f);
        txtMod.setLayoutParams(textParams);
        tableRow.addView(txtMod);

        TextView txtResult = new TextView(this);
        txtResult.setText(R.string.result);
        txtResult.setTextAppearance(R.style.tableHeader);
        txtResult.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
        textParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1f);
        txtResult.setLayoutParams(textParams);
        tableRow.addView(txtResult);

        TableLayout.LayoutParams tableParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT);
        tableParams.setMargins(10, 2, 2, 2); // left, top, right, bottom
        tableRow.setLayoutParams(tableParams);
        rollTable.addView(tableRow);

        int count = 0;
        for (final DiceRollRegistry diceRollRegistry: diceRollRegistries){
            tableRow = new TableRow(this);
            tableRow.setOrientation(TableRow.HORIZONTAL);
            if (count % 2 == 0)
                tableRow.setBackground(getDrawable(R.drawable.layout_line_light));
            else
                tableRow.setBackground(getDrawable(R.drawable.layout_line));
            count++;

            txtRoll = new TextView(this);
            txtRoll.setText(diceRollRegistry.getObservation());
            txtRoll.setTextAppearance(R.style.tableItem);
            textParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 2f);
            txtRoll.setLayoutParams(textParams);
            tableRow.addView(txtRoll);

            txtDie = new TextView(this);
            String text = diceRollRegistry.getDiceRolled() + "x " + diceRollRegistry.getDiceType();
            txtDie.setText(text);
            txtDie.setTextAppearance(R.style.tableItem);
            textParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1f);
            txtDie.setLayoutParams(textParams);
            tableRow.addView(txtDie);

            txtMod = new TextView(this);
            txtMod.setText(String.valueOf(diceRollRegistry.getModifier()));
            txtMod.setTextAppearance(R.style.tableItem);
            textParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1f);
            txtMod.setLayoutParams(textParams);
            tableRow.addView(txtMod);

            txtResult = new TextView(this);
            txtResult.setText(String.valueOf(diceRollRegistry.getResult()));
            txtResult.setTextAppearance(R.style.tableItem);
            textParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1f);
            txtResult.setLayoutParams(textParams);
            tableRow.addView(txtResult);

            tableParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT);
            tableParams.setMargins(10, 2, 2, 2); // left, top, right, bottom
            tableRow.setLayoutParams(tableParams);
            rollTable.addView(tableRow);
        }
    }
}
