package saacs.ufc.com.br.saacs.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import saacs.ufc.com.br.saacs.model.Acs;
import saacs.ufc.com.br.saacs.model.Pessoa;
import saacs.ufc.com.br.saacs.model.SituacaoSaude;

/**
 * Created by wallinsondeivesbatistalima on 11/17/16.
 */


public class AcsDAO {

    private SQLiteDatabase db;
    private DBHelper dbHelper;
    private Context context;

    public AcsDAO (Context context){
        this.context = context;
        dbHelper = new DBHelper(context);
    }

    public boolean inserir(Acs agente){
        db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();


        values.put("susNumber", agente.getSusNumber());
        values.put("password", agente.getPassword());
        values.put("nome", agente.getNome());
        long r = db.insert("Acs", null, values);
        db.close();
        if (r > 0L)
            return true;
        return false;
    }

    public List<Acs> buscarTodos(){

        List<Acs> agentes = new ArrayList<Acs>();
        String query = "SELECT * FROM Acs";
        return null;
    }

    public void deletar(long numSUS){

        db = dbHelper.getWritableDatabase();
        db.delete("Acs", "susNumber = ?", new String[]{String.valueOf(numSUS)});
        db.close();
    }


    public Acs recuperar(String susNumber){
        String QUERY = "SELECT * FROM Acs WHERE susNumber = "+susNumber+"";
        db = dbHelper.getReadableDatabase();
        Cursor c = db.rawQuery(QUERY, null);
        ArrayList<Acs> acses = new ArrayList<>();
        c.moveToFirst();
        while(c.isAfterLast() && c.getCount() > 0){
            Acs a = new Acs();
            a.setNome(c.getString(c.getColumnIndex("nome")));
            a.setPassword(c.getString(c.getColumnIndex("password")));
            a.setSusNumber(c.getLong(c.getColumnIndex("susNumber")));
            acses.add(a);
            c.moveToNext();
        }
        if (acses.size() > 0)
            return acses.get(0);
        return null;
    }

    public void atualizar(Acs agente){

        db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("susNumber", agente.getSusNumber());
        values.put("passaword", agente.getPassword());
        values.put("nome", agente.getNome());

        db.update("Acs", values, "susNumber = ?", new String[]{String.valueOf(agente.getSusNumber())});

        db.close();

    }



}
