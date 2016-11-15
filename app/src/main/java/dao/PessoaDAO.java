package dao;

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

    public void open() throws SQLException{
        db = dbHelper.getWritableDatabase();
    }

    public void close(){
        dbHelper.close();
    }

    public void inserir(Pessoa pessoa){

    }

    public void deletar(){

    }

    public Pessoa buscar(){
        return null;
    }

    public void atualizar(Pessoa pessoa){

    }

}