package codes.wise.applivros.modelos;

import com.orm.SugarRecord;

/**
 * Created by rogermac on 26/03/17.
 */

public class Livro extends SugarRecord{

    private String titulo;
    private int ano;
    private Autor autor;

    public Livro(){

    }

    public Livro(String titulo, int ano, Autor autor) {
        this.titulo = titulo;
        this.ano = ano;
        this.autor = autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getAno() {
        return ano;
    }
}

