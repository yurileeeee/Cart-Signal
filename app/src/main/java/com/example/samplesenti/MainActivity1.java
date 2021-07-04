package com.example.samplesenti;

import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.auth.data.model.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;

public class MainActivity1 extends AppCompatActivity{

    EditText et1;
    EditText et2;
    EditText et3;
    EditText et4;
    Button sendbt;
    Button button;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    ImageView companyimage;
    public String str1;
    public String str2;
    public String str3;
    public String str4;

    private FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    private ChildEventListener mChild;

    private ListView listView;
    private ArrayAdapter<String> adapter;
    ArrayList<String> Array = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        sendbt=(Button) findViewById(R.id.btnFinish2);
        et1=(EditText) findViewById(R.id.minMoney2);
        et2=(EditText) findViewById(R.id.maxUser2);
        et3=(EditText) findViewById(R.id.moreInfo2);
        et4=(EditText)findViewById(R.id.endDate2);

        listView = (ListView) findViewById(R.id.listviewmsg);

        initDatabase();

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, new ArrayList<String>());
        listView.setAdapter(adapter);

        companyimage = (ImageView) findViewById(R.id.companyimage);

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                companyimage.setImageResource(R.drawable.coupang);
            }
        });

        button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                companyimage.setImageResource(R.drawable.kurly);
            }
        });

        button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                companyimage.setImageResource(R.drawable.lotte);
            }
        });

        button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                companyimage.setImageResource(R.drawable.ssg);
            }
        });

        button5 = (Button) findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                companyimage.setImageResource(R.drawable.bag);
                Snackbar.make(view, "추가정보칸에 입력하세요.", Snackbar.LENGTH_LONG).show();
            }
        });

        sendbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uid, name, email;
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) {
                    // Name, email address, and profile photo Url
                    name = user.getDisplayName();
                    email = user.getEmail();
                    Uri photoUrl = user.getPhotoUrl();

                    // Check if user's email is verified
                    boolean emailVerified = user.isEmailVerified();

                    // The user's ID, unique to the Firebase project. Do NOT use this value to
                    // authenticate with your backend server, if you have one. Use
                    // FirebaseUser.getIdToken() instead.
                    uid = user.getUid();

                    str1 = et1.getText().toString();
                    str2 = et2.getText().toString();
                    str3 = et3.getText().toString();
                    str4 = et4.getText().toString();
                    //  databaseReference.child("content").setValue(uid);
                    Post post = new Post(str1, str2, str3, str4);
                    databaseReference.push().setValue(post); //넣기
                }
            }
        });
        databaseReference = firebaseDatabase.getReference("content"); // 변경값을 확인할 child 이름
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                adapter.clear();
                for (DataSnapshot messageData : dataSnapshot.getChildren()) {
                    //    String key = messageData.getKey();
                    Post get = messageData.getValue(Post.class);
                    //   String[] info = {get.minMoney, get.maxUser, get.moreInfo, get.endDate};
                    String result = "최소금액:"+get.minMoney+" 최대구매자:"+get.maxUser+" 기타: "+get.moreInfo+" 기한:"+get.endDate;
                    //Array.add(result);
                    adapter.add(result);
                    // child 내에 있는 데이터만큼 반복합니다.
                }
                adapter.notifyDataSetChanged();
                listView.setSelection(adapter.getCount() - 1);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity1.this, MoreInfoActivity.class);
                startActivity(intent);
            }
        });
        registerForContextMenu(listView);

    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        Log.d("test", "onCreateContextMenu");

        menu.setHeaderTitle("옵션");
        menu.add(0, 1, 100, "수정");
        menu.add(0, 2, 100, "삭제");
        menu.add(0, 3, 100, "종료");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case 1: //수정
                return true;
            case 2: //삭제
                return true;
            case 3: //종료
                Intent in = new Intent(getApplicationContext(), ChatActivity.class);
                startActivity(in);

                return true;

        }
        return super.onContextItemSelected(item);
    }
    private void initDatabase() {

        firebaseDatabase = FirebaseDatabase.getInstance();

        databaseReference = firebaseDatabase.getReference("log");
        databaseReference.child("log").setValue("check");

        mChild = new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        databaseReference.addChildEventListener(mChild);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        databaseReference.removeEventListener(mChild);
    }

}