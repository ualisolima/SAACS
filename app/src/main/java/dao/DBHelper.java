package dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by pc on 15/11/16.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "saacs.db";
    private static final int DB_VERSION = 1;

    // criar tabelas
    private static final String DB_CREATE_PESSOA =
            "create table pessoa ( );";

    private static final String DB_CREATE_SSAUDE =
            "create table ssaude ( );";

    private static final String DB_CREATE_GFAMILIAR =
            "create table gfamiliar ( );";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
//        sqLiteDatabase.execSQL(DB_CREATE_PESSOA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
