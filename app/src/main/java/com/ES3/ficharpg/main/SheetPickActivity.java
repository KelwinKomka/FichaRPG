package com.ES3.ficharpg.main;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.ES3.ficharpg.Program;
import com.ES3.ficharpg.R;
import com.ES3.ficharpg.model.Sheet;

import java.util.ArrayList;

public class SheetPickActivity extends AppCompatActivity {

    private Button btnSelect;
    private Button btnDelete;
    private TableLayout tblSheet;
    private Sheet selectedSheet;
    private ArrayList<Sheet> sheets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sheet_pick);

        sheets = (ArrayList<Sheet>) Program.readFile(this, "sheet.json");

        btnSelect = findViewById(R.id.btnSelect);
        btnDelete = findViewById(R.id.btnDelete);
        tblSheet = findViewById(R.id.tblSheet);

        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedSheet != null) {
                    Intent intent = new Intent(SheetPickActivity.this, MainActivity.class);
                    intent.putExtra("Sheet", selectedSheet);
                    startActivity(intent);
                    finish();
                } else {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(SheetPickActivity.this);
                    dialog.setTitle("Aviso")
                            .setMessage("Uma ficha deve ser selecionada!")
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialoginterface, int i) {
                                    dialoginterface.cancel();
                                }
                            }).show();
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedSheet != null) {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(SheetPickActivity.this);
                    dialog.setTitle("Confirmação")
                            .setMessage("Deseja apagar a ficha?")
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialoginterface, int i) {
                                    sheets.remove(selectedSheet);
                                    Program.createFile(SheetPickActivity.this, "sheet.json", sheets);
                                    fillTable();
                                }
                            }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialoginterface, int i) {
                            dialoginterface.cancel();
                        }
                    })
                            .show();
                } else {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(SheetPickActivity.this);
                    dialog.setTitle("Aviso")
                            .setMessage("Uma ficha deve ser selecionada!")
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialoginterface, int i) {
                                    dialoginterface.cancel();
                                }
                            }).show();
                }
            }
        });
        fillTable();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void fillTable(){
        if (sheets == null)
            sheets = new ArrayList<>();

        tblSheet.removeAllViews();

        int count = 0;
        TableRow tableRow = new TableRow(this);
        tableRow.setBackground(getDrawable(R.drawable.layout_line));
        count++;

        TextView textView = new TextView(this);
        textView.setText(R.string.createSheet);
        textView.setTextAppearance(R.style.label);
        textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        TableRow.LayoutParams textParams = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, 1f);
        textView.setLayoutParams(textParams);

        textView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    Intent intent = new Intent(SheetPickActivity.this, SheetCreationActivity.class);
                    startActivity(intent);
                    finish();
                }
                return false;
            }
        });
        tableRow.addView(textView);

        TableLayout.LayoutParams tableParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT);
        tableParams.setMargins(2, 2, 2, 2); // left, top, right, bottom
        tableRow.setLayoutParams(tableParams);
        tblSheet.addView(tableRow);


        for (final Sheet sheet: sheets){
            tableRow = new TableRow(this);
            if (count % 2 == 0)
                tableRow.setBackground(getDrawable(R.drawable.layout_line));
            else
                tableRow.setBackground(getDrawable(R.drawable.layout_line_light));
            count++;

            textView = new TextView(this);
            textView.setText(sheet.getName());
            textView.setTextAppearance(R.style.label);
            textView.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);

            textParams = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, 1f);
            textParams.setMarginStart(5);
            textView.setLayoutParams(textParams);

            final TableRow finalTableRow = tableRow;
            textView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        selectedSheet = sheet;
                        for (int i=0;i<tblSheet.getChildCount();i++) {
                            TableRow tableRow = (TableRow) tblSheet.getChildAt(i);
                            if (i % 2 == 0)
                                tableRow.setBackground(getDrawable(R.drawable.layout_line));
                            else
                                tableRow.setBackground(getDrawable(R.drawable.layout_line_light));
                        }
                        finalTableRow.setBackground(getDrawable(R.drawable.layout_line_selected));
                    }
                    return false;
                }
            });
            tableRow.addView(textView);

            tableParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT);
            tableParams.setMargins(2, 2, 2, 2); // left, top, right, bottom
            tableRow.setLayoutParams(tableParams);
            tblSheet.addView(tableRow);
        }
    }
}
