package model;

/**
 * Created by pc on 15/11/16.
 */

public class SituacaoSaude {

    private long id;
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
    private String qualDoencaCardiaca;
    private boolean problemaRins;
    private String qualProblemaRins;
    private boolean problemaRespiratorio;
    private String qualProblemaRespiratorio;
    private boolean hanseniase;
    private boolean tuberculose;
    private boolean cancer;
    private boolean internacao;
    private String motivoInternacao;
    private boolean problemaMental;
    private boolean tratamento;
    private int nivelSaude;
    private String plantasMedicinais;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isGestante() {
        return gestante;
    }

    public void setGestante(boolean gestante) {
        this.gestante = gestante;
    }

    public String getNivelPeso() {
        return nivelPeso;
    }

    public void setNivelPeso(String nivelPeso) {
        this.nivelPeso = nivelPeso;
    }

    public boolean isFumante() {
        return fumante;
    }

    public void setFumante(boolean fumante) {
        this.fumante = fumante;
    }

    public boolean isAlcool() {
        return alcool;
    }

    public void setAlcool(boolean alcool) {
        this.alcool = alcool;
    }

    public boolean isDrogas() {
        return drogas;
    }

    public void setDrogas(boolean drogas) {
        this.drogas = drogas;
    }

    public boolean isHipertenso() {
        return hipertenso;
    }

    public void setHipertenso(boolean hipertenso) {
        this.hipertenso = hipertenso;
    }

    public boolean isDiabete() {
        return diabete;
    }

    public void setDiabete(boolean diabete) {
        this.diabete = diabete;
    }

    public boolean isAVC_Derrame() {
        return AVC_Derrame;
    }

    public void setAVC_Derrame(boolean AVC_Derrame) {
        this.AVC_Derrame = AVC_Derrame;
    }

    public boolean isInfarto() {
        return infarto;
    }

    public void setInfarto(boolean infarto) {
        this.infarto = infarto;
    }

    public boolean isDoencaCardiaca() {
        return doencaCardiaca;
    }

    public void setDoencaCardiaca(boolean doencaCardiaca) {
        this.doencaCardiaca = doencaCardiaca;
    }

    public String getQualDoencaCardiaca() {
        return qualDoencaCardiaca;
    }

    public void setQualDoencaCardiaca(String qualDoencaCardiaca) {
        qualDoencaCardiaca = qualDoencaCardiaca;
    }

    public boolean isProblemaRins() {
        return problemaRins;
    }

    public void setProblemaRins(boolean problemaRins) {
        this.problemaRins = problemaRins;
    }

    public String getQualProblemaRins() {
        return qualProblemaRins;
    }

    public void setQualProblemaRins(String qualProblemaRins) {
        qualProblemaRins = qualProblemaRins;
    }

    public boolean isProblemaRespiratorios() {
        return problemaRespiratorio;
    }

    public void setProblemaRespiratorios(boolean problemaRespiratorios) {
        this.problemaRespiratorio = problemaRespiratorios;
    }

    public String getQualProblemaRespiratorios() {
        return qualProblemaRespiratorio;
    }

    public void setQualProblemaRespiratorios(String qualProblemaRespiratorios) {
        qualProblemaRespiratorios = qualProblemaRespiratorios;
    }

    public boolean isHanseniase() {
        return hanseniase;
    }

    public void setHanseniase(boolean hanseniase) {
        this.hanseniase = hanseniase;
    }

    public boolean isTuberculose() {
        return tuberculose;
    }

    public void setTuberculose(boolean tuberculose) {
        this.tuberculose = tuberculose;
    }

    public boolean isCancer() {
        return cancer;
    }

    public void setCancer(boolean cancer) {
        this.cancer = cancer;
    }

    public boolean isInternacao() {
        return internacao;
    }

    public void setInternacao(boolean internacao) {
        this.internacao = internacao;
    }

    public String getMotivoInternacao() {
        return motivoInternacao;
    }

    public void setMotivoInternacao(String motivoInternacao) {
        this.motivoInternacao = motivoInternacao;
    }

    public boolean isProblemaMental() {
        return problemaMental;
    }

    public void setProblemaMental(boolean problemaMental) {
        this.problemaMental = problemaMental;
    }

    public boolean isTratamento() {
        return tratamento;
    }

    public void setTratamento(boolean tratamento) {
        this.tratamento = tratamento;
    }

    public int getNivelSaude() {
        return nivelSaude;
    }

    public void setNivelSaude(int nivelSaude) {
        this.nivelSaude = nivelSaude;
    }

    public String getPlantasMedicinais() {
        return plantasMedicinais;
    }

    public void setPlantasMedicinais(String plantasMedicinais) {
        this.plantasMedicinais = plantasMedicinais;
    }
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