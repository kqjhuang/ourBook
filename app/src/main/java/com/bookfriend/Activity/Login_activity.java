package com.bookfriend.Activity;


import java.util.regex.Matcher;
import java.util.regex.Pattern;




import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.bookfriend.AsyncTask.LoginTask;
import com.bookfriend.R;


import com.bookfriend.tools.CleanableEditText;


public class Login_activity extends Activity implements OnClickListener {
    private Button login_button;
    private Button signup_button;
    private CleanableEditText user_name;
    private CleanableEditText user_password;

    public String nickName = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        login_button = (Button) findViewById(R.id.signin_button);
        signup_button = (Button) findViewById(R.id.signup_button);
        login_button.setOnClickListener(this);
        signup_button.setOnClickListener(this);
        user_name = (CleanableEditText) findViewById(R.id.username_edit);
        user_password = (CleanableEditText) findViewById(R.id.password_edit);

    }

    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.signin_button:
                signIn();
                break;
            case R.id.signup_button:
                signUp();
                break;
            default:
                break;
        }
    }
//登陆
    private void signIn() {
        // TODO Auto-generated method stub;

        String NAME = user_name.getText().toString();
        String PASSWORD = user_password.getText().toString();
        Pattern pattern = Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
        Matcher matcher = pattern.matcher(NAME);
        if(matcher.find()){
            LoginTask network = new LoginTask(NAME,PASSWORD,this);
            try{
                network.execute();
            }catch(Exception e){
                Toast.makeText(Login_activity.this,"嗯，你猜得没错，崩了。。", Toast.LENGTH_LONG).show();
                Log.i("message",e.getMessage());
            }
        }else{
            Toast.makeText(Login_activity.this,"请输入正确邮箱", Toast.LENGTH_LONG).show();
        }
    }

    private void signUp() {
        // TODO Auto-generated method stub
        Intent intent = new Intent();
        intent.setClass(Login_activity.this, Signup_activity.class);
        Login_activity.this.startActivity(intent);
        Login_activity.this.finish();
    }

    public Handler handler=	new Handler(){
        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            if(msg.obj.equals("success")){
                Intent intent = new Intent();
                intent.setClass(Login_activity.this, Main_activity.class);
                intent.putExtra("username",nickName);
                Login_activity.this.startActivity(intent);
                Login_activity.this.finish();
            }
        }
    };
}
