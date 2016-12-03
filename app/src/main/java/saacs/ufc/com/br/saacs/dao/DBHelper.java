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
    private static final String TB_CREATE_PESSOA =
            "create table if not exists pessoa (" +
                    "    numSUS big int not null primary key," +
                    "    nome text not null," +
                    "    dataNascimento text not null," +
                    "    numeroNis big int, " +
                    "    sexo text not null," +
                    "    etnia text, " +
                    "    nacionalidade text," +
                    "    paisDeOrigem text, " +
                    "    cidadeNatal text," +
                    "    estado text, "+
                    "    telefone text not null," +
                    "    email text," +
                    "    nomeDaMae text, "+
                    "    responsavelFamiliar integer default 0," +
                    "    relacaoParentRF text," +
                    "    profissao text," +
                    "    escolaridade text," +
                    "    situacaoMercado text," +
                    "    id_saude big int not null," +
                    "    FOREIGN KEY(id_saude) REFERENCES situacao_saude(id_situacao) ON DELETE RESTRICT ON UPDATE CASCADE" +
                    ");";


    private static final String TB_CREATE_SITUACAO_SAUDE =
            "create table if not exists situacao_saude (" +
                    "    id_situacao big int not null primary key," +
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


    private static final String TB_CREATE_GRUPO_FAMILIAR =
            "create table if not exists grupo_familiar ("+
                    "id_responsavel big int not null primary key," +
                    "id_agente int not null,"+
                    "tipoLogradouro text,"+
                    "logradouro text,"+
                    "numeroCasa text,"+
                    "municipio text,"+
                    "uf text,"+
                    "cep text,"+
                    "phone text,"+
                    "bairro text,"+
                    "localizacao text not null,"+
                    "condsMoradia text ,"+
                    "tipoDomicilio text ,"+
                    "energiaEletrica integer default 0," + //bool?
                    "saneamentoBasico integer default 0,"+ //bool?
                    "destLixo text ,"+
                    "temAnimais integer default 0,"+ //bool?
                    "animais text,"+
                    "FOREIGN KEY(id_responsavel) REFERENCES pessoa(numSUS)," +
                    "FOREIGN KEY (id_agente)  REFERENCES acs(susNumber)"+
                    ");";

    private static final String TB_CREATE_GRUPO_FAMILIAR_PESSOA =
            "create table if not exists grupo_familiar_pessoa ("+
                    "id big int auto_increment not null primary key," +
                    "id_grupo_familiar big int not null,"+
                    "id_pessoa big int not null,"+
                    "FOREIGN KEY(id_pessoa) REFERENCES pessoa(numSUS)," +
                    "FOREIGN KEY (id_grupo_familiar)  REFERENCES grupo_familiar(id_responsavel)"+
                    ");";


    private static final String TB_CREATE_ACS=
            "create table if not exists Acs (" +
                    "susNumber bigint primary key," +
                    "password text not null," +
                    "nome text not null" +
            "); ";




    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TB_CREATE_SITUACAO_SAUDE);
        sqLiteDatabase.execSQL(TB_CREATE_PESSOA);
        sqLiteDatabase.execSQL(TB_CREATE_GRUPO_FAMILIAR);
        sqLiteDatabase.execSQL(TB_CREATE_GRUPO_FAMILIAR_PESSOA);
        sqLiteDatabase.execSQL(TB_CREATE_ACS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // on upgrade drop older tables
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS pessoa");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS situacao_saude");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS grupo_familiar");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS grupo_familiar_pessoa");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Acs");
        // create new tables
        onCreate(sqLiteDatabase);
    }

}
