package com.example.capston;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.capston.databinding.ActivityBoardBinding;
import com.example.capston.databinding.ActivityInfoBinding;

public class BoardActivity extends AppCompatActivity {
    ActivityBoardBinding binding;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_board);

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        binding.writeBtn.setOnClickListener(view->{
            Intent intent = new Intent(BoardActivity.this, BoardWriteActivity.class);
            startActivity(intent);
            finish();
        });

    }
}
