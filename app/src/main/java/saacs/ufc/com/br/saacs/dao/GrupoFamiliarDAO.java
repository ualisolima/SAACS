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

    public Long inserir(GrupoFamiliar gf){
        db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();


        values.put("id_agente", gf.getId_agente());
        values.put("tipoLogradouro", gf.getTipoLogradouro());
        values.put("logradouro", gf.getLogradouro());
        values.put("numeroCasa", gf.getNumCasa());
        values.put("municipio", gf.getMunicipio());
        values.put("uf", gf.getuF());
        values.put("cep", gf.getCep());
        values.put("bairro", gf.getBairro());
        values.put("contato", gf.getContato());
        values.put("localizacao", gf.getLocalizacao());
        values.put("condsMoradia", gf.getCondsMoradia());
        values.put("tipoDomicilio", gf.getTipoDomicilio());
        values.put("energiaEletrica", gf.isEnergiaEletrica());
        values.put("saneamentoBasico", gf.isSaneamentoBasico());
        values.put("coletaLixo", gf.isColetaLixo()? 1:0);
        values.put("temAnimais", gf.isTemAnimais());
        values.put("animais", gf.getAnimais());

        Long id = db.insert("grupo_familiar", null, values);
        gf.setId(id);
        inserirRelacao(gf);
        db.close();
        return id;
    }

    public void inserirRelacao(GrupoFamiliar grupofamiliar){
        db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        for (Pessoa p : grupofamiliar.getPessoas()){
            System.out.println(p.getNome());
            values.put("id_grupo_familiar", grupofamiliar.getId());
            values.put("id_pessoa", p.getNumSUS());
            values.put("is_admin", p.isResponsavelFamiliar()? 1:0);
            db.insert("grupo_familiar_pessoa", null, values);
        }

        db.close();
    }

    public void updateRelacao(GrupoFamiliar grupofamiliar){

        db = dbHelper.getWritableDatabase();

        deletarRelacao(grupofamiliar.getId());

        ContentValues values = new ContentValues();

        for (Pessoa p : grupofamiliar.getPessoas()){
            values.put("id_grupo_familiar", grupofamiliar.getId());
            values.put("id_pessoa", p.getNumSUS());
            values.put("is_admin", p.isResponsavelFamiliar()? 1:0);
            db.insert("grupo_familiar_pessoa", null, values);
        }

        db.close();
    }

    public void atualizar(GrupoFamiliar gf){
        db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("id_grupo", gf.getId());
        values.put("id_agente", gf.getId_agente());
        values.put("tipoLogradouro", gf.getTipoLogradouro());
        values.put("logradouro", gf.getLogradouro());
        values.put("numeroCasa", gf.getNumCasa());
        values.put("municipio", gf.getMunicipio());
        values.put("uf", gf.getuF());
        values.put("cep", gf.getCep());
        values.put("bairro", gf.getBairro());
        values.put("contato", gf.getContato());
        values.put("localizacao", gf.getLocalizacao());
        values.put("condsMoradia", gf.getCondsMoradia());
        values.put("tipoDomicilio", gf.getTipoDomicilio());
        values.put("energiaEletrica", gf.isEnergiaEletrica());
        values.put("saneamentoBasico", gf.isSaneamentoBasico());
        values.put("coletaLixo", gf.isColetaLixo()? 1:0);
        values.put("temAnimais", gf.isTemAnimais());
        values.put("animais", gf.getAnimais());

        db.update("grupo_familiar", values, "id_grupo = ?", new String[]{String.valueOf(gf.getId())});

        db.close();

        updateRelacao(gf);

    }

    public List<Pessoa> recuperarPessoas(GrupoFamiliar gf){
        List<Pessoa> pessoas = new ArrayList<Pessoa>();
        String query = "SELECT * FROM grupo_familiar_pessoa where id_grupo_familiar = "+ String.valueOf(gf.getId());

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

    public List<Pessoa> recuperarResponsaveis(GrupoFamiliar gf){
        List<Pessoa> responsaveis = new ArrayList<Pessoa>();
        String query = "SELECT * FROM grupo_familiar_pessoa where id_grupo_familiar = "+ String.valueOf(gf.getId());

        db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()){
            do{
                PessoaDAO pDAO = new PessoaDAO(this.context);
                Pessoa p = pDAO.recuperar(cursor.getLong(cursor.getColumnIndex("id_pessoa")));
                if (p.isResponsavelFamiliar())
                    responsaveis.add(p);
            }while(cursor.moveToNext());
        }


        db.close();

        return responsaveis;
    }


    public List<GrupoFamiliar> buscarTodos(String pesq){
        List<GrupoFamiliar> gfs = new ArrayList<GrupoFamiliar>();
        String query;

        try {
            Long p = Long.parseLong(pesq);
            query = "SELECT DISTINCT gf.id_grupo, gf.id_agente, gf.tipoLogradouro, " +
                    "gf.logradouro, gf.numeroCasa, gf.municipio, gf.uf, " +
                    "gf.cep, gf.bairro, gf.contato, gf.localizacao, gf.condsMoradia, " +
                    "gf.tipoDomicilio, gf.energiaEletrica, gf.saneamentoBasico, gf.coletaLixo, " +
                    "gf.temAnimais, gf.animais FROM grupo_familiar gf, grupo_familiar_pessoa gfp , pessoa p WHERE " +
                    "gf.id_grupo = gfp.id_grupo_familiar AND gfp.id_pessoa = p.numSUS AND " +
                    "gf.id_grupo = " +p+" OR gfp.id_pessoa = "+p;

        }catch (Exception e) {
            query = "SELECT DISTINCT gf.id_grupo, gf.id_agente, gf.tipoLogradouro, " +
                    "gf.logradouro, gf.numeroCasa, gf.municipio, gf.uf, " +
                    "gf.cep, gf.bairro, gf.contato, gf.localizacao, gf.condsMoradia, " +
                    "gf.tipoDomicilio, gf.energiaEletrica, gf.saneamentoBasico, gf.coletaLixo, " +
                    "gf.temAnimais, gf.animais FROM grupo_familiar gf, grupo_familiar_pessoa gfp , pessoa p WHERE " +
                    "gf.id_grupo = gfp.id_grupo_familiar AND gfp.id_pessoa = p.numSUS AND " +
                    "gf.tipoLogradouro LIKE '%"+pesq+"%' OR gf.logradouro LIKE '%"+pesq+ "%' OR gf.numeroCasa LIKE '%"+pesq+"%' OR" +
                    " gf.municipio LIKE '%"+pesq+"%' OR gf.cep LIKE '%"+pesq+"%' OR gf.bairro LIKE '%"+pesq+"%' OR " +
                    "gf.contato LIKE '%"+pesq+"%' OR p.nome LIKE '%"+pesq+"%'";
        }

        db = dbHelper.getReadableDatabase();
        System.out.println("Aqui : :(");
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            do{
                GrupoFamiliar gf = new GrupoFamiliar();
                gf.setId(cursor.getLong(cursor.getColumnIndex("id_grupo")));
                gf.setId_agente(cursor.getLong(cursor.getColumnIndex("id_agente")));
                gf.setTipoLogradouro(cursor.getString(cursor.getColumnIndex("tipoLogradouro")));
                gf.setLogradouro(cursor.getString(cursor.getColumnIndex("logradouro")));
                gf.setNumCasa(cursor.getString(cursor.getColumnIndex("numeroCasa")));
                gf.setMunicipio(cursor.getString(cursor.getColumnIndex("municipio")));
                gf.setuF(cursor.getString(cursor.getColumnIndex("uf")));
                gf.setCep(cursor.getString(cursor.getColumnIndex("cep")));
                gf.setBairro(cursor.getString(cursor.getColumnIndex("bairro")));
                gf.setContato(cursor.getString(cursor.getColumnIndex("contato")));
                gf.setLocalizacao(cursor.getString(cursor.getColumnIndex("localizacao")));
                gf.setCondsMoradia(cursor.getString(cursor.getColumnIndex("condsMoradia")));
                gf.setTipoDomicilio(cursor.getString(cursor.getColumnIndex("tipoDomicilio")));
                gf.setEnergiaEletrica(cursor.getInt(cursor.getColumnIndex("energiaEletrica")) == 1);
                gf.setSaneamentoBasico(cursor.getInt(cursor.getColumnIndex("saneamentoBasico")) == 1);
                gf.setColetaLixo(cursor.getInt(cursor.getColumnIndex("coletaLixo")) == 1);
                gf.setTemAnimais(cursor.getInt(cursor.getColumnIndex("temAnimais")) == 1);
                gf.setAnimais(cursor.getString(cursor.getColumnIndex("animais")));

                List<Pessoa> pessoas = recuperarPessoas(gf);
                gf.setPessoas(pessoas);
                List<Pessoa> responsaveis = recuperarResponsaveis(gf);
                gf.setResponsaveis(responsaveis);
                gfs.add(gf);
                System.out.println("Aqui : " + gf.getId());

            }while(cursor.moveToNext());
        }

        db.close();

        return gfs;
    }

    public void deletar( Long id_grupo){

        deletarRelacao(id_grupo);

        db = dbHelper.getWritableDatabase();
        db.delete("grupo_familiar", "id_grupo = ?", new String[]{String.valueOf(id_grupo)});
        db.close();

    }

    public void deletarRelacao( Long id_grupo){

        db = dbHelper.getWritableDatabase();
        db.delete("grupo_familiar_pessoa", "id_grupo_familiar = ?", new String[]{String.valueOf(id_grupo)});
        db.close();

    }

}
