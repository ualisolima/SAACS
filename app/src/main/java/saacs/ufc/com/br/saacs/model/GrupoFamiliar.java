package saacs.ufc.com.br.saacs.model;

import java.util.ArrayList;

/**
 * Created by pc on 15/11/16.
 */

public class GrupoFamiliar {

    private long id;
    private long id_agente;
    private ArrayList<Pessoa> pessoas;
    private String contato;
    //private String endereco;
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
    private boolean energiaEletrica;
    private boolean saneamentoBasico;
    private String destLixo;
    private boolean temAnimais;
    private String animais;

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ArrayList<Pessoa> getPessoas() {
        return pessoas;
    }

    public void setPessoas(ArrayList<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }

    public String getContatos() {
        return contato;
    }

    public void setContatos(String contato) {
        this.contato = contato;
    }

    //public String getEndereco() {
   //     return endereco;
   // }

   // public void setEndereco(String endereco) {
   //     this.endereco = endereco;
   // }

    public long getId_agente() {
        return id_agente;
    }

    public void setId_agente(long id_agente) {
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

    public boolean isEnergiaEletrica() {
        return energiaEletrica;
    }

    public void setEnergiaEletrica(boolean energiaEletrica) {
        this.energiaEletrica = energiaEletrica;
    }

    public boolean isSaneamentoBasico() {
        return saneamentoBasico;
    }

    public void setSaneamentoBasico(boolean saneamentoBasico) {
        this.saneamentoBasico = saneamentoBasico;
    }

    public String getDestLixo() {
        return destLixo;
    }

    public void setDestLixo(String destLixo) {
        this.destLixo = destLixo;
    }

    public boolean isTemAnimais() {
        return temAnimais;
    }

    public void setTemAnimais(boolean temAnimais) {
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
