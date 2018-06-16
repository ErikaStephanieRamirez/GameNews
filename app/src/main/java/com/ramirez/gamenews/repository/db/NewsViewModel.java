package com.ramirez.gamenews.repository.db;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.ramirez.gamenews.repository.NewsRepository;
import com.ramirez.gamenews.repository.modelos.New;
import com.ramirez.gamenews.repository.modelos.Players;

import java.util.List;

/**
 * Created by Erika on 8/6/2018.
 */

public class NewsViewModel extends AndroidViewModel {

    private NewsRepository repository;
    private LiveData<List<New>> mAllNews;
    private LiveData<List<New>> lolNews;
    private LiveData<List<New>> overwatchNews;
    private LiveData<List<New>> csgoNews;

    public NewsViewModel(@NonNull Application application) {
        super(application);
        repository = new NewsRepository(application);
        mAllNews = repository.getAllNews();
        lolNews = repository.getLolnewsGame();
        overwatchNews = repository.getOverwatchnewsGame();
        csgoNews = repository.getCsgonewsGame();
    }

    public LiveData<List<New>> getAllNews() {
        return mAllNews;
    }
    public LiveData<List<New>> getLolnews() {
        return lolNews;
    }
    public LiveData<List<New>> getOverwatchnews() {
        return overwatchNews;
    }
    public LiveData<List<New>> getCsgonews() {
        return csgoNews;
    }
}
