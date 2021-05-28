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

import com.example.capston.databinding.ActivityReservPopupBinding;

import java.text.SimpleDateFormat;
import java.util.Date;

import Reserve.ReserveAPI;
import Reserve.ReserveInfo;
import Reserve.ReserveJsonObject;
import Retrofit.conRetrofit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BoardReservPopupActivity extends Activity {
    ActivityReservPopupBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_reserv_popup);

        Intent intent = getIntent();
        String confCode = intent.getStringExtra("confCode");
        String startArea = intent.getStringExtra("startArea");
        String startDateTime = intent.getStringExtra("startDateTime");
        String endArea = intent.getStringExtra("endArea");
        String boardNum = intent.getStringExtra("boardNum");
        SharedPreferences pref = getSharedPreferences("mine", MODE_PRIVATE);
        String userId = pref.getString("userId", "");
        long now = System.currentTimeMillis();
        Date mDate = new Date(now);
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String rsvDateTime = simpleDate.format(mDate);
        binding.startLocationData.setText(startArea);
        binding.startTimeData.setText(startDateTime);
        binding.arriveLocationData.setText(endArea);
        binding.passengerData.setText(boardNum);

        binding.okBtn.setOnClickListener(view->{
            ReserveAPI client = conRetrofit.getApiClient().create(ReserveAPI.class);
            ReserveJsonObject reserveJsonObject = new ReserveJsonObject(confCode, userId, rsvDateTime);
            Call<ReserveInfo> call = client.getJsonString(reserveJsonObject);
            call.enqueue(new Callback<ReserveInfo>() {
                @Override
                public void onResponse(Call<ReserveInfo> call, Response<ReserveInfo> response) {
                    String result = response.body().getResult();
                    Log.e("result",response.body().getResult());
                    if (result.equals("411")){
                        Intent intentt = new Intent();
                        intentt.putExtra("result", "Close Popup");
                        setResult(RESULT_OK, intentt);
                        finish();
                    }
                }

                @Override
                public void onFailure(Call<ReserveInfo> call, Throwable t) {
                    Log.e("실패 : ", t.getMessage());
                    Log.e("실패 : ", t.getLocalizedMessage());
                    t.printStackTrace();
                }
            });
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