package com.example.capston;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.capston.databinding.ActivityPasthistoryBinding;

public class PathHistoryActivity extends AppCompatActivity {
    ActivityPasthistoryBinding binding;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_pasthistory);


        binding.imageButton4.setOnClickListener(view->{
            Intent intent = new Intent(PathHistoryActivity.this, InfoActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
