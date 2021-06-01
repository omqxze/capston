package com.example.capston;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.capston.databinding.ActivityBoardReviseBinding;
import com.example.capston.databinding.ActivityBoardWriteBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONObject;

import Board.Board;
import Board.*;
import Retrofit.conRetrofit;
import lombok.SneakyThrows;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BoardReviseActivity extends AppCompatActivity {
    ActivityBoardReviseBinding binding;
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
    @SneakyThrows
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding=DataBindingUtil.setContentView(this,R.layout.activity_board_revise);

        binding.mypage.setOnClickListener(view->{
            Intent intent = new Intent(this, InfoActivity.class);
            startActivity(intent);
        });

        Intent intent = getIntent();
        JSONObject jo=new JSONObject(intent.getStringExtra("data"));
        Board b=new Board(jo);
        binding.setBoard(b);
        String confcode=jo.getString("confCode");

        // Toolbar 활성화
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        BottomNavigationView bottomNavigationView = binding.bottomNavi;
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.show:
                    Intent intent1 = new Intent(BoardReviseActivity.this, BoardActivity.class);
                    startActivity(intent1);
                    break;
                case R.id.write:
                    Intent intent2 = new Intent(BoardReviseActivity.this, QRCodeActivity.class);
                    startActivity(intent2);
                    break;
                case R.id.home:
                    Intent intent3 = new Intent(BoardReviseActivity.this, MainActivity.class);
                    startActivity(intent3);
                    break;
                case R.id.chat:
                    Intent intent4 = new Intent(BoardReviseActivity.this, ChatBoardActivity.class);
                    startActivity(intent4);
                    break;
                case R.id.reserv:
                    Intent intent5 = new Intent(BoardReviseActivity.this, ReservActivity.class);
                    startActivity(intent5);
                    break;
            }
            return false;
        });
        binding.reservYear.setSelection(1);
        binding.reservMonth.setSelection(5);
        binding.write.setOnClickListener(view->{
            String startArea = binding.startLocation.getText().toString();
            String startDateTime = value(binding.reservYear)+":"+value(binding.reservMonth)+":"+value(binding.reservDay)+" "+value(binding.reservHour)+":"+value(binding.reservMinute)+":00";
            String endArea = binding.arriveLocation.getText().toString();
            String boardNum = binding.passenger.getText().toString();
            String contents = binding.content.getText().toString();

            BoardReviseJsonObject boardReviseObject = new BoardReviseJsonObject(confcode,startArea, startDateTime, endArea, boardNum, contents);
            Intent intent1 = new Intent(BoardReviseActivity.this, BoardRevisePopupActivity.class);
            intent1.putExtra("confCode", confcode);
            intent1.putExtra("startArea", startArea);
            intent1.putExtra("startDateTime", startDateTime);
            intent1.putExtra("endArea", endArea);
            intent1.putExtra("boardNum", boardNum);
            intent1.putExtra("contents",contents);
            startActivityForResult(intent1, 1);
        });
        binding.plus.setOnClickListener(view->{
            int i=Integer.valueOf(binding.passenger.getText().toString());
            if(i+1<=4){
                binding.passenger.setText(String.valueOf(i+1));
            }
            else{
                Toast.makeText(BoardReviseActivity.this,"최대 4명을 넘을 수 없습니다.",Toast.LENGTH_SHORT).show();
            }

        });
        binding.minus.setOnClickListener(view->{
            int i=Integer.valueOf(binding.passenger.getText().toString());
            if(i-1<=0){
                Toast.makeText(BoardReviseActivity.this,"최소 1명 이상이어야합니다.",Toast.LENGTH_SHORT).show();
            }
            else{
                binding.passenger.setText(String.valueOf(i-1));
            }
        });
    }
    public String value(Spinner sp){
        return sp.getSelectedItem().toString();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                //데이터 받기
                String result = data.getStringExtra("result");
                Intent intent = new Intent(BoardReviseActivity.this, BoardActivity.class);
                startActivity(intent);
                finish();
            }
        }
    }

}