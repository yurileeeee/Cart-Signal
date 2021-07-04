package com.example.samplesenti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ScoreActivity extends AppCompatActivity {
    private RatingBar score_total_star;
    private RatingBar score_time_star;
    private RatingBar score_trust_star;
    private EditText score_review_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        final TextView score_total_title = (TextView)findViewById(R.id.score_total_title);
        final TextView score_time_title = (TextView)findViewById(R.id.score_time_title);
        final TextView score_trust_title = (TextView)findViewById(R.id.score_trust_title);

        final RatingBar score_total_star = (RatingBar)findViewById(R.id.score_total_star);
        final RatingBar score_time_star = (RatingBar)findViewById(R.id.score_time_star);
        final RatingBar score_trust_star = (RatingBar)findViewById(R.id.score_trust_star);

        final EditText score_review_text = (EditText)findViewById(R.id.score_review_text);
        Button score_submit = (Button)findViewById(R.id.score_submit);

        //총점 별점 표시
        score_total_star.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                score_total_title.setText("총점: "+rating);
            }
        });

        //응답시간 별점 표시
        score_time_star.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                score_time_title.setText("응답시간: "+rating);
            }
        });

        //신뢰도 별점 표시
        score_trust_star.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                score_trust_title.setText("신뢰도: "+rating);
            }
        });

        //제출 버튼 클릭시
        score_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference database = FirebaseDatabase.getInstance().getReference();
                //FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                //String uId = user.getUid();
                String reviewText = score_review_text.getText().toString();
                Review sendReview = new Review(score_total_star.getRating(),
                         score_time_star.getRating(),
                        score_trust_star.getRating(),
                        reviewText);
                database.child("HostReview").push().setValue(sendReview);


                //제출 감사 토스트 메세지
                Toast.makeText(getApplicationContext(),"소중한 후기 작성 감사합니다 :)", Toast.LENGTH_SHORT).show();

                Intent intent2List = new Intent(ScoreActivity.this, MainActivity1.class);
                startActivity(intent2List);

            }
        });

    }
}