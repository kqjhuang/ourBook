package com.bookfriend.AsyncTask;

import android.os.AsyncTask;
import android.os.Message;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.HashMap;

import com.bookfriend.Activity.Start_activity;
import com.bookfriend.http.HttpAgent;

/**
 * Created by gamezheng on 2014/8/11.
 */
public class AutoLoginTask extends AsyncTask<String, Integer, Integer> {
    public HttpAgent httpAgent = new HttpAgent();
    public HashMap<String, Object> paras = new HashMap<String, Object>();
    private Start_activity login;

    public String code = "";
    private String user = "";
    private String password = "";
    private JSONObject Msg;


    public  AutoLoginTask(String A ,String B,Start_activity C){
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
            Message msg = Message.obtain();
            msg.obj = "success";
            try {
                login.nickName = Msg.getString("userName");
            }catch (Exception e ){
                e.printStackTrace();
            }
            login.invisiableHandler.sendMessage(msg);
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