package saacs.ufc.com.br.saacs.model;

/**
 * Created by pc on 15/11/16.
 */

public class SituacaoSaude {

    private Long id;
    private Boolean gestante;
    private String nivelPeso;
    private Boolean fumante;
    private Boolean alcool;
    private Boolean drogas;
    private Boolean hipertenso;
    private Boolean diabete;
    private Boolean AVC_Derrame;
    private Boolean infarto;
    private Boolean doencaCardiaca;
    private String qualDoencaCardiaca;
    private Boolean problemaRins;
    private String qualProblemaRins;
    private Boolean problemaRespiratorio;
    private String qualProblemaRespiratorio;
    private Boolean hanseniase;
    private Boolean tuberculose;
    private Boolean cancer;
    private Boolean internacao;
    private String motivoInternacao;
    private Boolean problemaMental;
    private Boolean tratamento;
    private Boolean usaPlantas;
    private int nivelSaude;
    private String plantasMedicinais;
    private Boolean deficiencia;

    public Boolean isDeficiencia() {
        return deficiencia;
    }

    public void setDeficiencia(Boolean deficiencia) {
        this.deficiencia = deficiencia;
    }
    public Boolean isUsaPlantas() {
        return usaPlantas;
    }

    public Boolean isProblemaRespiratorio() {
        return problemaRespiratorio;
    }

    public void setProblemaRespiratorio(Boolean problemaRespiratorio) {
        this.problemaRespiratorio = problemaRespiratorio;
    }

    public String getQualProblemaRespiratorio() {
        return qualProblemaRespiratorio;
    }

    public void setQualProblemaRespiratorio(String qualProblemaRespiratorio) {
        this.qualProblemaRespiratorio = qualProblemaRespiratorio;
    }

    public void setUsaPlantas(Boolean usaPlantas) {
        this.usaPlantas = usaPlantas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isGestante() {
        return gestante;
    }

    public void setGestante(Boolean gestante) {
        this.gestante = gestante;
    }

    public String getNivelPeso() {
        return nivelPeso;
    }

    public void setNivelPeso(String nivelPeso) {
        this.nivelPeso = nivelPeso;
    }

    public Boolean isFumante() {
        return fumante;
    }

    public void setFumante(Boolean fumante) {
        this.fumante = fumante;
    }

    public Boolean isAlcool() {
        return alcool;
    }

    public void setAlcool(Boolean alcool) {
        this.alcool = alcool;
    }

    public Boolean isDrogas() {
        return drogas;
    }

    public void setDrogas(Boolean drogas) {
        this.drogas = drogas;
    }

    public Boolean isHipertenso() {
        return hipertenso;
    }

    public void setHipertenso(Boolean hipertenso) {
        this.hipertenso = hipertenso;
    }

    public Boolean isDiabete() {
        return diabete;
    }

    public void setDiabete(Boolean diabete) {
        this.diabete = diabete;
    }

    public Boolean isAVC_Derrame() {
        return AVC_Derrame;
    }

    public void setAVC_Derrame(Boolean AVC_Derrame) {
        this.AVC_Derrame = AVC_Derrame;
    }

    public Boolean isInfarto() {
        return infarto;
    }

    public void setInfarto(Boolean infarto) {
        this.infarto = infarto;
    }

    public Boolean isDoencaCardiaca() {
        return doencaCardiaca;
    }

    public void setDoencaCardiaca(Boolean doencaCardiaca) {
        this.doencaCardiaca = doencaCardiaca;
    }

    public String getQualDoencaCardiaca() {
        return qualDoencaCardiaca;
    }

    public void setQualDoencaCardiaca(String qualDoencaCardiaca) {
        qualDoencaCardiaca = qualDoencaCardiaca;
    }

    public Boolean isProblemaRins() {
        return problemaRins;
    }

    public void setProblemaRins(Boolean problemaRins) {
        this.problemaRins = problemaRins;
    }

    public String getQualProblemaRins() {
        return qualProblemaRins;
    }

    public void setQualProblemaRins(String qualProblemaRins) {
        qualProblemaRins = qualProblemaRins;
    }

    public Boolean isProblemaRespiratorios() {
        return problemaRespiratorio;
    }

    public void setProblemaRespiratorios(Boolean problemaRespiratorios) {
        this.problemaRespiratorio = problemaRespiratorios;
    }

    public String getQualProblemaRespiratorios() {
        return qualProblemaRespiratorio;
    }

    public void setQualProblemaRespiratorios(String qualProblemaRespiratorios) {
        qualProblemaRespiratorios = qualProblemaRespiratorios;
    }

    public Boolean isHanseniase() {
        return hanseniase;
    }

    public void setHanseniase(Boolean hanseniase) {
        this.hanseniase = hanseniase;
    }

    public Boolean isTuberculose() {
        return tuberculose;
    }

    public void setTuberculose(Boolean tuberculose) {
        this.tuberculose = tuberculose;
    }

    public Boolean isCancer() {
        return cancer;
    }

    public void setCancer(Boolean cancer) {
        this.cancer = cancer;
    }

    public Boolean isInternacao() {
        return internacao;
    }

    public void setInternacao(Boolean internacao) {
        this.internacao = internacao;
    }

    public String getMotivoInternacao() {
        return motivoInternacao;
    }

    public void setMotivoInternacao(String motivoInternacao) {
        this.motivoInternacao = motivoInternacao;
    }

    public Boolean isProblemaMental() {
        return problemaMental;
    }

    public void setProblemaMental(Boolean problemaMental) {
        this.problemaMental = problemaMental;
    }

    public Boolean isTratamento() {
        return tratamento;
    }

    public void setTratamento(Boolean tratamento) {
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