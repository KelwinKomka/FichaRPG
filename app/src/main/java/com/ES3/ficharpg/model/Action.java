package com.ES3.ficharpg.model;

import java.io.Serializable;

public class Action implements Serializable {
    private String type;
    private int uses;
    private Weapon weapon;
    private Spell spell;
    private String description;

    public static final String TABLE = "ACTION";
    public static final String ID = "ACTION_ID";
    public static final String SHEETID = "SHEET_ID";
    public static final String TYPE = "TYPE";
    public static final String USES = "USES";
    public static final String WEAPONID = "WEAPON_ID";
    public static final String SPELLID = "SPELL_ID";
    public static final String DESCRIPTION = "DESCRIPTION";

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getUses() {
        return uses;
    }

    public void setUses(int uses) {
        this.uses = uses;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Spell getSpell() {
        return spell;
    }

    public void setSpell(Spell spell) {
        this.spell = spell;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static String getTableCreate(){
        return "CREATE TABLE "+TABLE+"(" +
                    ID + " integer primary key autoincrement, " +
                    SHEETID + " integer, " +
                    TYPE + " text, " +
                    USES + " integer, " +
                    WEAPONID + " integer, " +
                    SPELLID + " integer, " +
                    DESCRIPTION + " text, " +
                        "FOREIGN KEY ("+SHEETID+") REFERENCES "+Sheet.TABLE+"("+Sheet.ID+"), " +
                        "FOREIGN KEY ("+WEAPONID+") REFERENCES "+Weapon.TABLE+"("+Weapon.ID+")" +
                        "FOREIGN KEY ("+SPELLID+") REFERENCES "+Spell.TABLE+"("+Spell.ID+")" +
                    ")";
    }

    public static String getTableDrop(){
        return "DROP TABLE IF EXISTS " + TABLE;
    }
}
