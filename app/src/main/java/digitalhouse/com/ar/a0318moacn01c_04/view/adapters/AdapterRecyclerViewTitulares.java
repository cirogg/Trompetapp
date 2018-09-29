package digitalhouse.com.ar.a0318moacn01c_04.view.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import digitalhouse.com.ar.a0318moacn01c_04.R;
import digitalhouse.com.ar.a0318moacn01c_04.model.pojo.ContenedorNoticia;
import digitalhouse.com.ar.a0318moacn01c_04.model.pojo.Noticia;

public class AdapterRecyclerViewTitulares extends RecyclerView.Adapter {

    private Context context;
    private List<Noticia> listaDeNoticias;
    private ComunicacionAdapterRecyclerTitular comunicacionAdapterRecyclerTitular;

    public AdapterRecyclerViewTitulares(List<Noticia> listaDeNoticias,
                                        ComunicacionAdapterRecyclerTitular comunicacionAdapterRecyclerTitular){
        this.listaDeNoticias = listaDeNoticias;
        this.comunicacionAdapterRecyclerTitular = comunicacionAdapterRecyclerTitular;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View celda = layoutInflater.inflate(R.layout.celda_titulares, parent,false);
        TitularViewHolder titularViewHolder = new TitularViewHolder(celda);

        return titularViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
    Noticia noticia = listaDeNoticias.get(position);
    TitularViewHolder titularViewHolder = (TitularViewHolder) holder;
    titularViewHolder.asignarDatos(noticia.getTitle(),noticia.getUrlToImage());

    }

    public void setList(List<Noticia> list) {
        this.listaDeNoticias = list;
        notifyDataSetChanged();
    }

    private class TitularViewHolder extends  RecyclerView.ViewHolder{
        TextView textoTituloNoticia;
        ImageView imageView;

        public TitularViewHolder(final View itemView){
            super(itemView);
            imageView = itemView.findViewById(R.id.imgNoticiaTitular);
            textoTituloNoticia = itemView.findViewById(R.id.textViewTituloNoticiaTitular);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(itemView.getContext(), "dasdas", Toast.LENGTH_SHORT).show();
                    Integer posicionSeleccionada = getAdapterPosition();
                    String urlDeNoticiaSeleccionada = listaDeNoticias.get(posicionSeleccionada).getUrl();
                    comunicacionAdapterRecyclerTitular.seleccionaronLaCeldaTitular(posicionSeleccionada,urlDeNoticiaSeleccionada);
                }
            });
        }
        public void asignarDatos(String texto,String urlToImage){
            textoTituloNoticia.setText(texto);
            Glide.with(context).load(urlToImage).into(imageView);
        }
    }

    @Override
    public int getItemCount() {
        return listaDeNoticias.size();
    }

    public interface ComunicacionAdapterRecyclerTitular {
        void seleccionaronLaCeldaTitular(Integer posicion, String urlNoticia);
    }
}
