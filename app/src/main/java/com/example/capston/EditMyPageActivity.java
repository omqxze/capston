package com.example.capston;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.capston.databinding.ActivityEditmypageBinding;

public class EditMyPageActivity extends AppCompatActivity {
    ActivityEditmypageBinding binding;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_editmypage);

        binding.imageButton4.setOnClickListener(view->{
            Intent intent = new Intent(EditMyPageActivity.this, MyPageActivity.class);
            startActivity(intent);
            finish();
        });

        binding.passBtn.setOnClickListener(view->{
            Intent intent = new Intent(EditMyPageActivity.this, MyPageActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
