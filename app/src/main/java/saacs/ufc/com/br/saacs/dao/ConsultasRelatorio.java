package saacs.ufc.com.br.saacs.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by pc on 11/12/16.
 */

public class ConsultasRelatorio {

    private SQLiteDatabase db;
    private DBHelper dbHelper;

    public ConsultasRelatorio(Context context){
        this.dbHelper = new DBHelper(context);
    }

    public int qtdPessoaPorSexo(String sexo){
        int qtd = 0;
        String query = "SELECT COUNT(*) FROM pessoa where sexo = '"+sexo+"'";
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst())
            qtd = cursor.getInt(0);
        db.close();
        return qtd;
    }

    public int qtdPessoaSituacao(String coluna){
        int qtd = 0;
        String query = "SELECT COUNT(*) FROM pessoa p, situacao_saude s where p.id_saude = s.id_situacao and s."+coluna+" = 1";
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst())
            qtd = cursor.getInt(0);
        db.close();
        return qtd;
    }


}
