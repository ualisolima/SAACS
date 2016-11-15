package dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import model.Pessoa;

/**
 * Created by pc on 15/11/16.
 */

public class PessoaDAO {

    private SQLiteDatabase db;
    private DBHelper dbHelper;

    public PessoaDAO (Context context){
        dbHelper = new DBHelper(context);
    }

    public void inserir(Pessoa pessoa){
        db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
//        values.put();

//        db.insert(  , null, values);

        db.close();
    }

//    // Adding new shop
//public void addShop(Shop shop) {
//    SQLiteDatabase db = this.getWritableDatabase();
//    ContentValues values = new ContentValues();
//    values.put(KEY_NAME, shop.getName()); // Shop Name
//    values.put(KEY_SH_ADDR, shop.getAddress()); // Shop Phone Number
//// Inserting Row
//    db.insert(TABLE_SHOPS, null, values);
//    db.close(); // Closing database connection
}

    public void deletar(){

    }

    public Pessoa buscar(){
        return null;
    }

    public void atualizar(Pessoa pessoa){

    }

}