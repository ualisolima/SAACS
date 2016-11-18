package saacs.ufc.com.br.saacs.dao;

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
            "create table if not exists pessoa (" +
                    "    numSUS big int not null primaty key," +
                    "    nome text not null," +
                    "    dataNascimento text not null," +
                    "    numSUSResponsavel big int not null," +
                    "    sexo text not null," +
                    "    nacionalidade text," +
                    "    cidadeUFNatal text," +
                    "    telefone text not null," +
                    "    email text," +
                    "    responsavelFamiliar integer default 0," +
                    "    relacaoParentRF text," +
                    "    profissao text," +
                    "    escolaridade text," +
                    "    situacaoMercado text," +
                    "    deficiencia integer default 0," +
                    "    qualDeficiencia text," +
                    "    id_saude big int not null," +
                    "    FOREIGN KEY(id_saude) REFERENCES situacao_saude(id_situacao) ON DELETE RESTRICT ON UPDATE CASCADE" +
                    ");";

    private static final String DB_CREATE_SITUACAO_SAUDE =
            "create table if not exists situacao_saude (" +
                    "    id_situacao big int not null primaty key," +
                    "    gestante integer default 0," +
                    "    nivelPeso text," +
                    "    fumante integer default 0," +
                    "    alcool integer default 0," +
                    "    drogas integer default 0," +
                    "    hipertenso integer default 0," +
                    "    diabetes integer default 0," +
                    "    AVC_Derrame integer default 0," +
                    "    infarto integer default 0," +
                    "    doencaCardiaca integer default 0," +
                    "    problemaRins integer default 0," +
                    "    qualProblemaRins text," +
                    "    problemaRespiratorio integer default 0," +
                    "    qualProblemaRespiratorio text," +
                    "    hanseniase integer default 0," +
                    "    tuberculose integer default 0," +
                    "    cancer integer default 0," +
                    "    internacao integer default 0," +
                    "    motivoInternacao text," +
                    "    problemaMental integer default 0," +
                    "    tratamento integer default 0," +
                    "    nivelSaude integer," +
                    "    plantasMedicinais text" +
                    ");";


    //////////////////////****grupo familiar****/////////////
    private static final String TB_GRUPO_FAMILIAR =

            "create table if not exists grupo_familiar("+
                    "id big int not null primary key," +
                    "id_agente int not null"+
                    "endereco text"+
                    "contato text not null"+
                    "localizacao text not null"+
                    "condsMoradia text not null"+
                    "tipoDomicilio text not null"+
                    "energiaEletrica text not null" + //bool?
                    "saneamentoBasico text not null"+ //bool?
                    "destLixo text not null"+
                    "temAnimais text not null"+ //bool?
                    "animais text not null"+
                    "FOREIGN KEY(id) REFERENCES pessoa(numSUS)"+
                    ");";

///////////////////////////////////////////////////////////////////////

    private static final String DB_CREATE_GRUPO_FAMILIAR =
            "create table grupo_familiar ( );";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DB_CREATE_SITUACAO_SAUDE);
        sqLiteDatabase.execSQL(DB_CREATE_PESSOA);
//        sqLiteDatabase.execSQL(DB_CREATE_GRUPO_FAMILIAR);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // on upgrade drop older tables
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS pessoa");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS situacao_saude");
//        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS grupo_familiar");
        // create new tables
        onCreate(sqLiteDatabase);
    }


    ///////////////////////////////////////////////////////////////////////
    private static final String Acs="susNumber bigint" +
            "password text not null" +
            "nome text not null" +
            "FOREIGN KEY(susNumber) REFERENCES TB_GRUPO_FAMILIAR(id_agente) ";


}
