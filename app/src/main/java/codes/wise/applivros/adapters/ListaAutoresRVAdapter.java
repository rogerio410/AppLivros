package codes.wise.applivros.adapters;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
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


    private final Context context;
    private final List<Autor> autores;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_autor, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

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
                PopupMenu pop = new PopupMenu(context, view);
                MenuInflater inflater = pop.getMenuInflater();

                inflater.inflate(R.menu.popup_menu_lista_livros, pop.getMenu());

                pop.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {

                        if (menuItem.getItemId() == R.id.item_remover_livro){
                            autor.delete();
                            autores.remove(position);
                            notifyItemRemoved(position);
                            notifyItemRangeChanged(position, ListaAutoresRVAdapter.this.autores.size());
                            Toast.makeText(context, R.string.removido, Toast.LENGTH_SHORT).show();
                        }

                        return false;
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

    public ListaAutoresRVAdapter(Context context, List<Autor> autores){

        this.context = context;
        this.autores = autores;

    }


}
