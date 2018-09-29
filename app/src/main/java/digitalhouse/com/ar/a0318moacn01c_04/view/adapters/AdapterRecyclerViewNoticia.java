package digitalhouse.com.ar.a0318moacn01c_04.view.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import digitalhouse.com.ar.a0318moacn01c_04.model.pojo.Noticia;
import digitalhouse.com.ar.a0318moacn01c_04.R;

public class AdapterRecyclerViewNoticia extends RecyclerView.Adapter{

    private List<Noticia> listaDeNoticias;
    private Context context;
    private ComunicacionAdapterRecyclerNoticia comunicacionAdapterRecyclerNoticia;

    public AdapterRecyclerViewNoticia(List<Noticia> listaDeNoticias,
                                      ComunicacionAdapterRecyclerNoticia comunicacionAdapterRecyclerNoticia) {
        this.listaDeNoticias = listaDeNoticias;
        this.comunicacionAdapterRecyclerNoticia = comunicacionAdapterRecyclerNoticia;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //Obtengo el context
        context = parent.getContext();
        //Obtengo el inflator
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        //inflamos la celda
        View celda = layoutInflater.inflate(R.layout.celda_noticia_feed,parent,false);
        //Al VH le asignamos la celda
        NoticiaViewHolder noticiaViewHolder = new NoticiaViewHolder(celda);
        return noticiaViewHolder;
    }

    // View Holder
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Noticia noticia = listaDeNoticias.get(position);
        NoticiaViewHolder noticiaViewHolder = (NoticiaViewHolder) holder;
        noticiaViewHolder.asignarDatos(noticia.getTitle(),noticia.getUrlToImage());
    }

    @Override
    public int getItemCount() {
        return listaDeNoticias.size();
    }

    // View holder noticia
    private class NoticiaViewHolder extends RecyclerView.ViewHolder{

        TextView textoTituloNoticia;
        ImageView imageView;

        public NoticiaViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imgNoticia);
            textoTituloNoticia = itemView.findViewById(R.id.textViewTituloNoticia);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Integer posicionSeleccionada = getAdapterPosition();
                    String urlDeNoticiaSeleccionada = listaDeNoticias.get(posicionSeleccionada).getUrl();
                    comunicacionAdapterRecyclerNoticia.seleccionaronLaCeldaFeedNoticia(posicionSeleccionada,urlDeNoticiaSeleccionada);
                }
            });
        }

        public void asignarDatos(String texto, String urlToImage){
            textoTituloNoticia.setText(texto);
            Glide.with(context).load(urlToImage).into(imageView);

        }
    }

    public interface ComunicacionAdapterRecyclerNoticia {
        void seleccionaronLaCeldaFeedNoticia(Integer posicion, String urlNoticia);
    }
}
