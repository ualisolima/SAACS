package saacs.ufc.com.br.saacs.model;

import java.util.ArrayList;

/**
 * Created by pc on 15/11/16.
 */

public class GrupoFamiliar {

    private long id;
    private ArrayList<Pessoa> pessoas;
    private String contato;
    private String endereco;
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

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
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
