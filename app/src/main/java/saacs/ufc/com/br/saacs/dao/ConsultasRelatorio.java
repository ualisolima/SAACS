package saacs.ufc.com.br.saacs.dao;

import android.content.Context;
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

    


}
