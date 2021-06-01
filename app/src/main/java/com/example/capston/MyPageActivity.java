package com.example.capston;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.capston.databinding.ActivityMypageBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import MyPage.MyPageAPI;
import MyPage.MyPageInfo;
import MyPage.MyPageJsonObject;
import Retrofit.conRetrofit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyPageActivity extends AppCompatActivity {
    ActivityMypageBinding binding;
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
        binding= DataBindingUtil.setContentView(this,R.layout.activity_mypage);

        SharedPreferences pref = getSharedPreferences("mine", MODE_PRIVATE);
        String userId = pref.getString("userId", "0");
        String userPass = pref.getString("userPass", "0");

        // Toolbar 활성화
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        binding.mypage.setOnClickListener(view->{
            Intent intent = new Intent(MyPageActivity.this, InfoActivity.class);
            startActivity(intent);
        });
        BottomNavigationView bottomNavigationView = binding.bottomNavi;
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.show:
                    Intent intent = new Intent(MyPageActivity.this, BoardActivity.class);
                    startActivity(intent);
                    break;
                case R.id.write:
                    Intent intent2 = new Intent(MyPageActivity.this, QRCodeActivity.class);
                    startActivity(intent2);
                    break;
                case R.id.home:
                    Intent intent3 = new Intent(MyPageActivity.this, MainActivity.class);
                    startActivity(intent3);
                    break;
                case R.id.chat:
                    Intent intent4 = new Intent(MyPageActivity.this, ChatBoardActivity.class);
                    startActivity(intent4);
                    break;
                case R.id.reserv:
                    Intent intent5 = new Intent(MyPageActivity.this, ReservActivity.class);
                    startActivity(intent5);
                    break;
            }
            return false;
        });

        MyPageAPI client = conRetrofit.getApiClient().create(MyPageAPI.class);
        MyPageJsonObject mypageJsonObject = new MyPageJsonObject(userId);
        Call<MyPageInfo> call = client.getJsonString(mypageJsonObject);
        call.enqueue(new Callback<MyPageInfo>() {
            @Override
            public void onResponse(Call<MyPageInfo> call, Response<MyPageInfo> response) {
                String userStunum = response.body().getUserStunum();
                binding.userId3.setText(userId);
                binding.userPass3.setText(userPass);
                binding.userStunum.setText(userStunum);
            }

            @Override
            public void onFailure(Call<MyPageInfo> call, Throwable t) {

            }
        });

        binding.btnEditfinish.setOnClickListener(view->{
            Intent intent = new Intent(MyPageActivity.this, EditMyPageActivity.class);
            startActivity(intent);
        });
    }
}
