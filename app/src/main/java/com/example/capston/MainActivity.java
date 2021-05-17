package com.example.capston;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import com.example.capston.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
        ActivityMainBinding binding;

        @SuppressLint("ClickableViewAccessibility")
        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                binding=DataBindingUtil.setContentView(this,R.layout.activity_main);

                binding.reservText.setOnClickListener(view->{
                        Intent intent = new Intent(MainActivity.this, BoardActivity.class);
                        startActivity(intent);
                        finish();
                });
                binding.lateText.setOnClickListener(view->{
                        Intent intent = new Intent(MainActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                });
                binding.chattingText.setOnClickListener(view->{
                        Intent intent = new Intent(MainActivity.this, ChatBoardActivity.class);
                        startActivity(intent);
                        finish();
                });
        }
}