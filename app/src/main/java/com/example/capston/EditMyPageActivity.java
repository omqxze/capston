package com.example.capston;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.capston.databinding.ActivityEditmypageBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class EditMyPageActivity extends AppCompatActivity {
    ActivityEditmypageBinding binding;
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_editmypage);
        // Toolbar 활성화
        setSupportActionBar(binding.toolbar2);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        BottomNavigationView bottomNavigationView = binding.bottomNavi;
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.show:
                    Intent intent = new Intent(EditMyPageActivity.this, BoardActivity.class);
                    startActivity(intent);
                    break;
                case R.id.write:
                    Intent intent2 = new Intent(EditMyPageActivity.this, QRCodeActivity.class);
                    startActivity(intent2);
                    break;
                case R.id.home:
                    Intent intent3 = new Intent(EditMyPageActivity.this, MainActivity.class);
                    startActivity(intent3);
                    break;
                case R.id.chat:
                    Intent intent4 = new Intent(EditMyPageActivity.this, ChatBoardActivity.class);
                    startActivity(intent4);
                    break;
                case R.id.reserv:
                    break;
            }
            return false;
        });

        binding.btnEditfinish.setOnClickListener(view->{
            Intent intent = new Intent(EditMyPageActivity.this, MyPageActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
