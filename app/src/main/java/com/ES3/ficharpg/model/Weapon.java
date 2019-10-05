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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Weapon extends Item {
    private String attributes;
    private String notes;
    private int dieNumber;
    private int die;
    private int minRange;
    private int maxRange;
    private String damageType; //TODO DamageTypeEnum
    private ArrayList<String> properties; //TODO WeaponPropertiesEnum

    public static final String TABLE = "WEAPON";
    public static final String ID = "WEAPON_ID";
    public static final String NAME = "NAME";
    public static final String ATTRIBUTES = "ATTRIBUTES";
    public static final String NOTES = "NOTES";
    public static final String DIENUMBER = "DIENUMBER";
    public static final String DIE = "DIE";
    public static final String MINRANGE = "MINRANGE";
    public static final String MAXRANGE = "MAXRANGE";
    public static final String DAMAGETYPE = "DAMAGETYPE";
    public static final String PROPERTIES = "PROPERTIES";

    public String getAttributes() {
        return attributes;
    }

    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public int getDieNumber() {
        return dieNumber;
    }

    public void setDieNumber(int dieNumber) {
        this.dieNumber = dieNumber;
    }

    public int getDie() {
        return die;
    }

    public void setDie(int die) {
        this.die = die;
    }

    public int getMinRange() {
        return minRange;
    }

    public void setMinRange(int minRange) {
        this.minRange = minRange;
    }

    public int getMaxRange() {
        return maxRange;
    }

    public void setMaxRange(int maxRange) {
        this.maxRange = maxRange;
    }

    public String getDamageType() {
        return damageType;
    }

    public void setDamageType(String damageType) {
        this.damageType = damageType;
    }

    public ArrayList<String> getProperties() {
        return properties;
    }

    public void setProperties(ArrayList<String> properties) {
        this.properties = properties;
    }

    public static String getTableCreate(){
        return "CREATE TABLE "+TABLE+"(" +
                ID + " integer primary key autoincrement, " +
                NAME + " text, "+
                ATTRIBUTES + " text, " +
                NOTES + " text, " +
                DIENUMBER + " integer, " +
                DIE + " integer, " +
                MINRANGE + " integer, " +
                MAXRANGE + " integer, " +
                DAMAGETYPE + " text, " +
                PROPERTIES + " text, " +
                WEIGHT + " real, " +
                TYPE + " text, " +
                COST + " integer, " +
                COIN + " text " +
                ")";
    }

    public static String getTableDrop(){
        return "DROP TABLE IF EXISTS " + TABLE;
    }

    public static long insertWeapon(Context context, Weapon weapon){
        ContentValues values = new ContentValues();

        values.put(Weapon.NAME, weapon.getName());
        values.put(Weapon.DESCRIPTION, weapon.getDescription());
        values.put(Weapon.ATTRIBUTES, weapon.getAttributes());
        values.put(Weapon.NOTES, weapon.getNotes());
        values.put(Weapon.DIENUMBER, weapon.getDieNumber());
        values.put(Weapon.DIE, weapon.getDie());
        values.put(Weapon.MINRANGE, weapon.getMinRange());
        values.put(Weapon.MAXRANGE, weapon.getMaxRange());
        values.put(Weapon.DAMAGETYPE, weapon.getDamageType());
        StringBuilder properties = new StringBuilder();
        for (String property: weapon.getProperties())
            properties.append(property).append(";");
        values.put(Weapon.PROPERTIES, properties.toString());
        values.put(Weapon.WEIGHT, weapon.getWeight());
        values.put(Weapon.TYPE, weapon.getType());
        values.put(Weapon.COST, weapon.getCost());
        values.put(Weapon.COIN, weapon.getCoin());

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
    
    public static void insertWeapon(SQLiteDatabase db){
        ContentValues values;
        
        values = new ContentValues();
        values.put(NAME, "Maça");
        values.put(DESCRIPTION, "");
        values.put(ATTRIBUTES, "");
        values.put(NOTES, "");
        values.put(DIENUMBER, 1); //n
        values.put(DIE, 6); //n
        values.put(MINRANGE, 5); //n
        values.put(MAXRANGE, 5); //n
        values.put(DAMAGETYPE, Program.BLUDGEONING);
        values.put(PROPERTIES, "");
        values.put(WEIGHT, 4);
        values.put(TYPE, Program.SIMPLE_WEAPONS);
        values.put(COST, 5);
        values.put(COIN, "po");
        db.insert(TABLE, null, values);

        values = new ContentValues();
        values.put(NAME, "Martelo de Guerra");
        values.put(DESCRIPTION, "");
        values.put(ATTRIBUTES, "");
        values.put(NOTES, "");
        values.put(DIENUMBER, 1); //n
        values.put(DIE, 8); //n
        values.put(MINRANGE, 5); //n
        values.put(MAXRANGE, 5); //n
        values.put(DAMAGETYPE, Program.BLUDGEONING);
        values.put(PROPERTIES, Program.VERSATILE);
        values.put(WEIGHT, 2);
        values.put(TYPE, Program.SIMPLE_WEAPONS);
        values.put(COST, 15);
        values.put(COIN, "po");
        db.insert(TABLE, null, values);

        values = new ContentValues();
        values.put(NAME, "Besta leve");
        values.put(DESCRIPTION, "");
        values.put(ATTRIBUTES, "");
        values.put(NOTES, "");
        values.put(DIENUMBER, 1); //n
        values.put(DIE, 8); //n
        values.put(MINRANGE, 80); //n
        values.put(MAXRANGE, 320); //n
        values.put(DAMAGETYPE, Program.PIERCING);
        values.put(PROPERTIES, "");
        values.put(WEIGHT, 5);
        values.put(TYPE, Program.SIMPLE_WEAPONS);
        values.put(COST, 25);
        values.put(COIN, "po");
        db.insert(TABLE, null, values);

        values = new ContentValues();
        values.put(NAME, "Rapieira");
        values.put(DESCRIPTION, "");
        values.put(ATTRIBUTES, "");
        values.put(NOTES, "");
        values.put(DIENUMBER, 1); //n
        values.put(DIE, 8); //n
        values.put(MINRANGE, 5); //n
        values.put(MAXRANGE, 5); //n
        values.put(DAMAGETYPE, Program.PIERCING);
        values.put(PROPERTIES, Program.FINESSE);
        values.put(WEIGHT, 2);
        values.put(TYPE, Program.MARTIAL_WEAPONS);
        values.put(COST, 25);
        values.put(COIN, "po");
        db.insert(TABLE, null, values);

        values = new ContentValues();
        values.put(NAME, "Espada curta");
        values.put(DESCRIPTION, "");
        values.put(ATTRIBUTES, "");
        values.put(NOTES, "");
        values.put(DIENUMBER, 1); //n
        values.put(DIE, 6); //n
        values.put(MINRANGE, 5); //n
        values.put(MAXRANGE, 5); //n
        values.put(DAMAGETYPE, Program.PIERCING);
        values.put(PROPERTIES, Program.FINESSE);
        values.put(WEIGHT, 2);
        values.put(TYPE, Program.MARTIAL_WEAPONS);
        values.put(COST, 10);
        values.put(COIN, "po");
        db.insert(TABLE, null, values);

        values = new ContentValues();
        values.put(NAME, "Arco curto");
        values.put(DESCRIPTION, "");
        values.put(ATTRIBUTES, "");
        values.put(NOTES, "");
        values.put(DIENUMBER, 1); //n
        values.put(DIE, 6); //n
        values.put(MINRANGE, 80); //n
        values.put(MAXRANGE, 320); //n
        values.put(DAMAGETYPE, Program.PIERCING);
        values.put(PROPERTIES, Program.FINESSE);
        values.put(WEIGHT, 2);
        values.put(TYPE, Program.SIMPLE_WEAPONS);
        values.put(COST, 25);
        values.put(COIN, "po");
        db.insert(TABLE, null, values);

        values = new ContentValues();
        values.put(NAME, "Arco longo");
        values.put(DESCRIPTION, "");
        values.put(ATTRIBUTES, "");
        values.put(NOTES, "");
        values.put(DIENUMBER, 1); //n
        values.put(DIE, 8); //n
        values.put(MINRANGE, 150); //n
        values.put(MAXRANGE, 600); //n
        values.put(DAMAGETYPE, Program.PIERCING);
        values.put(PROPERTIES, "");
        values.put(WEIGHT, 2);
        values.put(TYPE, Program.MARTIAL_WEAPONS);
        values.put(COST, 50);
        values.put(COIN, "po");
        db.insert(TABLE, null, values);

        values = new ContentValues();
        values.put(NAME, "Adaga");
        values.put(DESCRIPTION, "");
        values.put(ATTRIBUTES, "");
        values.put(NOTES, "");
        values.put(DIENUMBER, 1); //n
        values.put(DIE, 4); //n
        values.put(MINRANGE, 20); //n
        values.put(MAXRANGE, 60); //n
        values.put(DAMAGETYPE, Program.PIERCING);
        values.put(PROPERTIES, Program.THROWN);
        values.put(WEIGHT, 1);
        values.put(TYPE, Program.SIMPLE_WEAPONS);
        values.put(COST, 2);
        values.put(COIN, "po");
        db.insert(TABLE, null, values);

        values = new ContentValues();
        values.put(NAME, "Machado de mão");
        values.put(DESCRIPTION, "");
        values.put(ATTRIBUTES, "");
        values.put(NOTES, "");
        values.put(DIENUMBER, 1); //n
        values.put(DIE, 6); //n
        values.put(MINRANGE, 20); //n
        values.put(MAXRANGE, 60); //n
        values.put(DAMAGETYPE, Program.SLASHING);
        values.put(PROPERTIES, Program.THROWN);
        values.put(WEIGHT, 2);
        values.put(TYPE, Program.SIMPLE_WEAPONS);
        values.put(COST, 5);
        values.put(COIN, "po");
        db.insert(TABLE, null, values);

        values = new ContentValues();
        values.put(NAME, "Bastão");
        values.put(DESCRIPTION, "");
        values.put(ATTRIBUTES, "");
        values.put(NOTES, "");
        values.put(DIENUMBER, 1); //n
        values.put(DIE, 6); //n
        values.put(MINRANGE, 5); //n
        values.put(MAXRANGE, 5); //n
        values.put(DAMAGETYPE, Program.BLUDGEONING);
        values.put(PROPERTIES, "");
        values.put(WEIGHT, 4);
        values.put(TYPE, Program.SIMPLE_WEAPONS);
        values.put(COST, 2);
        values.put(COIN, "pp");
        db.insert(TABLE, null, values);

        values = new ContentValues();
        values.put(NAME, "Javelin");
        values.put(DESCRIPTION, "");
        values.put(ATTRIBUTES, "");
        values.put(NOTES, "");
        values.put(DIENUMBER, 1); //n
        values.put(DIE, 6); //n
        values.put(MINRANGE, 30); //n
        values.put(MAXRANGE, 120); //n
        values.put(DAMAGETYPE, Program.PIERCING);
        values.put(PROPERTIES, Program.THROWN);
        values.put(WEIGHT, 2);
        values.put(TYPE, Program.SIMPLE_WEAPONS);
        values.put(COST, 5);
        values.put(COIN, "pp");
        db.insert(TABLE, null, values);
    }

    public static List<Item> getWeaponList(Context context){
        try {
            List<Item> weaponList = new ArrayList<>();
            DBManager dbManager = new DBManager(context);
            SQLiteDatabase db = dbManager.getReadableDatabase();

            String[] projection = {NAME, ATTRIBUTES, NOTES, DIENUMBER, DIE, MINRANGE, MAXRANGE, DAMAGETYPE, PROPERTIES, WEIGHT, TYPE, COST, COIN};
            Cursor cursor = dbManager.selectData(db, TABLE, projection, null, null, NAME);

            while (cursor.moveToNext()) {
                Weapon weapon = new Weapon();
                weapon.setName(cursor.getString(cursor.getColumnIndexOrThrow(NAME)));
                weapon.setDescription("");
                weapon.attributes = cursor.getString(cursor.getColumnIndexOrThrow(ATTRIBUTES));
                weapon.notes = cursor.getString(cursor.getColumnIndexOrThrow(NOTES));
                weapon.dieNumber = cursor.getInt(cursor.getColumnIndexOrThrow(DIENUMBER));
                weapon.die = cursor.getInt(cursor.getColumnIndexOrThrow(DIE));
                weapon.minRange = cursor.getInt(cursor.getColumnIndexOrThrow(MINRANGE));
                weapon.maxRange = cursor.getInt(cursor.getColumnIndexOrThrow(MAXRANGE));
                weapon.damageType = cursor.getString(cursor.getColumnIndexOrThrow(DAMAGETYPE));
                weapon.properties = new ArrayList<>(Arrays.asList(cursor.getString(cursor.getColumnIndexOrThrow(PROPERTIES)).split(";")));
                weapon.setWeight(cursor.getDouble(cursor.getColumnIndexOrThrow(WEIGHT)));
                weapon.setType(cursor.getString(cursor.getColumnIndexOrThrow(TYPE)));
                weapon.setCost(cursor.getDouble(cursor.getColumnIndexOrThrow(COST)));
                weapon.setCoin(cursor.getString(cursor.getColumnIndexOrThrow(COIN)));
                weaponList.add(weapon);
            }
            cursor.close();
            db.close();

            return weaponList;
        } catch (Exception ex) {
            Toast.makeText(context, "Erro ao buscar lista de armas: \n" + ex.getMessage(), Toast.LENGTH_LONG).show();
            return null;
        }
    }
}
