package com.example.hackathonapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;



public class Fun_page extends AppCompatActivity {
    private FirebaseAuth mAuth;
    TextView fp_reportIncident;
    TextView fp_legal;
    TextView fp_forum;
    TextView fp_selfDefence;
    Button fp_logout;
    public static SharedPreferences sp;

    static TextView temp;

    static TextView weather_Type;


    String cityName;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fun_page);
        mAuth = FirebaseAuth.getInstance();
        fp_reportIncident = findViewById(R.id.reportIncident);
        fp_legal = findViewById(R.id.legal);
        fp_forum = findViewById(R.id.forum);
        fp_selfDefence = findViewById(R.id.selfDefence);
        fp_logout = findViewById(R.id.logout);
        sp = getSharedPreferences("login", MODE_PRIVATE);

        temp=findViewById(R.id.am_tvTemp);






        fp_legal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(Fun_page.this, Legal.class));


            }
        });
        fp_selfDefence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(Fun_page.this, selfdef.class));
                finish();
            }
        });
        fp_forum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Fun_page.this, ForumPage.class));
                finish();
            }
        });

        fp_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                Toast.makeText(getApplicationContext(), "Logged out", Toast.LENGTH_SHORT).show();
                sp.edit().putBoolean("logged", false).apply();
                startActivity(new Intent(Fun_page.this, SignInPage.class));
                finish();
            }
        });
        fp_reportIncident.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Fun_page.this, ReportIncident.class));
                finish();
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        cityName = "Dhaka";
            Weather getData =new Weather();
            getData.execute("http://api.openweathermap.org/data/2.5/weather?q="+cityName+"&units=metric&appid=83b1bbc315986efcf8826fd83f4ffa83");
            weather_Type=findViewById(R.id.amweatherType);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
