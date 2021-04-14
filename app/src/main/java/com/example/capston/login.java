package com.example.capston;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.capston.Retrofit.GitHubClient;
import com.example.capston.Retrofit.GitHubRepo;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class login extends AppCompatActivity {
    Context mContext;
    public login(Context context) {
        mContext = context;
       TextView textview = ((MainActivity) mContext).textView;
        Retrofit retrofit =new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        GitHubClient client = retrofit.create(GitHubClient.class);

        Call<List<GitHubRepo>> call = client.reposForUser("wonseok5893");

        call.enqueue(new Callback<List<GitHubRepo>>() {
            @Override
            public void onFailure(@NotNull Call<List<GitHubRepo>> call, @NonNull Throwable t) {
                Log.e("debugTest","error:(${t.message})");
            }

            @Override
            public void onResponse(Call<List<GitHubRepo>>call, Response<List<GitHubRepo>> response) {
                if(response.isSuccessful() && response.body()!=null){
                    List<GitHubRepo> repos = response.body();
                    String reposStr = "";
                    if (repos!=null){
                        for (GitHubRepo it:repos
                        ) {
                            reposStr += it.name + '\n';
                        }
                    }
                    textView.setText(reposStr);
                }

            }
        });
    }
}
