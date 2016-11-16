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
    private static final String TB_PESSOA = "Pessoa";

    private static final String nome = "nome";
    private static final String numSUS = "numSUS"; // id da pessoa
    private static final String dataNascimento="dataNascimento";
    private static final String numSUSResponsavel = "numSUSResponsavel";
    private static final String sexo = "sexo";
    private static final String nacionalidade ="nacionalidade";
    private static final String cidadeUFNatal= "cidadeUFNatal";
    private static final String telefone= "telefone";
    private static final String email= "email";
    //Informações Sociodemográficas
    private static final String responsavelFamiliar="responsavelFamiliar";
    private static final String relacaoParentRF="relacaoParentRF";
    private static final String profissao="profissao";
    private static final String escolaridade="escolaridade";
    private static final String situacaoMercado="situacaoMercado";
    private static final String deficiencia="deficiencia";
    private static final String qualDeficiencia="qualDeficiencia";
    // Saúde em geral da pessoas chave estrangeira da tabela Situacao Saude
    private static final  String saude = "saude";


    /////////////////////////////////////////////////////////////////

    /////////////****SituacaoSaude****//////////////
    private static final String TB_SITUACAO_SAUDE = "Situacao_Saude";
    ////////////////////////////////////////////////////////

    private static final String id_situacao = "id_situacao";
    private static final String gestante = "gestante";
    private static final String nivelPeso = "nivelPeso";
    private static final String fumante = "fumante";
    private static final String alcool = "alcool";
    private static final String drogas = "drogas";
    private static final String hipertenso = "hipertenso";
    private static final String diabete ="diabete";
    private static final String AVC_Derrame = "AVC_Derrame";
    private static final String infarto = "infarto";
    private static final String doencaCardiaca ="doencaCardiaca";
    private static final String qualDoencaCardiaca = "qualDoencaCardiaca";
    private static final String problemaRins = "problemaRins";
    private static final String qualProblemaRins = "qualProblemaRins";
    private static final String problemaRespiratorios = "problemaRespiratorios";
    private static final String qualProblemaRespiratorios = "qualProblemaRespiratorios";
    private static final String hanseniase = "hanseniase";
    private static final String tuberculose = "tuberculose";
    private static final String cancer = "cancer";
    private static final String internacao = "internacao";
    private static final String motivoInternacao = "motivoInternacao";
    private static final String problemaMental = "problemaMental";
    private static final String tratamento = "tratamento";
    private static final String nivelSaude = "nivelSaude";
    private static final String plantasMedicinais = "plantasMedicinais";
    /////////////////////////////////////////////////////////////////////

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
