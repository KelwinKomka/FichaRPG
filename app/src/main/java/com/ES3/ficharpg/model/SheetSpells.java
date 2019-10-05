package com.ES3.ficharpg.model;

import java.io.Serializable;
import java.util.ArrayList;

public class SheetSpells implements Serializable {

    private String modifier;
    private int spellAttack;
    private int saveDC;

    private ArrayList<Spell> knownSpells = new ArrayList<>();
    private ArrayList<Spell> cantrips;
    private ArrayList<Spell> level1;
    private ArrayList<Spell> level2;
    private ArrayList<Spell> level3;
    private ArrayList<Spell> level4;
    private ArrayList<Spell> level5;
    private ArrayList<Spell> level6;
    private ArrayList<Spell> level7;
    private ArrayList<Spell> level8;
    private ArrayList<Spell> level9;

    public static final String TABLE = "SHEETSPELLS";
    public static final String ID = "SHEETSPELLS_ID";
    public static final String SHEETID = "SHEET_ID";
    public static final String SPELLID = "SPELL_ID";

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public int getSpellAttack() {
        return spellAttack;
    }

    public void setSpellAttack(int spellAttack) {
        this.spellAttack = spellAttack;
    }

    public int getSaveDC() {
        return saveDC;
    }

    public void setSaveDC(int saveDC) {
        this.saveDC = saveDC;
    }

    public ArrayList<Spell> getKnownSpells() {
        return knownSpells;
    }

    public void setKnownSpells(ArrayList<Spell> knownSpells) {
        this.knownSpells = knownSpells;
    }

    public ArrayList<Spell> getCantrips() {
        return cantrips;
    }

    public void setCantrips(ArrayList<Spell> cantrips) {
        this.cantrips = cantrips;
    }

    public ArrayList<Spell> getLevel1() {
        return level1;
    }

    public void setLevel1(ArrayList<Spell> level1) {
        this.level1 = level1;
    }

    public ArrayList<Spell> getLevel2() {
        return level2;
    }

    public void setLevel2(ArrayList<Spell> level2) {
        this.level2 = level2;
    }

    public ArrayList<Spell> getLevel3() {
        return level3;
    }

    public void setLevel3(ArrayList<Spell> level3) {
        this.level3 = level3;
    }

    public ArrayList<Spell> getLevel4() {
        return level4;
    }

    public void setLevel4(ArrayList<Spell> level4) {
        this.level4 = level4;
    }

    public ArrayList<Spell> getLevel5() {
        return level5;
    }

    public void setLevel5(ArrayList<Spell> level5) {
        this.level5 = level5;
    }

    public ArrayList<Spell> getLevel6() {
        return level6;
    }

    public void setLevel6(ArrayList<Spell> level6) {
        this.level6 = level6;
    }

    public ArrayList<Spell> getLevel7() {
        return level7;
    }

    public void setLevel7(ArrayList<Spell> level7) {
        this.level7 = level7;
    }

    public ArrayList<Spell> getLevel8() {
        return level8;
    }

    public void setLevel8(ArrayList<Spell> level8) {
        this.level8 = level8;
    }

    public ArrayList<Spell> getLevel9() {
        return level9;
    }

    public void setLevel9(ArrayList<Spell> level9) {
        this.level9 = level9;
    }

    public static String getTableCreate(){
        return "CREATE TABLE "+TABLE+"(" +
                ID + " integer primary key autoincrement, " +
                SHEETID + " integer, " +
                SPELLID + " integer, " +
                    "FOREIGN KEY ("+SHEETID+") REFERENCES "+Sheet.TABLE+"("+Sheet.ID+"), " +
                    "FOREIGN KEY ("+SPELLID+") REFERENCES "+Spell.TABLE+"("+Spell.ID+") " +
                ")";
    }

    public static String getTableDrop(){
        return "DROP TABLE IF EXISTS " + TABLE;
    }
}
