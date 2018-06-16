package com.ramirez.gamenews.repository.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.ramirez.gamenews.repository.modelos.Players;

import java.util.List;

/**
 * Created by Erika on 8/6/2018.
 */

@Dao
public interface PlayersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Players player);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Players> players);

    @Query("SELECT * FROM players WHERE game = :game")
    LiveData<List<Players>> getPlayersGame(String game);
}
