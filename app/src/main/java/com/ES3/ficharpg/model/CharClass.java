package com.ES3.ficharpg.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.strictmode.SqliteObjectLeakedViolation;
import android.widget.Toast;

import com.ES3.ficharpg.Program;
import com.ES3.ficharpg.main.DBManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class CharClass implements Serializable {
    private String name;
    private int hitDice;
    private int startHitPoints;

    private ArrayList<String> armorProficiencies;
    private ArrayList<String> weaponProficiencies;
    private ArrayList<String> toolProficiencies;
    private ArrayList<String> savingThrows; //TODO savingThrowsEnum
    private ArrayList<String> skills; //TODO skillsEnum

    private ArrayList<ArrayList<Equipment>> startingEquipment;

    public static final String TABLE = "CLASS";
    public static final String ID = "CLASS_ID";
    public static final String NAME = "NAME";
    public static final String HITDICE = "HITDICE";
    public static final String STARTHP = "STARTHP";
    public static final String ARMORPROF = "ARMORPROF";
    public static final String WEAPONPROF = "WEAPONPROF";
    public static final String TOOLPROF = "TOOLPROF";
    public static final String SAVINGTHROWS = "SAVINGTHROWS";
    public static final String SKILLS = "SKILLS";
    public static final String STARTEQUIP = "STARTEQUIP";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHitDice() {
        return hitDice;
    }

    public void setHitDice(int hitDice) {
        this.hitDice = hitDice;
    }

    public int getStartHitPoints() {
        return startHitPoints;
    }

    public void setStartHitPoints(int startHitPoints) {
        this.startHitPoints = startHitPoints;
    }

    public ArrayList<String> getArmorProficiencies() {
        return armorProficiencies;
    }

    public void setArmorProficiencies(ArrayList<String> armorProficiencies) {
        this.armorProficiencies = armorProficiencies;
    }

    public ArrayList<String> getWeaponProficiencies() {
        return weaponProficiencies;
    }

    public void setWeaponProficiencies(ArrayList<String> weaponProficiencies) {
        this.weaponProficiencies = weaponProficiencies;
    }

    public ArrayList<String> getToolProficiencies() {
        return toolProficiencies;
    }

    public void setToolProficiencies(ArrayList<String> toolProficiencies) {
        this.toolProficiencies = toolProficiencies;
    }

    public ArrayList<String> getSavingThrows() {
        return savingThrows;
    }

    public void setSavingThrows(ArrayList<String> savingThrows) {
        this.savingThrows = savingThrows;
    }

    public ArrayList<String> getSkills() {
        return skills;
    }

    public void setSkills(ArrayList<String> skills) {
        this.skills = skills;
    }

    public ArrayList<ArrayList<Equipment>> getStartingEquipment() {
        return startingEquipment;
    }

    public static String getTableCreate(){
        return "CREATE TABLE "+TABLE+"(" +
                ID + " integer primary key autoincrement, " +
                NAME + " text, " +
                HITDICE + " integer, " +
                STARTHP + " integer, " +
                ARMORPROF + " text, " +
                WEAPONPROF + " text, " +
                TOOLPROF + " text, " +
                SAVINGTHROWS + " text, " +
                SKILLS + " text, " +
                STARTEQUIP + " text " +
                ")";
    }

    public static String getTableDrop(){
        return "DROP TABLE IF EXISTS " + TABLE;
    }

    public static List<CharClass> getClassList(Context context){
        try {
            List<CharClass> charClassList = new ArrayList<>();
            DBManager dbManager = new DBManager(context);
            SQLiteDatabase db = dbManager.getReadableDatabase();

            String[] projection = {NAME, HITDICE, STARTHP, ARMORPROF, WEAPONPROF, TOOLPROF, SAVINGTHROWS, SKILLS, STARTEQUIP};
            Cursor cursor = dbManager.selectData(db, TABLE, projection, null, null, NAME);

            while (cursor.moveToNext()) {
                CharClass charClass = new CharClass();
                charClass.name = cursor.getString(cursor.getColumnIndexOrThrow(NAME));
                charClass.hitDice = cursor.getInt(cursor.getColumnIndexOrThrow(HITDICE));
                charClass.startHitPoints = cursor.getInt(cursor.getColumnIndexOrThrow(STARTHP));
                charClass.armorProficiencies = new ArrayList<>(Arrays.asList(cursor.getString(cursor.getColumnIndexOrThrow(ARMORPROF)).split(";")));
                charClass.weaponProficiencies = new ArrayList<>(Arrays.asList(cursor.getString(cursor.getColumnIndexOrThrow(WEAPONPROF)).split(";")));
                charClass.toolProficiencies = new ArrayList<>(Arrays.asList(cursor.getString(cursor.getColumnIndexOrThrow(TOOLPROF)).split(";")));
                charClass.skills = new ArrayList<>(Arrays.asList(cursor.getString(cursor.getColumnIndexOrThrow(SKILLS)).split(";")));
                charClass.savingThrows = new ArrayList<>(Arrays.asList(cursor.getString(cursor.getColumnIndexOrThrow(SAVINGTHROWS)).split(";")));
                charClass.startingEquipment = getEquipment(charClass.name);
                charClassList.add(charClass);
            }
            cursor.close();
            db.close();

            return charClassList;
        } catch (Exception ex) {
            Toast.makeText(context, "Erro ao buscar lista de classes: \n" + ex.getMessage(), Toast.LENGTH_LONG).show();
            return null;
        }
    }

    public static void insertCharClass(SQLiteDatabase db){
        ContentValues values;

        values = new ContentValues();
        values.put(NAME, Program.CLERIC);
        values.put(HITDICE, 8);
        values.put(STARTHP, 8);
        values.put(ARMORPROF, Program.LIGHT_ARMOR + ";" + Program.MEDIUM_ARMOR + ";" + Program.HEAVY_ARMOR);
        values.put(WEAPONPROF, Program.SIMPLE_WEAPONS);
        values.put(TOOLPROF, "");
        values.put(SAVINGTHROWS, Program.WISDOM + ";" + Program.CHARISMA);
        values.put(SKILLS, Program.HISTORY + ";" + Program.INSIGHT + ";" + Program.MEDICINE + ";" + Program.PERSUASION + ";" + Program.RELIGION);
        values.put(STARTEQUIP, "STARTEQUIP");
        db.insert(TABLE, null, values);

        values = new ContentValues();
        values.put(NAME, Program.ROGUE);
        values.put(HITDICE, 8);
        values.put(STARTHP, 8);
        values.put(ARMORPROF, Program.LIGHT_ARMOR);
        values.put(WEAPONPROF, Program.SIMPLE_WEAPONS);
        values.put(TOOLPROF, Program.THIEVES_TOOLS);
        values.put(SAVINGTHROWS, Program.DEXTERITY + ";" + Program.INTELLIGENCE);
        values.put(SKILLS, Program.ACROBATICS + ";" + Program.ATHLETICS + ";" + Program.PERSUASION + ";" + Program.PERFORMANCE + ";" + Program.DECEPTION + ";" +
                Program.STEALTH + ";" + Program.INTIMIDATION + ";" + Program.INSIGHT + ";" + Program.PERCEPTION + ";" + Program.SLEIGHTOFHAND);
        values.put(STARTEQUIP, "STARTEQUIP");
        db.insert(TABLE, null, values);

        values = new ContentValues();
        values.put(NAME, Program.FIGHTER);
        values.put(HITDICE, 10);
        values.put(STARTHP, 10);
        values.put(ARMORPROF, Program.LIGHT_ARMOR + ";" + Program.MEDIUM_ARMOR + ";" + Program.HEAVY_ARMOR + ";" + Program.SHIELD);
        values.put(WEAPONPROF, Program.SIMPLE_WEAPONS + ";" + Program.MARTIAL_WEAPONS);
        values.put(TOOLPROF, "");
        values.put(SAVINGTHROWS, Program.STRENGTH + ";" + Program.CONSTITUTION);
        values.put(SKILLS, Program.ACROBATICS + ";" + Program.ANIMALHANDLING + ";" + Program.ATHLETICS + ";" + Program.HISTORY + ";" +
                Program.INSIGHT + ";" + Program.INTIMIDATION + ";" + Program.PERCEPTION + ";" + Program.SURVIVAL);
        values.put(STARTEQUIP, "STARTEQUIP");
        db.insert(TABLE, null, values);

        values = new ContentValues();
        values.put(NAME, Program.WIZARD);
        values.put(HITDICE, 6);
        values.put(STARTHP, 6);
        values.put(ARMORPROF, "");
        values.put(WEAPONPROF, Program.SIMPLE_WEAPONS);
        values.put(TOOLPROF, "");
        values.put(SAVINGTHROWS, Program.INTELLIGENCE + ";" + Program.WISDOM);
        values.put(SKILLS, Program.ARCANA + ";" + Program.HISTORY + ";" + Program.INSIGHT + ";" + Program.INVESTIGATION + ";" +
                Program.MEDICINE + ";" + Program.RELIGION);
        values.put(STARTEQUIP, "STARTEQUIP");
        db.insert(TABLE, null, values);

        values = new ContentValues();
        values.put(NAME, Program.PALADIN);
        values.put(HITDICE, 10);
        values.put(STARTHP, 10);
        values.put(ARMORPROF, Program.LIGHT_ARMOR + ";" + Program.MEDIUM_ARMOR + ";" + Program.HEAVY_ARMOR + ";" + Program.SHIELD);
        values.put(WEAPONPROF, Program.SIMPLE_WEAPONS + ";" + Program.MARTIAL_WEAPONS);
        values.put(TOOLPROF, "");
        values.put(SAVINGTHROWS, Program.WISDOM + ";" + Program.CHARISMA);
        values.put(SKILLS, Program.ATHLETICS + ";" + Program.INTIMIDATION + ";" + Program.MEDICINE + ";" + Program.PERCEPTION + ";" +
                Program.PERSUASION + ";" + Program.RELIGION);
        values.put(STARTEQUIP, "STARTEQUIP");
        db.insert(TABLE, null, values);
    }

    private static ArrayList<ArrayList<Equipment>> getEquipment(String className){
        ArrayList<ArrayList<Equipment>> equipmentList = new ArrayList<>();

        ArrayList<Equipment> equipment = new ArrayList<>();
        Equipment equip;
        ArrayList<String> properties = null;
        switch (className){
            case Program.CLERIC:

                Weapon mace = new Weapon();
                mace.setName("Maça");
                mace.setDescription("");
                mace.setAttributes("");
                mace.setNotes("");
                mace.setDieNumber(1); //n
                mace.setDie(6); //n
                mace.setMinRange(5); //n
                mace.setMaxRange(5); //n
                mace.setDamageType(Program.BLUDGEONING);
                mace.setProperties(null);
                mace.setWeight(4.0);
                mace.setType(Program.SIMPLE_WEAPONS);
                mace.setCost(5.0);
                mace.setCoin("po");
                equip = new Equipment();
                equip.setWeapon(mace);
                equip.setQuantity(1);
                equipment.add(equip);

                Weapon warhammer = new Weapon();
                warhammer.setName("Martelo de Guerra");
                warhammer.setDescription("");
                warhammer.setAttributes("");
                warhammer.setNotes("");
                warhammer.setDieNumber(1); //n
                warhammer.setDie(8); //n
                warhammer.setMinRange(5); //n
                warhammer.setMaxRange(5); //n
                warhammer.setDamageType(Program.BLUDGEONING);
                properties = new ArrayList<>();
                properties.add(Program.VERSATILE);
                warhammer.setProperties(properties);
                warhammer.setWeight(2.0);
                warhammer.setType(Program.SIMPLE_WEAPONS);
                warhammer.setCost(15.0);
                warhammer.setCoin("po");
                equip = new Equipment();
                equip.setWeapon(warhammer);
                equip.setQuantity(1);
                equipment.add(equip);

                equipmentList.add(equipment);
                equipment = new ArrayList<>();

                Armor scaleMail = new Armor();
                scaleMail.setName("Armadura de escama");
                scaleMail.setDescription("");
                scaleMail.setAttributes(14);
                scaleMail.setRequirement(null);
                scaleMail.setStealthModifier(false);
                scaleMail.setWeight(45.0);
                scaleMail.setType(Program.MEDIUM_ARMOR);
                scaleMail.setCost(50.0);
                scaleMail.setCoin("po");
                equip = new Equipment();
                equip.setArmor(scaleMail);
                equip.setQuantity(1);
                equipment.add(equip);

                Armor leatherMail = new Armor();
                leatherMail.setName("Armadura de couro");
                leatherMail.setDescription("");
                leatherMail.setAttributes(11);
                leatherMail.setRequirement(null);
                leatherMail.setStealthModifier(false);
                leatherMail.setWeight(10.0);
                leatherMail.setType(Program.LIGHT_ARMOR);
                leatherMail.setCost(10.0);
                leatherMail.setCoin("po");
                equip = new Equipment();
                equip.setArmor(leatherMail);
                equip.setQuantity(1);
                equipment.add(equip);

                Armor chainMail = new Armor();
                chainMail.setName("Cota de malha");
                chainMail.setDescription("");
                chainMail.setAttributes(16);
                chainMail.setRequirement(null);
                chainMail.setStealthModifier(false);
                chainMail.setWeight(55.0);
                chainMail.setType(Program.HEAVY_ARMOR);
                chainMail.setCost(75.0);
                chainMail.setCoin("po");
                equip = new Equipment();
                equip.setArmor(chainMail);
                equip.setQuantity(1);
                equipment.add(equip);

                equipmentList.add(equipment);
                equipment = new ArrayList<>();

                Weapon lightCrossbow = new Weapon();
                lightCrossbow.setName("Besta leve");
                lightCrossbow.setDescription("");
                lightCrossbow.setAttributes("");
                lightCrossbow.setNotes("");
                lightCrossbow.setDieNumber(1); //n
                lightCrossbow.setDie(8); //n
                lightCrossbow.setMinRange(80); //n
                lightCrossbow.setMaxRange(320); //n
                lightCrossbow.setDamageType(Program.PIERCING);
                lightCrossbow.setProperties(new ArrayList<String>());
                lightCrossbow.setWeight(5.0);
                lightCrossbow.setType(Program.SIMPLE_WEAPONS);
                lightCrossbow.setCost(25.0);
                lightCrossbow.setCoin("po");
                equip = new Equipment();
                equip.setWeapon(lightCrossbow);
                equip.setQuantity(1);
                equipment.add(equip);

                Weapon dagger = new Weapon();
                dagger.setName("Adaga");
                dagger.setDescription("");
                dagger.setAttributes("");
                dagger.setNotes("");
                dagger.setDieNumber(1); //n
                dagger.setDie(4); //n
                dagger.setMinRange(20); //n
                dagger.setMaxRange(60); //n
                dagger.setDamageType(Program.PIERCING);
                properties = new ArrayList<>();
                properties.add(Program.THROWN);
                dagger.setProperties(properties);
                dagger.setWeight(1.0);
                dagger.setType(Program.SIMPLE_WEAPONS);
                dagger.setCost(2.0);
                dagger.setCoin("po");
                equip = new Equipment();
                equip.setWeapon(dagger);
                equip.setQuantity(1);
                equipment.add(equip);

                equipmentList.add(equipment);
                equipment = new ArrayList<>();

                Item holySymbol = new Item();
                holySymbol.setName("Símbolo Sagrado");
                holySymbol.setDescription("");
                holySymbol.setCost(0.0);
                holySymbol.setCoin("");
                holySymbol.setWeight(0.0);
                holySymbol.setType(Program.HOLYSYMBOL);
                equip = new Equipment();
                equip.setItem(holySymbol);
                equip.setQuantity(1);
                equipment.add(equip);

                equipmentList.add(equipment);

                break;
            case Program.FIGHTER:

                Armor leatherMailf = new Armor();
                leatherMailf.setName("Armadura de couro");
                leatherMailf.setDescription("");
                leatherMailf.setAttributes(11);
                leatherMailf.setRequirement(null);
                leatherMailf.setStealthModifier(false);
                leatherMailf.setWeight(10.0);
                leatherMailf.setType(Program.LIGHT_ARMOR);
                leatherMailf.setCost(10.0);
                leatherMailf.setCoin("po");
                equip = new Equipment();
                equip.setArmor(leatherMailf);
                equip.setQuantity(1);
                equipment.add(equip);

                Armor chainMailf = new Armor();
                chainMailf.setName("Cota de malha");
                chainMailf.setDescription("");
                chainMailf.setAttributes(16);
                chainMailf.setRequirement(null);
                chainMailf.setStealthModifier(false);
                chainMailf.setWeight(55.0);
                chainMailf.setType(Program.HEAVY_ARMOR);
                chainMailf.setCost(75.0);
                chainMailf.setCoin("po");
                equip = new Equipment();
                equip.setArmor(chainMailf);
                equip.setQuantity(1);
                equipment.add(equip);

                equipmentList.add(equipment);
                equipment = new ArrayList<>();

                Weapon rapier = new Weapon();
                rapier.setName("Rapieira");
                rapier.setDescription("");
                rapier.setAttributes("");
                rapier.setNotes("");
                rapier.setDieNumber(1); //n
                rapier.setDie(8); //n
                rapier.setMinRange(5); //n
                rapier.setMaxRange(5); //n
                rapier.setDamageType(Program.PIERCING);
                properties = new ArrayList<>();
                properties.add(Program.FINESSE);
                rapier.setProperties(properties);
                rapier.setWeight(2.0);
                rapier.setType(Program.MARTIAL_WEAPONS);
                rapier.setCost(25.0);
                rapier.setCoin("po");
                equip = new Equipment();
                equip.setWeapon(rapier);
                equip.setQuantity(1);
                equipment.add(equip);

                Weapon shortSword = new Weapon();
                shortSword.setName("Espada curta");
                shortSword.setDescription("");
                shortSword.setAttributes("");
                shortSword.setNotes("");
                shortSword.setDieNumber(1); //n
                shortSword.setDie(6); //n
                shortSword.setMinRange(5); //n
                shortSword.setMaxRange(5); //n
                shortSword.setDamageType(Program.PIERCING);
                properties = new ArrayList<>();
                properties.add(Program.FINESSE);
                shortSword.setProperties(properties);
                shortSword.setWeight(2.0);
                shortSword.setType(Program.MARTIAL_WEAPONS);
                shortSword.setCost(10.0);
                shortSword.setCoin("po");
                equip = new Equipment();
                equip.setWeapon(shortSword);
                equip.setQuantity(2);
                equipment.add(equip);

                equipmentList.add(equipment);
                equipment = new ArrayList<>();

                Weapon lightBow = new Weapon();
                lightBow.setName("Arco curto");
                lightBow.setDescription("");
                lightBow.setAttributes("");
                lightBow.setNotes("");
                lightBow.setDieNumber(1); //n
                lightBow.setDie(6); //n
                lightBow.setMinRange(80); //n
                lightBow.setMaxRange(320); //n
                lightBow.setDamageType(Program.PIERCING);
                properties = new ArrayList<>();
                properties.add(Program.FINESSE);
                lightBow.setProperties(properties);
                lightBow.setWeight(2.0);
                lightBow.setType(Program.SIMPLE_WEAPONS);
                lightBow.setCost(25.0);
                lightBow.setCoin("po");
                equip = new Equipment();
                equip.setWeapon(lightBow);
                equip.setQuantity(1);
                equipment.add(equip);

                Weapon handAxe = new Weapon();
                handAxe.setName("Machado de mão");
                handAxe.setDescription("");
                handAxe.setAttributes("");
                handAxe.setNotes("");
                handAxe.setDieNumber(1); //n
                handAxe.setDie(6); //n
                handAxe.setMinRange(20); //n
                handAxe.setMaxRange(60); //n
                handAxe.setDamageType(Program.SLASHING);
                properties = new ArrayList<>();
                properties.add(Program.THROWN);
                handAxe.setProperties(properties);
                handAxe.setWeight(2.0);
                handAxe.setType(Program.SIMPLE_WEAPONS);
                handAxe.setCost(5.0);
                handAxe.setCoin("po");
                equip = new Equipment();
                equip.setWeapon(handAxe);
                equip.setQuantity(2);
                equipment.add(equip);

                equipmentList.add(equipment);

                break;
            case Program.PALADIN:

                Weapon rapierp = new Weapon();
                rapierp.setName("Rapieira");
                rapierp.setDescription("");
                rapierp.setAttributes("");
                rapierp.setNotes("");
                rapierp.setDieNumber(1); //n
                rapierp.setDie(8); //n
                rapierp.setMinRange(5); //n
                rapierp.setMaxRange(5); //n
                rapierp.setDamageType(Program.PIERCING);
                properties = new ArrayList<>();
                properties.add(Program.FINESSE);
                rapierp.setProperties(properties);
                rapierp.setWeight(2.0);
                rapierp.setType(Program.MARTIAL_WEAPONS);
                rapierp.setCost(25.0);
                rapierp.setCoin("po");
                equip = new Equipment();
                equip.setWeapon(rapierp);
                equip.setQuantity(1);
                equipment.add(equip);

                Weapon shortSwordp = new Weapon();
                shortSwordp.setName("Espada curta");
                shortSwordp.setDescription("");
                shortSwordp.setAttributes("");
                shortSwordp.setNotes("");
                shortSwordp.setDieNumber(1); //n
                shortSwordp.setDie(6); //n
                shortSwordp.setMinRange(5); //n
                shortSwordp.setMaxRange(5); //n
                shortSwordp.setDamageType(Program.PIERCING);
                properties = new ArrayList<>();
                properties.add(Program.FINESSE);
                shortSwordp.setProperties(properties);
                shortSwordp.setWeight(2.0);
                shortSwordp.setType(Program.MARTIAL_WEAPONS);
                shortSwordp.setCost(10.0);
                shortSwordp.setCoin("po");
                equip = new Equipment();
                equip.setWeapon(shortSwordp);
                equip.setQuantity(2);
                equipment.add(equip);

                equipmentList.add(equipment);
                equipment = new ArrayList<>();

                Weapon javelin = new Weapon();
                javelin.setName("Javelin");
                javelin.setDescription("");
                javelin.setAttributes("");
                javelin.setNotes("");
                javelin.setDieNumber(1); //n
                javelin.setDie(6); //n
                javelin.setMinRange(30); //n
                javelin.setMaxRange(120); //n
                javelin.setDamageType(Program.PIERCING);
                properties = new ArrayList<>();
                properties.add(Program.THROWN);
                javelin.setProperties(properties);
                javelin.setWeight(2.0);
                javelin.setType(Program.SIMPLE_WEAPONS);
                javelin.setCost(5.0);
                javelin.setCoin("pp");
                equip = new Equipment();
                equip.setWeapon(javelin);
                equip.setQuantity(5);
                equipment.add(equip);

                Weapon warhammerp = new Weapon();
                warhammerp.setName("Martelo de Guerra");
                warhammerp.setDescription("");
                warhammerp.setAttributes("");
                warhammerp.setNotes("");
                warhammerp.setDieNumber(1); //n
                warhammerp.setDie(8); //n
                warhammerp.setMinRange(5); //n
                warhammerp.setMaxRange(5); //n
                warhammerp.setDamageType(Program.BLUDGEONING);
                properties = new ArrayList<>();
                properties.add(Program.VERSATILE);
                warhammerp.setProperties(properties);
                warhammerp.setWeight(2.0);
                warhammerp.setType(Program.SIMPLE_WEAPONS);
                warhammerp.setCost(15.0);
                warhammerp.setCoin("po");
                equip = new Equipment();
                equip.setWeapon(warhammerp);
                equip.setQuantity(1);
                equipment.add(equip);

                equipmentList.add(equipment);
                equipment = new ArrayList<>();

                //TODO chainMail

                Item holySymbolp = new Item();
                holySymbolp.setName("Símbolo Sagrado");
                holySymbolp.setDescription("");
                holySymbolp.setCost(0.0);
                holySymbolp.setCoin("");
                holySymbolp.setWeight(0.0);
                holySymbolp.setType(Program.HOLYSYMBOL);
                equip = new Equipment();
                equip.setItem(holySymbolp);
                equip.setQuantity(1);
                equipment.add(equip);

                equipmentList.add(equipment);

                break;
            case Program.ROGUE:

                Weapon rapierr = new Weapon();
                rapierr.setName("Rapieira");
                rapierr.setDescription("");
                rapierr.setAttributes("");
                rapierr.setNotes("");
                rapierr.setDieNumber(1); //n
                rapierr.setDie(8); //n
                rapierr.setMinRange(5); //n
                rapierr.setMaxRange(5); //n
                rapierr.setDamageType(Program.PIERCING);
                properties = new ArrayList<>();
                properties.add(Program.FINESSE);
                rapierr.setProperties(properties);
                rapierr.setWeight(2.0);
                rapierr.setType(Program.MARTIAL_WEAPONS);
                rapierr.setCost(25.0);
                rapierr.setCoin("po");
                equip = new Equipment();
                equip.setWeapon(rapierr);
                equip.setQuantity(1);
                equipment.add(equip);

                Weapon shortSwordr = new Weapon();
                shortSwordr.setName("Espada curta");
                shortSwordr.setDescription("");
                shortSwordr.setAttributes("");
                shortSwordr.setNotes("");
                shortSwordr.setDieNumber(1); //n
                shortSwordr.setDie(6); //n
                shortSwordr.setMinRange(5); //n
                shortSwordr.setMaxRange(5); //n
                shortSwordr.setDamageType(Program.PIERCING);
                properties = new ArrayList<>();
                properties.add(Program.FINESSE);
                shortSwordr.setProperties(properties);
                shortSwordr.setWeight(2.0);
                shortSwordr.setType(Program.MARTIAL_WEAPONS);
                shortSwordr.setCost(10.0);
                shortSwordr.setCoin("po");
                equip = new Equipment();
                equip.setWeapon(shortSwordr);
                equip.setQuantity(1);
                equipment.add(equip);

                equipmentList.add(equipment);
                equipment = new ArrayList<>();

                Weapon lightBowr = new Weapon();
                lightBowr.setName("Arco curto");
                lightBowr.setDescription("");
                lightBowr.setAttributes("");
                lightBowr.setNotes("");
                lightBowr.setDieNumber(1); //n
                lightBowr.setDie(6); //n
                lightBowr.setMinRange(80); //n
                lightBowr.setMaxRange(320); //n
                lightBowr.setDamageType(Program.PIERCING);
                properties = new ArrayList<>();
                properties.add(Program.FINESSE);
                lightBowr.setProperties(properties);
                lightBowr.setWeight(2.0);
                lightBowr.setType(Program.SIMPLE_WEAPONS);
                lightBowr.setCost(25.0);
                lightBowr.setCoin("po");
                equip = new Equipment();
                equip.setWeapon(lightBowr);
                equip.setQuantity(1);
                equipment.add(equip);
                equip = new Equipment();
                equip.setWeapon(shortSwordr);
                equip.setQuantity(1);
                equipment.add(equip);

                equipmentList.add(equipment);
                equipment = new ArrayList<>();

                Armor leatherMailr = new Armor();
                leatherMailr.setName("Armadura de couro");
                leatherMailr.setDescription("");
                leatherMailr.setAttributes(11);
                leatherMailr.setRequirement(null);
                leatherMailr.setStealthModifier(false);
                leatherMailr.setWeight(10.0);
                leatherMailr.setType(Program.LIGHT_ARMOR);
                leatherMailr.setCost(10.0);
                leatherMailr.setCoin("po");
                equip = new Equipment();
                equip.setArmor(leatherMailr);
                equip.setQuantity(1);
                equipment.add(equip);

                equipmentList.add(equipment);

                //TODO 2 dagger, thieves tools

                break;
            case Program.WIZARD:

                Weapon quarterStaff = new Weapon();
                quarterStaff.setName("Bastão");
                quarterStaff.setDescription("");
                quarterStaff.setAttributes("");
                quarterStaff.setNotes("");
                quarterStaff.setDieNumber(1); //n
                quarterStaff.setDie(5); //n
                quarterStaff.setMinRange(5); //n
                quarterStaff.setMaxRange(5); //n
                quarterStaff.setDamageType(Program.BLUDGEONING);
                quarterStaff.setProperties(new ArrayList<String>());
                quarterStaff.setWeight(4.0);
                quarterStaff.setType(Program.SIMPLE_WEAPONS);
                quarterStaff.setCost(2.0);
                quarterStaff.setCoin("pp");
                equip = new Equipment();
                equip.setWeapon(quarterStaff);
                equip.setQuantity(1);
                equipment.add(equip);

                Weapon daggerw = new Weapon();
                daggerw.setName("Adaga");
                daggerw.setDescription("");
                daggerw.setAttributes("");
                daggerw.setNotes("");
                daggerw.setDieNumber(1); //n
                daggerw.setDie(4); //n
                daggerw.setMinRange(20); //n
                daggerw.setMaxRange(60); //n
                daggerw.setDamageType(Program.PIERCING);
                properties = new ArrayList<>();
                properties.add(Program.THROWN);
                daggerw.setProperties(properties);
                daggerw.setWeight(1.0);
                daggerw.setType(Program.SIMPLE_WEAPONS);
                daggerw.setCost(2.0);
                daggerw.setCoin("po");
                equip = new Equipment();
                equip.setWeapon(daggerw);
                equip.setQuantity(1);
                equipment.add(equip);

                equipmentList.add(equipment);
                equipment = new ArrayList<>();

                Item componentPouch = new Item();
                componentPouch.setName("Bolsa de componentes");
                componentPouch.setDescription("");
                componentPouch.setCost(25.0);
                componentPouch.setCoin("po");
                componentPouch.setWeight(2.0);
                componentPouch.setType(Program.GEAR);
                equip = new Equipment();
                equip.setItem(componentPouch);
                equip.setQuantity(1);
                equipment.add(equip);

                equipmentList.add(equipment);

                break;
        }
        return equipmentList;
    }
}
