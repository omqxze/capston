package com.example.capston;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.Window;

import androidx.databinding.DataBindingUtil;

import com.example.capston.databinding.ActivityBoardDeletePopupBinding;

import Board.BoardDeleteAPI;
import Board.BoardDeleteInfo;
import Board.BoardDeleteJsonObject;
import Retrofit.conRetrofit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BoardDeletePopupActivity extends Activity {
    ActivityBoardDeletePopupBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_board_delete_popup);

        Intent intent = getIntent();
        SharedPreferences pref = getSharedPreferences("mine", MODE_PRIVATE);
        String userId = pref.getString("userId", "");
        binding.txtText.setText(userId+"님 삭제가 완료되었습니다.");

        binding.okBtn.setOnClickListener(view->{
            String confCode = intent.getStringExtra("confCode");
            BoardDeleteAPI client = conRetrofit.getApiClient().create(BoardDeleteAPI.class);
            BoardDeleteJsonObject boardDeleteJsonObject = new BoardDeleteJsonObject(confCode);
            Call<BoardDeleteInfo> call = client.getJsonString(boardDeleteJsonObject);
            call.enqueue(new Callback<BoardDeleteInfo>() {
                @Override
                public void onResponse(Call<BoardDeleteInfo> call, Response<BoardDeleteInfo> response) {
                    String result = response.body().getResult();
                    if(result.equals("521")){
                        Intent intentt = new Intent();
                        intentt.putExtra("result", "Close Popup");
                        setResult(RESULT_OK, intentt);
                        finish();
                    }
                }

                @Override
                public void onFailure(Call<BoardDeleteInfo> call, Throwable t) {

                }
            });
        });
        binding.noBtn.setOnClickListener(view->{
            Intent intentt = new Intent();
            intentt.putExtra("result", "Close Popup");
            setResult(RESULT_OK, intentt);
            finish();
        });
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //바깥레이어 클릭시 안닫히게
        if(event.getAction()==MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }
    @Override
    public void onBackPressed() {
        //안드로이드 백버튼 막기
        return;
    }
}