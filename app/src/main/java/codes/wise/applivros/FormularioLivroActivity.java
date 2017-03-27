package codes.wise.applivros;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import codes.wise.applivros.modelos.Autor;
import codes.wise.applivros.modelos.Livro;

public class FormularioLivroActivity extends AppCompatActivity {

    private EditText edLivroTitulo;
    private EditText edLivroAno;
    private AutoCompleteTextView acLivroAutor;
    Autor autorSelecionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_livro);

        edLivroTitulo = (EditText) findViewById(R.id.ed_livro_titulo);
        edLivroAno = (EditText) findViewById(R.id.ed_livro_ano);

        List<Autor> autores = Autor.listAll(Autor.class);

    }

    public void salvarLivro(View view) {

        String titulo = edLivroTitulo.getText().toString();
        int ano = Integer.valueOf(edLivroAno.getText().toString());

        Autor autor1 = Autor.findById(Autor.class, 1);

        Livro livro = new Livro(titulo, ano, autor1);
        livro.save();

        finish();
    }
}
