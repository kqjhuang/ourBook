package bookfriend.Activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;
import android.widget.ImageView;

import bookfriend.AsyncTask.AutoLoginTask;
import bookfriend.R;


public class Start_activity extends Activity {

    public ImageView image;


    private static final int GO_HOME = 1;
    private static final int GO_GUIDE = 2;

    private static final long DELAY_MILLIS = 3000;
    public boolean isFirstIn ;
    public String username;
    public String password;
    private static final String SHAREDPREFERENCES_NAME = "cloud";
    //private Button button;

    private SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        init();
    }

    private void init() {
        // TODO Auto-generated method stub
        preferences  = getSharedPreferences(
                SHAREDPREFERENCES_NAME, 0);
        isFirstIn = preferences.getBoolean("isFirstIn", true);
        password = preferences.getString("password","");
        username = preferences.getString("username","");
        if(!password.equals("") && !username.equals("")) {
            try {
                AutoLoginTask network = new AutoLoginTask(username,password,this);
                network.execute();
            }catch (Exception e){
                e.printStackTrace();
            }

        }else if (!isFirstIn) {
            invisiableHandler.sendEmptyMessage(GO_HOME);
        } else {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("isFirstIn", false);
            editor.commit();
            invisiableHandler.sendEmptyMessage(GO_GUIDE);
        }

    }

    public Handler invisiableHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case GO_HOME:
                    goHome();
                    break;
                case GO_GUIDE:
                    goGuide();
                    break;
            }
            if(msg.obj != null && msg.obj.equals("success")){
                Intent intent = new Intent();
                intent.setClass(Start_activity.this, Main_activity.class);
                intent.putExtra("username","huang");    //这里还没有获得username先写死
                Start_activity.this.startActivity(intent);
                try{
                    Thread.sleep(666);
                }
                catch(InterruptedException e){
                }
                Start_activity.this.finish();
            }
            super.handleMessage(msg);

        }
    };
    protected void goHome() {
        // TODO Auto-generated method stub
        Intent intent = new Intent();
        intent.setClass(Start_activity.this, Login_activity.class);
        Start_activity.this.startActivity(intent);
        try{
            Thread.sleep(666);
        }
        catch(InterruptedException e){
        }
        finish();
    }

    protected void goGuide() {
        // TODO Auto-generated method stub

        setContentView(R.layout.activity_start);
        image = (ImageView) findViewById(R.id.guide);
        image.setImageResource(R.drawable.start);
        invisiableHandler.sendEmptyMessageDelayed(GO_HOME,DELAY_MILLIS);
    }


}
