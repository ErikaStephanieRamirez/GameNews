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
    private LiveData<List<Players>> lolPlayer;
    private LiveData<List<Players>> overwatchPlayer;
    private LiveData<List<Players>> csgoPlayer;

    public PlayerViewModel(@NonNull Application application) {
        super(application);
        repository = new PlayersRepository(application);
        lolPlayer = repository.getLolplayersGame();
        overwatchPlayer = repository.getOverwatchplayers();
        csgoPlayer = repository.getCsgoplayers();
    }

    public LiveData<List<Players>> getLolPlayers() {
        return lolPlayer;
    }
    public LiveData<List<Players>> getOverwatchPlayes() {
        return overwatchPlayer;
    }
    public LiveData<List<Players>> getCsgoPlayer() {
        return csgoPlayer;
    }
}
