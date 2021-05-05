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
                binding.setActivity(this);

                binding.nextActivity.setOnClickListener(v -> {
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(intent);
                });

               binding.nextActivity2.setOnClickListener(v -> {
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(intent);
                });

        }


}