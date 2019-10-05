package com.ES3.ficharpg.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.ES3.ficharpg.Program;
import com.ES3.ficharpg.main.DBManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Spell implements Serializable {
    private String name;
    private String school;
    private String components;
    private String materialComponent;
    private int level; //TODO SpellLevelEnum
    private String castingTime;
    private double range;
    private double area;
    private String areaType;
    private String duration;
    private boolean meleeAttack;
    private boolean rangedAttack;
    private boolean save;
    private String effect;
    private String description;
    private boolean concentration;
    private boolean ritual;

    private int dieNumber;
    private int die;

    public static final String TABLE = "SPELL";
    public static final String ID = "SPELL_ID";
    public static final String NAME = "SHEET_ID";
    public static final String SCHOOL = "SCHOOL";
    public static final String COMPONENTS = "COMPONENTS";
    public static final String MATERIALCOMPONENTS = "MATERIALCOMPONENTS";
    public static final String LEVEL = "LEVEL";
    public static final String CASTINGTIME = "CASTINGTIME";
    public static final String RANGE = "RANGE";
    public static final String AREA = "AREA";
    public static final String AREATYPE = "AREATYPE";
    public static final String DURATION = "DURATION";
    public static final String MELEEATK = "MELEEATK";
    public static final String RANGEDATK = "RANGEDATK";
    public static final String SAVE = "SAVE";
    public static final String EFFECT = "EFFECT";
    public static final String DESCRIPTION = "DESCRIPTION";
    public static final String CONCENTRATION = "CONCENTRATION";
    public static final String RITUAL = "RITUAL";
    public static final String DIENUMBER = "DIENUMBER";
    public static final String DIE = "DIE";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getComponents() {
        return components;
    }

    public void setComponents(String components) {
        this.components = components;
    }

    public String getMaterialComponent() {
        return materialComponent;
    }

    public void setMaterialComponent(String materialComponent) {
        this.materialComponent = materialComponent;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getCastingTime() {
        return castingTime;
    }

    public void setCastingTime(String castingTime) {
        this.castingTime = castingTime;
    }

    public double getRange() {
        return range;
    }

    public void setRange(double range) {
        this.range = range;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public String getAreaType() {
        return areaType;
    }

    public void setAreaType(String areaType) {
        this.areaType = areaType;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public boolean isMeleeAttack() {
        return meleeAttack;
    }

    public void setMeleeAttack(boolean meleeAttack) {
        this.meleeAttack = meleeAttack;
    }

    public boolean isRangedAttack() {
        return rangedAttack;
    }

    public void setRangedAttack(boolean rangedAttack) {
        this.rangedAttack = rangedAttack;
    }

    public boolean isSave() {
        return save;
    }

    public void setSave(boolean save) {
        this.save = save;
    }

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isConcentration() {
        return concentration;
    }

    public void setConcentration(boolean concentration) {
        this.concentration = concentration;
    }

    public boolean isRitual() {
        return ritual;
    }

    public void setRitual(boolean ritual) {
        this.ritual = ritual;
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

    public static String getTableCreate(){
        return "CREATE TABLE "+TABLE+"(" +
                ID + " integer primary key autoincrement, " +
                NAME + " text, " +
                SCHOOL + " text, " +
                COMPONENTS + " text, " +
                MATERIALCOMPONENTS + " text, " +
                LEVEL + " integer, " +
                CASTINGTIME + " text, " +
                RANGE + " real, " +
                AREA + " real, " +
                AREATYPE + " text, " +
                DURATION + " text, " +
                MELEEATK + " numeric, " +
                RANGEDATK + " numeric, " +
                SAVE + " numeric, " +
                EFFECT + " text, " +
                DESCRIPTION + " text, " +
                CONCENTRATION + " numeric, " +
                RITUAL + " numeric, " +
                DIENUMBER + " integer, " +
                DIE + " integer " +
                ")";
    }

    public static String getTableDrop(){
        return "DROP TABLE IF EXISTS " + TABLE;
    }

    public static void insertSpell(SQLiteDatabase db){
        ContentValues values;

        values = new ContentValues();
        values.put(NAME, "Mão de mago");
        values.put(SCHOOL, "Conjuração");
        values.put(COMPONENTS, "Vocal, Somático");
        values.put(MATERIALCOMPONENTS, "");
        values.put(LEVEL, 0);
        values.put(CASTINGTIME, 0);
        values.put(RANGE, 30);
        values.put(AREA, 5);
        values.put(AREATYPE, "SQUARE");
        values.put(DURATION, "1 minuto");
        values.put(MELEEATK, 0);
        values.put(RANGEDATK, 0);
        values.put(SAVE, 0);
        values.put(EFFECT, "Utilidade");
        values.put(DESCRIPTION, "Uma mão flutuante espectral aparece em um ponto que você escolhe dentro do alcance. " +
                "A mão dura a duração ou até que você a dispense como uma ação. " +
                "A mão desaparece se estiver a mais de 10 metros de distância de você ou se você lançar este feitiço novamente.\n" +
                "\n" +
                "Você pode usar sua ação para controlar a mão. Você pode usar a mão para manipular um objeto, " +
                "abrir uma porta destravada ou recipiente, arrumar ou recuperar um item de um recipiente aberto ou despejar o conteúdo de um frasco. " +
                "Você pode mover a mão até 30 pés cada vez que a usar.\n" +
                "\n" +
                "A mão não pode atacar, ativar itens mágicos ou carregar mais de 10 libras.\n" +
                "\n");
        values.put(CONCENTRATION, 0);
        values.put(RITUAL, 0);
        values.put(DIENUMBER, 0);
        values.put(DIE, 0);
        db.insert(TABLE, null, values);

        values = new ContentValues();
        values.put(NAME, "Mãos Flamejantes");
        values.put(SCHOOL, "Evocação");
        values.put(COMPONENTS, "Vocal, Somático");
        values.put(MATERIALCOMPONENTS, "");
        values.put(LEVEL, 1);
        values.put(CASTINGTIME, 0);
        values.put(RANGE, 15);
        values.put(AREA, 15);
        values.put(AREATYPE, "CONE");
        values.put(DURATION, "Instantâneo");
        values.put(MELEEATK, 0);
        values.put(RANGEDATK, 0);
        values.put(SAVE, 1);
        values.put(EFFECT, "FIRE");
        values.put(DESCRIPTION, "Quando você segura as mãos com os polegares se tocando e os dedos se espalham, " +
                "uma fina camada de chamas dispara de suas pontas esticadas. " +
                "Cada criatura em um cone de 15 pés deve fazer um teste de resistência de Destreza. " +
                "Uma criatura recebe 3d6 de dano de fogo em um teste falhado, ou metade do dano em um bem sucedido.\n" +
                "\n" +
                "O fogo inflama qualquer objeto inflamável na área que não esteja sendo usado ou transportado.\n" +
                "\n");
        values.put(CONCENTRATION, 0);
        values.put(RITUAL, 0);
        values.put(DIENUMBER, 3);
        values.put(DIE, 6);
        db.insert(TABLE, null, values);
    }

    public static List<Spell> getSpellList(Context context){
        try {
            List<Spell> spellList = new ArrayList<>();
            DBManager dbManager = new DBManager(context);
            SQLiteDatabase db = dbManager.getReadableDatabase();

            String[] projection = {NAME, SCHOOL, COMPONENTS, MATERIALCOMPONENTS, LEVEL, CASTINGTIME, RANGE, AREA, AREATYPE, DURATION,
                    MELEEATK, RANGEDATK, SAVE, EFFECT, DESCRIPTION, CONCENTRATION, RITUAL, DIENUMBER, DIE};
            Cursor cursor = dbManager.selectData(db, TABLE, projection, null, null, NAME);

            while (cursor.moveToNext()) {
                Spell spell = new Spell();
                spell.name = cursor.getString(cursor.getColumnIndexOrThrow(NAME));
                spell.school = cursor.getString(cursor.getColumnIndexOrThrow(SCHOOL));
                spell.components = cursor.getString(cursor.getColumnIndexOrThrow(COMPONENTS));
                spell.materialComponent = cursor.getString(cursor.getColumnIndexOrThrow(MATERIALCOMPONENTS));
                spell.level = cursor.getInt(cursor.getColumnIndexOrThrow(LEVEL));
                spell.castingTime = cursor.getString(cursor.getColumnIndexOrThrow(CASTINGTIME));
                spell.range = cursor.getDouble(cursor.getColumnIndexOrThrow(RANGE));
                spell.area = cursor.getDouble(cursor.getColumnIndexOrThrow(AREA));
                spell.areaType = cursor.getString(cursor.getColumnIndexOrThrow(AREATYPE));
                spell.duration = cursor.getString(cursor.getColumnIndexOrThrow(DURATION));
                spell.meleeAttack = Program.NumericToBoolean(cursor.getInt(cursor.getColumnIndexOrThrow(MELEEATK)));
                spell.rangedAttack = Program.NumericToBoolean(cursor.getInt(cursor.getColumnIndexOrThrow(RANGEDATK)));
                spell.save = Program.NumericToBoolean(cursor.getInt(cursor.getColumnIndexOrThrow(SAVE)));
                spell.effect = cursor.getString(cursor.getColumnIndexOrThrow(EFFECT));
                spell.description = cursor.getString(cursor.getColumnIndexOrThrow(DESCRIPTION));
                spell.concentration =Program.NumericToBoolean(cursor.getInt(cursor.getColumnIndexOrThrow(CONCENTRATION)));
                spell.ritual = Program.NumericToBoolean(cursor.getInt(cursor.getColumnIndexOrThrow(RITUAL)));
                spell.dieNumber = cursor.getInt(cursor.getColumnIndexOrThrow(DIENUMBER));
                spell.die = cursor.getInt(cursor.getColumnIndexOrThrow(DIE));
                spellList.add(spell);
            }
            cursor.close();
            db.close();

            return spellList;
        } catch (Exception ex) {
            Toast.makeText(context, "Erro ao buscar lista de magias: \n" + ex.getMessage(), Toast.LENGTH_LONG).show();
            return null;
        }
    }
}
