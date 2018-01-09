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
import codes.wise.applivros.modelos.Livro;

/**
 * Created by rogermac on 26/03/17.
 */

public class ListaLivrosRVAdapter extends RecyclerView.Adapter<ListaLivrosRVAdapter.ViewHolder>{


    private final Context context;
    private final List<Livro> livros;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_livro, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        final Livro livro = this.livros.get(position);

        holder.tvLivroTitulo.setText(livro.getTitulo());
        holder.tvLivroAno.setText(""+livro.getAno());
        holder.tvLivroAutorNome.setText(livro.getAutor().getNome());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Livro: " + livro.getTitulo(), Snackbar.LENGTH_LONG).show();
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                PopupMenu pop = new PopupMenu(context, view);
                MenuInflater inflater = pop.getMenuInflater();

                inflater.inflate(R.menu.popup_menu_lista_livros, pop.getMenu());

                pop.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {

                        if (menuItem.getItemId() == R.id.item_remover_livro){
                            livro.delete();
                            livros.remove(position);
                            notifyItemRemoved(position);
                            notifyItemRangeChanged(position, ListaLivrosRVAdapter.this.livros.size());
                            Toast.makeText(context, "Removido", Toast.LENGTH_SHORT).show();
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
        return livros.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        protected TextView tvLivroTitulo;
        protected TextView tvLivroAno;
        protected TextView tvLivroAutorNome;


        public ViewHolder(View itemView) {
            super(itemView);

            tvLivroTitulo = (TextView) itemView.findViewById(R.id.tv_livro_titulo);
            tvLivroAno = (TextView) itemView.findViewById(R.id.tv_livro_ano);
            tvLivroAutorNome = (TextView) itemView.findViewById(R.id.tv_livro_autor_nome);
        }
    }

    public ListaLivrosRVAdapter(Context context, List<Livro> livros){

        this.context = context;
        this.livros = livros;

    }


}
