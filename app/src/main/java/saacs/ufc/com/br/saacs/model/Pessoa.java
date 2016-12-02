package saacs.ufc.com.br.saacs.model;

/**
 * Created by pc on 15/11/16.
 */

public class Pessoa {
    // Identificação
    private String nome;
    private long NumSUS;
    private String dataNascimento;
//    private long numSUSResponsavel;
    private String sexo;
    private String nacionalidade;
    private String cidadeUFNatal;
    private String telefone;
    private String email;
    private String etnia;

    // Informações Sociodemográficas:
    private boolean responsavelFamiliar;
    private String relacaoParentRF;
    private String profissao;
    private String escolaridade;
    private String situacaoMercado;
    private boolean deficiencia;
    private String qualDeficiencia;

    // Situação de Saúde Geral
    private SituacaoSaude saude;

    public Pessoa() {
        saude = new SituacaoSaude();
    }

    public String getEtnia() {
        return etnia;
    }

    public void setEtnia(String etnia) {
        this.etnia = etnia;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public long getNumSUS() {
        return NumSUS;
    }

    public void setNumSUS(long numSUS) {
        NumSUS = numSUS;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

//    public long getNumSUSResponsavel() {
//        return numSUSResponsavel;
//    }

//    public void setNumSUSResponsavel(long numSUSResponsavel) {
//        numSUSResponsavel = numSUSResponsavel;
//    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public String getCidadeUFNatal() {
        return cidadeUFNatal;
    }

    public void setCidadeUFNatal(String cidadeUFNatal) {
        this.cidadeUFNatal = cidadeUFNatal;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isResponsavelFamiliar() {
        return responsavelFamiliar;
    }

    public void setResponsavelFamiliar(boolean responsavelFamiliar) {
        this.responsavelFamiliar = responsavelFamiliar;
    }

    public String getRelacaoParentRF() {
        return relacaoParentRF;
    }

    public void setRelacaoParentRF(String relacaoParentRF) {
        this.relacaoParentRF = relacaoParentRF;
    }

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    public String getEscolaridade() {
        return escolaridade;
    }

    public void setEscolaridade(String escolaridade) {
        escolaridade = escolaridade;
    }

    public String getSituacaoMercado() {
        return situacaoMercado;
    }

    public void setSituacaoMercado(String situacaoMercado) {
        this.situacaoMercado = situacaoMercado;
    }

    public boolean isDeficiencia() {
        return deficiencia;
    }

    public void setDeficiencia(boolean deficiencia) {
        this.deficiencia = deficiencia;
    }

    public String getQualDeficiencia() {
        return qualDeficiencia;
    }

    public void setQualDeficiencia(String qualDeficiencia) {
        this.qualDeficiencia = qualDeficiencia;
    }

    public SituacaoSaude getSaude() {
        return saude;
    }

    public void setSaude(SituacaoSaude saude) {
        this.saude = saude;
    }
}
