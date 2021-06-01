package com.example.capston;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.example.capston.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import Reserve.*;

import Board.BoardAPI;
import Board.BoardMainAdapter;
import Reserve.Reserve;
import Retrofit.conRetrofit;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
        ActivityMainBinding binding;
        ArrayList<JSONObject> ar=new ArrayList<>();

        @SuppressLint("ClickableViewAccessibility")
        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                binding=DataBindingUtil.setContentView(this,R.layout.activity_main);
                binding.mypage.setOnClickListener(view->{
                        Intent intent = new Intent(MainActivity.this, InfoActivity.class);
                        startActivity(intent);
                });

                BoardAPI clientt = conRetrofit.getApiClient().create(BoardAPI.class);
                Call<ResponseBody> call = clientt.getJsonString();
                call.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                try {
                                        String result = response.body().string();
                                        try {
                                                JSONArray jsonArray = new JSONArray(result);
                                                for(int i = jsonArray.length()-1; i>=jsonArray.length()-3 ; i--){
                                                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                                                        ar.add(jsonObject);
                                                        Log.e("이거 오는지",ar.toString());
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
                BoardMainAdapter boAd=new BoardMainAdapter(getApplicationContext(),ar);
                binding.listview.setAdapter(boAd);
                binding.listview.setOnItemClickListener((parent, view, position, id) -> {
                        Intent intent = new Intent(this, BoardDetailActivity.class);
                        JSONObject jo=boAd.getItem(position);
                        intent.putExtra("data", jo.toString());
                        startActivityForResult(intent, 1103);
                });

                SharedPreferences pref = getSharedPreferences("mine", MODE_PRIVATE);
                String userId = pref.getString("userId", "");

                Reserve client = conRetrofit.getApiClient().create(Reserve.class);
                ReserveTotalJsonObject reserveTotalJsonObject = new ReserveTotalJsonObject(userId);
                Call<ResponseBody> calll = client.getJsonString(reserveTotalJsonObject);
                calll.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                try {
                                        String result = response.body().string();
                                        try {
                                                JSONArray jsonArray = new JSONArray(result);
                                                if(jsonArray.length()==0){
                                                        binding.rsv1.setText("예약 내역이 없습니다");
                                                        binding.rsv2.setText("");
                                                        binding.rsv3.setText("예약 내역이 없습니다.");
                                                        binding.rsv4.setText("");
                                                }
                                                else {
                                                        for (int i = jsonArray.length()-1; i>=0 ; i--) {
                                                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                                                Log.v("예약목록", jsonObject.toString());
                                                                ar.add(jsonObject);
                                                        }
                                                        if (jsonArray.length() == 1) {
                                                                JSONObject jo = ar.get(0);
                                                                binding.rsv1.setText(jo.getString("startArea")+"에서 "+jo.getString("endArea")+"까지");
                                                                binding.rsv2.setText("출발시간 : "+jo.getString("startDateTime"));
                                                        }
                                                        else {
                                                                JSONObject jo = ar.get(0);
                                                                binding.rsv1.setText(jo.getString("startArea")+"에서 "+jo.getString("endArea")+"까지");
                                                                binding.rsv2.setText("출발시간 : "+jo.getString("startDateTime"));

                                                                jo = ar.get(1);
                                                                binding.rsv3.setText(jo.getString("startArea")+"에서 "+jo.getString("endArea")+"까지");
                                                                binding.rsv4.setText("출발시간 : "+jo.getString("startDateTime"));
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

                binding.reservText.setOnClickListener(view->{
                        Intent intent = new Intent(MainActivity.this, ReservActivity.class);
                        startActivity(intent);
                });
                binding.lateText.setOnClickListener(view->{
                        Intent intent = new Intent(MainActivity.this, BoardActivity.class);
                        startActivity(intent);
                });
                binding.chattingText.setOnClickListener(view->{
                        Intent intent = new Intent(MainActivity.this, ChatBoardActivity.class);
                        startActivity(intent);
                });
                BottomNavigationView bottomNavigationView = binding.bottomNavi;
                bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
                        switch (item.getItemId()) {
                                case R.id.show:
                                        Intent intent = new Intent(MainActivity.this, BoardActivity.class);
                                        startActivity(intent);
                                        break;
                                case R.id.write:
                                        Intent intent2 = new Intent(MainActivity.this, QRCodeActivity.class);
                                        startActivity(intent2);
                                        break;
                                case R.id.home:
                                        Intent intent3 = new Intent(MainActivity.this, MainActivity.class);
                                        startActivity(intent3);
                                        break;
                                case R.id.chat:
                                        Intent intent4 = new Intent(MainActivity.this, ChatBoardActivity.class);
                                        startActivity(intent4);
                                        break;
                                case R.id.reserv:
                                        Intent intent5 = new Intent(MainActivity.this, ReservActivity.class);
                                        startActivity(intent5);
                                        break;
                        }
                        return false;
                });
        }
}