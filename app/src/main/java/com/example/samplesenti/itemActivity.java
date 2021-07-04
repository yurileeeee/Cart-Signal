package com.example.samplesenti;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class itemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.itemhost);
    }

    public void optionClick(View v) {
        PopupMenu popup= new PopupMenu(getApplicationContext(), v);//v는 클릭된 뷰를 의미
        getMenuInflater().inflate(R.menu.mymenu, popup.getMenu());
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.m1:
                        Toast.makeText(getApplication(),"수정",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.m2:
                        Toast.makeText(getApplication(),"삭제",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.m3:
                        Toast.makeText(getApplication(),"종료",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),ChatActivity.class);
                        startActivity(intent);
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
        popup.show();//Popup Menu 보이기
    }
}
