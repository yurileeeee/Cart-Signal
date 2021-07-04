package com.example.samplesenti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class FirstLoading extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_loading);

        Handler hd = new Handler();
        hd.postDelayed(new FirstLoading.mainhandler(), 5000);
    }

    public class mainhandler implements Runnable {
        @Override
        public void run(){
            startActivity(new Intent(getApplication(),MainActivity.class));
            FirstLoading.this.finish();
        }
    }
}