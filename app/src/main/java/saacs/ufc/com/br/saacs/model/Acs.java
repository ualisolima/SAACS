package saacs.ufc.com.br.saacs.model;

import java.util.ArrayList;

/**
 * Created by wallinsondeivesbatistalima on 11/17/16.
 */

public class Acs {
    private long susNumber;
    private String password;
    private ArrayList<GrupoFamiliar> grupos;
    private String nome;

    public long getSusNumber() {
        return susNumber;
    }

    public void setSusNumber(long susNumber) {
        this.susNumber = susNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<GrupoFamiliar> getGrupos() {
        return grupos;
    }

    public void setGrupos(ArrayList<GrupoFamiliar> grupos) {
        this.grupos = grupos;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
