package com.example.capston;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;
import com.example.capston.Retrofit.GitHubClient;

public class MainActivity extends AppCompatActivity {
        public static Context context_main;
        public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = (TextView) findViewById(R.id.textView);
        login Login = new login(this);
        }
        }
