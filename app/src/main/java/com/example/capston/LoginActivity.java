package com.example.capston;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Login.LoginCheck;
import Login.loginInfo;
import Retrofit.conRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity {
    private EditText userId, userPass;
    private Button login_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userId = findViewById(R.id.userId);
        userPass = findViewById(R.id.userPass);
        login_button = findViewById(R.id.loginBtn);
        login_button.setOnClickListener(view -> {
            String id = userId.getText().toString();
            String pass = userPass.getText().toString();

            LoginCheck client = conRetrofit.getApiClient().create(LoginCheck.class);
            Call<loginInfo> call = client.setPostField(id, pass);
            call.enqueue(new Callback<loginInfo>() {
                @Override
                public void onResponse(Call<loginInfo> call, Response<loginInfo> response) {

                    switch (response.code()){
                        case 200:
                            Toast.makeText(LoginActivity.this,"로그인",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                            break;
                        case 404:
                            Toast.makeText(LoginActivity.this,"로그인 실패 : 서버 오류",Toast.LENGTH_SHORT).show();
                            break;
                        case 500:
                            Toast.makeText(LoginActivity.this,"이메일 인증이 필요합니다",Toast.LENGTH_SHORT).show();
                            break;
                        case 600:
                            Toast.makeText(LoginActivity.this,"아이디와 비밀번호를 확인해주세요",Toast.LENGTH_SHORT).show();
                            userId.setText("");
                            userPass.setText("");
                            break;
                    }
                }

                @Override
                public void onFailure(@NonNull Call<loginInfo> call,@NonNull Throwable t) {
                    Log.e("debugTest","error:"+t.getMessage());
                }
            });

        });
    }
}