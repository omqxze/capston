package com.example.capston;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;


import com.example.capston.databinding.ActivityQrcodeBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class QRCodeActivity extends AppCompatActivity {
    ActivityQrcodeBinding binding;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_qrcode);
        // Toolbar 활성화
        setSupportActionBar(binding.toolbar7);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        BottomNavigationView bottomNavigationView = binding.bottomNavi;
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.show:
                    Intent intent = new Intent(QRCodeActivity.this, BoardActivity.class);
                    startActivity(intent);
                    break;
                case R.id.write:
                    Intent intent2 = new Intent(QRCodeActivity.this, QRCodeActivity.class);
                    startActivity(intent2);
                    break;
                case R.id.home:
                    Intent intent3 = new Intent(QRCodeActivity.this, MainActivity.class);
                    startActivity(intent3);
                    break;
                case R.id.chat:
                    Intent intent4 = new Intent(QRCodeActivity.this, ChatBoardActivity.class);
                    startActivity(intent4);
                    break;
                case R.id.reserv:
                    Intent intent5 = new Intent(QRCodeActivity.this, BoardDetailActivity.class);
                    startActivity(intent5);
                    break;
            }
            return false;
        });
    }
}
