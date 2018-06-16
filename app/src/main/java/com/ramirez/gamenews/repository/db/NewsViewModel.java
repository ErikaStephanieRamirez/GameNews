package com.ramirez.gamenews.repository.db;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.ramirez.gamenews.repository.NewsRepository;
import com.ramirez.gamenews.repository.modelos.New;

import java.util.List;

/**
 * Created by Erika on 8/6/2018.
 */

public class NewsViewModel extends AndroidViewModel {

    private NewsRepository repository;
    private LiveData<List<New>> mAllNews;

    public NewsViewModel(@NonNull Application application) {
        super(application);
        repository = new NewsRepository(application);
        mAllNews = repository.getAllNews();
        //repository.insert();
    }

    public LiveData<List<New>> getAllNews() {
        return mAllNews;
    }
}
