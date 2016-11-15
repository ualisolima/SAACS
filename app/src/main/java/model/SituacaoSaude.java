package model;

/**
 * Created by pc on 15/11/16.
 */

public class SituacaoSaude {

    private boolean gestante;
    private String nivelPeso;
    private boolean fumante;
    private boolean alcool;
    private boolean drogas;
    private boolean hipertenso;
    private boolean diabete;
    private boolean AVC_Derrame;
    private boolean infarto;
    private boolean doencaCardiaca;
    private String QualDoencaCardiaca;
    private boolean problemaRins;
    private String QualProblemaRins;
    private boolean problemaRespiratorios;
    private String QualProblemaRespiratorios;
    private boolean hanseniase;
    private boolean tuberculose;
    private boolean cancer;
    private boolean internacao;
    private String motivoInternacao;
    private boolean problemaMental;
    private boolean tratamento;
    private int nivelSaude;
    private String plantasMedicinais;

}

//
//    Situações de Saúde Gerais:
//        Se Feminino - Está Gestante? Sim|Não
//        Sobre o peso, como você está? Abaixo do peso|PesoAdequado|Acima do Peso
//        Fumante? Sim|Não
//        Bebidas Alcólicas? Sim|Não
//        Usa outras Drogas? Sim|Não
//        Tem Hipertensão Arterial? Sim|Não
//        Tem Diabetes? Sim|Não
//        Teve AVC/Derrame? Sim|Não
//        Teve infarto? Sim|Não
//        Tem doença cardiáca? Sim|Não -- Se sim Qual?
//        Tem ou teve Problemas nos rins? Sim|Não -- Se sim Qual?
//        Tem doença Respiratória? Sim|Não -- Se sim Qual?
//        Está com Hanseniáse? Sim|Não
//        Está com Tuberculose? Sim|Não
//        Tem ou teve Câncer? Sim|Não
//        Teve alguma internação nos últimos 12 meses? Sim|Não -- Se sim Qual causa?
//        Problema Mental? Sim|Não -- Se sim Faz Tratamento com PSIQUIATRA? Sim|Não
//        Estado de Saúde atual? Saudável|Acamado Faixa de 0-5
//        Usa plantas Medicinais? Sim|Não -- Se sim Qual?