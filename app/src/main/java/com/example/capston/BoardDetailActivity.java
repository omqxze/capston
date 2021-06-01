package com.example.capston;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

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
    String userId2;
    @SneakyThrows
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_board_detail);

        SharedPreferences pref = getSharedPreferences("mine", MODE_PRIVATE);
        String userId = pref.getString("userId", "");

        binding.mypage.setOnClickListener(view->{
            Intent intent = new Intent(this, InfoActivity.class);
            startActivity(intent);
        });
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
        Board b=new Board(jo);
        binding.setBoard(b);

        try {
             userId2 = jo.getString("userId");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        binding.reservBtn.setOnClickListener(view->{
            if(!userId.equals(userId2)){
                Intent intent2 = new Intent(this, BoardReservPopupActivity.class);
                try {
                    intent2.putExtra("confCode", jo.getString("confCode"));
                    intent2.putExtra("startArea", jo.getString("startArea"));
                    intent2.putExtra("startDateTime", jo.getString("startDateTime"));
                    intent2.putExtra("endArea", jo.getString("endArea"));
                    intent2.putExtra("boardNum", jo.getString("boardNum"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                startActivityForResult(intent2, 1);
            }
            else
                Toast.makeText(BoardDetailActivity.this,"권한이 없습니다.",Toast.LENGTH_SHORT).show();
        });
        binding.rewriteBtn.setOnClickListener(view->{
            if(userId.equals(userId2)){
                Intent intent3 = new Intent(BoardDetailActivity.this, BoardReviseActivity.class);
                intent3.putExtra("data",jo.toString());
                startActivityForResult(intent3,1);
            }
            else
                Toast.makeText(BoardDetailActivity.this,"권한이 없습니다.",Toast.LENGTH_SHORT).show();
        });
        binding.delBtn.setOnClickListener(view->{
            if(userId.equals(userId2)){
                Intent intent4 = new Intent(this, BoardDeletePopupActivity.class);
                try {
                    intent4.putExtra("confCode", jo.getString("confCode"));
                    intent4.putExtra("userId",userId);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                startActivityForResult(intent4, 1);
            }
            else
                Toast.makeText(BoardDetailActivity.this,"본인만 가능합니다.",Toast.LENGTH_SHORT).show();
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
