package ru.arlen.androidroom.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import ru.arlen.androidroom.model.Props;

@Dao
public interface PropsDAO {
    @Query("select * from props")
    public Props getProps();
    @Update
    public void updateProps(Props props);
    @Insert
    public void insertProps(Props props);
}
