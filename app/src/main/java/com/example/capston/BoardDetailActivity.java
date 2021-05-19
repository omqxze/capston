package com.example.capston;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.capston.databinding.ActivityBoardDetailBinding;

public class BoardDetailActivity extends AppCompatActivity {
    ActivityBoardDetailBinding binding;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_board_detail);

        binding.reservBtn.setOnClickListener(view->{
            Intent intent = new Intent(this, BoardReservPopupActivity.class);
            intent.putExtra("data", "Test Popup");
            startActivityForResult(intent, 1);
        });

        binding.rewriteBtn.setOnClickListener(view->{
            Intent intent = new Intent(BoardDetailActivity.this, BoardReviseActivity.class);
            startActivity(intent);
            finish();
        });

        binding.delBtn.setOnClickListener(view->{
            Intent intent = new Intent(this, BoardDeletePopupActivity.class);
            intent.putExtra("data", "Test Popup");
            startActivityForResult(intent, 1);
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                //데이터 받기
                String result = data.getStringExtra("result");
                Intent intent = new Intent(BoardDetailActivity.this, BoardActivity.class);
                startActivity(intent);
                finish();
            }
        }
    }
}
