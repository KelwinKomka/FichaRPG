package com.ES3.ficharpg.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.ES3.ficharpg.Program;
import com.ES3.ficharpg.main.DBManager;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Race implements Serializable {
    private String name;
    private int speed;

    private HashMap<String, Integer> skills;

    private ArrayList<String> languages; //TODO LanguageEnum
    private ArrayList<String> traits;

    public static final String TABLE = "RACE";
    public static final String ID = "RACE_ID";
    public static final String NAME = "NAME";
    public static final String SPEED = "SPEED";
    public static final String STRMOD = "STRMOD";
    public static final String DEXMOD = "DEXMOD";
    public static final String CONMOD = "CONMOD";
    public static final String INTMOD = "INTMOD";
    public static final String WISMOD = "WISMOD";
    public static final String CHAMOD = "CHAMOD";
    public static final String LANGUAGES = "LANGUAGES";
    public static final String TRAITS = "TRAITS";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public HashMap<String, Integer> getSkills() {
        return skills;
    }

    public int getSkill(String skill){
        if (this.skills.get(skill) != null)
            return this.skills.get(skill);
        else
            return 0;
    }

    public void setSkills(HashMap<String, Integer> skills) {
        this.skills = skills;
    }

    public ArrayList<String> getLanguages() {
        return languages;
    }

    public void setLanguages(ArrayList<String> languages) {
        this.languages = languages;
    }

    public ArrayList<String> getTraits() {
        return traits;
    }

    public void setTraits(ArrayList<String> traits) {
        this.traits = traits;
    }

    public static String getTableCreate(){
        return "CREATE TABLE "+TABLE+"(" +
                ID + " integer primary key autoincrement, " +
                NAME + " text, " +
                SPEED + " integer, " +
                STRMOD + " integer, " +
                DEXMOD + " integer, " +
                CONMOD + " integer, " +
                INTMOD + " integer, " +
                WISMOD + " integer, " +
                CHAMOD + " integer, " +
                LANGUAGES + " text, " +
                TRAITS + " text " +
                ")";
    }

    public static String getTableDrop(){
        return "DROP TABLE IF EXISTS " + TABLE;
    }

    public static List<Race> getRaceList(Context context){
        try {
            List<Race> raceList = new ArrayList<>();
            DBManager dbManager = new DBManager(context);
            SQLiteDatabase db = dbManager.getReadableDatabase();

            String[] projection = {NAME, SPEED, STRMOD, DEXMOD, CONMOD, INTMOD, WISMOD, CHAMOD, LANGUAGES, TRAITS};
            Cursor cursor = dbManager.selectData(db, TABLE, projection, null, null, NAME);

            while (cursor.moveToNext()) {
                Race race = new Race();
                race.name = cursor.getString(cursor.getColumnIndexOrThrow(NAME));
                race.speed = cursor.getInt(cursor.getColumnIndexOrThrow(SPEED));

                HashMap<String, Integer> skills = new HashMap<>();
                skills.put(Program.STRENGTH, cursor.getInt(cursor.getColumnIndexOrThrow(STRMOD)));
                skills.put(Program.DEXTERITY, cursor.getInt(cursor.getColumnIndexOrThrow(DEXMOD)));
                skills.put(Program.CONSTITUTION, cursor.getInt(cursor.getColumnIndexOrThrow(CONMOD)));
                skills.put(Program.INTELLIGENCE, cursor.getInt(cursor.getColumnIndexOrThrow(INTMOD)));
                skills.put(Program.WISDOM, cursor.getInt(cursor.getColumnIndexOrThrow(WISMOD)));
                skills.put(Program.CHARISMA, cursor.getInt(cursor.getColumnIndexOrThrow(CHAMOD)));
                race.skills = skills;

                race.languages = new ArrayList<>(Arrays.asList(cursor.getString(cursor.getColumnIndexOrThrow(LANGUAGES)).split(";")));
                race.traits = new ArrayList<>(Arrays.asList(cursor.getString(cursor.getColumnIndexOrThrow(TRAITS)).split(";")));
                raceList.add(race);
            }
            cursor.close();
            db.close();

            return raceList;
        } catch (Exception ex) {
            Toast.makeText(context, "Erro ao buscar lista de raças: \n" + ex.getMessage(), Toast.LENGTH_LONG).show();
            return null;
        }
    }

    public static void insertRace(SQLiteDatabase db){
        ContentValues values;

        values = new ContentValues();
        values.put(NAME, "Anão");
        values.put(SPEED, 25);
        values.put(STRMOD, 0);
        values.put(DEXMOD, 0);
        values.put(CONMOD, 2);
        values.put(INTMOD, 0);
        values.put(WISMOD, 0);
        values.put(CHAMOD, 0);
        values.put(LANGUAGES, Program.COMMON + ";" + Program.DWARVISH);
        values.put(TRAITS, "Visão no escuro." +
                "\nResistência à veneno." +
                "\nProficiência com machados de guerra, machadinha, martelo de arremesso, martelo de guerra.");
        db.insert(TABLE, null, values);

        values = new ContentValues();
        values.put(NAME, "Elfo");
        values.put(SPEED, 30);
        values.put(STRMOD, 0);
        values.put(DEXMOD, 2);
        values.put(CONMOD, 0);
        values.put(INTMOD, 0);
        values.put(WISMOD, 0);
        values.put(CHAMOD, 0);
        values.put(LANGUAGES, Program.COMMON + ";" + Program.ELVISH);
        values.put(TRAITS, "Visão no escuro." +
                "\nVantagem contra magias de sono." +
                "\nProficiência em percepção." +
                "\nDescanso longo de 4 horas");
        db.insert(TABLE, null, values);

        values = new ContentValues();
        values.put(NAME, "Gnomo");
        values.put(SPEED, 25);
        values.put(STRMOD, 0);
        values.put(DEXMOD, 0);
        values.put(CONMOD, 0);
        values.put(INTMOD, 2);
        values.put(WISMOD, 0);
        values.put(CHAMOD, 0);
        values.put(LANGUAGES, Program.COMMON + ";" + Program.GNOMISH);
        values.put(TRAITS, "Vantagem em testes de resistência: inteligênica, sabedoria ou carisma." +
                "\nVisão no escuro.");
        db.insert(TABLE, null, values);

        values = new ContentValues();
        values.put(NAME, "Halfling");
        values.put(SPEED, 25);
        values.put(STRMOD, 0);
        values.put(DEXMOD, 2);
        values.put(CONMOD, 0);
        values.put(INTMOD, 0);
        values.put(WISMOD, 0);
        values.put(CHAMOD, 0);
        values.put(LANGUAGES, Program.COMMON + ";" + Program.HALFLING);
        values.put(TRAITS, "Sorte: pode rejogar um dado com valor 1." +
                "\nBravo: resistência contra efeito AMEDRONTADO." +
                "\nAgilidade halfling: pode mover-se pelo espaço de criaturas maiores.");
        db.insert(TABLE, null, values);

        values = new ContentValues();
        values.put(NAME, "Humano");
        values.put(SPEED, 30);
        values.put(STRMOD, 1);
        values.put(DEXMOD, 1);
        values.put(CONMOD, 1);
        values.put(INTMOD, 1);
        values.put(WISMOD, 1);
        values.put(CHAMOD, 1);
        values.put(LANGUAGES, Program.COMMON);
        values.put(TRAITS, "");
        db.insert(TABLE, null, values);

        values = new ContentValues();
        values.put(NAME, "Meio-Elfo");
        values.put(SPEED, 30);
        values.put(STRMOD, 0);
        values.put(DEXMOD, 0);
        values.put(CONMOD, 0);
        values.put(INTMOD, 0);
        values.put(WISMOD, 0);
        values.put(CHAMOD, 2);
        values.put(LANGUAGES, Program.COMMON + ";" + Program.ELVISH);
        values.put(TRAITS, "Visão no escuro." +
                "\nVantagem contra magias de sono.");
        db.insert(TABLE, null, values);

        values = new ContentValues();
        values.put(NAME, "Tiefling");
        values.put(SPEED, 30);
        values.put(STRMOD, 0);
        values.put(DEXMOD, 0);
        values.put(CONMOD, 0);
        values.put(INTMOD, 1);
        values.put(WISMOD, 0);
        values.put(CHAMOD, 2);
        values.put(LANGUAGES, Program.COMMON + ";" + Program.INFERNAL);
        values.put(TRAITS, "Pode usar a magia Taumaturgia." +
                "\nResistência à fogo." +
                "\nVisão no escuro.");
        db.insert(TABLE, null, values);
    }
}
