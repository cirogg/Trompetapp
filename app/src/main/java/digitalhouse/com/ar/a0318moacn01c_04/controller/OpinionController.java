package digitalhouse.com.ar.a0318moacn01c_04.controller;

import android.content.Context;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Result;

import digitalhouse.com.ar.a0318moacn01c_04.model.dao.DAOOpinion;
import digitalhouse.com.ar.a0318moacn01c_04.model.dao.DAOOpinionRoom;
import digitalhouse.com.ar.a0318moacn01c_04.model.pojo.ContenedorOpinion;
import digitalhouse.com.ar.a0318moacn01c_04.model.pojo.Opinion;
import digitalhouse.com.ar.a0318moacn01c_04.model.pojo.Usuario;
import digitalhouse.com.ar.a0318moacn01c_04.util.HTTPConnectionManager;
import digitalhouse.com.ar.a0318moacn01c_04.util.ResultListener;

/**
 * Created by DH on 4/7/2018.
 */

public class OpinionController {

    public void obtenerOpiniones(Context context, final ResultListener<ContenedorOpinion> escuchadorDeLaVista) {

        // Obtengo las opiniones que tengo persistidas en Room
        final DAOOpinionRoom daoOpinionRoom = new DAOOpinionRoom(context);
        escuchadorDeLaVista.finish(new ContenedorOpinion(daoOpinionRoom.findAll()));

        ResultListener<ContenedorOpinion> escuchadorDelControlador = new ResultListener<ContenedorOpinion>() {
            @Override
            public void finish(ContenedorOpinion resultado) {

                // Al llegar la información de Firebase, persisto en Room
                daoOpinionRoom.deleteAll();
                for (Opinion opinion : resultado.getListaDeOpiniones()) {
                    daoOpinionRoom.insert(opinion);
                }
                escuchadorDeLaVista.finish(new ContenedorOpinion(daoOpinionRoom.findAll()));
            }
        };

        // Si hay internet, hago petición a Firebase
        if (HTTPConnectionManager.isNetworkingOnline(context)) {
            DAOOpinion daoOpinion = new DAOOpinion();
            daoOpinion.obtenerOpiniones(escuchadorDelControlador);
        }

    }

    public void obtenerOpinion(Integer id, Context context, final ResultListener<Opinion> escuchadorDeLaVista) {
        // Obtengo las opiniones que tengo persistidas en Room
        final DAOOpinionRoom daoOpinionRoom = new DAOOpinionRoom(context);
        Opinion opinion = daoOpinionRoom.find(id);
        escuchadorDeLaVista.finish(opinion);
    }

    public void escribirOpinion(Context context, Opinion opinion, final ResultListener<Boolean> escuchadorDeLaVista) {

        ResultListener<Boolean> escuchadorDelControlador = new ResultListener<Boolean>() {
            @Override
            public void finish(Boolean resultado) {
                escuchadorDeLaVista.finish(resultado);
            }
        };

        DAOOpinion daoOpinion = new DAOOpinion();
        daoOpinion.escribirOpinion(opinion, escuchadorDelControlador);
    }

    public Boolean puedeEscribir() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null)
            return true;
        else
            return false;
    }

}
