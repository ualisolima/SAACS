package saacs.ufc.com.br.saacs.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import saacs.ufc.com.br.saacs.model.Pessoa;
import saacs.ufc.com.br.saacs.model.SituacaoSaude;

/**
 * Created by pc on 15/11/16.
 */

public class PessoaDAO {

    private SQLiteDatabase db;
    private DBHelper dbHelper;
    private Context context;

    public PessoaDAO (Context context){
        this.context = context;
        dbHelper = new DBHelper(context);
    }

    public void inserir(Pessoa pessoa){
        db = dbHelper.getWritableDatabase();

        SituacaoSaudeDAO saudeDAO  = new SituacaoSaudeDAO(this.context);
        saudeDAO.inserir(pessoa.getSaude());

        ContentValues values = new ContentValues();

        values.put("numSUS", pessoa.getNumSUS());
        values.put("nome", pessoa.getNome());
        values.put("dataNascimento", pessoa.getDataNascimento());
        values.put("numeroNis", pessoa.getNumeroNis());
        values.put("sexo", pessoa.getSexo());
        values.put("etnia", pessoa.getEtnia());
        values.put("nacionalidade", pessoa.getNacionalidade());
        values.put("paisDeOrigem", pessoa.getPaisDeOrigem());
        values.put("cidadeNatal", pessoa.getCidadeNatal());
        values.put("estado", pessoa.getEstado());
        values.put("telefone", pessoa.getTelefone());
        values.put("email", pessoa.getEmail());
        values.put("nomeDaMae", pessoa.getNomedaMae());
        values.put("responsavelFamiliar", pessoa.isResponsavelFamiliar());
        values.put("relacaoParentRF", pessoa.getRelacaoParentRF());
        values.put("profissao", pessoa.getProfissao());
        values.put("escolaridade", pessoa.getEscolaridade());
        values.put("situacaoMercado", pessoa.getSituacaoMercado());
        values.put("id_saude", pessoa.getSaude().getId());

        db.insert("pessoa", null, values);

        db.close();
    }

    public List<Pessoa> buscarTodos(){

        List<Pessoa> pessoas = new ArrayList<Pessoa>();
        String query = "SELECT * FROM pessoa";

        SituacaoSaudeDAO saudeDAO  = new SituacaoSaudeDAO(this.context);

        db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            do {
                Pessoa p = new Pessoa();
                p.setNumSUS(cursor.getLong(cursor.getColumnIndex("numSUS")));
                p.setNome(cursor.getString(cursor.getColumnIndex("nome")));
                p.setDataNascimento(cursor.getString(cursor.getColumnIndex("dataNascimento")));
                p.setNumeroNis(cursor.getLong(cursor.getColumnIndex("numeroNis")));
                p.setSexo(cursor.getString(cursor.getColumnIndex("sexo")));
                p.setSexo(cursor.getString(cursor.getColumnIndex("etnia")));
                p.setNacionalidade(cursor.getString(cursor.getColumnIndex("nacionalidade")));
                p.setSexo(cursor.getString(cursor.getColumnIndex("paisDeOrigem")));
                p.setCidadeNatal(cursor.getString(cursor.getColumnIndex("cidadeNatal")));
                p.setCidadeNatal(cursor.getString(cursor.getColumnIndex("estado")));
                p.setTelefone(cursor.getString(cursor.getColumnIndex("telefone")));
                p.setEmail(cursor.getString(cursor.getColumnIndex("email")));

                p.setEmail(cursor.getString(cursor.getColumnIndex("nomeDaMae")));
                p.setResponsavelFamiliar(cursor.getInt(cursor.getColumnIndex("responsavelFamiliar")) == 1);
                p.setRelacaoParentRF(cursor.getString(cursor.getColumnIndex("relacaoParentRF")));
                p.setProfissao(cursor.getString(cursor.getColumnIndex("profissao")));
                p.setEscolaridade(cursor.getString(cursor.getColumnIndex("escolaridade")));
                p.setSituacaoMercado(cursor.getString(cursor.getColumnIndex("situacaoMercado")));

                SituacaoSaude saude = saudeDAO.buscar(cursor.getLong(cursor.getColumnIndex("id_saude")));
                p.setSaude(saude);
                pessoas.add(p);
            } while(cursor.moveToNext());
        }
        db.close();
        return pessoas;

    }

    public Pessoa recuperar(long idPessoa){
        List<Pessoa> pessoas = buscarTodos();

        for(Pessoa p : pessoas){
            if (p.getNumSUS() == idPessoa )
                return  p;
        }
        return null;
    }

    public void deletar( long numSUS){

        db = dbHelper.getWritableDatabase();
        db.delete("pessoa", "numSUS = ?", new String[]{String.valueOf(numSUS)});
        db.close();

    }

    public void atualizar(Pessoa pessoa){

        db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("numSUS", pessoa.getNumSUS());
        values.put("nome", pessoa.getNome());
        values.put("dataNascimento", pessoa.getDataNascimento());
        values.put("numeroNis", pessoa.getNumeroNis());
        values.put("sexo", pessoa.getSexo());
        values.put("etnia", pessoa.getEtnia());
        values.put("nacionalidade", pessoa.getNacionalidade());
        values.put("paisDeOrigem", pessoa.getPaisDeOrigem());
        values.put("cidadeNatal", pessoa.getCidadeNatal());
        values.put("estado", pessoa.getEstado());
        values.put("telefone", pessoa.getTelefone());
        values.put("email", pessoa.getEmail());
        values.put("nomeDaMae", pessoa.getNomedaMae());
        values.put("responsavelFamiliar", pessoa.isResponsavelFamiliar());
        values.put("relacaoParentRF", pessoa.getRelacaoParentRF());
        values.put("profissao", pessoa.getProfissao());
        values.put("escolaridade", pessoa.getEscolaridade());
        values.put("situacaoMercado", pessoa.getSituacaoMercado());
        values.put("id_saude", pessoa.getSaude().getId());

        db.update("pessoa", values, "numSUS = ?", new String[]{String.valueOf(pessoa.getNumSUS())});

        db.close();

    }

}