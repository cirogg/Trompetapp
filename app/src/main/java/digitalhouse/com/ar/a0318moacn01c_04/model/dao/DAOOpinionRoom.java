package digitalhouse.com.ar.a0318moacn01c_04.model.dao;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.graphics.Path;

import java.util.List;

import digitalhouse.com.ar.a0318moacn01c_04.model.pojo.Opinion;

/**
 * Created by DH on 21/7/2018.
 */

public class DAOOpinionRoom implements RoomServiceOpinion{

    private DatabaseAbstract roomDatabase;

    public DAOOpinionRoom(Context context) {
        roomDatabase = Room.databaseBuilder(context,DatabaseAbstract.class, "database-name").allowMainThreadQueries().build();
    }

    @Override
    public Opinion find(Integer id) {
        return roomDatabase.opinionDao().find(id);
    }

    @Override
    public List<Opinion> findAll() {
        return roomDatabase.opinionDao().findAll();
    }

    @Override
    public List<Opinion> findByUsuarioID(String usuarioID) {
        return roomDatabase.opinionDao().findByUsuarioID(usuarioID);
    }

    @Override
    public void insert(Opinion... opinions) {
        roomDatabase.opinionDao().insert(opinions);
    }

    @Override
    public void delete(Opinion... opinions) {
        roomDatabase.opinionDao().delete(opinions);
    }

    @Override
    public void deleteAll() { roomDatabase.opinionDao().deleteAll(); }

    @Override
    public void update(Opinion... opinions) {
        roomDatabase.opinionDao().update(opinions);
    }
}
