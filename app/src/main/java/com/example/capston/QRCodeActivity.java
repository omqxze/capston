package com.example.capston;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;


import com.example.capston.databinding.ActivityQrcodeBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.zxing.WriterException;

import QR.QRAPI;
import QR.QRCreate;
import QR.QRInfo;
import QR.QRJsonObject;
import Retrofit.conRetrofit;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QRCodeActivity extends AppCompatActivity {
    ActivityQrcodeBinding binding;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_qrcode);
        // Toolbar 활성화
        setSupportActionBar(binding.toolbar7);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        SharedPreferences pref = getSharedPreferences("mine", MODE_PRIVATE);
        String userId = pref.getString("userId", "");

        BottomNavigationView bottomNavigationView = binding.bottomNavi;
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.show:
                    Intent intent = new Intent(QRCodeActivity.this, BoardActivity.class);
                    startActivity(intent);
                    break;
                case R.id.write:
                    Intent intent2 = new Intent(QRCodeActivity.this, QRCodeActivity.class);
                    startActivity(intent2);
                    break;
                case R.id.home:
                    Intent intent3 = new Intent(QRCodeActivity.this, MainActivity.class);
                    startActivity(intent3);
                    break;
                case R.id.chat:
                    Intent intent4 = new Intent(QRCodeActivity.this, ChatBoardActivity.class);
                    startActivity(intent4);
                    break;
                case R.id.reserv:
                    Intent intent5 = new Intent(QRCodeActivity.this, BoardDetailActivity.class);
                    startActivity(intent5);
                    break;
            }
            return false;
        });
        binding.btnCreateQR.setOnClickListener(view->{
            QRAPI client = conRetrofit.getApiClient().create(QRAPI.class);
            QRJsonObject qrJsonObject = new QRJsonObject(userId);
            Call<QRInfo> call = client.getJsonString(qrJsonObject);
             call.enqueue(new Callback<QRInfo>() {
            @Override
        public void onResponse(Call<QRInfo> call, Response<QRInfo> response) {
            String result = response.body().getUserId();
            Log.e("res",result);
            QRCreate qrCreate = new QRCreate();
            try {
                binding.textView22.setBackground(qrCreate.CreateQR(result));
            } catch (WriterException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onFailure(Call<QRInfo> call, Throwable t) {

        }
    });
});

    }
}