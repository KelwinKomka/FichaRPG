package com.ES3.ficharpg.model;

import java.io.Serializable;

public class Equipment implements Serializable {
    private Weapon weapon;
    private Armor armor;
    private Item item;
    private int quantity;

    public static final String TABLE = "EQUIPMENT";
    public static final String ID = "EQUIPMENT_ID";
    public static final String SHEETID = "SHEET_ID";
    public static final String ARMORID = "ARMOR_ID";
    public static final String WEAPONID = "WEAPON_ID";
    public static final String ITEMID = "ITEM_ID";
    public static final String QUANTITY = "QUANTITY";
    public static final String TYPE = "TYPE";

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Armor getArmor() {
        return armor;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public static String getTableCreate(){
        return "CREATE TABLE "+TABLE+"(" +
                ID + " integer primary key autoincrement, " +
                SHEETID + " integer, " +
                ARMORID + " integer, " +
                WEAPONID + " integer, " +
                ITEMID + " integer, " +
                QUANTITY + " integer, " +
                    "FOREIGN KEY ("+SHEETID+") REFERENCES "+Sheet.TABLE+"("+Sheet.ID+"), " +
                    "FOREIGN KEY ("+ARMORID+") REFERENCES "+Armor.TABLE+"("+Armor.ID+"), " +
                    "FOREIGN KEY ("+WEAPONID+") REFERENCES "+Weapon.TABLE+"("+Weapon.ID+"), " +
                    "FOREIGN KEY ("+ITEMID+") REFERENCES "+Item.TABLE+"("+Item.ID+") " +
                ")";
    }

    public static String getTableDrop(){
        return "DROP TABLE IF EXISTS " + TABLE;
    }

}
