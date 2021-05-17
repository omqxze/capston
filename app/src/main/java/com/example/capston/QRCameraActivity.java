package com.example.capston;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.capston.databinding.ActivityQrcameraBinding;

public class QRCameraActivity extends AppCompatActivity {
    ActivityQrcameraBinding binding;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_qrcode);

        binding.imageButton.setOnClickListener(view->{
            Intent intent = new Intent(QRCameraActivity.this, QRCodeActivity.class);
            startActivity(intent);
            finish();
        });


    }
}
