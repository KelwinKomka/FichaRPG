package com.ES3.ficharpg.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.ES3.ficharpg.main.DBManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DiceRollRegistry implements Serializable {
    private int diceRolled;
    private String diceType;
    private int modifier;
    private String observation;
    private int result;

    public static final String TABLE = "DICEROLL";
    public static final String ID = "DICEROLL_ID";
    public static final String SHEETID = "SHEET_ID";
    public static final String DICEROLLED = "DICEROLLED";
    public static final String TYPE = "TYPE";
    public static final String MODIFIER = "MODIFIER";
    public static final String OBSERVATION = "OBSERVATION";
    public static final String RESULT = "RESULT";

    public DiceRollRegistry(int diceRolled, String diceType, int modifier, String observation, int result) {
        this.diceRolled = diceRolled;
        this.diceType = diceType;
        this.modifier = modifier;
        this.observation = observation;
        this.result = result;
    }

    public int getDiceRolled() {
        return diceRolled;
    }

    public void setDiceRolled(int diceRolled) {
        this.diceRolled = diceRolled;
    }

    public String getDiceType() {
        return diceType;
    }

    public void setDiceType(String diceType) {
        this.diceType = diceType;
    }

    public int getModifier() {
        return modifier;
    }

    public void setModifier(int modifier) {
        this.modifier = modifier;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public static String getTableCreate(){
        return "CREATE TABLE "+TABLE+"(" +
                ID + " integer primary key autoincrement, " +
                SHEETID + " integer, " +
                DICEROLLED + " integer, " +
                TYPE + " text, " +
                MODIFIER + " integer, " +
                OBSERVATION + " text, " +
                RESULT + " integer, " +
                    "FOREIGN KEY ("+SHEETID+") REFERENCES "+Sheet.TABLE+"("+Sheet.ID+")" +
                ")";
    }

    public static String getTableDrop(){
        return "DROP TABLE IF EXISTS " + TABLE;
    }

    public static List<DiceRollRegistry> getDiceRollRegistryList(Context context, long sheetId) {
        try {
            List<DiceRollRegistry> diceRollRegistryListe = new ArrayList<>();
            DBManager dbManager = new DBManager(context);
            SQLiteDatabase db = dbManager.getReadableDatabase();

            String[] projection = {DICEROLLED, TYPE, MODIFIER, OBSERVATION, RESULT};
            String[] selectionArgs = {String.valueOf(sheetId)};
            Cursor cursor = dbManager.selectData(db, TABLE, projection, SHEETID + " = ?", selectionArgs, ID + " DESC");

            while (cursor.moveToNext()) {
                int diceRolled = cursor.getInt(cursor.getColumnIndexOrThrow(DICEROLLED));
                String type = cursor.getString(cursor.getColumnIndexOrThrow(TYPE));
                int mod = cursor.getInt(cursor.getColumnIndexOrThrow(MODIFIER));
                String observation = cursor.getString(cursor.getColumnIndexOrThrow(OBSERVATION));
                int result = cursor.getInt(cursor.getColumnIndexOrThrow(RESULT));

                DiceRollRegistry diceRollRegistry = new DiceRollRegistry(diceRolled, type, mod, observation, result);
                diceRollRegistryListe.add(diceRollRegistry);
            }
            cursor.close();
            db.close();

            return diceRollRegistryListe;
        } catch (Exception ex) {
            Toast.makeText(context, "Erro ao buscar lista de rolagem de dados: \n" + ex.getMessage(), Toast.LENGTH_LONG).show();
            return null;
        }
    }
}
