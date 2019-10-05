package com.ES3.ficharpg.main;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.ES3.ficharpg.Program;
import com.ES3.ficharpg.model.*;

public class DBManager extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "rpgdb.db";
    private static final int VERSAO = 1;
    private Context context;

    public DBManager(Context context){
        super(context, NOME_BANCO,null, VERSAO);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Action.getTableCreate());
        db.execSQL(Armor.getTableCreate());
        db.execSQL(Background.getTableCreate());
        db.execSQL(CharClass.getTableCreate());
        db.execSQL(DiceRollRegistry.getTableCreate());
        db.execSQL(Equipment.getTableCreate());
        db.execSQL(Item.getTableCreate());
        db.execSQL(LifeStyle.getTableCreate());
        db.execSQL(Race.getTableCreate());
        db.execSQL(Sheet.getTableCreate());
        db.execSQL(Sheet.getTableSkillCreate());
        db.execSQL(SheetSpells.getTableCreate());
        db.execSQL(Spell.getTableCreate());
        db.execSQL(Weapon.getTableCreate());

        insertDataOnCreate(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(Action.getTableDrop());
        db.execSQL(Armor.getTableDrop());
        db.execSQL(Background.getTableDrop());
        db.execSQL(CharClass.getTableDrop());
        db.execSQL(DiceRollRegistry.getTableDrop());
        db.execSQL(Equipment.getTableDrop());
        db.execSQL(Item.getTableDrop());
        db.execSQL(LifeStyle.getTableDrop());
        db.execSQL(Race.getTableDrop());
        db.execSQL(Sheet.getTableDrop());
        db.execSQL(Sheet.getTableSkillDrop());
        db.execSQL(SheetSpells.getTableDrop());
        db.execSQL(Spell.getTableDrop());
        db.execSQL(Weapon.getTableDrop());
        onCreate(db);
    }

    private void insertDataOnCreate(SQLiteDatabase db){
        try {
            Race.insertRace(db);
            CharClass.insertCharClass(db);
            LifeStyle.insertLifeStyle(db);
            Background.insertBackground(db);
            Item.insertItem(db);
            Armor.insertArmor(db);
            Weapon.insertWeapon(db);
            Spell.insertSpell(db);
        } catch (Exception ex) {
            Toast.makeText(context, "Erro ao inserir registros. \n" + ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public long insertData(String table, ContentValues values) throws Exception{
        long result;

        SQLiteDatabase db = getWritableDatabase();
        result = db.insert(table, null, values);
        db.close();

        if (result == -1)
            throw new Exception("Erro ao inserir registro!");
        else
            return result; //Returns the primary key value of the new row
    }

    public Cursor selectData(SQLiteDatabase db, String table, String[] projection, String selection, String[] selectionArgs, String orderBy){
        return db.query(table, projection, selection, selectionArgs, null, null, orderBy, null);
    }

    public void updateData(String table, ContentValues values, String where){
        SQLiteDatabase db = getWritableDatabase();
        db.update(table, values, where,null);
        db.close();
    }

    public void deleteData(String table, String where){
        SQLiteDatabase db = getReadableDatabase();
        db.delete(table,where,null);
        db.close();
    }
}
