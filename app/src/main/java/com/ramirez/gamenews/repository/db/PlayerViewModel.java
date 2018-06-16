package com.ramirez.gamenews.repository.db;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.ramirez.gamenews.repository.NewsRepository;
import com.ramirez.gamenews.repository.PlayersRepository;
import com.ramirez.gamenews.repository.modelos.New;
import com.ramirez.gamenews.repository.modelos.Players;

import java.util.List;

/**
 * Created by Erika on 15/6/2018.
 */

public class PlayerViewModel extends AndroidViewModel {
    private PlayersRepository repository;
    private LiveData<List<Players>> pAllNews;

    public PlayerViewModel(@NonNull Application application) {
        super(application);
        repository = new PlayersRepository(application);
        pAllNews = repository.getPlayersGame();
        //repository.insert();
    }

    public LiveData<List<Players>> getAllPlayers() {
        return pAllNews;
    }
}
