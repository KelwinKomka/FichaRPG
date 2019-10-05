package com.ES3.ficharpg;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.ES3.ficharpg.main.DBManager;
import com.ES3.ficharpg.model.Sheet;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Objects;

public class Program {

    private DBManager dbManager;

    public DBManager getDbManager() {
        return dbManager;
    }

    public void DBInitialize(Context context){
        dbManager = new DBManager(context);
    }

    //Type
    public static final String SCORE = "SCORE";
    public static final String MODIFIER = "MODIFIER";
    public static final String SAVING = "SAVING";

    //Attribute
    public static final String STRENGTH = "STRENGTH";
    public static final String DEXTERITY = "DEXTERITY";
    public static final String CONSTITUTION = "CONSTITUTION";
    public static final String INTELLIGENCE = "INTELLIGENCE";
    public static final String WISDOM = "WISDOM";
    public static final String CHARISMA = "CHARISMA";

    //Race
    public static final String DWARF = "DWARF";
    public static final String ELF = "ELF";
    public static final String GNOME = "GNOME";
    public static final String HALFELF = "HALFELF";
    public static final String HUMAN = "HUMAN";
    public static final String TIEFLING = "TIEFLING";

    //Class
    public static final String CLERIC = "CLERIC";
    public static final String FIGHTER = "FIGHTER";
    public static final String PALADIN = "PALADIN";
    public static final String ROGUE = "ROGUE";
    public static final String WIZARD = "WIZARD";

    //Background
    public static final String ACOLYTE = "ACOLYTE";
    public static final String CRIMINAL = "CRIMINAL";
    public static final String FOLKHERO = "FOLKHERO";
    public static final String NOBLE = "NOBLE";
    public static final String SAGE = "SAGE";
    public static final String SOLDIER = "SOLDIER";

    //Skill
    public static final String ACROBATICS = "ACROBATICS";
    public static final String ANIMALHANDLING = "ANIMALHANDLING";
    public static final String ARCANA = "ARCANA";
    public static final String ATHLETICS = "ATHLETICS";
    public static final String DECEPTION = "DECEPTION";
    public static final String HISTORY = "HISTORY";
    public static final String INSIGHT = "INSIGHT";
    public static final String INTIMIDATION = "INTIMIDATION";
    public static final String INVESTIGATION = "INVESTIGATION";
    public static final String MEDICINE = "MEDICINE";
    public static final String NATURE = "NATURE";
    public static final String PERCEPTION = "PERCEPTION";
    public static final String PERFORMANCE = "PERFORMANCE";
    public static final String PERSUASION = "PERSUASION";
    public static final String RELIGION = "RELIGION";
    public static final String SLEIGHTOFHAND = "SLEIGHTOFHAND";
    public static final String STEALTH = "STEALTH";
    public static final String SURVIVAL = "SURVIVAL";

    //Condition
    public static final String BLINDED = "BLINDED";
    public static final String CHARMED = "CHARMED";
    public static final String DEAFENED = "DEAFENED";
    public static final String FRIGHTENED = "FRIGHTENED";
    public static final String GRAPPLED = "GRAPPLED";
    public static final String INCAPACITATED = "INCAPACITATED";
    public static final String INVISIBLE = "INVISIBLE";
    public static final String PARALYZED = "PARALYZED";
    public static final String PETRIFIED = "PETRIFIED";
    public static final String POISONED = "POISONED";
    public static final String PRONE = "PRONE";
    public static final String RESTRAINED = "RESTRAINED";
    public static final String STUNNED = "STUNNED";
    public static final String UNCONSCIOUS = "UNCONSCIOUS";

    //Languages
    public static final String ABYSSAL = "ABYSSAL";
    public static final String CELESTIAL = "CELESTIAL";
    public static final String COMMON = "COMMON";
    public static final String DEEPSPEECH = "DEEPSPEECH"; //Dialeto Subterrâneo
    public static final String DRACONIC = "DRACONIC";
    public static final String DWARVISH = "DWARVISH";
    public static final String ELVISH = "ELVISH";
    public static final String GIANT = "GIANT";
    public static final String GNOMISH = "GNOMISH";
    public static final String GOBLIN = "GOBLIN";
    public static final String HALFLING = "HALFLING";
    public static final String INFERNAL = "INFERNAL";
    public static final String ORC = "ORC";
    public static final String PRIMORDIAL = "PRIMORDIAL";
    public static final String SYLVAN = "SYLVAN"; //Silvestre
    public static final String UNDERCOMMON = "UNDERCOMMON"; //Subcomum

    //ArmorProf
    public static final String LIGHT_ARMOR = "LIGHT_ARMOR";
    public static final String MEDIUM_ARMOR = "MEDIUM_ARMOR";
    public static final String HEAVY_ARMOR = "HEAVY_ARMOR";
    public static final String SHIELD = "SHIELD";

    //WeaponProf
    public static final String SIMPLE_WEAPONS = "SIMPLE_WEAPONS";
    public static final String MARTIAL_WEAPONS = "MARTIAL_WEAPONS";

    //ToolProf
    public static final String THIEVES_TOOLS = "THIEVES_TOOLS";

    //ItemTypes
    public static final String ARMOR = "ARMOR";
    public static final String GEAR = "GEAR";
    public static final String TOOL = "TOOL";
    public static final String AMMUNITION = "AMMUNITION";
    public static final String HOLYSYMBOL = "HOLYSYMBOL";
    public static final String WEAPON = "WEAPON";

    //DamageType
    public static final String BLUDGEONING = "BLUDGEONING";
    public static final String PIERCING = "PIERCING";
    public static final String SLASHING = "SLASHING";

    //WeaponPropeties
    public static final String VERSATILE = "VERSATILE";
    public static final String FINESSE = "FINESSE";
    public static final String THROWN = "THROWN";

    //ActionType
    public static final String ATTACK = "ATTACK";
    public static final String SPELL = "SPELL";
    public static final String OTHER = "OTHER";

    public static long BooleanToNumeric(Boolean value){
        return value ? 1 : 0;
    }

    public static boolean NumericToBoolean(long value){
        return value == 1;
    }

    public static int getModValue(int value){
        int result = (value-10)/2;
        if (result > 0)
            return result;
        else
            return 0;
    }

    //Returns ArrayList<Sheet>
    public static Object readFile(Context context, String fileName) {
        try {
            FileInputStream fis = context.openFileInput(fileName);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
            Gson gson = new Gson();

            Type listType = new TypeToken<ArrayList<Sheet>>(){}.getType();
            return gson.fromJson(sb.toString(), listType);
        } catch (FileNotFoundException fileNotFound) {
            return null;
        } catch (IOException ioException) {
            return null;
        }
    }

    //Receives ArrayList<Sheet>
    public static boolean createFile(Context context, String fileName, Object obj){
        try {
            FileOutputStream fos = context.openFileOutput(fileName,Context.MODE_PRIVATE);

            Gson gson = new Gson();
            String jsonString = gson.toJson(obj);

            if (jsonString != null) {
                fos.write(jsonString.getBytes());
            }
            fos.close();
            return true;
        } catch (FileNotFoundException fileNotFound) {
            return false;
        } catch (IOException ioException) {
            return false;
        }

    }

    public static boolean isFilePresent(Context context, String fileName) {
        String path = context.getFilesDir().getAbsolutePath() + "/" + fileName;
        File file = new File(path);
        return file.exists();
    }
}
