package com.example.hackathonapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class LandingPage extends AppCompatActivity {
    ImageView lp_ivLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing_page);
        lp_ivLogo = findViewById(R.id.alp_ivLogo);


        Animation zoomout = AnimationUtils.loadAnimation(this, R.anim.splash_screen);
        lp_ivLogo.setAnimation(zoomout);


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(LandingPage.this, SignInPage.class);
                startActivity(intent);

                finish();
            }
        }, 2000);
    }

}

