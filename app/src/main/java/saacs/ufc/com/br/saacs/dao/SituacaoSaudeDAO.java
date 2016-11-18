package saacs.ufc.com.br.saacs.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import saacs.ufc.com.br.saacs.model.SituacaoSaude;

/**
 * Created by pc on 17/11/16.
 */

public class SituacaoSaudeDAO {

    private SQLiteDatabase db;
    private DBHelper dbHelper;

    public SituacaoSaudeDAO(Context context) {

        dbHelper = new DBHelper(context);

    }

    public void inserir(SituacaoSaude saude){

        db = dbHelper.getWritableDatabase();

        ContentValues values =  new ContentValues();

        values.put("id_situacao", saude.getId());
        values.put("gestante", saude.isGestante());
        values.put("nivelPeso", saude.getNivelPeso());
        values.put("fumante", saude.isFumante());
        values.put("alcool", saude.isAlcool());
        values.put("drogas", saude.isDrogas());
        values.put("hipertenso", saude.isHipertenso());
        values.put("diabetes", saude.isDiabete());
        values.put("AVC_Derrame", saude.isAVC_Derrame());
        values.put("infarto", saude.isInfarto());
        values.put("doencaCardiaca", saude.isDoencaCardiaca());
        values.put("problemaRins", saude.isProblemaRins());
        values.put("qualProblemaRins", saude.getQualProblemaRins());
        values.put("problemaRespiratorio", saude.isProblemaRespiratorios());
        values.put("qualProblemaRespiratorio", saude.getQualProblemaRespiratorios());
        values.put("hanseniase", saude.isHanseniase());
        values.put("tuberculose", saude.isTuberculose());
        values.put("cancer", saude.isCancer());
        values.put("internacao", saude.isInternacao());
        values.put("motivoInternacao", saude.getMotivoInternacao());
        values.put("problemaMental", saude.isProblemaMental());
        values.put("tratamento", saude.isTratamento());
        values.put("nivelSaude", saude.getNivelSaude());
        values.put("plantasMedicinais", saude.getPlantasMedicinais());

        db.insert("situacao_saude", null, values);

        db.close();


    }

    public SituacaoSaude buscar(long idSaude){

        SituacaoSaude saude = new SituacaoSaude();
        String query = "SELECT * FROM situacao_saude WHERE id_situacao = ";

        db = dbHelper.getWritableDatabase();

        Cursor cursor = db.rawQuery(query+String.valueOf(idSaude), null);

        if(cursor.moveToFirst()){
            saude.setId(cursor.getLong(0));
            saude.setGestante(cursor.getInt(1) == 1);
            saude.setNivelPeso(cursor.getString(2));
            saude.setFumante(cursor.getInt(3) == 1);
            saude.setAlcool(cursor.getInt(4) == 1);
            saude.setDrogas(cursor.getInt(5) == 1);
            saude.setHipertenso(cursor.getInt(6) == 1);
            saude.setDiabete(cursor.getInt(7) == 1);
            saude.setAVC_Derrame(cursor.getInt(8) == 1);
            saude.setInfarto(cursor.getInt(9) == 1);
            saude.setDoencaCardiaca(cursor.getInt(10) == 1);
            saude.setProblemaRins(cursor.getInt(11) == 1);
            saude.setQualProblemaRins(cursor.getString(12));
            saude.setProblemaRespiratorios(cursor.getInt(13) == 1);
            saude.setQualProblemaRespiratorios(cursor.getString(14));
            saude.setHanseniase(cursor.getInt(15) == 1);
            saude.setTuberculose(cursor.getInt(16) == 1);
            saude.setCancer(cursor.getInt(17) == 1);
            saude.setInternacao(cursor.getInt(18) == 1);
            saude.setMotivoInternacao(cursor.getString(19));
            saude.setProblemaMental(cursor.getInt(20) == 1);
            saude.setTratamento(cursor.getInt(21) == 1);
            saude.setNivelSaude(cursor.getInt(22));
            saude.setPlantasMedicinais(cursor.getString(23));
        }

        db.close();

        return saude;
    }

    public void deletar(long idSaude){

        db = dbHelper.getWritableDatabase();
        db.delete("situacao_saude", "id_situacao = ?", new String[]{String.valueOf(idSaude)});
        db.close();

    }

    public void alterar(SituacaoSaude saude){

        db = dbHelper.getWritableDatabase();

        db.close();

    }

}
