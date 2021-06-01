package com.example.capston;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.capston.databinding.ActivityInfoBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import Util.SaveSharedPreference;


public class InfoActivity extends AppCompatActivity {
    ActivityInfoBinding binding;
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: { //toolbar의 back키 눌렀을 때 동작
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_info);

        // Toolbar 활성화
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        binding.mypage.setOnClickListener(view->{
            Intent intent = new Intent(InfoActivity.this, InfoActivity.class);
            startActivity(intent);
        });
        BottomNavigationView bottomNavigationView = binding.bottomNavi;
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.show:
                    Intent intent = new Intent(InfoActivity.this, BoardActivity.class);
                    startActivity(intent);
                    break;
                case R.id.write:
                    Intent intent2 = new Intent(InfoActivity.this, QRCodeActivity.class);
                    startActivity(intent2);
                    break;
                case R.id.home:
                    Intent intent3 = new Intent(InfoActivity.this, MainActivity.class);
                    startActivity(intent3);
                    break;
                case R.id.chat:
                    Intent intent4 = new Intent(InfoActivity.this, ChatBoardActivity.class);
                    startActivity(intent4);
                    break;
                case R.id.reserv:
                    Intent intent5 = new Intent(InfoActivity.this, BoardDetailActivity.class);
                    startActivity(intent5);
                    break;
            }
            return false;
        });

        binding.mypageText.setOnClickListener(view->{
            Intent intent = new Intent(InfoActivity.this, MyPageActivity.class);
            startActivity(intent);
        });

        binding.infoText.setOnClickListener(view->{
            Intent intent = new Intent(InfoActivity.this, GuideActivity.class);
            startActivity(intent);
        });

        binding.pastText.setOnClickListener(view->{
            Intent intent = new Intent(InfoActivity.this, PathHistoryActivity.class);
            startActivity(intent);
        });

    }
}