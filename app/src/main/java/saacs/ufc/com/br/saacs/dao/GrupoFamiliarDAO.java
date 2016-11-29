package saacs.ufc.com.br.saacs.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import saacs.ufc.com.br.saacs.model.GrupoFamiliar;

/**
 * Created by pc on 29/11/16.
 */

public class GrupoFamiliarDAO {

    private SQLiteDatabase db;
    private DBHelper dbHelper;
    private Context context;

    public GrupoFamiliarDAO(Context context){
        this.context = context;
        dbHelper = new DBHelper(context);
    }

    public void inserir(GrupoFamiliar gf){
        db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("id", gf.getId());
        values.put("id_agente", gf.getId_agente());
        values.put("tipoLogradouro", gf.getTipoLogradouro());
        values.put("logradouro", gf.getLogradouro());
        values.put("numeroCasa", gf.getNumCasa());
        values.put("municipio", gf.getMunicipio());
        values.put("uf", gf.getuF());
        values.put("cep", gf.getCep());
        values.put("phone", gf.getPhone());
        values.put("bairro", gf.getBairro());
        values.put("contato", gf.getContato());
        values.put("localizacao", gf.getLocalizacao());
        values.put("condsMoradia", gf.getCondsMoradia());
        values.put("tipoDomicilio", gf.getTipoDomicilio());
        values.put("energiaEletrica", gf.isEnergiaEletrica());
        values.put("saneamentoBasico", gf.isSaneamentoBasico());
        values.put("destLixo", gf.getDestLixo());
        values.put("temAnimais", gf.isTemAnimais());
        values.put("animais", gf.getAnimais());

        db.insert("grupo_familiar", null, values);

        db.close();
    }

}
