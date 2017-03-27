package codes.wise.applivros.modelos;

import com.orm.SugarRecord;
import com.orm.dsl.Ignore;

import java.util.List;

/**
 * Created by rogermac on 26/03/17.
 */

public class Autor extends SugarRecord{

    private String nome;
    private String pais;

    @Ignore
    private List<Livro> livros;

    public Autor(){

    }

    public Autor(String nome, String pais) {
        this.nome = nome;
        this.pais = pais;
    }

    public String getNome() {
        return nome;
    }

    public String getPais() {
        return pais;
    }
}
