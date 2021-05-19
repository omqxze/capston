package com.example.capston;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.capston.databinding.BoardWriteBinding;

public class BoardWriteActivity extends AppCompatActivity {
    BoardWriteBinding binding;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_board_write);

        binding.write.setOnClickListener(view->{
            Intent intent = new Intent(this, BoardWritePopupActivity.class);
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
                Intent intent = new Intent(BoardWriteActivity.this, BoardActivity.class);
                startActivity(intent);
                finish();
            }
        }
    }
}
