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

    public void deletar(){

    }

    public Pessoa buscar(){
        return null;
    }

    public void atualizar(Pessoa pessoa){

    }

}