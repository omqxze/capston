package com.example.capston;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.capston.databinding.ActivityImageBinding;
import com.example.capston.databinding.ActivityJoinBinding;

import java.net.URI;

public class ImageActivity extends AppCompatActivity {
    ActivityImageBinding binding;
    private final int GET_GALLERY_IMAGE = 200;
    private String image;

    @SuppressLint({"ClickableViewAccessibility", "ResourceType"})
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_image);

        binding.btnUploadPicture.setOnClickListener(view->{
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,"image/*");
            startActivityForResult(intent, GET_GALLERY_IMAGE);
        });

        binding.btnSignupfinish.setOnClickListener(view->{
            Toast.makeText(ImageActivity.this,"이미지 업로드가 완료되었습니다.",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(ImageActivity.this, LoginActivity.class);
            startActivity(intent);
            if(image!=null){
                intent.putExtra("image",image);
            }
            finish();
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GET_GALLERY_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri selectedImageUri = data.getData();
            image=data.getDataString();
            Log.e("오는지",data.getDataString());
            binding.userImage.setImageURI(selectedImageUri);
        }
    }
}