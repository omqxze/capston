package com.example.capston;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.capston.databinding.ActivityBoardDetailBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BoardDetailActivity extends AppCompatActivity {
    ActivityBoardDetailBinding binding;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_board_detail);

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        BottomNavigationView bottomNavigationView = binding.bottomNavi;
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.show:
                        Intent intent = new Intent(BoardDetailActivity.this, BoardActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                    case R.id.write:
                        break;
                    case R.id.home:
                        Intent intent3 = new Intent(BoardDetailActivity.this, MainActivity.class);
                        startActivity(intent3);
                        break;
                    case R.id.chat:
                        Intent intent4 = new Intent(BoardDetailActivity.this, ChatBoardActivity.class);
                        startActivity(intent4);
                        break;
                    case R.id.reserv:
                        break;
                }
                return false;
            }
        });

        binding.reservBtn.setOnClickListener(view->{
            Intent intent = new Intent(this, BoardReservPopupActivity.class);
            intent.putExtra("data", "Test Popup");
            startActivityForResult(intent, 1);
        });
        binding.rewriteBtn.setOnClickListener(view->{
            Intent intent = new Intent(BoardDetailActivity.this, BoardReviseActivity.class);
            startActivity(intent);
        });
        binding.delBtn.setOnClickListener(view->{
            Intent intent = new Intent(this, BoardDeletePopupActivity.class);
            intent.putExtra("data", "Test Popup");
            startActivityForResult(intent, 1);
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                //데이터 받기
                String result = data.getStringExtra("result");
                Intent intent = new Intent(BoardDetailActivity.this, BoardActivity.class);
                startActivity(intent);
                finish();
            }
        }
    }
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
}
