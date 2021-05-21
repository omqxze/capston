package com.example.capston;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.capston.databinding.ActivityGuideBinding;
import com.example.capston.databinding.ActivityInfoBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class GuideActivity extends AppCompatActivity {
    ActivityGuideBinding binding;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_guide);

        // Toolbar 활성화
        setSupportActionBar(binding.toolbar11);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        BottomNavigationView bottomNavigationView = binding.bottomNavi;
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.show:
                        Intent intent = new Intent(GuideActivity.this, BoardActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.write:
                        Intent intent2 = new Intent(GuideActivity.this, QRCodeActivity.class);
                        startActivity(intent2);
                        break;
                    case R.id.home:
                        Intent intent3 = new Intent(GuideActivity.this, MainActivity.class);
                        startActivity(intent3);
                        break;
                    case R.id.chat:
                        Intent intent4 = new Intent(GuideActivity.this, ChatBoardActivity.class);
                        startActivity(intent4);
                        break;
                    case R.id.reserv:
                        Intent intent5 = new Intent(GuideActivity.this, ReservActivity.class);
                        startActivity(intent5);
                        break;
                }
                return false;
            }
        });
    }
}