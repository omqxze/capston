package com.example.capston;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.capston.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
        ActivityMainBinding binding;

        @SuppressLint("ClickableViewAccessibility")
        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                binding=DataBindingUtil.setContentView(this,R.layout.activity_main);
                binding.reservText.setOnClickListener(view->{
                        Intent intent = new Intent(MainActivity.this, MainActivity.class);
                        startActivity(intent);
                });
                binding.lateText.setOnClickListener(view->{
                        Intent intent = new Intent(MainActivity.this, BoardActivity.class);
                        startActivity(intent);
                });
                binding.chattingText.setOnClickListener(view->{
                        Intent intent = new Intent(MainActivity.this, ChatBoardActivity.class);
                        startActivity(intent);
                });

                BottomNavigationView bottomNavigationView = binding.bottomNavi;
                bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
                        switch (item.getItemId()) {
                                case R.id.show:
                                        Intent intent = new Intent(MainActivity.this, BoardActivity.class);
                                        startActivity(intent);
                                        break;
                                case R.id.write:
                                        break;
                                case R.id.home:
                                        Intent intent3 = new Intent(MainActivity.this, MainActivity.class);
                                        startActivity(intent3);
                                        break;
                                case R.id.chat:
                                        Intent intent4 = new Intent(MainActivity.this, ChatBoardActivity.class);
                                        startActivity(intent4);
                                        break;
                                case R.id.reserv:
                                        break;
                        }
                        return false;
                });
        }
}