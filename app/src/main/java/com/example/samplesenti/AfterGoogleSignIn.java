package com.example.samplesenti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class AfterGoogleSignIn extends AppCompatActivity {

    private TextView tv_result;
    private ImageView iv_profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_google_sign_in);

        Intent intent = getIntent();
        String nickName = intent.getStringExtra("nickName");
        String photoUrl = intent.getStringExtra("photoUrl");

        tv_result = findViewById(R.id.tv_result);
        tv_result.setText(nickName);

        iv_profile = findViewById(R.id.iv_profile);
        Glide.with(this).load(photoUrl).into(iv_profile);

        Button nextToLocation = (Button) findViewById(R.id.btn_profileTolocation);
        nextToLocation.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(intent);
            }
        });
    }
}