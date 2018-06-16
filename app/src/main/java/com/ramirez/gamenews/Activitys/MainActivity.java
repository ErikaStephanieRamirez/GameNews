package com.ramirez.gamenews.Activitys;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ramirez.gamenews.R;
import com.ramirez.gamenews.repository.api.GNAPI;
import com.ramirez.gamenews.repository.api.DeserializerToken;
import com.ramirez.gamenews.repository.modelos.Users;

import java.net.SocketTimeoutException;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {


    EditText username, password;
    Button login;
    RelativeLayout rel1;
    Handler handler = new Handler();

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            rel1.setVisibility(View.VISIBLE);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        SharedPreferences sharedPreferences = this.getSharedPreferences("Login", Context.MODE_PRIVATE);
        if (sharedPreferences.contains("Token")) {
            startActivity(new Intent(this,PrincipalActivity.class));
            finish();
        }else {
            setContentView(R.layout.activity_main);


            login = findViewById(R.id.login);
            rel1 = findViewById(R.id.rel1);
            username = findViewById(R.id.user);
            password = findViewById(R.id.contra);  //inicializa las variables

            handler.postDelayed(runnable, 2000); //el tiempo que se tardara para que el relative layout sea visible

            login.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    Gson gson = new GsonBuilder().registerTypeAdapter(String.class, new DeserializerToken()).create();
                    Retrofit.Builder builder = new Retrofit.Builder().baseUrl(GNAPI.BASEURL).addConverterFactory(GsonConverterFactory.create(gson));
                    Retrofit retrofit = builder.build();
                    GNAPI gNewsAPI = retrofit.create(GNAPI.class); //revisa en la api
                    final Users users = new Users(username.getText().toString(), password.getText().toString()); //obtiene los valores de los usuarios de la api y los convierte a string
                    Call<String> call = gNewsAPI.login(users.getUser(), users.getPassword()); //a traves de la llamada obtiene las contras y usuarios de la api
                    call.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            if (response.isSuccessful() && !response.body().equals("") && !username.getText().equals("") && !password.getText().equals("")) {
                                sharedpreferences(response.body());
                                Toast.makeText(MainActivity.this, response.body(), Toast.LENGTH_SHORT).show();
                                startativity();
                            } else {
                                Toast.makeText(MainActivity.this, "no response", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            if (t instanceof SocketTimeoutException) {
                                Toast.makeText(MainActivity.this, "false", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            });
        }
    }

    private void startativity(){
        Intent nueva = new Intent(getApplicationContext(), PrincipalActivity.class);
        startActivity(nueva);
        finish();
    }

    private void sharedpreferences(String token){
        SharedPreferences sharedPreferences = this.getSharedPreferences("Login", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Token",token);
        editor.apply();
    }
}