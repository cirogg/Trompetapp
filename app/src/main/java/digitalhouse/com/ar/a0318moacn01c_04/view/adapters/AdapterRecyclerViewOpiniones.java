package digitalhouse.com.ar.a0318moacn01c_04.view.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import digitalhouse.com.ar.a0318moacn01c_04.R;
import digitalhouse.com.ar.a0318moacn01c_04.model.pojo.ContenedorOpinion;
import digitalhouse.com.ar.a0318moacn01c_04.model.pojo.Opinion;
import digitalhouse.com.ar.a0318moacn01c_04.model.pojo.Usuario;
import digitalhouse.com.ar.a0318moacn01c_04.util.ResultListener;

public class AdapterRecyclerViewOpiniones extends RecyclerView.Adapter{

    private ContenedorOpinion contenedorOpinion;
    private Context context;
    private ComunicacionAdapterRecyclerOpinion comunicacionAdapterRecyclerOpinion;

    public AdapterRecyclerViewOpiniones(ContenedorOpinion contenedorOpinion,
                                        ComunicacionAdapterRecyclerOpinion comunicacionAdapterRecyclerOpinion) {
        this.contenedorOpinion = contenedorOpinion;
        this.comunicacionAdapterRecyclerOpinion = comunicacionAdapterRecyclerOpinion;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View celda = layoutInflater.inflate(R.layout.celda_opinion, parent, false);
        OpinionViewHolder opinionViewHolder = new OpinionViewHolder(celda);

        return opinionViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Opinion opinion = contenedorOpinion.getListaDeOpiniones().get(position);
        OpinionViewHolder opinionViewHolder = (OpinionViewHolder) holder;
        opinionViewHolder.asignarDatos(opinion);
    }

    @Override
    public int getItemCount() { return contenedorOpinion.getListaDeOpiniones().size(); }

    private class OpinionViewHolder extends RecyclerView.ViewHolder {

        ImageView imageViewPerfil;
        TextView textViewNombre;
        TextView textViewOpinion;
        ImageView imageViewBoton;
        TextView textViewHora;

        public OpinionViewHolder(View itemView) {
            super(itemView);
            imageViewPerfil = itemView.findViewById(R.id.imageViewPerfil);
            textViewNombre = itemView.findViewById(R.id.textViewNombre);
            textViewOpinion = itemView.findViewById(R.id.textViewCuerpo);
            imageViewBoton = itemView.findViewById(R.id.imageViewBoton);
            textViewHora = itemView.findViewById(R.id.textViewHora);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //FIXME ¿Deberiamos usar el key de firebase?
                    Opinion opinion = contenedorOpinion.getListaDeOpiniones().get(getAdapterPosition());
                    comunicacionAdapterRecyclerOpinion.seleccionaronLaCelda(opinion.getId());
                }
            });

        }
        public void asignarDatos(Opinion opinion){
            String cuerpoFormateado = opinion.getCuerpo().replace("\n", " ");
            textViewOpinion.setText(cuerpoFormateado);
            textViewNombre.setText(opinion.getUsuarioNombre());
        }
    }

    public void setContenedorOpinion(ContenedorOpinion contenedorOpinion) {
        this.contenedorOpinion = contenedorOpinion;
        notifyDataSetChanged();
    }

    public interface ComunicacionAdapterRecyclerOpinion {
        void seleccionaronLaCelda(Integer id);
    }
}
