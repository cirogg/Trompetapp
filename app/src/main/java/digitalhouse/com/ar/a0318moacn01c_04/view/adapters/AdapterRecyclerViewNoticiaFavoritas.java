package digitalhouse.com.ar.a0318moacn01c_04.view.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import digitalhouse.com.ar.a0318moacn01c_04.R;
import digitalhouse.com.ar.a0318moacn01c_04.model.pojo.Noticia;
import digitalhouse.com.ar.a0318moacn01c_04.model.pojo.NoticiaFavorita;


public class AdapterRecyclerViewNoticiaFavoritas extends RecyclerView.Adapter{

    List<NoticiaFavorita> listaDeNoticiasFavoritas = new ArrayList<>();
    private ComunicacionAdapterRecyclerNoticiaFavorita comunicacionAdapterRecyclerNoticiaFavorita;
    private Context context;

    public AdapterRecyclerViewNoticiaFavoritas(List<NoticiaFavorita> listaDeNoticiasFavoritas, ComunicacionAdapterRecyclerNoticiaFavorita comunicacionAdapterRecyclerNoticiaFavorita) {
        this.listaDeNoticiasFavoritas = listaDeNoticiasFavoritas;
        this.comunicacionAdapterRecyclerNoticiaFavorita = comunicacionAdapterRecyclerNoticiaFavorita;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Obtengo el context
        context = parent.getContext();
        //Obtengo el inflator
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        //inflamos la celda
        View celda = layoutInflater.inflate(R.layout.celda_noticia_feed,parent,false);
        //Al VH le asignamos la celda
        NoticiaFavoritaViewHolder noticiaFavoritaViewHolder = new NoticiaFavoritaViewHolder(celda);

        return noticiaFavoritaViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        NoticiaFavorita noticiaFavorita = listaDeNoticiasFavoritas.get(position);
        NoticiaFavoritaViewHolder noticiaFavoritaViewHolder = (NoticiaFavoritaViewHolder) holder;
        noticiaFavoritaViewHolder.asignarDatos(noticiaFavorita.getTitle(),noticiaFavorita.getUrlToImage());
    }

    @Override
    public int getItemCount() {
        return listaDeNoticiasFavoritas.size();
    }

    private class NoticiaFavoritaViewHolder extends RecyclerView.ViewHolder{

        TextView textoTituloNoticia;
        ImageView imageView;

        public NoticiaFavoritaViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imgNoticia);
            textoTituloNoticia = itemView.findViewById(R.id.textViewTituloNoticia);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Integer posicionSeleccionada = getAdapterPosition();

                    String urlDeNoticiaSeleccionada = listaDeNoticiasFavoritas.get(posicionSeleccionada).getUrl();
                    comunicacionAdapterRecyclerNoticiaFavorita.seleccionaronLaCelda(posicionSeleccionada,urlDeNoticiaSeleccionada);
                }
            });

        }
        public void asignarDatos(String texto, String urlToImage){
            textoTituloNoticia.setText(texto);
            Glide.with(context).load(urlToImage).into(imageView);
        }
    }

    public interface ComunicacionAdapterRecyclerNoticiaFavorita {
        void seleccionaronLaCelda(Integer posicion,String urlNoticia);
    }
}
