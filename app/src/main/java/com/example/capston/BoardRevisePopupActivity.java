package com.example.capston;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.Window;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;

import com.example.capston.databinding.ActivityBoardRevisePopupBinding;
import com.example.capston.databinding.ActivityReservPopupBinding;

import java.text.SimpleDateFormat;
import java.util.Date;

import Board.BoardReviseAPI;
import Board.BoardReviseInfo;
import Board.BoardReviseJsonObject;
import Reserve.ReserveAPI;
import Reserve.ReserveInfo;
import Reserve.ReserveJsonObject;
import Retrofit.conRetrofit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BoardRevisePopupActivity extends Activity {
    ActivityBoardRevisePopupBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_board_revise_popup);

        Intent intent = getIntent();
        String confCode = intent.getStringExtra("confCode");
        String startArea = intent.getStringExtra("startArea");
        String startDateTime = intent.getStringExtra("startDateTime");
        String endArea = intent.getStringExtra("endArea");
        String boardNum = intent.getStringExtra("boardNum");
        String contents=intent.getStringExtra("contents");
        binding.startLocationData.setText(startArea);
        binding.startTimeData.setText(startDateTime);
        binding.arriveLocationData.setText(endArea);
        binding.passengerData.setText(boardNum);

        binding.okBtn.setOnClickListener(view->{
            BoardReviseAPI client = conRetrofit.getApiClient().create(BoardReviseAPI.class);
            BoardReviseJsonObject BoardReviseJsonObject = new BoardReviseJsonObject(confCode,startArea,startDateTime,endArea,boardNum,contents);
            Call<BoardReviseInfo> call = client.getJsonString(BoardReviseJsonObject);
            call.enqueue(new Callback<BoardReviseInfo>() {
                @Override
                public void onResponse(Call<BoardReviseInfo> call, Response<BoardReviseInfo> response) {
                    String result = response.body().getResult();
                    if (result.equals("511")){
                        Intent intentt = new Intent();
                        intentt.putExtra("result", "Close Popup");
                        setResult(RESULT_OK, intentt);
                        finish();
                    }
                }

                @Override
                public void onFailure(Call<BoardReviseInfo> call, Throwable t) {
                    Log.e("오는지",""+t.getMessage());
                    Log.e("오는지",t.getLocalizedMessage());
                    Log.e("debugTest","error:(${t.message})");
                }
            });
        });
        binding.noBtn.setOnClickListener(view->{
            Intent intentt = new Intent();
            intentt.putExtra("result", "Close Popup");
            setResult(RESULT_OK, intentt);
            finish();
        });
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //바깥레이어 클릭시 안닫히게
        if(event.getAction()==MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }
    @Override
    public void onBackPressed() {
        //안드로이드 백버튼 막기
        return;
    }
}