package com.ES3.ficharpg.main;

import android.app.Activity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.ES3.ficharpg.Program;
import com.ES3.ficharpg.R;
import com.ES3.ficharpg.model.Armor;
import com.ES3.ficharpg.model.Item;
import com.ES3.ficharpg.model.Weapon;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EquipmentEditActivity extends AppCompatActivity {

    private Item item;
    private EditText edtName;
    private EditText edtWeight;
    private EditText edtCost;
    private EditText edtDescription;
    private Spinner spnType;
    private Spinner spnCoin;
    private Button btnConfirm;

    private boolean creatingItem;
    private ArrayList<String> itemType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipment_edit);

        creatingItem = true;

        edtName = findViewById(R.id.edtName);
        edtWeight = findViewById(R.id.edtWeight);
        edtCost = findViewById(R.id.edtCost);
        edtDescription = findViewById(R.id.edtDescription);
        spnType = findViewById(R.id.spnType);
        spnCoin = findViewById(R.id.spnCoin);
        btnConfirm = findViewById(R.id.btnConfirm);

        itemType = new ArrayList<>();
        itemType.add(Program.LIGHT_ARMOR);
        itemType.add(Program.MEDIUM_ARMOR);
        itemType.add(Program.HEAVY_ARMOR);
        itemType.add(Program.SIMPLE_WEAPONS);
        itemType.add(Program.MARTIAL_WEAPONS);
        itemType.add(Program.GEAR);
        itemType.add(Program.SHIELD);
        itemType.add(Program.TOOL);

        List<String> spinnerTypeArray =  new ArrayList<>();
        for (String type: itemType) {
            spinnerTypeArray.add(getString(getResources().getIdentifier(type, "string", getPackageName())));
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinnerTypeArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnType.setAdapter(adapter);

        List<String> spinnerCoinArray =  new ArrayList<>();
        spinnerCoinArray.add("(pc) Peça de cobre");
        spinnerCoinArray.add("(pp) Peça de prata");
        spinnerCoinArray.add("(po) Peça de ouro");
        spinnerCoinArray.add("(pe) Peça de electrum");
        spinnerCoinArray.add("(ppl) Peça de platina");

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinnerCoinArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnCoin.setAdapter(adapter);

        item = (Item) getIntent().getSerializableExtra("item");
        if (item == null) item = new Item();
        else {
            creatingItem = false;
            edtName.setText(item.getName());
            edtWeight.setText(String.valueOf(item.getWeight()));
            edtCost.setText(String.valueOf(item.getCost()));
            edtDescription.setText(item.getDescription());
        }

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg;
                if (creatingItem) msg = "Deseja finalizar a criação de item?";
                else msg = "Deseja finalizar a edição do item?";

                AlertDialog.Builder dialog = new AlertDialog.Builder(EquipmentEditActivity.this);
                dialog.setTitle("Confirmação")
                        .setMessage(msg)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialoginterface, int i) {
                                createItem();
                                Intent intent = new Intent();
                                intent.putExtra("item", item);
                                finish();
                            }
                        }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                        dialoginterface.cancel();
                    }}).show();
            }
        });
    }

    private void createItem(){
        item.setName(edtName.getText().toString());
        item.setWeight(Double.parseDouble(edtWeight.getText().toString()));
        item.setCost(Double.parseDouble(edtCost.getText().toString()));
        String type = itemType.get(spnType.getSelectedItemPosition());
        item.setType(type);
        String coin = spnCoin.getSelectedItem().toString();
        item.setCoin(coin.substring(1, coin.indexOf(")")));

        if (creatingItem){
            int index = spnType.getSelectedItemPosition();
            switch (index) {
                case 0:
                case 1:
                case 2:
                case 6:
                    Armor.insertArmor(this, (Armor) item); //TODO type, description, attributes, requirement, stealthMod
                    break;
                case 3:
                case 4:
                    Weapon.insertWeapon(this, (Weapon) item); //TODO type, description, attributes, notes, dieNumber, die, minRange, maxRange, damageType, properties
                    break;
                case 5:
                case 7:
                    Item.insertItem(this, item); //TODO type
                    break;
            }
        }
    }

    @Override
    public void onBackPressed() {
        String msg;
        if (creatingItem) msg = "Deseja sair da criação de item?";
        else msg = "Deseja sair da edição do item?";

        AlertDialog.Builder dialog = new AlertDialog.Builder(EquipmentEditActivity.this);
        dialog.setTitle("Confirmação")
                .setMessage(msg)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                        Intent intent = new Intent();
                        setResult(Activity.RESULT_CANCELED, intent);
                        finish();
                    }
                }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialoginterface, int i) {
                dialoginterface.cancel();
            }}).show();
    }
}
