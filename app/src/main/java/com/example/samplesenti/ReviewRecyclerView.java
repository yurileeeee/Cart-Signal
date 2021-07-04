package com.example.samplesenti;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ReviewRecyclerView extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Review> arrayList;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_recycler_view);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerViewReview);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        arrayList = new ArrayList<>();

        database = FirebaseDatabase.getInstance();

        databaseReference = database.getReference("HostReview");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //파이어베이스 데이터베이스의 데이터를 받아옴
                arrayList.clear();//기존 배열리스트가 존재하지 않게 초기화
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){ //반복문으로 데이터 List를 추출해냄
                    Review reviewHost = snapshot.getValue(Review.class); //만들어뒀던 Review 객체에 담는다.
                    arrayList.add(reviewHost); //담은 데이터를 배열리스트에 넣고 리사이클러뷰로 보낼 준비
                }
                adapter.notifyDataSetChanged();//리스트 저장 및 새로고침
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //디비를 가져오던 중 에러 발생시
                Log.e("MainActivity", String.valueOf(databaseError.toException())); //에러문 출력
            }
        });

        adapter = new ReviewCustomAdapter(arrayList, this);
        recyclerView.setAdapter(adapter); //리사이클러뷰에 어댑터 연결

        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(recyclerView.getContext(),new LinearLayoutManager(this).getOrientation());
        // 리싸이클러뷰 구분선
        recyclerView.addItemDecoration(dividerItemDecoration);
        // 구분선 추가

        RecyclerDecoration spaceDecoration = new RecyclerDecoration(30);
        recyclerView.addItemDecoration(spaceDecoration);

        Intent intent = getIntent();
    }
}