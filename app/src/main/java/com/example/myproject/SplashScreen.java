package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

public class SplashScreen extends AppCompatActivity {
    Handler handler;
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        img = findViewById(R.id.imgsplash);

        img.animate().alpha(5000).setDuration(0);
        handler =new Handler();
        startAnimation();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent dsp = new Intent(SplashScreen.this, LoginActivity.class);
                startActivity(dsp);
                finish();
            }
        },2500);
    }


    private void startAnimation() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                img.animate().rotationBy(360).withEndAction(this).setDuration(0).setInterpolator(new LinearInterpolator()).start();
            }
        };
        img.animate().rotationBy(360).withEndAction(runnable).setDuration(1000).setInterpolator(new LinearInterpolator()).start();
    }
}