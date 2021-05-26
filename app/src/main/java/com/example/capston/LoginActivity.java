package com.example.capston;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.capston.databinding.ActivityLoginBinding;
import Login.*;
import Retrofit.conRetrofit;
import retrofit2.*;
import Util.BASE64;

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
    ActivityLoginBinding binding;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=DataBindingUtil.setContentView(this,R.layout.activity_login);

        SharedPreferences pref = getSharedPreferences("mine", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("permission",0);
        int permission = pref.getInt("permission", 0);
        if(permission == 1){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        Intent secondIntent = getIntent();
        String message = secondIntent.getStringExtra("userId");
        binding.userId.setText(message);

        binding.loginBtn.setOnClickListener(view -> {
            String id = binding.userId.getText().toString();
            String pass = binding.userPass.getText().toString();
            LoginCheck client = conRetrofit.getApiClient().create(LoginCheck.class);
            LoginJsonObject loginJsonObject=new LoginJsonObject(id,new BASE64().encode(pass));
            Log.e("hi",loginJsonObject.toString());
            Call<LoginInfo> call = client.getJsonString(loginJsonObject);
            call.enqueue(new Callback<LoginInfo>() {
                @Override
                public void onResponse(Call<LoginInfo> call, Response<LoginInfo> response) {
                    Log.e("hi",response.body().getResult());
                    switch (response.body().getResult()){
                        case "444":
                            Toast.makeText(LoginActivity.this,"로그인 되었습니다(탑승자)",Toast.LENGTH_SHORT).show();
                            if(binding.autologin.isChecked()){
                                editor.putInt("permission",1);
                                editor.putString("userId",id);
                                editor.apply();
                                editor.commit();
                            }
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                            break;
                        case "400":
                            Toast.makeText(LoginActivity.this,"로그인 실패 : 값 전달 오류",Toast.LENGTH_SHORT).show();
                            break;
                        case "222":
                            Toast.makeText(LoginActivity.this,"비밀번호가 틀렸습니다",Toast.LENGTH_SHORT).show();
                            binding.userId.setText("");
                            binding.userPass.setText("");
                            break;
                        case "000":
                            Toast.makeText(LoginActivity.this,"아이디가 틀렸습니다",Toast.LENGTH_SHORT).show();
                            binding.userId.setText("");
                            binding.userPass.setText("");
                            break;
                        case "333":
                            Toast.makeText(LoginActivity.this,"이메일 인증이 필요합니다",Toast.LENGTH_SHORT).show();
                            break;
                        case "555":
                            Toast.makeText(LoginActivity.this,"로그인 되었습니다",Toast.LENGTH_SHORT).show();
                            if(binding.autologin.isChecked()){
                                editor.putInt("permission",1);
                                editor.apply();
                                editor.commit();
                            }
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

        binding.searchBtn.setOnClickListener(view->{
            Intent intent = new Intent(LoginActivity.this, SearchIdActivity.class);
            startActivity(intent);
            finish();
        });

        binding.joinBtn.setOnClickListener(view->{
            Intent intent = new Intent(LoginActivity.this, JoinActivity.class);
            startActivity(intent);
            finish();
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}