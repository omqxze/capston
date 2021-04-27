package com.example.capston;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.capston.databinding.ActivityJoinBinding;

import Join.JoinAPI;
import Join.JoinInfo;
import Join.JoinJsonObject;
import Retrofit.conRetrofit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JoinActivity extends AppCompatActivity {
    ActivityJoinBinding binding;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_join);

        binding.registerBtn.setOnClickListener(view->{
            JoinAPI client = conRetrofit.getApiClient().create(JoinAPI.class);
            JoinJsonObject JoinJsonObject=new JoinJsonObject();
            Call<JoinInfo> call = client.getJsonString(JoinJsonObject);
            call.enqueue(new Callback<JoinInfo>() {
                @Override
                public void onResponse(Call<JoinInfo> call, Response<JoinInfo> response) {
                    switch(response.body().getResult()){
                        case "101":
                            Toast.makeText(JoinActivity.this,"회원가입 되었습니다(탑승자)",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(JoinActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                        case "102":
                            Toast.makeText(JoinActivity.this,"로그인 되었습니다(운전자)",Toast.LENGTH_SHORT).show();
                            intent = new Intent(JoinActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                    }
                }

                @Override
                public void onFailure(Call<JoinInfo> call, Throwable t) {
                    Log.e("오는지",""+t.getMessage());
                    Log.e("오는지",t.getLocalizedMessage());
                    Log.e("debugTest","error:(${t.message})");
                }
            });

        });

    }
}
