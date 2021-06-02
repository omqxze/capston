package com.example.capston;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.capston.databinding.ActivityBoardWriteBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import Board.BoardWriteAPI;
import Board.BoardWriteInfo;
import Board.BoardWriteJsonObject;
import Retrofit.conRetrofit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BoardWriteActivity extends AppCompatActivity {
    ActivityBoardWriteBinding binding;
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
        binding= DataBindingUtil.setContentView(this,R.layout.activity_board_write);

        binding.mypage.setOnClickListener(view->{
            Intent intent = new Intent(this, InfoActivity.class);
            startActivity(intent);
        });

        SharedPreferences pref = getSharedPreferences("mine", MODE_PRIVATE);
        String userId = pref.getString("userId", "");
/*
        Intent secondIntent = getIntent();
        String start_location = secondIntent.getStringExtra("start_location");
        binding.startLocation.setText(start_location);

        Intent thirdIntent = getIntent();
        String end_location = thirdIntent.getStringExtra("end_location");
        binding.arriveLocation.setText(end_location);
*/
        // Toolbar 활성화
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        BottomNavigationView bottomNavigationView = binding.bottomNavi;
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.show:
                    Intent intent = new Intent(BoardWriteActivity.this, BoardActivity.class);
                    startActivity(intent);
                    break;
                case R.id.write:
                    Intent intent2 = new Intent(BoardWriteActivity.this, QRCodeActivity.class);
                    startActivity(intent2);
                    break;
                case R.id.home:
                    Intent intent3 = new Intent(BoardWriteActivity.this, MainActivity.class);
                    startActivity(intent3);
                    break;
                case R.id.chat:
                    Intent intent4 = new Intent(BoardWriteActivity.this, ChatBoardActivity.class);
                    startActivity(intent4);
                    break;
                case R.id.reserv:
                    Intent intent5 = new Intent(BoardWriteActivity.this, ReservActivity.class);
                    startActivity(intent5);
                    break;
            }
            return false;
        });
        binding.reservYear.setSelection(1);
        binding.reservMonth.setSelection(5);
        binding.staLocation.setOnClickListener(view -> {
            Intent intent = new Intent(BoardWriteActivity.this, MapActivity.class);
            startActivityForResult(intent,2);
        });
        binding.arrLocation.setOnClickListener(view -> {
            Intent intent = new Intent(BoardWriteActivity.this, MapActivity2.class);
            startActivityForResult(intent,3);
        });
        binding.write.setOnClickListener(view->{
            String startArea = binding.startLocation.getText().toString();
            String startDateTime = value(binding.reservYear)+":"+value(binding.reservMonth)+":"+value(binding.reservDay)+" "+value(binding.reservHour)+":"+value(binding.reservMinute)+":00";
            String endArea = binding.arriveLocation.getText().toString();
            String boardNum = binding.passenger.getText().toString();
            String contents = binding.content.getText().toString();

            BoardWriteAPI client = conRetrofit.getApiClient().create(BoardWriteAPI.class);
            BoardWriteJsonObject boardJsonObject = new BoardWriteJsonObject(userId,startArea, startDateTime, endArea, boardNum, contents);
            Call<BoardWriteInfo> call = client.getJsonString(boardJsonObject);
            call.enqueue(new Callback<BoardWriteInfo>() {
                @Override
                public void onResponse(Call<BoardWriteInfo> call, Response<BoardWriteInfo> response) {
                    String result = response.body().getResult();

                    if (result.equals("501"))  {   //성공이라고 가정
                        Intent intent = new Intent(BoardWriteActivity.this, BoardWritePopupActivity.class);
                        intent.putExtra("data", "Test Popup");
                        startActivityForResult(intent, 1);
                    }
                    else {
                        Log.e("실패", "실패");
                    }
                }

                @Override
                public void onFailure(Call<BoardWriteInfo> call, Throwable t) {

                }
            });
        });
        binding.plus.setOnClickListener(view->{
            int i=Integer.valueOf(binding.passenger.getText().toString());
            if(i+1<=4){
                binding.passenger.setText(String.valueOf(i+1));
            }
            else{
                Toast.makeText(BoardWriteActivity.this,"최대 4명을 넘을 수 없습니다.",Toast.LENGTH_SHORT).show();
            }

        });
        binding.minus.setOnClickListener(view->{
            int i=Integer.valueOf(binding.passenger.getText().toString());
            if(i-1<=0){
                Toast.makeText(BoardWriteActivity.this,"최소 1명 이상이어야합니다.",Toast.LENGTH_SHORT).show();
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
                Intent intent = new Intent(BoardWriteActivity.this, BoardActivity.class);
                startActivity(intent);
                finish();
            }
        }else if(requestCode==2){
            if (resultCode==RESULT_OK){
                String result=data.getStringExtra("start_location");
                binding.startLocation.setText(result);
            }
        }
        else if(requestCode==3){
            if (resultCode==RESULT_OK){
                String result=data.getStringExtra("end_location");
                binding.arriveLocation.setText(result);
            }
        }
    }

}