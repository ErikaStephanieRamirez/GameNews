package com.ramirez.gamenews;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_main);

        login = findViewById(R.id.login);
        rel1 = findViewById(R.id.rel1);

        handler.postDelayed(runnable, 3000); //el tiempo que se tardara para que el relative layout sea visible
        login.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent nueva = new Intent(getApplicationContext(), PrincipalActivity.class);
                startActivity(nueva);
            }
        });
    }
}
