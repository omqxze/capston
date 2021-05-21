package com.example.capston;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import com.example.capston.databinding.ActivitySearchpassBinding;
import Login.SearchPwAPI;
import Login.SearchPwInfo;
import Login.SearchPwJsonObject;
import Retrofit.conRetrofit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchPassActivity extends AppCompatActivity {
    ActivitySearchpassBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_searchpass);

        setSupportActionBar(binding.toolbar6);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        binding.userStunum2.setOnClickListener(view->{
            binding.userStunum2.setText(null);
        });
        binding.userId2.setOnClickListener(view->{
            binding.userId2.setText(null);
        });
        binding.userName2.setOnClickListener(view->{
            binding.userName2.setText(null);
        });
        binding.btnFindpass.setOnClickListener(view->{
            if (binding.userStunum2.getText().toString().equals("")){
                Toast.makeText(SearchPassActivity.this,"학번을 입력해주세요",Toast.LENGTH_SHORT).show();
            }
            else if(binding.userId2.getText().equals("")){
                Toast.makeText(SearchPassActivity.this,"아이디를 입력해주세요",Toast.LENGTH_SHORT).show();
            }
            else if(binding.userName2.getText().equals("")){
                Toast.makeText(SearchPassActivity.this,"이름을 입력해주세요",Toast.LENGTH_SHORT).show();
            }
            else {
                Intent intent = new Intent(SearchPassActivity.this, SearchPassActivity.class);
                startActivity(intent);
                String stunum = binding.userStunum2.getText().toString();
                String id = binding.userId2.getText().toString();
                String name = binding.userName2.getText().toString();
                SearchPwAPI client = conRetrofit.getApiClient().create(SearchPwAPI.class);
                SearchPwJsonObject searchpwJsonObject = new SearchPwJsonObject(stunum, id, name);
                Call<SearchPwInfo> call = client.getJsonString(searchpwJsonObject);
                call.enqueue(new Callback<SearchPwInfo>() {
                    @Override
                    public void onResponse(Call<SearchPwInfo> call, Response<SearchPwInfo> response) {
                        String result = response.body().getResult();
                        if(result.equals("011")){
                            Toast.makeText(SearchPassActivity.this,"비밀번호 찾기 성공, 메일을 확인해주세요.",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(SearchPassActivity.this, LoginActivity.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(SearchPassActivity.this,"비밀번호 찾기 실패, 학번, 아이디, 이름을 확인해주세요.",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<SearchPwInfo> call, Throwable t) {
                        Log.e("오는지",""+t.getMessage());
                        Log.e("오는지",t.getLocalizedMessage());
                        Log.e("debugTest","error:(${t.message})");
                    }
                });
                finish();
            }
        });
    }
}