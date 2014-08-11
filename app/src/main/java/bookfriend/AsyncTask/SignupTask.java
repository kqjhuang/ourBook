package bookfriend.AsyncTask;

/**
 * Created by gamezheng on 2014/8/8.
 */

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Message;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.HashMap;

import bookfriend.Activity.Main_activity;
import bookfriend.Activity.Signup_activity;
import bookfriend.http.HttpAgent;

/**
 * 异步任务验证注册
 *
 * @author Administrator
 *
 */
public class SignupTask extends AsyncTask<String, Integer, Integer> {
    //ProgressDialog pd;
    public HttpAgent httpAgent = new HttpAgent();
    private Signup_activity signup = new Signup_activity();
    public HashMap<String, Object> paras = new HashMap<String, Object>();
    private String code = "";
    private String msg = "";
    private String user = "";
    private String password = "";
    private String nickname = "";
    private String weixin = "";


    public  SignupTask(String A ,String B,String C,String D,Signup_activity E){
        user =A;
        password =B;
        nickname =C;
        weixin = D;
        signup = E;
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
            Toast.makeText(signup, "注册成功", Toast.LENGTH_LONG).show();
            SharedPreferences preferences  = signup.getSharedPreferences("cloud", 0);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("username", user);
            editor.putString("password", password);
            editor.commit();
            Message message = new Message();
            message.obj = "success";
            signup.handler.sendMessage(message);
        }else{
            if(msg.equals("email is existed")){
                Toast.makeText(signup,"邮箱已经被注册", Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(signup,"网络异常", Toast.LENGTH_LONG).show();
            }
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
        paras.put("name", nickname);
        paras.put("passwd", password);
        paras.put("wechatNum", weixin);

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
        String result = httpAgent.request("api/app/register", paras, "");
        try{
            JSONObject mess=new JSONObject(result);
            code = mess.getString("code");
            msg = mess.getString("msg");
        }catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}