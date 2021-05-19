package com.example.capston;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.capston.databinding.ActivitySearchidBinding;

import Login.LoginCheck;
import Login.LoginInfo;
import Login.LoginJsonObject;
import Login.SearchIdAPI;
import Login.SearchIdInfo;
import Login.SearchIdJsonObject;
import Retrofit.conRetrofit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchIdActivity extends AppCompatActivity{


    ActivitySearchidBinding binding;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_searchid);

        binding.userStunum.setOnClickListener(view->{
            binding.userStunum.setText(null);
        });
        binding.IdBtn.setOnClickListener(view->{
            if (binding.userStunum.getText().toString().equals("")){
                Toast.makeText(SearchIdActivity.this,"학번을 입력해주세요",Toast.LENGTH_SHORT).show();
            }
            else{
                Intent intent = new Intent(SearchIdActivity.this, SearchIdActivity.class);
                startActivity(intent);
                String stunum = binding.userStunum.getText().toString();
                SearchIdAPI client = conRetrofit.getApiClient().create(SearchIdAPI.class);
                SearchIdJsonObject searchIdJsonObject = new SearchIdJsonObject(stunum);
                Call<SearchIdInfo> call = client.getJsonString(searchIdJsonObject);
                call.enqueue(new Callback<SearchIdInfo>() {
                    @Override
                    public void onResponse(Call<SearchIdInfo> call, Response<SearchIdInfo> response) {
                        String userId = response.body().getResult();
                        if (userId.equals("001")){
                            Toast.makeText(SearchIdActivity.this,"아이디가 존재하지 않습니다.",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(SearchIdActivity.this,userId,Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(SearchIdActivity.this, LoginActivity.class);
                            intent.putExtra("userId", userId);
                            startActivity(intent);
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<SearchIdInfo> call, Throwable t) {
                        Log.e("오는지",""+t.getMessage());
                        Log.e("오는지",t.getLocalizedMessage());
                        Log.e("debugTest","error:(${t.message})");
                    }
                });
                finish();
            }
        });
        binding.searchPass.setOnClickListener(view->{
            Intent intent = new Intent(SearchIdActivity.this, SearchPassActivity.class);
            startActivity(intent);
            finish();
        });
    }
}