package com.ramirez.gamenews.repository.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.ramirez.gamenews.repository.modelos.New;

import java.util.List;

/**
 * Created by Erika on 8/6/2018.
 */

@Dao
public interface NewsDao  {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(New noticia);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<New> noticias);

    @Query("SELECT * FROM new ORDER BY create_date DESC")
    LiveData<List<New>> getAllNews();

    @Query("SELECT * FROM new WHERE game = :game ORDER BY create_date DESC")
    LiveData<List<New>> getNewsByGame(String game);
}
