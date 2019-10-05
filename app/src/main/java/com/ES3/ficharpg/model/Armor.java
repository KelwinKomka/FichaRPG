package com.ES3.ficharpg.model;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.ES3.ficharpg.Program;
import com.ES3.ficharpg.main.DBManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PropertyResourceBundle;

public class Armor extends Item implements Serializable {
    private int attributes;

    private HashMap<String, Integer> requirement;
    private boolean stealthModifier;

    public static final String TABLE = "ARMOR";
    public static final String ID = "ARMOR_ID";
    public static final String ATTRIBUTES = "ATTRIBUTES";
    public static final String REQUIREMENT = "REQUIREMENT"; //TODO: rever requerimentos
    public static final String STEALHMOD = "STEALHMOD";

    public int getAttributes() {
        return attributes;
    }

    public void setAttributes(int attributes) {
        this.attributes = attributes;
    }

    public HashMap<String, Integer> getRequirement() {
        return requirement;
    }

    public void setRequirement(HashMap<String, Integer> requirement) {
        this.requirement = requirement;
    }

    public boolean getStealthModifier() {
        return stealthModifier;
    }

    public void setStealthModifier(boolean stealthModifier) {
        this.stealthModifier = stealthModifier;
    }

    public static String getTableCreate(){
        return "CREATE TABLE "+TABLE+"(" +
                ID + " integer primary key autoincrement, " +
                NAME + " text, " +
                ATTRIBUTES + " integer, " +
                REQUIREMENT + " integer, " +
                STEALHMOD + " numeric, " +
                DESCRIPTION + " text, " +
                WEIGHT + " real, " +
                TYPE + " text, " +
                COST + " integer, " +
                COIN + " text " +
                ")";
    }

    public static String getTableDrop(){
        return "DROP TABLE IF EXISTS " + TABLE;
    }

    public static long insertArmor(Context context, Armor armor){
        ContentValues values = new ContentValues();

        values.put(Armor.NAME, armor.getName());
        values.put(Armor.DESCRIPTION, armor.getDescription());
        values.put(Armor.ATTRIBUTES, armor.getAttributes());
        values.put(Armor.REQUIREMENT, 0);
        values.put(Armor.STEALHMOD, Program.BooleanToNumeric(armor.getStealthModifier()));
        values.put(Armor.WEIGHT, armor.getWeight());
        values.put(Armor.TYPE, armor.getType());
        values.put(Armor.COST, armor.getCost());
        values.put(Armor.COIN, armor.getCoin());

        try {
            DBManager dbManager = new DBManager(context);
            return dbManager.insertData(TABLE, values);
        } catch (Exception ex){
            AlertDialog.Builder dialog = new AlertDialog.Builder(context);
            dialog.setTitle("Erro")
                    .setMessage("Erro ao inserir item. \n" + ex.getMessage())
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialoginterface, int i) {
                            dialoginterface.cancel();
                        }
                    }).show();
        }
        return 0L;
    }
    
    public static void insertArmor(SQLiteDatabase db){
        ContentValues values;
        
        values = new ContentValues();
        values.put(NAME, "Armadura de escama");
        values.put(DESCRIPTION, "");
        values.put(ATTRIBUTES, 14);
        values.put(REQUIREMENT, "");
        values.put(STEALHMOD, 0);
        values.put(WEIGHT, 45);
        values.put(TYPE, Program.MEDIUM_ARMOR);
        values.put(COST, 50);
        values.put(COIN, "po");
        db.insert(TABLE, null, values);

        values = new ContentValues();
        values.put(NAME, "Armadura de couro");
        values.put(DESCRIPTION, "");
        values.put(ATTRIBUTES, 11);
        values.put(REQUIREMENT, "");
        values.put(STEALHMOD, 0);
        values.put(WEIGHT, 10);
        values.put(TYPE, Program.LIGHT_ARMOR);
        values.put(COST, 10);
        values.put(COIN, "po");
        db.insert(TABLE, null, values);

        values = new ContentValues();
        values.put(NAME, "Cota de malha");
        values.put(DESCRIPTION, "");
        values.put(ATTRIBUTES, 16);
        values.put(REQUIREMENT, "");
        values.put(STEALHMOD, 0);
        values.put(WEIGHT, 55);
        values.put(TYPE, Program.HEAVY_ARMOR);
        values.put(COST, 75);
        values.put(COIN, "po");
        db.insert(TABLE, null, values);
    }

    public static List<Item> getArmorList(Context context){
        try {
            List<Item> armorList = new ArrayList<>();
            DBManager dbManager = new DBManager(context);
            SQLiteDatabase db = dbManager.getReadableDatabase();

            String[] projection = {NAME, DESCRIPTION, ATTRIBUTES, REQUIREMENT, STEALHMOD, WEIGHT, TYPE, COST, COIN};
            Cursor cursor = dbManager.selectData(db, TABLE, projection, null, null, NAME);

            while (cursor.moveToNext()) {
                Armor armor = new Armor();
                armor.setName(cursor.getString(cursor.getColumnIndexOrThrow(NAME)));
                armor.setDescription(cursor.getString(cursor.getColumnIndexOrThrow(DESCRIPTION)));
                armor.attributes = cursor.getInt(cursor.getColumnIndexOrThrow(ATTRIBUTES));
                armor.requirement = new HashMap<>();
                armor.stealthModifier = Program.NumericToBoolean(cursor.getInt(cursor.getColumnIndexOrThrow(STEALHMOD)));
                armor.setWeight(cursor.getDouble(cursor.getColumnIndexOrThrow(WEIGHT)));
                armor.setType(cursor.getString(cursor.getColumnIndexOrThrow(TYPE)));
                armor.setCost(cursor.getDouble(cursor.getColumnIndexOrThrow(COST)));
                armor.setCoin(cursor.getString(cursor.getColumnIndexOrThrow(COIN)));
                armorList.add(armor);
            }
            cursor.close();
            db.close();

            return armorList;
        } catch (Exception ex) {
            Toast.makeText(context, "Erro ao buscar lista de armaduras: \n" + ex.getMessage(), Toast.LENGTH_LONG).show();
            return null;
        }
    }
}
