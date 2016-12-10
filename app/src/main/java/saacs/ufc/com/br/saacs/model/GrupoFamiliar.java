package saacs.ufc.com.br.saacs.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pc on 15/11/16.
 */

public class GrupoFamiliar implements Serializable{

    private Long id;
    private Long id_agente;
    private List<Pessoa> pessoas;
    private String contato;
    private String tipoLogradouro;
    private String logradouro;
    private String numCasa;
    private String cep;
    private String municipio;
    private String bairro;
    private String uF;
    private String phone;
    private String localizacao;
    private String condsMoradia;
    private String tipoDomicilio;
    private Boolean energiaEletrica;
    private Boolean saneamentoBasico;
    private String destLixo;
    private Boolean temAnimais;
    private String animais;
    private Boolean coletaLixo;

    public Boolean getEnergiaEletrica() {
        return energiaEletrica;
    }

    public Boolean getSaneamentoBasico() {
        return saneamentoBasico;
    }

    public Boolean getColetaLixo() {
        return coletaLixo;
    }

    public void setColetaLixo(Boolean coletaLixo) {
        this.coletaLixo = coletaLixo;
    }

    public GrupoFamiliar() {
        pessoas = new ArrayList<Pessoa>();
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public String getTipoLogradouro() {
        return tipoLogradouro;
    }

    public void setTipoLogradouro(String tipoLogradouro) {
        this.tipoLogradouro = tipoLogradouro;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumCasa() {
        return numCasa;
    }

    public void setNumCasa(String numCasa) {
        this.numCasa = numCasa;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getuF() {
        return uF;
    }

    public void setuF(String uF) {
        this.uF = uF;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Pessoa> getPessoas() {
        return pessoas;
    }

    public void setPessoas(List<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }

    public String getContatos() {
        return contato;
    }

    public void setContatos(String contato) {
        this.contato = contato;
    }

    public Long getId_agente() {
        return id_agente;
    }

    public void setId_agente(Long id_agente) {
        this.id_agente = id_agente;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public String getCondsMoradia() {
        return condsMoradia;
    }

    public void setCondsMoradia(String condsMoradia) {
        this.condsMoradia = condsMoradia;
    }

    public String getTipoDomicilio() {
        return tipoDomicilio;
    }

    public void setTipoDomicilio(String tipoDomicilio) {
        this.tipoDomicilio = tipoDomicilio;
    }

    public Boolean isEnergiaEletrica() {
        return energiaEletrica;
    }

    public void setEnergiaEletrica(Boolean energiaEletrica) {
        this.energiaEletrica = energiaEletrica;
    }

    public Boolean isSaneamentoBasico() {
        return saneamentoBasico;
    }

    public void setSaneamentoBasico(Boolean saneamentoBasico) {
        this.saneamentoBasico = saneamentoBasico;
    }

    public String getDestLixo() {
        return destLixo;
    }

    public void setDestLixo(String destLixo) {
        this.destLixo = destLixo;
    }

    public Boolean isTemAnimais() {
        return temAnimais;
    }

    public void setTemAnimais(Boolean temAnimais) {
        this.temAnimais = temAnimais;
    }

    public String getAnimais() {
        return animais;
    }

    public void setAnimais(String animais) {
        this.animais = animais;
    }

}


//    Cadastro grupo Familiar:
//
//        Lista de pessoas
//        Telefones para contato
//        Endereço - Rua, Número e Bairro, Cidade, Estado
//        Localização - Urbana| Rural
//        Condições de Moradia - Própria|Financiado|Alugado|Cedido|Ocupação|Outra
//        Tipo de Domicílio - Casa|Apartamento|Cômodo|Outro
//        Energia Elétrica?
//        Saneamento Básico?
//        Destino do Lixo?
//        Animais no Domicílio - Sim|Não -- Se sim Qual|Quais?
