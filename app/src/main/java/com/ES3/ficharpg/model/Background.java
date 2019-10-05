package com.ES3.ficharpg.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.ES3.ficharpg.Program;
import com.ES3.ficharpg.main.DBManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Background implements Serializable {
    private String name;
    private String feature;
    private ArrayList<String> skillProficiencies;
    private ArrayList<String> toolProficiencies; //TODO ToolProficienciesEnum
    private ArrayList<String> specialty; //TODO SpecialityEnum

    private String description;
    private ArrayList<String> languages; //TODO LanguageEnum
    private ArrayList<Equipment> equipment;

    public static final String TABLE = "BACKGROUND";
    public static final String ID = "BACKGROUND_ID";
    public static final String NAME = "NAME";
    public static final String FEATURE = "FEATURE";
    public static final String SKILLPROF = "SKILLPROF";
    public static final String TOOLPROF = "TOOLPROF";
    public static final String SPECIALTY = "SPECIALTY";
    public static final String DESCRIPTION = "DESCRIPTION";
    public static final String LANGUAGES = "LANGUAGES";
    public static final String EQUIPMENT = "EQUIPMENT";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public ArrayList<String> getSkillProficiencies() {
        return skillProficiencies;
    }

    public void setSkillProficiencies(ArrayList<String> skillProficiencies) {
        this.skillProficiencies = skillProficiencies;
    }

    public ArrayList<String> getToolProficiencies() {
        return toolProficiencies;
    }

    public void setToolProficiencies(ArrayList<String> toolProficiencies) {
        this.toolProficiencies = toolProficiencies;
    }

    public ArrayList<String> getSpecialty() {
        return specialty;
    }

    public void setSpecialty(ArrayList<String> specialty) {
        this.specialty = specialty;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<String> getLanguages() {
        return languages;
    }

    public void setLanguages(ArrayList<String> languages) {
        this.languages = languages;
    }

    public ArrayList<Equipment> getEquipment() {
        return equipment;
    }

    public static String getTableCreate(){
        return "CREATE TABLE "+TABLE+"(" +
                ID + " integer primary key autoincrement, " +
                NAME + " text, " +
                FEATURE + " text, " +
                SKILLPROF + " text, " +
                TOOLPROF + " text, " +
                SPECIALTY + " text, " +
                DESCRIPTION + " text, " +
                LANGUAGES + " text, " +
                EQUIPMENT + " text " +
                ")";
    }

    public static String getTableDrop(){
        return "DROP TABLE IF EXISTS " + TABLE;
    }

    public static List<Background> getBackgroundList(Context context) {
        try {
            List<Background> backgroundList = new ArrayList<>();
            DBManager dbManager = new DBManager(context);
            SQLiteDatabase db = dbManager.getReadableDatabase();

            String[] projection = {NAME, FEATURE, SKILLPROF, TOOLPROF, SPECIALTY, DESCRIPTION, LANGUAGES, EQUIPMENT};
            Cursor cursor = dbManager.selectData(db, TABLE, projection, null, null, NAME);

            while (cursor.moveToNext()) {
                Background background = new Background();
                background.name = cursor.getString(cursor.getColumnIndexOrThrow(NAME));
                background.feature = cursor.getString(cursor.getColumnIndexOrThrow(FEATURE));
                if (cursor.getString(cursor.getColumnIndexOrThrow(SKILLPROF)).trim().length() > 0)
                    background.skillProficiencies = new ArrayList<>(Arrays.asList(cursor.getString(cursor.getColumnIndexOrThrow(SKILLPROF)).split(";")));
                else
                    background.skillProficiencies = new ArrayList<>();
                if (cursor.getString(cursor.getColumnIndexOrThrow(TOOLPROF)).trim().length() > 0)
                    background.toolProficiencies = new ArrayList<>(Arrays.asList(cursor.getString(cursor.getColumnIndexOrThrow(TOOLPROF)).split(";")));
                else
                    background.toolProficiencies = new ArrayList<>();
                if (cursor.getString(cursor.getColumnIndexOrThrow(SPECIALTY)).trim().length() > 0)
                    background.specialty = new ArrayList<>(Arrays.asList(cursor.getString(cursor.getColumnIndexOrThrow(SPECIALTY)).split(";")));
                else
                    background.specialty = new ArrayList<>();
                background.description = cursor.getString(cursor.getColumnIndexOrThrow(DESCRIPTION));
                if (cursor.getString(cursor.getColumnIndexOrThrow(LANGUAGES)).trim().length() > 0)
                    background.languages = new ArrayList<>(Arrays.asList(cursor.getString(cursor.getColumnIndexOrThrow(LANGUAGES)).split(";")));
                else
                    background.languages = new ArrayList<>();
                background.equipment = getBackgroundEquipment(background.name, context);

                backgroundList.add(background);
            }
            cursor.close();
            db.close();

            return backgroundList;
        } catch (Exception ex) {
            Toast.makeText(context, "Erro ao buscar lista de raças: \n" + ex.getMessage(), Toast.LENGTH_LONG).show();
            return null;
        }
    }
    
    public static void insertBackground(SQLiteDatabase db){
        ContentValues values;
        
        values = new ContentValues();
        values.put(NAME, Program.ACOLYTE);
        values.put(FEATURE, "Abrigo dos Fiéis");
        values.put(SKILLPROF, Program.INSIGHT+";"+Program.RELIGION);
        values.put(TOOLPROF, "");
        values.put(SPECIALTY, "");
        values.put(DESCRIPTION, "Você passou sua vida a serviço de um templo para um deus específico ou panteão de deuses. " +
                "Você age como um intermediário entre o reino do mundo santo e o mortal, " +
                "realizando ritos sagrados e oferecendo sacrifícios para conduzir os adoradores à presença do divino. " +
                "Você não é necessariamente um clérigo - realizar ritos sagrados não é a mesma coisa que canalizar o poder divino.");
        values.put(LANGUAGES, "");
        values.put(EQUIPMENT, "");
        db.insert(TABLE, null, values);

//            values = new ContentValues();
//            values.put(NAME, "Amaldiçoado");
//            values.put(FEATURE, "Coração das Trevas");
//            values.put(SKILLPROF, Program.ARCANA+";"+Program.INVESTIGATION+";"+Program.RELIGION+";"+Program.SURVIVAL);
//            values.put(TOOLPROF, "");
//            values.put(SPECIALTY, "");
//            values.put(DESCRIPTION, "Você é assombrado por algo tão terrível que você não ousa falar sobre isso. " +
//                    "Você tentou enterrá-lo e fugir dele, sem sucesso. " +
//                    "O que quer que seja essa coisa que assombra, você não pode ser morto com uma espada ou banido com um feitiço. " +
//                    "Pode vir até você como uma sombra na parede, um pesadelo horripilante, uma lembrança que se recusa a morrer ou um sussurro demoníaco no escuro. " +
//                    "O fardo cobrou seu preço, isolando você da maioria das pessoas e fazendo você questionar sua sanidade. " +
//                    "Você deve encontrar uma maneira de superá-lo antes que ele destrua você.");
//            values.put(LANGUAGES, "");
//            values.put(EQUIPMENT, "");
//            db.insert(TABLE, null, values);

        values = new ContentValues();
        values.put(NAME, Program.CRIMINAL);
        values.put(FEATURE, "Contato Criminoso");
        values.put(SKILLPROF, Program.DECEPTION+";"+Program.STEALTH);
        values.put(TOOLPROF, Program.THIEVES_TOOLS);
        values.put(SPECIALTY, "");
        values.put(DESCRIPTION, "Você é um criminoso experiente com histórico de violar a lei. " +
                "Você passou muito tempo entre outros criminosos e ainda tem contatos dentro do submundo do crime. " +
                "Você está muito mais perto do que a maioria das pessoas para o mundo do assassinato, " +
                "do roubo e da violência que permeia o submundo da civilização, " +
                "e você sobreviveu até este ponto desrespeitando as regras e regulamentos da sociedade.");
        values.put(LANGUAGES, "");
        values.put(EQUIPMENT, "");
        db.insert(TABLE, null, values);

        values = new ContentValues();
        values.put(NAME, Program.FOLKHERO);
        values.put(FEATURE, "Hospitalidade Rústica");
        values.put(SKILLPROF, Program.ANIMALHANDLING+";"+Program.SURVIVAL);
        values.put(TOOLPROF, "");
        values.put(SPECIALTY, "");
        values.put(DESCRIPTION, "Você vem de uma classe social humilde, mas você está destinado a muito mais. " +
                "As pessoas da sua aldeia natal já o consideram seu campeão, " +
                "e seu destino o chama para lutar contra os tiranos e monstros que ameaçam as pessoas comuns em todos os lugares.");
        values.put(LANGUAGES, "");
        values.put(EQUIPMENT, "");
        db.insert(TABLE, null, values);

        values = new ContentValues();
        values.put(NAME, Program.NOBLE);
        values.put(FEATURE, "Posição de Privilégio");
        values.put(SKILLPROF, Program.HISTORY+";"+Program.PERSUASION);
        values.put(TOOLPROF, "");
        values.put(SPECIALTY, "");
        values.put(DESCRIPTION, "Você entende riqueza, poder e privilégio. " +
                "Você tem um título nobre e sua família é proprietária de terras, " +
                "cobra impostos e exerce influência política significativa. " +
                "Você pode ser um aristocrata mimado, não familiarizado com trabalho ou desconforto, " +
                "um ex-comerciante recém elevado à nobreza, ou um canalha deserdado com um senso desproporcional de direito. " +
                "Ou você pode ser um proprietário de terras honesto e trabalhador, que se preocupa profundamente com as pessoas que vivem e trabalham em sua terra, " +
                "profundamente consciente de sua responsabilidade para com elas.");
        values.put(LANGUAGES, "");
        values.put(EQUIPMENT, "");
        db.insert(TABLE, null, values);

        values = new ContentValues();
        values.put(NAME, Program.SAGE);
        values.put(FEATURE, "Pesquisador");
        values.put(SKILLPROF, Program.ARCANA+";"+Program.HISTORY);
        values.put(TOOLPROF, "");
        values.put(SPECIALTY, "");
        values.put(DESCRIPTION, "Você passou anos aprendendo a sabedoria do multiverso. " +
                "Você vasculhou manuscritos, estudou pergaminhos e ouviu os maiores especialistas nos assuntos que lhe interessam. " +
                "Seus esforços fizeram de você um mestre em seus campos de estudo.");
        values.put(LANGUAGES, "");
        values.put(EQUIPMENT, "");
        db.insert(TABLE, null, values);

        values = new ContentValues();
        values.put(NAME, Program.SOLDIER);
        values.put(FEATURE, "Hierarquia militar");
        values.put(SKILLPROF, Program.ATHLETICS+";"+Program.INTIMIDATION);
        values.put(TOOLPROF, "");
        values.put(SPECIALTY, "");
        values.put(DESCRIPTION, "A guerra tem sido sua vida desde que você se lembre. " +
                "Você treinou como um jovem, estudou o uso de armas e armaduras, aprendeu técnicas básicas de sobrevivência, " +
                "incluindo como se manter vivo no campo de batalha. Você pode ter sido parte de um exército nacional permanente ou de uma empresa mercenária," +
                " ou talvez membro de uma milícia local que ganhou destaque durante uma guerra recente.");
        values.put(LANGUAGES, "");
        values.put(EQUIPMENT, "");
        db.insert(TABLE, null, values);
    }

    private static ArrayList<Equipment> getBackgroundEquipment(String background, Context context){
        List<Item> existingItems = Item.getItemList(context);
        ArrayList<Equipment> equipmentList = new ArrayList<>();

        if (existingItems != null)
            switch (background){
                case Program.ACOLYTE:
                    for (Item item: existingItems){
                        switch (item.getName()){
                            case "Símbolo Sagrado":
                            case "Vestimentas":
                            case "Roupas Comuns":
                                Equipment equip = new Equipment();
                                equip.setItem(item);
                                equip.setQuantity(1);
                                equipmentList.add(equip);
                                break;
                        }
                    }
                    break;
                case Program.CRIMINAL:
                    for (Item item: existingItems){
                        switch (item.getName()){
                            case "Pé de Cabra":
                            case "Roupas Comuns":
                                Equipment equip = new Equipment();
                                equip.setItem(item);
                                equip.setQuantity(1);
                                equipmentList.add(equip);
                                break;
                        }
                    }
                    break;
                case Program.FOLKHERO:
                    for (Item item: existingItems){
                        switch (item.getName()){
                            case "Pá":
                            case "Panela de Ferro":
                            case "Roupas Comuns":
                                Equipment equip = new Equipment();
                                equip.setItem(item);
                                equip.setQuantity(1);
                                equipmentList.add(equip);
                                break;
                        }
                    }
                    break;
                case Program.NOBLE:
                    for (Item item: existingItems){
                        switch (item.getName()){
                            case "Roupas Elegantes":
                            case "Anel de identificação":
                                Equipment equip = new Equipment();
                                equip.setItem(item);
                                equip.setQuantity(1);
                                equipmentList.add(equip);
                                break;
                        }
                    }
                    break;
                case Program.SAGE:
                    for (Item item: existingItems){
                        switch (item.getName()){
                            case "Frasco de tinta":
                            case "Vestimentas":
                            case "Roupas Comuns":
                                Equipment equip = new Equipment();
                                equip.setItem(item);
                                equip.setQuantity(1);
                                equipmentList.add(equip);
                                break;
                        }
                    }
                    break;
                case Program.SOLDIER:
                    for (Item item: existingItems){
                        switch (item.getName()){
                            case "Conjunto de dados":
                            case "Baralho":
                            case "Roupas Comuns":
                                Equipment equip = new Equipment();
                                equip.setItem(item);
                                equip.setQuantity(1);
                                equipmentList.add(equip);
                                break;
                        }
                    }
                    break;
            }

        return equipmentList;
    }
}