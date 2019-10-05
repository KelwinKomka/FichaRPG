package com.ES3.ficharpg.model;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.ES3.ficharpg.Program;
import com.ES3.ficharpg.main.DBManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Item implements Serializable {
    private String name;
    private String description;
    private Double weight;
    private String type;
    private Double cost;
    private String coin;

    public static final String TABLE = "ITEM";
    public static final String ID = "ITEM_ID";
    public static final String NAME = "NAME";
    public static final String DESCRIPTION = "DESCRIPTION";
    public static final String WEIGHT = "WEIGHT";
    public static final String TYPE = "TYPE";
    public static final String COST = "COST";
    public static final String COIN = "COIN";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public String getCoin() {
        return coin;
    }

    public void setCoin(String coin) {
        this.coin = coin;
    }

    public static String getTableCreate(){
        return "CREATE TABLE "+TABLE+"(" +
                ID + " integer primary key autoincrement, " +
                NAME + " text, " +
                DESCRIPTION + " text, " +
                WEIGHT + " real, " +
                TYPE + " text, " +
                COST + " integer, " +
                COIN + " text " +
                ")";
    }

    public static String getTableDrop(){
        return "DROP TABLE IF EXISTS " + TABLE;
    }

    public static long insertItem(Context context, Item item){
        ContentValues values = new ContentValues();

        values.put(Item.NAME, item.getName());
        values.put(Item.DESCRIPTION, "");
        values.put(Item.COST, item.getCost());
        values.put(Item.WEIGHT, item.getWeight());
        values.put(Item.TYPE, item.getType());
        values.put(Item.COIN, item.getCoin());

        try {
            DBManager dbManager = new DBManager(context);
            return dbManager.insertData(TABLE, values);
        } catch (Exception ex){
            AlertDialog.Builder dialog = new AlertDialog.Builder(context);
            dialog.setTitle("Erro")
                    .setMessage("Erro ao inserir item. \n" + ex.getMessage())
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialoginterface, int i) {
                            dialoginterface.cancel();
                        }
                    }).show();
        }
        return 0L;
    }
    
    public static void insertItem(SQLiteDatabase db){
        ContentValues values;

        //Items
        values = new ContentValues();
        values.put(NAME, "Símbolo Sagrado");
        values.put(DESCRIPTION, "Um símbolo sagrado é uma representação de um deus ou panteão. " +
                "Um clérigo ou paladino pode usar um símbolo sagrado como um foco de conjuração, " +
                "como descrito na seção de conjuração. Para usar o símbolo desta maneira, o lançador deve segurá-lo na mão, " +
                "usá-lo visivelmente ou carregá-lo em um escudo.");
        values.put(COST, 0);
        values.put(WEIGHT, 0);
        values.put(TYPE, Program.HOLYSYMBOL);
        values.put(COIN, "");
        db.insert(TABLE, null, values);

        values = new ContentValues();
        values.put(NAME, "Vestimentas");
        values.put(DESCRIPTION, "Roupas religiosas, normalmente encontradas em uma mochila de padre.");
        values.put(COST, 0);
        values.put(WEIGHT, 0);
        values.put(TYPE, Program.GEAR);
        values.put(COIN, "");
        db.insert(TABLE, null, values);

        values = new ContentValues();
        values.put(NAME, "Roupas Comuns");
        values.put(DESCRIPTION, "Esse conjunto de roupas poderia consistir de uma camisa solta e calças folgadas, " +
                "ou uma camisa solta e saia ou vestido. Envolturas de pano são usadas para sapatos.");
        values.put(COST, 5);
        values.put(WEIGHT, 3);
        values.put(TYPE, Program.GEAR);
        values.put(COIN, "pp");
        db.insert(TABLE, null, values);

        values = new ContentValues();
        values.put(NAME, "Pé de Cabra");
        values.put(DESCRIPTION, "Usar um pé-de-cabra garante vantagem aos testes de Força, " +
                "onde a alavancagem do pé-de-cabra pode ser aplicada.");
        values.put(COST, 2);
        values.put(WEIGHT, 5);
        values.put(TYPE, Program.GEAR);
        values.put(COIN, "po");
        db.insert(TABLE, null, values);

        values = new ContentValues();
        values.put(NAME, "Pá");
        values.put(DESCRIPTION, "Uma pá normal usada para escavar.");
        values.put(COST, 2);
        values.put(WEIGHT, 5);
        values.put(TYPE, Program.GEAR);
        values.put(COIN, "po");
        db.insert(TABLE, null, values);

        values = new ContentValues();
        values.put(NAME, "Panela de Ferro");
        values.put(DESCRIPTION, "Um pote de ferro pode conter 1 litro de líquido.");
        values.put(COST, 2);
        values.put(WEIGHT, 10);
        values.put(TYPE, Program.GEAR);
        values.put(COIN, "po");
        db.insert(TABLE, null, values);

        values = new ContentValues();
        values.put(NAME, "Roupas Elegantes");
        values.put(DESCRIPTION, "Este conjunto de roupas é projetado especificamente para ser caro e mostrá-lo, " +
                "incluindo roupas extravagantes e sob medida de qualquer forma que seja o estilo atual nas cortes dos nobres. " +
                "Metais preciosos e pedras preciosas poderiam ser trabalhados na roupa.");
        values.put(COST, 15);
        values.put(WEIGHT, 6);
        values.put(TYPE, Program.GEAR);
        values.put(COIN, "po");
        db.insert(TABLE, null, values);

        values = new ContentValues();
        values.put(NAME, "Anel de identificação");
        values.put(DESCRIPTION, "Cada anel de sinete tem um design distinto gravado nele. " +
                "Quando você pressiona este anel em cera quente, você deixa uma marca de identificação.");
        values.put(COST, 5);
        values.put(WEIGHT, 0);
        values.put(TYPE, Program.GEAR);
        values.put(COIN, "po");
        db.insert(TABLE, null, values);

        values = new ContentValues();
        values.put(NAME, "Frasco de tinta");
        values.put(DESCRIPTION, "A tinta é normalmente usada com uma caneta de tinta para escrever.");
        values.put(COST, 10);
        values.put(WEIGHT, 0);
        values.put(TYPE, Program.GEAR);
        values.put(COIN, "po");
        db.insert(TABLE, null, values);

        values = new ContentValues();
        values.put(NAME, "Conjunto de dados");
        values.put(DESCRIPTION, "Se você é proficiente com um conjunto de jogos, " +
                "você pode adicionar seu bônus de proficiência aos testes de habilidade que você faz para jogar um jogo com esse conjunto.");
        values.put(COST, 1);
        values.put(WEIGHT, 0);
        values.put(TYPE, Program.TOOL);
        values.put(COIN, "pp");
        db.insert(TABLE, null, values);

        values = new ContentValues();
        values.put(NAME, "Baralho");
        values.put(DESCRIPTION, "Se você é proficiente com um conjunto de jogos, " +
                "você pode adicionar seu bônus de proficiência aos testes de habilidade que você faz para jogar um jogo com esse conjunto.");
        values.put(COST, 5);
        values.put(WEIGHT, 0);
        values.put(TYPE, Program.TOOL);
        values.put(COIN, "pp");
        db.insert(TABLE, null, values);

        values = new ContentValues();
        values.put(NAME, "Bolsa de componentes");
        values.put(DESCRIPTION, "Uma bolsa de componente é uma pequena bolsa de couro impermeável que possui compartimentos para armazenar" +
                " todos os componentes de material e outros itens especiais que você precisa para lançar suas magias, " +
                "exceto os componentes que têm um custo específico (conforme indicado na descrição de uma magia).");
        values.put(COST, 25);
        values.put(WEIGHT, 2);
        values.put(TYPE, Program.GEAR);
        values.put(COIN, "po");
        db.insert(TABLE, null, values);
    }

    public static List<Item> getItemList(Context context){
        try {
            List<Item> itemList = new ArrayList<>();
            DBManager dbManager = new DBManager(context);
            SQLiteDatabase db = dbManager.getReadableDatabase();

            String[] projection = {NAME, DESCRIPTION, WEIGHT, TYPE, COST, COIN};
            Cursor cursor = dbManager.selectData(db, TABLE, projection, null, null, NAME);

            while (cursor.moveToNext()) {
                Item item = new Item();
                item.name = cursor.getString(cursor.getColumnIndexOrThrow(NAME));
                item.description = cursor.getString(cursor.getColumnIndexOrThrow(DESCRIPTION));
                item.weight = cursor.getDouble(cursor.getColumnIndexOrThrow(WEIGHT));
                item.type = cursor.getString(cursor.getColumnIndexOrThrow(TYPE));
                item.cost = cursor.getDouble(cursor.getColumnIndexOrThrow(COST));
                item.coin = cursor.getString(cursor.getColumnIndexOrThrow(COIN));
                itemList.add(item);
            }
            cursor.close();
            db.close();

            return itemList;
        } catch (Exception ex) {
            Toast.makeText(context, "Erro ao buscar lista de itens: \n" + ex.getMessage(), Toast.LENGTH_LONG).show();
            return null;
        }
    }
}
