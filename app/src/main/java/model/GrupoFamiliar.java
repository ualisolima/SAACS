package model;

import java.util.ArrayList;

/**
 * Created by pc on 15/11/16.
 */

public class GrupoFamiliar {

    private long id;
    ArrayList<Pessoa> pessoas;
    ArrayList<String> contatos;
    private String endereco;
    private String localizacao;
    private String condsMoradia;
    private String tipoDomicilio;
    private boolean energiaEletrica;
    private boolean saneamentoBasico;
    private String destLixo;
    private boolean temAnimais;
    private String animais;


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
