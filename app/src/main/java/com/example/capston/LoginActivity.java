package com.example.capston;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.JsonObject;

import Login.*;

import Retrofit.conRetrofit;
import retrofit2.*;

/*
400 json 오류
000 아이디 없음
111 아이디 패스워드 빈칸
222 패스워드 오류
333 이메일인증 놉
444 탑승자로그인
555 운전자로그인
 */

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
            byte[] data=pass.getBytes();

            Log.e("함 보자고","아이디"+id+"비번"+Base64.encodeToString(data,Base64.DEFAULT));
            LoginCheck client = conRetrofit.getApiClient().create(LoginCheck.class);
            LoginJsonObject loginJsonObject=new LoginJsonObject(id, Base64.encodeToString(data,Base64.DEFAULT));
            Call<LoginInfo> call = client.getJsonString(loginJsonObject);
            call.enqueue(new Callback<LoginInfo>() {
                @Override
                public void onResponse(Call<LoginInfo> call, Response<LoginInfo> response) {
                    LoginInfo res=response.body();
                    Log.e("오는지",""+response.code());
                    Log.e("오는지",response.message());
                    Log.e("바디",response.body().getResult()+" ");
                    switch (res.getResult()){
                        case "444":
                            Log.e("뭐 오는지",""+response.code());
                            Log.e("뭐 오는지",response.body().getResult()+"");
                            Toast.makeText(LoginActivity.this,"로그인 되었습니다",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                            break;
                        case "400":
                            Toast.makeText(LoginActivity.this,"로그인 실패 : 값 전달 오류",Toast.LENGTH_SHORT).show();
                            break;
                        case "222":
                            Toast.makeText(LoginActivity.this,"비밀번호가 틀렸습니다",Toast.LENGTH_SHORT).show();
                            userId.setText("");
                            userPass.setText("");
                            break;
                        case "000":
                            Toast.makeText(LoginActivity.this,"아이디가 틀렸습니다",Toast.LENGTH_SHORT).show();
                            userId.setText("");
                            userPass.setText("");
                            break;
                        case "333":
                            Toast.makeText(LoginActivity.this,"이메일 인증이 필요합니다",Toast.LENGTH_SHORT).show();
                            break;
                        case "555":
                            Toast.makeText(LoginActivity.this,"로그인 되었습니다",Toast.LENGTH_SHORT).show();
                            Intent intent1 = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent1);
                            finish();
                            break;
                    }
                }

                @Override
                public void onFailure(@NonNull Call<LoginInfo> call, @NonNull Throwable t) {
                    Log.e("오는지",""+t.getMessage());
                    Log.e("오는지",t.getLocalizedMessage());
                    Log.e("debugTest","error:(${t.message})");
                }
            });

        });
    }
}