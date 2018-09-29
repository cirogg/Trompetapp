package digitalhouse.com.ar.a0318moacn01c_04.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import digitalhouse.com.ar.a0318moacn01c_04.model.pojo.Opinion;

/**
 * Created by DH on 21/7/2018.
 */
@Dao
public interface RoomServiceOpinion {

    @Query("SELECT * FROM Opinion WHERE id = :id")
    Opinion find(Integer id);

    @Query("SELECT * FROM Opinion")
    List<Opinion> findAll();

    @Query("SELECT * FROM Opinion WHERE usuarioID = :usuarioID")
    List<Opinion> findByUsuarioID(String usuarioID);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Opinion... opinions);

    @Delete
    void delete(Opinion... opinions);

    @Query("DELETE FROM Opinion")
    void deleteAll();

    @Update
    void update(Opinion... opinions);
}
