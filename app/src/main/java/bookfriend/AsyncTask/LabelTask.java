package bookfriend.AsyncTask;

import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import bookfriend.Fragment.Label_fragment;
import bookfriend.http.HttpAgent;
import bookfriend.listview.LazyAdapter;

/**
 * Created by gamezheng on 2014/8/8.
 */
public class LabelTask extends AsyncTask<String, Integer, Integer> {

    public HttpAgent httpAgent = new HttpAgent();
    private String title ="";
    public JSONArray Msg;
    public HashMap<String, Object> paras = new HashMap<String, Object>();
    private String code = "";
    public Label_fragment label;
    private LazyAdapter adapter;
    private JSONArray Msg1;
    public static final String KEY_title = "bookName";
    public static final String KEY_authorName = "authorName";

    public LabelTask(Label_fragment A){
        label = A;
    }

    /**
     * 执行任务后调用
     */
    @Override
    protected void onPostExecute(Integer result) {
        // TODO Auto-generated method stub
        super.onPostExecute(result);

        if(code.equals("200")){
            //Toast.makeText(getActivity(), "查找成功", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(label.getActivity(), "网络错误", Toast.LENGTH_LONG).show();
        }
        label.Msg1 = this.Msg1 = getMsg();
        try {
            if(Msg1.length() ==0)
            {
                Toast.makeText(label.getActivity(), "找不到", Toast.LENGTH_LONG).show();

            }else{

                ArrayList<HashMap<String, String>> songsList = new ArrayList<HashMap<String, String>>();
                for (int i = 0; i < Msg.length(); i++) {
                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put(KEY_title, Msg1.getJSONObject(i).getString("labelName"));
                    map.put(KEY_authorName, Msg1.getJSONObject(i).getString("id"));
                    songsList.add(map);
                }

                adapter=new LazyAdapter(label,songsList);
                label.list.setAdapter(adapter);
            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /**
     * 执行任务之前调用
     */
    @Override
    protected void onPreExecute() {
        // TODO Auto-generated method stub
        super.onPreExecute();
        paras.put("", title);
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
        String result = httpAgent.request("api/app/labels", paras, "");
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
