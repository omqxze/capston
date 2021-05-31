package com.example.capston;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.capston.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import Board.BoardAPI;
import Board.BoardMainAdapter;
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

                binding.reservText.setOnClickListener(view->{
                        Intent intent = new Intent(MainActivity.this, ReservActivity.class);
                        startActivity(intent);
                });
                binding.lateText.setOnClickListener(view->{
                        Intent intent = new Intent(MainActivity.this, BoardActivity.class);
                        startActivity(intent);
                });
                binding.chattingText.setOnClickListener(view->{
                        Intent intent = new Intent(MainActivity.this, QRCodeActivity.class);
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