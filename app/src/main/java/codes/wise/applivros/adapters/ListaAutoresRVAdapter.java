package codes.wise.applivros.adapters;

import android.app.Activity;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import codes.wise.applivros.R;
import codes.wise.applivros.modelos.Autor;

/**
 * Created by rogermac on 26/03/17.
 */

public class ListaAutoresRVAdapter extends RecyclerView.Adapter<ListaAutoresRVAdapter.ViewHolder>{
    /** Usar a uma activity como contexto a fim de mostrar o diálogo de edição
     * @see #editarAutor(Autor, int)}
     **/
    private final Activity activity;
    private final List<Autor> autores;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_autor, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final Autor autor = this.autores.get(position);

        holder.tvAutorNome.setText(autor.getNome());
        holder.tvAutorPais.setText(autor.getPais());

        configurarClicks(holder.itemView, autor, position);

    }

    private void configurarClicks(View itemView, Autor autor, int position) {

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Autor " + autor.getNome(), Snackbar.LENGTH_LONG).show();
            }
        });

        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                PopupMenu pop = new PopupMenu(activity, view);
                MenuInflater inflater = pop.getMenuInflater();

                inflater.inflate(R.menu.popup_menu_lista, pop.getMenu());

                pop.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.item_remover: removerAutor(autor, position); break;
                            case R.id.item_editar:   editarAutor(autor, position); break;
                        }
                        return true;
                    }
                });

                pop.show();

                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return autores.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        protected TextView tvAutorNome;
        protected TextView tvAutorPais;

        public ViewHolder(View itemView) {
            super(itemView);

            tvAutorNome = itemView.findViewById(R.id.tv_autor_nome);
            tvAutorPais = itemView.findViewById(R.id.tv_autor_pais);

        }

    }

    public ListaAutoresRVAdapter(Activity context, List<Autor> autores){

        this.activity = context;
        this.autores = autores;

    }

    private void removerAutor(Autor autor, int position) {
        autor.delete();
        autores.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, getItemCount());
        Toast.makeText(activity, R.string.removido, Toast.LENGTH_SHORT).show();
    }

    private void editarAutor(Autor autor, int posicao) {
        LayoutInflater inflater = activity.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_editar_autor, null);

        // Estes EditTexts precisam ser setados antes de mostrar o diálogo

        EditText editAutorNome = dialogView.findViewById(R.id.edit_autor_nome);
        EditText editAutorPais = dialogView.findViewById(R.id.edit_autor_pais);
        editAutorNome.setText(autor.getNome());
        editAutorPais.setText(autor.getPais());

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Editar");
        builder.setView(dialogView)
                .setPositiveButton("OK", (dialog, id) -> {
                    // TODO: 17/01/2018 Atualizar o BD
                    Autor novoAutor = new Autor(getText(editAutorNome), getText(editAutorPais));
                    autores.set(posicao, novoAutor);
                    notifyItemChanged(posicao);
                })
                .setNegativeButton("Cancelar", (dialog, which) -> {

                });
        builder.create();
        builder.show();
    }

    /** Obter o valor de um EditText
     *
     * @param editText o EditText para obter o valor
     * @return a String correnspondente ao {@link EditText#getText()} atual se não for nulo, "" caso contrário
     **/
    private String getText(EditText editText) {
        if (editText.getText() != null)
            return editText.getText().toString();
        return "";
    }

}
