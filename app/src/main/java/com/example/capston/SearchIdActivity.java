package com.example.capston;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

        searchId.setOnClickListener(view->{
            String num=userStunum.getText().toString();
        });
    }
}
