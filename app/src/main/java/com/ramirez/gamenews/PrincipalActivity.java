package com.ramirez.gamenews;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.gson.Gson;
import com.ramirez.gamenews.repository.api.GNAPI;
import com.ramirez.gamenews.adapter.AdapterNews;
import com.ramirez.gamenews.repository.modelos.New;
import com.ramirez.gamenews.tabs.CsGoActivity;
import com.ramirez.gamenews.tabs.DotaActivity;
import com.ramirez.gamenews.tabs.LegueActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PrincipalActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout mdrawerLayout;
    private ActionBarDrawerToggle mactionBarDrawerToggle;
    private NavigationView navigationView;
    private List<New> list2;
    private String token;
    //private List<New> ListNews;

    private RecyclerView recyclerView;
    private AdapterNews adapter;
    private ArrayList<New> list;

    private NewsViewModel newsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        newsViewModel = ViewModelProviders.of(this).get(NewsViewModel.class);

        // sharedpreferences();

        //inicializando atributos
        initialize();


        //newslist();


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position % 3 == 0) {
                    return 2;
                } else {
                    return 1;
                }
            }
        });

        /*recyclerView.setLayoutManager(gridLayoutManager);
        adapter = new AdapterNews(list,this);
        recyclerView.setAdapter(adapter);*/

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.aNews) {
            // Handle the camera action

        } else if (id == R.id.legue) {
            Intent nueva = new Intent(getApplicationContext(), LegueActivity.class);
            startActivity(nueva);
        } else if (id == R.id.dota) {
            Intent nueva = new Intent(getApplicationContext(), DotaActivity.class);
            startActivity(nueva);
        } else if (id == R.id.csgo) {
            Intent nueva = new Intent(getApplicationContext(), CsGoActivity.class);
            startActivity(nueva);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void initialize() {
        recyclerView = findViewById(R.id.recycler);
        final AdapterNews adapterNews = new AdapterNews(this);
        recyclerView.setAdapter(adapterNews);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        newsViewModel.getAllNews().observe(this, new Observer<List<New>>() {
            @Override
            public void onChanged(@Nullable List<New> news) {
                adapterNews.setNews(news);
            }
        });
    }

    private void newslist() {
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
                    Toast.makeText(getApplicationContext(), "Cargando...", Toast.LENGTH_SHORT).show();
                    list2 = (List<New>) response.body();
                } else {
                    Toast.makeText(getApplicationContext(), "No se pudo cargar las noticias", Toast.LENGTH_LONG).show();
                    Toast.makeText(getApplicationContext(), token, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<New>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "imposible", Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(), token, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void sharedpreferences() {
        SharedPreferences sharedPreferences = this.getSharedPreferences("Login", Context.MODE_PRIVATE);
        if (!sharedPreferences.contains("Token")) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            token = sharedPreferences.getString("Token", "");
        }
    }
}