package com.ramirez.gamenews.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.ramirez.gamenews.repository.api.GNAPI;
import com.ramirez.gamenews.repository.db.GamesNewsDatabase;
import com.ramirez.gamenews.repository.db.NewsDao;
import com.ramirez.gamenews.repository.modelos.New;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *
 * Created by Erika on 8/6/2018.
 */

public class NewsRepository {

    private NewsDao mDao;
    private LiveData<List<New>> mNews;
    private GNAPI gamesAPI;
    private String token;

    public NewsRepository(Application application) {
       GamesNewsDatabase db = GamesNewsDatabase.getDatabase(application.getApplicationContext());
        mDao = db.newsDao();
        mNews = mDao.getAllNews();
        SharedPreferences sharedPreferences = application.getSharedPreferences("Login", Context.MODE_PRIVATE);
        token = sharedPreferences.getString("Token", "");
    }

    public LiveData<List<New>> getAllNews() {
        getNewsAPI();
        return mNews;
    }

    /**
     *  hfvgfcvn
     * @param noticia ncfgncbn
     */

    public void insert(New noticia) {
          new insertAsyncTask(mDao).execute(noticia);
    }

    public void insert(List<New> noticias) {
        new insertAsyncTaskM(mDao).execute(noticias);
    }


    private void getNewsAPI() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GNAPI.BASEURL)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();

        GNAPI gameNewsAPI = retrofit.create(GNAPI.class);

        Call<List<New>> news = gameNewsAPI.getNews("Beared " + token);

        news.enqueue(new Callback<List<New>>() {
            @Override
            public void onResponse(Call<List<New>> call, Response<List<New>> response) {
                if (response.isSuccessful()) {
                    List<New> news = response.body();
                    insert(news);
                }
            }

            @Override
            public void onFailure(Call<List<New>> call, Throwable t) {
                //TODO: FALTA IMPLIMETAR CUANDO HAY UN ERROR
                Log.d("asd","asdasd");
            }
        });
    }

    private static class insertAsyncTask extends AsyncTask<New, Void, Void> {

        private NewsDao mAsyncTaskDao;

        insertAsyncTask(NewsDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final New... params) {
            try {
                mAsyncTaskDao.insert(params[0]);
            }catch (Throwable e){
                e.printStackTrace();
            }
            return null;
        }
    }

    private static class insertAsyncTaskM extends AsyncTask<List<New>, Void, Void> {

        private NewsDao mAsyncTaskDao;

        insertAsyncTaskM(NewsDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(List<New>[] lists) {
            mAsyncTaskDao.insert(lists[0]);
            return null;
        }
    }
}
