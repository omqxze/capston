package com.example.capston;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Window;

import androidx.databinding.DataBindingUtil;

import com.example.capston.databinding.ActivityBoardWriteBinding;
import com.example.capston.databinding.ActivityBoardWritePopupBinding;

public class BoardWritePopupActivity extends Activity {
    ActivityBoardWritePopupBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_board_write_popup);

        Intent intent = getIntent();
        String data = intent.getStringExtra("data");
        binding.txtText.setText(data+"님 등록이 완료되었습니다.");

        binding.okBtn.setOnClickListener(view->{
            Intent intentt = new Intent(BoardWritePopupActivity.this,BoardActivity.class);
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
