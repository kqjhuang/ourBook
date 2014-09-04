package com.bookfriend.Activity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View.OnClickListener;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.bookfriend.R;
import com.bookfriend.tools.CleanableEditText;
import com.bookfriend.AsyncTask.SignupTask;

public class Signup_activity extends Activity implements OnClickListener{
	private Button go_back;
	private Button ensure_button;
	private CleanableEditText Email;
	private CleanableEditText nickname;
	private CleanableEditText signup_password;
	private CleanableEditText weixin;
	private CleanableEditText signup_passwordagain;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_signup);
        ensure_button = (Button) findViewById(R.id.ensure);
        go_back = (Button) findViewById(R.id.button_back);
        go_back.setOnClickListener(this);
        ensure_button.setOnClickListener(this);
        Email = (CleanableEditText) findViewById(R.id.Email_edit);
        nickname = (CleanableEditText) findViewById(R.id.nickname_edit);
        signup_password = (CleanableEditText) findViewById(R.id.password_edit);
        weixin = (CleanableEditText) findViewById(R.id.weixin_edit);
        signup_passwordagain = (CleanableEditText) findViewById(R.id.passwordagain_edit);

    }
	
	public void onClick(View v) {
	    // TODO Auto-generated method stub
	    switch (v.getId()) {
	    case R.id.button_back:
	    	goback();
	    	break;
	    case R.id.ensure:
	    	ensure();
	    	break;
	    default:
	    	break;
	    }
	    }


	private void goback() {
		// TODO Auto-generated method stub
		Intent intent = new Intent();
		intent.setClass(Signup_activity.this, Login_activity.class);
		Signup_activity.this.startActivity(intent);
		Signup_activity.this.finish();
	}
//确定注册
	private void ensure() {
		// TODO Auto-generated method stub
		String email_S =Email.getText().toString();
		String nickname_S =nickname.getText().toString();
		String signup_password_S =signup_password.getText().toString();
		String weixin_S =weixin.getText().toString();
		String signup_passwordagain_S =signup_passwordagain.getText().toString();

        if(nickname_S.equals("")){
            Toast.makeText(Signup_activity.this,"昵称不可为空", Toast.LENGTH_LONG).show();
            return;
        }

		if(signup_password_S.equals(signup_passwordagain_S)){
			Pattern pattern = Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
			Matcher matcher = pattern.matcher(email_S);
			if(matcher.find()){
				//NetWork network = new NetWork(NAME,PASSWORD);
                SignupTask network = new SignupTask(email_S,signup_password_S,nickname_S,weixin_S,this);
				network.execute();
				}else{
					Toast.makeText(Signup_activity.this,"请输入正确邮箱", Toast.LENGTH_LONG).show();
				}
		}else{
			Toast.makeText(Signup_activity.this,"请确认密码", Toast.LENGTH_LONG).show();
		}
	}

    public Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.obj.equals("success")) {
                Intent intent = new Intent();
                intent.setClass(Signup_activity.this, Main_activity.class);
                intent.putExtra("username",nickname.getText().toString());
                Signup_activity.this.startActivity(intent);
                Signup_activity.this.finish();
            }
        }
    };

}