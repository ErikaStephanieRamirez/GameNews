package com.ramirez.gamenews.Activitys;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.ramirez.gamenews.Fragments.TabsFragment;
import com.ramirez.gamenews.R;
import com.ramirez.gamenews.adapter.AdapterNews;
import com.ramirez.gamenews.repository.db.NewsViewModel;
import com.ramirez.gamenews.repository.modelos.New;

import java.util.List;

public class PrincipalActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {



    private RecyclerView recyclerView;

    private NewsViewModel newsViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        newsViewModel = ViewModelProviders.of(this).get(NewsViewModel.class);

        //inicializando atributos
        initialize();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

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
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.


        Fragment fragment = null;

        boolean estado =false;

        int id = item.getItemId();

        if (id == R.id.aNews) {

            Intent nueva = new Intent(getApplicationContext(), PrincipalActivity.class);
            startActivity(nueva);

        }
        else if (id == R.id.lol) {

            fragment = TabsFragment.newInstance(item.getTitle().toString());
            estado = true;

        } else if (id == R.id.dota) {

            fragment = TabsFragment.newInstance(item.getTitle().toString());
            estado = true;

        } else if (id == R.id.csgo) {

            fragment = TabsFragment.newInstance(item.getTitle().toString());
            estado = true;

        }
        if(estado){
            getSupportFragmentManager().beginTransaction().replace(R.id.content,fragment).commit();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void initialize() {

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

        recyclerView = findViewById(R.id.recycler);
        final AdapterNews adapterNews = new AdapterNews(this);

        recyclerView.setAdapter(adapterNews);
        recyclerView.setLayoutManager(gridLayoutManager);

        newsViewModel.getAllNews().observe(this, new Observer<List<New>>() {
            @Override
            public void onChanged(@Nullable List<New> news) {
                adapterNews.setNews(news);
            }
        });
    }

}