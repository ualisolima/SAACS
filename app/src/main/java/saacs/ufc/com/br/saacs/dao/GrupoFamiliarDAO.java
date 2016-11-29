package saacs.ufc.com.br.saacs.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import saacs.ufc.com.br.saacs.model.GrupoFamiliar;
import saacs.ufc.com.br.saacs.model.Pessoa;

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

        values.put("id_responsavel", gf.getId());
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

    public void inserirRelacao(Long idGF, List<Long> idsPs){
        db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        for (Long id : idsPs){
            values.put("id_grupo_familiar", idGF);
            values.put("id_pessoa", id);
            db.insert("grupo_familiar_pessoa", null, values);
        }

        db.close();
    }

    public void atualizar(GrupoFamiliar gf){
        db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("id_responsavel", gf.getId());
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

        db.update("grupo_familiar", values, "id_responsavel = ?", new String[]{String.valueOf(gf.getId())});

        db.close();

    }

    public List<Pessoa> recuperarPessoas(Long idGF){
        List<Pessoa> pessoas = new ArrayList<Pessoa>();
        String query = "SELECT * FROM grupo_familiar_pessoa where id_grupo_familiar = "+ String.valueOf(idGF);

        db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()){
            do{
                PessoaDAO pDAO = new PessoaDAO(this.context);
                Pessoa p = pDAO.recuperar(cursor.getLong(cursor.getColumnIndex("id_pessoa")));
                pessoas.add(p);
            }while(cursor.moveToNext());
        }


        db.close();

        return pessoas;
    }

    public List<GrupoFamiliar> buscarTodos(){
        List<GrupoFamiliar> gfs = new ArrayList<GrupoFamiliar>();
        String query = "SELECT * FROM grupo_familiar";

        db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            do{
                GrupoFamiliar gf = new GrupoFamiliar();
                gf.setId(cursor.getLong(cursor.getColumnIndex("id_responsavel")));
                gf.setId_agente(cursor.getLong(cursor.getColumnIndex("id_agente")));
                gf.setTipoLogradouro(cursor.getString(cursor.getColumnIndex("tipoLogradouro")));
                gf.setLogradouro(cursor.getString(cursor.getColumnIndex("logradouro")));
                gf.setNumCasa(cursor.getString(cursor.getColumnIndex("numeroCasa")));
                gf.setMunicipio(cursor.getString(cursor.getColumnIndex("municipio")));
                gf.setuF(cursor.getString(cursor.getColumnIndex("uf")));
                gf.setCep(cursor.getString(cursor.getColumnIndex("cep")));
                gf.setPhone(cursor.getString(cursor.getColumnIndex("phone")));
                gf.setBairro(cursor.getString(cursor.getColumnIndex("bairro")));
                gf.setContato(cursor.getString(cursor.getColumnIndex("contato")));
                gf.setLocalizacao(cursor.getString(cursor.getColumnIndex("localizacao")));
                gf.setCondsMoradia(cursor.getString(cursor.getColumnIndex("condsMoradia")));
                gf.setTipoDomicilio(cursor.getString(cursor.getColumnIndex("tipoDomicilio")));
                gf.setEnergiaEletrica(cursor.getInt(cursor.getColumnIndex("energiaEletrica")) == 1);
                gf.setSaneamentoBasico(cursor.getInt(cursor.getColumnIndex("saneamentoBasico")) == 1);
                gf.setDestLixo(cursor.getString(cursor.getColumnIndex("destLixo")));
                gf.setTemAnimais(cursor.getInt(cursor.getColumnIndex("temAnimais")) == 1);
                gf.setAnimais(cursor.getString(cursor.getColumnIndex("animais")));

                List<Pessoa> pessoas = recuperarPessoas(cursor.getLong(cursor.getColumnIndex("id_responsavel")));
                gf.setPessoas(pessoas);

                gfs.add(gf);

            }while(cursor.moveToNext());
        }

        db.close();

        return gfs;
    }

}
