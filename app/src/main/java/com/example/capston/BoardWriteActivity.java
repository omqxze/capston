package com.example.capston;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.capston.databinding.BoardWriteBinding;

import Board.BoardAPI;
import Board.BoardInfo;
import Board.BoardJsonObject;
import Login.LoginCheck;
import Retrofit.conRetrofit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BoardWriteActivity extends AppCompatActivity {
    BoardWriteBinding binding;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_board_write);

        // Toolbar 활성화
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        binding.write.setOnClickListener(view->{
            String startArea = binding.startLocation.getText().toString();
            String startDateTime = binding.startTime.getText().toString();
            String endArea = binding.arriveLocation.getText().toString();
            String boardNum = binding.passenger.getText().toString();
            String contents = binding.content.getText().toString();

            BoardAPI client = conRetrofit.getApiClient().create(BoardAPI.class);
            BoardJsonObject boardJsonObject = new BoardJsonObject(startArea, startDateTime, endArea, boardNum, contents);
            Call<BoardInfo> call = client.getJsonString(boardJsonObject);
            call.enqueue(new Callback<BoardInfo>() {
                @Override
                public void onResponse(Call<BoardInfo> call, Response<BoardInfo> response) {
                    String result = response.body().getResult();
                    if (result.equals("101"))  {   //성공이라고 가정
                        Intent intent = new Intent(BoardWriteActivity.this, BoardWritePopupActivity.class);
                        intent.putExtra("data", "Test Popup");
                        startActivityForResult(intent, 1);
                    }
                    else {
                        Log.e("실패", "실패");
                    }
                }

                @Override
                public void onFailure(Call<BoardInfo> call, Throwable t) {

                }
            });
        });
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
        }
    }
}