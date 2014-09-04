package com.bookfriend.AsyncTask;

import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

import com.bookfriend.Fragment.Label_fragment;
import com.bookfriend.http.HttpAgent;

/**
 * Created by gamezheng on 2014/8/8.
 */
public class AddLabelTask extends AsyncTask<String, Integer, Integer> {

    public HttpAgent httpAgent = new HttpAgent();
    private String labelName ="";
    public JSONArray Msg;
    public HashMap<String, Object> paras = new HashMap<String, Object>();
    private String code = "";
    public Label_fragment label;

    public AddLabelTask(String A, Label_fragment B){
        labelName = A;
        label = B;
    }
    /**
     * 执行任务后调用
     */
    @Override
    protected void onPostExecute(Integer result) {
        // TODO Auto-generated method stub
        super.onPostExecute(result);

        if(code.equals("200")){
            Toast.makeText(label.getActivity(), "添加成功", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(label.getActivity(), "网络错误", Toast.LENGTH_LONG).show();
        }


    }

    /**
     * 执行任务之前调用
     */
    @Override
    protected void onPreExecute() {
        // TODO Auto-generated method stub
        super.onPreExecute();
        paras.put("labelName", labelName);
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
        String result = httpAgent.request("api/app/label-add", paras, "");
        try{
            JSONObject mess=new JSONObject(result);
            code = mess.getString("code");
            Msg = mess.getJSONArray("msg");
        }catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public JSONArray getMsg(){
        return this.Msg;
    }
}
