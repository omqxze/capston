package com.example.capston;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.capston.databinding.ActivityReservlistBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import Reserve.*;
import java.io.IOException;
import java.util.ArrayList;

import Reserve.Reserve;
import Retrofit.conRetrofit;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReservActivity extends AppCompatActivity {
    ActivityReservlistBinding binding;
    ArrayList<JSONObject> ar = new ArrayList<>();
    String confCode1;
    String confCode2;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_reservlist);

        // Toolbar 활성화
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        BottomNavigationView bottomNavigationView = binding.bottomNavi;
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.show:
                        Intent intent = new Intent(ReservActivity.this, BoardActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.write:
                        Intent intent2 = new Intent(ReservActivity.this, QRCodeActivity.class);
                        startActivity(intent2);
                        break;
                    case R.id.home:
                        Intent intent3 = new Intent(ReservActivity.this, MainActivity.class);
                        startActivity(intent3);
                        break;
                    case R.id.chat:
                        Intent intent4 = new Intent(ReservActivity.this, ChatBoardActivity.class);
                        startActivity(intent4);
                        break;
                    case R.id.reserv:
                        Intent intent5 = new Intent(ReservActivity.this, ReservActivity.class);
                        startActivity(intent5);
                        break;
                }
                return false;
            }
        });
        binding.mypage.setOnClickListener(view->{
            Intent intent = new Intent(ReservActivity.this, InfoActivity.class);
            startActivity(intent);
        });


        SharedPreferences pref = getSharedPreferences("mine", MODE_PRIVATE);
        String userId = pref.getString("userId", "");

        Reserve client = conRetrofit.getApiClient().create(Reserve.class);
        ReserveTotalJsonObject reserveTotalJsonObject = new ReserveTotalJsonObject(userId);
        Call<ResponseBody> call = client.getJsonString(reserveTotalJsonObject);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String result = response.body().string();
                    try {
                        JSONArray jsonArray = new JSONArray(result);
                        if(jsonArray.length()==0){
                            binding.startLocation.setText("출발지 : ");
                            binding.arriveLocation.setText("도착지 : ");
                            binding.startTime.setText("출발시간 : ");
                            binding.passengerNum.setText("차량 번호 : ");

                            binding.startLocation2.setText("출발지 : ");
                            binding.arriveLocation2.setText("도착지 : ");
                            binding.startTime2.setText("출발시간 : ");
                            binding.passengerNum2.setText("차량 번호 : ");
                        }
                        else {
                            for (int i = jsonArray.length()-1; i>=0 ; i--) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                Log.v("예약목록", jsonObject.toString());
                                ar.add(jsonObject);
                            }
                            if (jsonArray.length() == 1) {
                                JSONObject jo = ar.get(0);
                                binding.startLocation.setText("출발지 : "+jo.getString("startArea"));
                                binding.arriveLocation.setText("도착지 : "+jo.getString("endArea"));
                                binding.startTime.setText("출발시간 : "+jo.getString("startDateTime"));
                                binding.passengerNum.setText("차량 번호 : "+jo.getString("carNum"));
                            }
                            else {
                                JSONObject jo = ar.get(0);
                                binding.startLocation.setText("출발지 : "+jo.getString("startArea"));
                                binding.arriveLocation.setText("도착지 : "+jo.getString("endArea"));
                                binding.startTime.setText("출발시간 : "+jo.getString("startDateTime"));
                                binding.passengerNum.setText("차량 번호 : "+jo.getString("carNum"));

                                jo = ar.get(1);
                                binding.startLocation2.setText("출발지 : "+jo.getString("startArea"));
                                binding.arriveLocation2.setText("도착지 : "+jo.getString("endArea"));
                                binding.startTime2.setText("출발시간 : "+jo.getString("startDateTime"));
                                binding.passengerNum2.setText("차량 번호 : "+jo.getString("carNum"));
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("실패 : ", t.getMessage());
                Log.e("실패 : ", t.getLocalizedMessage());
            }
        });

        binding.reservDeleteBtn.setOnClickListener(view -> {
            ReserveDeleteAPI client2 = conRetrofit.getApiClient().create(ReserveDeleteAPI.class);
            if(binding.delcheck1.isChecked()){
                ReserveDeleteJsonObject rsvJo = new ReserveDeleteJsonObject(confCode1, userId);
                Call<ReserveDeleteInfo> call2 = client2.getJsonString(rsvJo);
                call2.enqueue(new Callback<ReserveDeleteInfo>() {
                    @Override
                    public void onResponse(Call<ReserveDeleteInfo> call, Response<ReserveDeleteInfo> response) {
                        String result = response.body().getResult();
                        if(result.equals("401")){
                            Intent intent = new Intent(ReservActivity.this, ReservActivity.class);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<ReserveDeleteInfo> call, Throwable t) {
                        Log.e("실패 : ", t.getMessage());
                        Log.e("실패 : ", t.getLocalizedMessage());
                    }
                });
            }
            if(binding.delcheck2.isChecked()){
                ReserveDeleteJsonObject rsvJo = new ReserveDeleteJsonObject(confCode2, userId);
                Log.e("값",confCode2+" , "+userId);
                Call<ReserveDeleteInfo> call2 = client2.getJsonString(rsvJo);
                call2.enqueue(new Callback<ReserveDeleteInfo>() {
                    @Override
                    public void onResponse(Call<ReserveDeleteInfo> call, Response<ReserveDeleteInfo> response) {
                        String result = response.body().getResult();
                        if(result.equals("401")){
                            Intent intent = new Intent(ReservActivity.this, ReservActivity.class);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<ReserveDeleteInfo> call, Throwable t) {
                        Log.e("실패 : ", t.getMessage());
                        Log.e("실패 : ", t.getLocalizedMessage());
                    }
                });
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:{
                Intent intent = new Intent(ReservActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}