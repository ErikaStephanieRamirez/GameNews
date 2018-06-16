package com.ramirez.gamenews.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.ramirez.gamenews.repository.api.GNAPI;
import com.ramirez.gamenews.repository.db.GamesNewsDatabase;
import com.ramirez.gamenews.repository.db.PlayersDao;
import com.ramirez.gamenews.repository.modelos.Players;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Erika on 15/6/2018.
 */

public class PlayersRepository {

    private PlayersDao pDao;
    private LiveData<List<Players>> pPlayers;
    private String token;

    public PlayersRepository(Application application) {
        GamesNewsDatabase db = GamesNewsDatabase.getDatabase(application.getApplicationContext());
        pDao = db.playersDao();
        pPlayers = pDao.getPlayersGame("lol");
        SharedPreferences sharedPreferences = application.getSharedPreferences("Login", Context.MODE_PRIVATE);
        token = sharedPreferences.getString("Token", "");
    }

    public LiveData<List<Players>> getPlayersGame() {
        getPlayersAPI();
        return pPlayers;
    }

    public void insert(Players jugador) {
        new PlayersRepository.insertAsyncTask(pDao).execute(jugador);
    }

    public void insert(List<Players> jugadores) {new PlayersRepository.insertAsyncTaskP(pDao).execute(jugadores);}


    private void getPlayersAPI() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GNAPI.BASEURL)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();

        GNAPI gameNewsAPI = retrofit.create(GNAPI.class);

        Call<List<Players>> players = gameNewsAPI.getPlayers("Beared " + token);

        players.enqueue(new Callback<List<Players>>() {
            @Override
            public void onResponse(Call<List<Players>> call, Response<List<Players>> response) {
                if (response.isSuccessful()) {
                    List<Players> players = response.body();
                    insert(players);
                }
            }

            @Override
            public void onFailure(Call<List<Players>> call, Throwable t) {
                //TODO: FALTA IMPLIMETAR CUANDO HAY UN ERROR
                Log.d("asd","asdasd");
            }
        });
    }

    private static class insertAsyncTask extends AsyncTask<Players, Void, Void> {

        private PlayersDao pAsyncTaskDao;

        insertAsyncTask(PlayersDao dao) {
            pAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Players... params) {
            try {
                pAsyncTaskDao.insert(params[0]);
            }catch (Throwable e){
                e.printStackTrace();
            }
            return null;
        }
    }

    private static class insertAsyncTaskP extends AsyncTask<List<Players>, Void, Void> {

        private PlayersDao pAsyncTaskDao;

        insertAsyncTaskP(PlayersDao dao) {
            pAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(List<Players>[] lists) {
            pAsyncTaskDao.insert(lists[0]);
            return null;
        }
    }

}
