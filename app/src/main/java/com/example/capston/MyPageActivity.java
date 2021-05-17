package com.example.capston;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.capston.databinding.ActivityLoginBinding;
import com.example.capston.databinding.ActivityMypageBinding;

public class MyPageActivity extends AppCompatActivity {
    ActivityMypageBinding binding;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_mypage);

        binding.imageButton4.setOnClickListener(view->{
            Intent intent = new Intent(MyPageActivity.this, InfoActivity.class);
            startActivity(intent);
            finish();
        });

        binding.passBtn.setOnClickListener(view->{
            Intent intent = new Intent(MyPageActivity.this, EditMyPageActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
