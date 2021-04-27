package com.example.capston;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SearchIdActivity extends AppCompatActivity{

    private TextView searchId,searchPass;
    private EditText userStunum;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchid);
        searchId=(TextView)findViewById(R.id.searchId);
        userStunum=findViewById(R.id.userStunum);
        Toast.makeText(SearchIdActivity.this,"학번을 입력해주세요",Toast.LENGTH_SHORT).show();

        searchId.setOnClickListener(view->{
            Intent intent = new Intent(SearchIdActivity.this, SearchIdActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
