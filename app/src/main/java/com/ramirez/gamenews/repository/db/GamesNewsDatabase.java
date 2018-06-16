package com.ramirez.gamenews.repository.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.ramirez.gamenews.repository.modelos.New;
import com.ramirez.gamenews.repository.modelos.Players;

/**
 * Created by Erika on 8/6/2018.
 */

@Database( entities = {New.class, Players.class}, version = 1)
public abstract class GamesNewsDatabase extends RoomDatabase {

    public abstract NewsDao newsDao();
    public abstract PlayersDao playersDao();

    private static GamesNewsDatabase INSTANCE;

    public static GamesNewsDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (GamesNewsDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            GamesNewsDatabase.class, "games_new_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
