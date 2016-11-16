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


    /////////////////******PESSOA*****////////////////////////
//    private static final String TB_PESSOA = "Pessoa";
//
//    private static final String nome = "nome";
//    private static final String numSUS = "numSUS"; // id da pessoa
//    private static final String dataNascimento="dataNascimento";
//    private static final String numSUSResponsavel = "numSUSResponsavel";
//    private static final String sexo = "sexo";
//    private static final String nacionalidade ="nacionalidade";
//    private static final String cidadeUFNatal= "cidadeUFNatal";
//    private static final String telefone= "telefone";
//    private static final String email= "email";
//    //Informações Sociodemográficas
//    private static final String responsavelFamiliar="responsavelFamiliar";
//    private static final String relacaoParentRF="relacaoParentRF";
//    private static final String profissao="profissao";
//    private static final String escolaridade="escolaridade";
//    private static final String situacaoMercado="situacaoMercado";
//    private static final String deficiencia="deficiencia";
//    private static final String qualDeficiencia="qualDeficiencia";
//    // Saúde em geral da pessoas chave estrangeira da tabela Situacao Saude
//    private static final  String saude = "saude";


    /////////////////////////////////////////////////////////////////

    /////////////****SituacaoSaude****//////////////
    private static final String TB_SITUACAO_SAUDE = "Situacao_Saude";
    ////////////////////////////////////////////////////////
//
//    private static final String id_situacao = "id_situacao";
//    private static final String gestante = "gestante";
//    private static final String nivelPeso = "nivelPeso";
//    private static final String fumante = "fumante";
//    private static final String alcool = "alcool";
//    private static final String drogas = "drogas";
//    private static final String hipertenso = "hipertenso";
//    private static final String diabete ="diabete";
//    private static final String AVC_Derrame = "AVC_Derrame";
//    private static final String infarto = "infarto";
//    private static final String doencaCardiaca ="doencaCardiaca";
//    private static final String qualDoencaCardiaca = "qualDoencaCardiaca";
//    private static final String problemaRins = "problemaRins";
//    private static final String qualProblemaRins = "qualProblemaRins";
//    private static final String problemaRespiratorios = "problemaRespiratorios";
//    private static final String qualProblemaRespiratorios = "qualProblemaRespiratorios";
//    private static final String hanseniase = "hanseniase";
//    private static final String tuberculose = "tuberculose";
//    private static final String cancer = "cancer";
//    private static final String internacao = "internacao";
//    private static final String motivoInternacao = "motivoInternacao";
//    private static final String problemaMental = "problemaMental";
//    private static final String tratamento = "tratamento";
//    private static final String nivelSaude = "nivelSaude";
//    private static final String plantasMedicinais = "plantasMedicinais";
    /////////////////////////////////////////////////////////////////////

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
                    "    FOREIGN KEY(id_saude) REFERENCES situacao_saude(id_situacao)" +
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
    private static final String TB_GRUPO_FAMILIAR = "Grupo_Familiar";
    /////////////////////////////////////////////////////////////
    private static final String id = "id";
    private static final String endereco ="endereco";
    private static final String contato ="contato";
    private static final String localizacao = "localizacao";
    private static final String condsMoradia="condsMoradia";
    private static final String tipoDomicilio = "tipoDomicilio";
    private static final String energiaEletrica = "energiaEletrica";
    private static final String saneamentoBasico = "saneamentoBasico";
    private static final String destLixo = "destLixo";
    private static final String temAnimais = "temAnimais";
    private static final String animais = "animais";

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
}
