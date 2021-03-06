package com.bookfriend.AsyncTask;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Message;
import android.widget.Toast;

import org.json.JSONObject;
import java.util.HashMap;

import com.bookfriend.Activity.Login_activity;
import com.bookfriend.http.HttpAgent;

/**
 * 异步任务验证账号
 *
 * @author Administrator
 *
 */
public class LoginTask extends AsyncTask<String, Integer, Integer> {
    public HttpAgent httpAgent = new HttpAgent();
    public HashMap<String, Object> paras = new HashMap<String, Object>();
    public String code = "";
    public JSONObject Msg;
    private String user = "";
    private String password = "";
    private Login_activity login;


    public  LoginTask(String A ,String B,Login_activity C){
        user=A;
        password =B;
        login = C;
    }
    /**
     * 执行任务后调用
     */
    @Override
    protected void onPostExecute(Integer result) {
        // TODO Auto-generated method stub
        super.onPostExecute(result);
        //pd.dismiss();// 消失
        if(code.equals("200")){
            SharedPreferences preferences  = login.getSharedPreferences("cloud", 0);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("username", user);
            editor.putString("password", password);
            editor.commit();
            Message messageg = Message.obtain();
            messageg.obj = "success";
            try {
                login.nickName = Msg.getString("userName");
            }catch (Exception e ){
                e.printStackTrace();
            }
            login.handler.sendMessage(messageg);

        }else{
            Toast.makeText(login, "用户名或密码错误", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * 执行任务之前调用
     */
    @Override
    protected void onPreExecute() {
        // TODO Auto-generated method stub
        super.onPreExecute();
        paras.put("email", user);
        paras.put("passwd", password);
    }

    /**
     * 执行任务中调用更新任务进度
     */
    @Override
    protected void onProgressUpdate(Integer... values) {
        // TODO Auto-generated method stub
        super.onProgressUpdate(values);
    }

    /**
     * 执行任务
     */
    @Override
    protected Integer doInBackground(String... arg0) {
        String result = httpAgent.request("api/app/login", paras, "");
        try{
            JSONObject mess=new JSONObject(result);
            code = mess.getString("code");
            Msg = mess.getJSONObject("msg");
        }catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}