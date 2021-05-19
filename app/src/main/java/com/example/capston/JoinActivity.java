package com.example.capston;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.capston.databinding.ActivityJoinBinding;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import Join.JoinAPI;
import Join.JoinInfo;
import Join.JoinJsonObject;
import Retrofit.conRetrofit;
import Util.ConvertImage;
import lombok.SneakyThrows;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JoinActivity extends AppCompatActivity {
    ActivityJoinBinding binding;
    static final int REQUEST_PHOTO_1 = 1;
    static final int REQUEST_PHOTO_2 = 2;
    static final int REQUEST_PHOTO_3 = 3;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=DataBindingUtil.setContentView(this,R.layout.activity_join);

        Intent secondIntent = getIntent();
        String message = secondIntent.getStringExtra("image");
        binding.userLicense.setText(message);
        binding.fileStuLicense.setOnClickListener(view->{
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,"image/*");
            startActivityForResult(intent, REQUEST_PHOTO_1);
        });
        binding.fileLicense1.setOnClickListener(view->{
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,"image/*");
            startActivityForResult(intent, REQUEST_PHOTO_2);
        });
        binding.fileLicense2.setOnClickListener(view->{
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,"image/*");
            startActivityForResult(intent, REQUEST_PHOTO_3);
        });

        binding.registerBtn.setOnClickListener(view->{
            if(binding.checkBox2.isChecked()&&binding.checkBox3.isChecked()){
                JoinAPI client = conRetrofit.getApiClient().create(JoinAPI.class);
                JoinJsonObject JoinJsonObject=new JoinJsonObject(binding);
                Call<JoinInfo> call = client.getJsonString(JoinJsonObject);
                call.enqueue(new Callback<JoinInfo>() {
                    @Override
                    public void onResponse(Call<JoinInfo> call, Response<JoinInfo> response) {
                        switch(response.body().getResult()){
                            case "101":
                                Toast.makeText(JoinActivity.this,"회원가입 되었습니다(탑승자)",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(JoinActivity.this, LoginActivity.class);
                                startActivity(intent);
                                finish();
                                break;
                            case "102":
                                Toast.makeText(JoinActivity.this,"회원가입 되었습니다(운전자)",Toast.LENGTH_SHORT).show();
                                intent = new Intent(JoinActivity.this, LoginActivity.class);
                                startActivity(intent);
                                finish();
                                break;
                        }
                    }

                    @Override
                    public void onFailure(Call<JoinInfo> call, Throwable t) {
                        Log.e("오는지",""+t.getMessage());
                        Log.e("오는지",t.getLocalizedMessage());
                        Log.e("debugTest","error:(${t.message})");
                    }
                });
            }
            else {
                Toast.makeText(JoinActivity.this,"필수 약관에 동의 해주세요",Toast.LENGTH_SHORT).show();
            }
        });
        binding.checkBox.setOnClickListener(view->{
            if(binding.checkBox.isChecked()){
                binding.checkBox2.setChecked(binding.checkBox.isChecked());
                binding.checkBox3.setChecked(binding.checkBox.isChecked());
            }
            else {
                binding.checkBox2.setChecked(binding.checkBox.isChecked());
                binding.checkBox3.setChecked(binding.checkBox.isChecked());
            }
        });
    }
    @SneakyThrows
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri selectedImageUri = data.getData();
        Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(),selectedImageUri);
        Log.e("오는지",bitmap.toString());
        String image=new ConvertImage().getStringFromBitmap(bitmap);
        if (requestCode == 1)
            binding.userLicense.setText(image);
        else if (requestCode == 2)
            binding.carLicense.setText(image);
        else if (requestCode == 3)
            binding.carInsurance.setText(image);
    }
}