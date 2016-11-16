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

    public PessoaDAO (Context context){
        dbHelper = new DBHelper(context);
    }

    public void inserir(Pessoa pessoa){
        db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("numSUS", pessoa.getNumSUS());
        values.put("nome", pessoa.getNome());
        values.put("dataNascimento", pessoa.getDataNascimento());
        values.put("numSUSResponsavel", pessoa.getNumSUSResponsavel());
        values.put("sexo", pessoa.getSexo());
        values.put("nacionalidade", pessoa.getNacionalidade());
        values.put("cidadeUFNatal", pessoa.getCidadeUFNatal());
        values.put("telefone", pessoa.getTelefone());
        values.put("email", pessoa.getEmail());
        values.put("responsavelFamiliar", pessoa.isResponsavelFamiliar());
        values.put("relacaoParentRF", pessoa.getRelacaoParentRF());
        values.put("profissao", pessoa.getProfissao());
        values.put("escolaridade", pessoa.getEscolaridade());
        values.put("situacaoMercado", pessoa.getSituacaoMercado());
        values.put("deficiencia", pessoa.isDeficiencia());
        values.put("qualDeficiencia", pessoa.getQualDeficiencia());
        values.put("id_saude", pessoa.getSaude().getId());

        db.insert("pessoa", null, values);

        db.close();
    }

    public List<Pessoa> buscarTodos(){

        List<Pessoa> pessoas = new ArrayList<Pessoa>();
        String query = "SELECT * FROM pessoa";

        SituacaoSaude saude  = new SituacaoSaude();
        Cursor cSaude;

        db = dbHelper.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            do {
                Pessoa p = new Pessoa();
                p.setNumSUS(cursor.getLong(cursor.getColumnIndex("numSUS")));
                p.setNome(cursor.getString(cursor.getColumnIndex("nome")));
                p.setDataNascimento(cursor.getString(cursor.getColumnIndex("dataNascimento")));
                p.setNumSUSResponsavel(cursor.getLong(cursor.getColumnIndex("numSUSResponsavel")));
                p.setSexo(cursor.getString(cursor.getColumnIndex("sexo")));
                p.setNacionalidade(cursor.getString(cursor.getColumnIndex("nacionalidade")));
                p.setCidadeUFNatal(cursor.getString(cursor.getColumnIndex("cidadeUFNatal")));
                p.setTelefone(cursor.getString(cursor.getColumnIndex("telefone")));
                p.setEmail(cursor.getString(cursor.getColumnIndex("email")));

                p.setResponsavelFamiliar(cursor.getInt(cursor.getColumnIndex("responsavelFamiliar")) == 1);
                p.setRelacaoParentRF(cursor.getString(cursor.getColumnIndex("relacaoParentRF")));
                p.setProfissao(cursor.getString(cursor.getColumnIndex("profissao")));
                p.setEscolaridade(cursor.getString(cursor.getColumnIndex("escolaridade")));
                p.setSituacaoMercado(cursor.getString(cursor.getColumnIndex("situacaoMercado")));
                p.setDeficiencia(cursor.getInt(cursor.getColumnIndex("deficiencia")) == 1);
                p.setQualDeficiencia(cursor.getString(cursor.getColumnIndex("qualDeficiencia")));

                cSaude = db.rawQuery("SELECT * FROM situacao_saude WHERE id_situacao = "+
                        cursor.getLong(cursor.getColumnIndex("id_saude")) ,null);
                if(cSaude.moveToFirst()){
                    saude.setId(cSaude.getLong(0));
                    saude.setGestante(cSaude.getInt(1) == 1);
                    saude.setNivelPeso(cSaude.getString(2));
                    saude.setFumante(cSaude.getInt(3) == 1);
                    saude.setAlcool(cSaude.getInt(4) == 1);
                    saude.setDrogas(cSaude.getInt(5) == 1);
                    saude.setHipertenso(cSaude.getInt(6) == 1);
                    saude.setDiabete(cSaude.getInt(7) == 1);
                    saude.setAVC_Derrame(cSaude.getInt(8) == 1);
                    saude.setInfarto(cSaude.getInt(9) == 1);
                    saude.setDoencaCardiaca(cSaude.getInt(10) == 1);
                    saude.setProblemaRins(cSaude.getInt(11) == 1);
                    saude.setQualProblemaRins(cSaude.getString(12));
                    saude.setProblemaRespiratorios(cSaude.getInt(13) == 1);
                    saude.setQualProblemaRespiratorios(cSaude.getString(14));
                    saude.setHanseniase(cSaude.getInt(15) == 1);
                    saude.setTuberculose(cSaude.getInt(16) == 1);
                    saude.setCancer(cSaude.getInt(17) == 1);
                    saude.setInternacao(cSaude.getInt(18) == 1);
                    saude.setMotivoInternacao(cSaude.getString(19));
                    saude.setProblemaMental(cSaude.getInt(20) == 1);
                    saude.setTratamento(cSaude.getInt(21) == 1);
                    saude.setNivelSaude(cSaude.getInt(22));
                    saude.setPlantasMedicinais(cSaude.getString(23));
                }

                p.setSaude(saude);

                pessoas.add(p);
            } while(cursor.moveToNext());
        }

        db.close();

        // fazer uma função para gerar a lista? return toList(cursor)

        return pessoas;

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
        values.put("numSUSResponsavel", pessoa.getNumSUSResponsavel());
        values.put("sexo", pessoa.getSexo());
        values.put("nacionalidade", pessoa.getNacionalidade());
        values.put("cidadeUFNatal", pessoa.getCidadeUFNatal());
        values.put("telefone", pessoa.getTelefone());
        values.put("email", pessoa.getEmail());
        values.put("responsavelFamiliar", pessoa.isResponsavelFamiliar());
        values.put("relacaoParentRF", pessoa.getRelacaoParentRF());
        values.put("profissao", pessoa.getProfissao());
        values.put("escolaridade", pessoa.getEscolaridade());
        values.put("situacaoMercado", pessoa.getSituacaoMercado());
        values.put("deficiencia", pessoa.isDeficiencia());
        values.put("qualDeficiencia", pessoa.getQualDeficiencia());
        values.put("id_saude", pessoa.getSaude().getId());

        db.update("pessoa", values, "numSUS = ?", new String[]{String.valueOf(pessoa.getNumSUS())});

        db.close();

    }

}