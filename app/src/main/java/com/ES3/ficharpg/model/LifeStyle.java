package com.ES3.ficharpg.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.ES3.ficharpg.main.DBManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class LifeStyle implements Serializable {
    private String name;
    private String cost;
    private String description;

    public static final String TABLE = "LIFESTYLE";
    public static final String ID = "LIFESTYLE_ID";
    public static final String NAME = "NAME";
    public static final String COST = "COST";
    public static final String DESCRIPTION = "DESCRIPTION";

    public String getName() {
        return name;
    }

    public String getCost() {
        return cost;
    }

    public String getDescription() {
        return description;
    }

    public static String getTableCreate(){
        return "CREATE TABLE "+TABLE+"(" +
                ID + " integer primary key autoincrement, " +
                NAME + " text, " +
                COST + " text, " +
                DESCRIPTION + " text " +
                ")";
    }

    public static String getTableDrop(){
        return "DROP TABLE IF EXISTS " + TABLE;
    }

    public static List<LifeStyle> getLifeStyleList(Context context) {
        try {
            List<LifeStyle> lifeStyleList = new ArrayList<>();
            DBManager dbManager = new DBManager(context);
            SQLiteDatabase db = dbManager.getReadableDatabase();

            String[] projection = {NAME, COST, DESCRIPTION};
            Cursor cursor = dbManager.selectData(db, TABLE, projection, null, null, null);

            while (cursor.moveToNext()) {
                LifeStyle lifeStyle = new LifeStyle();
                lifeStyle.name = cursor.getString(cursor.getColumnIndexOrThrow(NAME));
                lifeStyle.cost = cursor.getString(cursor.getColumnIndexOrThrow(COST));
                lifeStyle.description = cursor.getString(cursor.getColumnIndexOrThrow(DESCRIPTION));

                lifeStyleList.add(lifeStyle);
            }
            cursor.close();
            db.close();

            return lifeStyleList;
        } catch (Exception ex) {
            Toast.makeText(context, "Erro ao buscar lista de estilos de vida: \n" + ex.getMessage(), Toast.LENGTH_LONG).show();
            return null;
        }
    }

    public static void insertLifeStyle(SQLiteDatabase db){
        ContentValues values;
        
        values = new ContentValues();
        values.put(NAME, "Miserável");
        values.put(DESCRIPTION, "Miserável.");
        values.put(COST, "-");
        db.insert(TABLE, null, values);

        values = new ContentValues();
        values.put(NAME, "Esquálido");
        values.put(DESCRIPTION, "Esquálido.");
        values.put(COST, "1 pp");
        db.insert(TABLE, null, values);

        values = new ContentValues();
        values.put(NAME, "Pobre");
        values.put(DESCRIPTION, "Pobre.");
        values.put(COST, "2 pp");
        db.insert(TABLE, null, values);

        values = new ContentValues();
        values.put(NAME, "Modesto");
        values.put(DESCRIPTION, "Modesto.");
        values.put(COST, "1 po");
        db.insert(TABLE, null, values);

        values = new ContentValues();
        values.put(NAME, "Confortável");
        values.put(DESCRIPTION, "Confortável.");
        values.put(COST, "2 po");
        db.insert(TABLE, null, values);

        values = new ContentValues();
        values.put(NAME, "Rico");
        values.put(DESCRIPTION, "Rico.");
        values.put(COST, "4 po");
        db.insert(TABLE, null, values);

        values = new ContentValues();
        values.put(NAME, "Aristocrático");
        values.put(DESCRIPTION, "Aristocrático.");
        values.put(COST, "mín. 10 po");
        db.insert(TABLE, null, values);
    }
}
