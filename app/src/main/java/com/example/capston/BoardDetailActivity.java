package com.example.capston;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.capston.databinding.ActivityBoardDetailBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import Board.Board;
import lombok.SneakyThrows;

public class BoardDetailActivity extends AppCompatActivity {
    ActivityBoardDetailBinding binding;

    @SneakyThrows
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_board_detail);

        SharedPreferences pref = getSharedPreferences("mine", MODE_PRIVATE);
        String userId = pref.getString("userId", "");

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
                        Intent intent2 = new Intent(BoardDetailActivity.this, QRCodeActivity.class);
                        startActivity(intent2);
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
                        Intent intent5 = new Intent(BoardDetailActivity.this, ReservActivity.class);
                        startActivity(intent5);
                        break;
                }
                return false;
            }
        });

        Intent intent = getIntent();
        JSONObject jo=new JSONObject(intent.getStringExtra("data"));
        Log.e("뭘봐",jo.getString("startArea"));
        Board b=new Board(jo);
        binding.setBoard(b);

        binding.reservBtn.setOnClickListener(view->{
            Intent intent2 = new Intent(this, BoardReservPopupActivity.class);
            intent2.putExtra("data", "Test Popup");
            startActivityForResult(intent2, 1);
        });
        binding.rewriteBtn.setOnClickListener(view->{
            Intent intent3 = new Intent(BoardDetailActivity.this, BoardReviseActivity.class);
            startActivity(intent3);
        });
        binding.delBtn.setOnClickListener(view->{
            Intent intent4 = new Intent(this, BoardDeletePopupActivity.class);
            try {
                intent4.putExtra("confCode", jo.getString("confCode"));
                intent4.putExtra("userId",userId);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            startActivityForResult(intent4, 1);
        });
    }
    @SneakyThrows
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
