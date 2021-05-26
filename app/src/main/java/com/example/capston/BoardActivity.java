package com.example.capston;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.capston.databinding.ActivityBoardBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import Board.BoardAPI;
import Board.BoardAdapter;
import Retrofit.conRetrofit;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BoardActivity extends AppCompatActivity {
    ActivityBoardBinding binding;
    ArrayList<JSONObject> ar=new ArrayList<>();
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
        binding= DataBindingUtil.setContentView(this,R.layout.activity_board);

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        BottomNavigationView bottomNavigationView = binding.bottomNavi;
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.show:
                    Intent intent = new Intent(BoardActivity.this, BoardActivity.class);
                    startActivity(intent);
                    break;
                case R.id.write:
                    Intent intent2 = new Intent(BoardActivity.this, QRCodeActivity.class);
                    startActivity(intent2);
                    break;
                case R.id.home:
                    Intent intent3 = new Intent(BoardActivity.this, MainActivity.class);
                    startActivity(intent3);
                    break;
                case R.id.chat:
                    Intent intent4 = new Intent(BoardActivity.this, ChatBoardActivity.class);
                    startActivity(intent4);
                    break;
                case R.id.reserv:
                    Intent intent5 = new Intent(BoardActivity.this, ReservActivity.class);
                    startActivity(intent5);
                    break;
            }
            return false;
        });

        String key = "key";
        BoardAPI client = conRetrofit.getApiClient().create(BoardAPI.class);
        Call<ResponseBody> call = client.getJsonString();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String result = response.body().string();
                    try {
                        JSONArray jsonArray = new JSONArray(result);
                        for(int i = jsonArray.length()-1; i>=0 ; i--){
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String confCode = jsonObject.getString("confCode");
                            String userId = jsonObject.getString("userId");
                            String boardNum = jsonObject.getString("boardNum");
                            String rsvCount = jsonObject.getString("rsvCount");
                            //String startDateTime = jsonObject.getString("startDatetime");
                            String startArea = jsonObject.getString("startArea");
                            String endArea = jsonObject.getString("endArea");
                            String contents = jsonObject.getString("contents");
                            String rsvStat = jsonObject.getString("rsvStat");
                            Log.v("Test", jsonObject.toString());
                            ar.add(jsonObject);
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

        BoardAdapter boAd=new BoardAdapter(getApplicationContext(),ar);
        binding.listview.setAdapter(boAd);
        binding.listview.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(this, BoardDetailActivity.class);
            JSONObject jo=boAd.getItem(position);
            try {
                Log.e("test",jo.getString("startArea"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            intent.putExtra("data", jo.toString());
            Log.e("test",jo.toString());
            startActivityForResult(intent, 1103);
        });

        binding.writeBtn.setOnClickListener(view->{
            Intent intent = new Intent(BoardActivity.this, BoardWriteActivity.class);
            startActivity(intent);

        });
    }

}