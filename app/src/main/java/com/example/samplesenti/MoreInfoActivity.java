package com.example.samplesenti;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class MoreInfoActivity extends AppCompatActivity {
    private FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    NotificationManager manager;

    private static String CHANNEL_ID = "channel1";
    private static String CHANNEL_NAME = "Channel";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_info);

        Button button = (Button)findViewById(R.id.btn_vibrate);
        ImageButton moreInfo_profile = (ImageButton)findViewById(R.id.moreInfo_profile);
        Button btn_plus = (Button)findViewById(R.id.btn_plus);
        TextView namee=(TextView)findViewById(R.id.moreInfo_host_name);

        String uid, name, email;
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url
            name = user.getDisplayName();
            email = user.getEmail();
            Uri photoUrl = user.getPhotoUrl();

            moreInfo_profile.setImageURI(photoUrl);
            namee.setText(name);
            // Check if user's email is verified
            boolean emailVerified = user.isEmailVerified();

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getIdToken() instead.
            uid = user.getUid();

        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Vibrator vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
                vibrator.vibrate(1000);
                showNoti();
            }
        });

        moreInfo_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MoreInfoActivity.this, ReviewRecyclerView.class);
                startActivity(intent);
            }
        });
        Intent intent1 = getIntent();

        btn_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"신청 완료되었습니다 :)", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void showNoti(){
        NotificationCompat.Builder builder = null;
        manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        //버전 오레오 이상일 경우
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            manager.createNotificationChannel(
                    new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT)
            );
            builder = new NotificationCompat.Builder(this,CHANNEL_ID);

            //하위 버전일 경우
        }else{ builder = new NotificationCompat.Builder(this);
        }

        Intent notiIntent = new Intent(this, ScoreActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 101, notiIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        //알림창 제목
        builder.setContentTitle("후기 작성");
        //알림창 메시지
        builder.setContentText("호스트에 대한 후기를 작성하시겠습니까?");
        //알림창 아이콘
        builder.setSmallIcon(android.R.drawable.ic_menu_view);
        builder.setAutoCancel(true);
        builder.setContentIntent(pendingIntent);

        Notification notification = builder.build();
        //알림창 실행
        manager.notify(1,notification);
    }
}