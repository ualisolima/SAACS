package model;

import java.util.Date;

/**
 * Created by pc on 15/11/16.
 */

public class Pessoa {
    // Identificação
    private String nome;
    private long NumSUS;
    private Date dataNascimento;
    private Pessoa responsavel;
    private char sexo;
    private String nacionalidade;
    private String cidadeUFNatal;
    private String telefone;
    private String email;

    // Informações Sociodemográficas:
    private boolean responsavelFamiliar;
    private String relacaoParentRF;
    private String profissao;
    private String Escolaridade;
    private String situacaoMercado;
    private boolean deficiencia;
    private String qualDeficiencia;

    // Situação de Saúde Geral
    private SituacaoSaude saude;



}
