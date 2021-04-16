package com.example.capston;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Login.*;
import Util.*;

import Retrofit.conRetrofit;
import retrofit2.*;


public class LoginActivity extends AppCompatActivity {
    private EditText userId, userPass;
    private Button loginBtn;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userId = findViewById(R.id.userId);
        userPass = findViewById(R.id.userPass);
        loginBtn = findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(view -> {
            String id = userId.getText().toString();
            String pass = userPass.getText().toString();
            Log.e("함 보자고","아이디"+id+"비번"+pass);
            LoginCheck client = conRetrofit.getApiClient().create(LoginCheck.class);
            Call<loginInfo> call = client.setPostField(id, pass);
            call.enqueue(new Callback<loginInfo>() {
                @Override
                public void onResponse(Call<loginInfo> call, Response<loginInfo> response) {
                    Log.e("오는지",""+response.code());
                    Log.e("오는지",response.message());
                    Log.e("바디",response.body().getResult()+" ");
                    switch (response.code()){
                        case 777:
                            Log.e("뭐 오는지",""+response.code());
                            Log.e("뭐 오는지",response.body().getResult()+"");
                            Toast.makeText(LoginActivity.this,"로그인 되었습니다",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                            break;
                        case 500:
                            Toast.makeText(LoginActivity.this,"로그인 실패 : 서버 오류",Toast.LENGTH_SHORT).show();
                            break;
                        case 334:
                            Toast.makeText(LoginActivity.this,"비밀번호가 틀렸습니다",Toast.LENGTH_SHORT).show();
                            userId.setText("");
                            userPass.setText("");
                            break;
                        case 333:
                            Toast.makeText(LoginActivity.this,"아이디가 틀렸습니다",Toast.LENGTH_SHORT).show();
                            userId.setText("");
                            userPass.setText("");
                            break;
                        case 335:
                            Toast.makeText(LoginActivity.this,"이메일 인증이 필요합니다",Toast.LENGTH_SHORT).show();
                            break;
                        case 888:
                            Toast.makeText(LoginActivity.this,"로그인 되었습니다",Toast.LENGTH_SHORT).show();
                            Intent intent1 = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent1);
                            finish();
                            break;
                    }
                }

                @Override
                public void onFailure(@NonNull Call<loginInfo> call,@NonNull Throwable t) {
                    Log.e("오는지",""+t.getMessage());
                    Log.e("오는지",t.getLocalizedMessage());
                    Log.e("debugTest","error:(${t.message})");
                }
            });

        });
    }
}