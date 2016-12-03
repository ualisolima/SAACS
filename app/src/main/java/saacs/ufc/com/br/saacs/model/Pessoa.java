package saacs.ufc.com.br.saacs.model;

/**
 * Created by pc on 15/11/16.
 */

public class Pessoa {
    // Identificação
    private String nome;
    private Long NumSUS;
    private String dataNascimento;
//    private Long numSUSResponsavel;
    private String sexo;
    private String nacionalidade;
    private String telefone;
    private String email;
    private String etnia;
    private String paisDeOrigem;
    private Long numeroNis;


    // Informações Sociodemográficas:
    private boolean responsavelFamiliar;
    private String relacaoParentRF;
    private String profissao;
    private String escolaridade;
    private String nomedaMae;
    private String cidadeNatal;
    private String estado;
    private String situacaoMercado;
    private boolean deficiencia;
    private String qualDeficiencia;

    // Situação de Saúde Geral
    private SituacaoSaude saude;

    public Long getNumeroNis() {
        return numeroNis;
    }
    public String getPaisDeOrigem() {
        return paisDeOrigem;
    }

    public void setPaisDeOrigem(String paisDeOrigem) {
        this.paisDeOrigem = paisDeOrigem;
    }

    public void setNumeroNis(Long numeroNis) {
        this.numeroNis = numeroNis;
    }

    public Pessoa() {
        saude = new SituacaoSaude();
    }

    public String getEtnia() {
        return etnia;
    }

    public String getNomedaMae() {
        return nomedaMae;
    }

    public void setNomedaMae(String nomedaMae) {
        this.nomedaMae = nomedaMae;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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

    public Long getNumSUS() {
        return NumSUS;
    }

    public void setNumSUS(Long numSUS) {
        NumSUS = numSUS;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

//    public Long getNumSUSResponsavel() {
//        return numSUSResponsavel;
//    }

//    public void setNumSUSResponsavel(Long numSUSResponsavel) {
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

    public String getCidadeNatal() {
        return cidadeNatal;
    }

    public void setCidadeNatal(String cidadeNatal) {
        this.cidadeNatal = cidadeNatal;
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
