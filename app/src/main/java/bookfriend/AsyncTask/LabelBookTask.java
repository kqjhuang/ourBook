package bookfriend.AsyncTask;

import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import bookfriend.Activity.LabelBook_activity;
import bookfriend.http.HttpAgent;
import bookfriend.listview.LazyAdapter;

/**
 * Created by gamezheng on 2014/8/8.
 */
public class LabelBookTask extends AsyncTask<String, Integer, Integer> {
    //ProgressDialog pd;
    public HttpAgent httpAgent = new HttpAgent();
    private String label;
    public JSONArray Msg;
    public HashMap<String, Object> paras = new HashMap<String, Object>();
    public static final String KEY_title = "bookName"; // parent node
    public static final String KEY_authorName = "authorName";
    private LazyAdapter adapter;
    private LabelBook_activity labelbook;

    private String code = "";

    public  LabelBookTask(String A,LabelBook_activity B ){
        label =A;
        labelbook = B;
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
            Toast.makeText(labelbook, "网络错误", Toast.LENGTH_LONG).show();
        }
        labelbook.Msg1 = Msg;
        try {
            if(Msg.length() ==0)
            {
                Toast.makeText(labelbook, "找不到", Toast.LENGTH_LONG).show();
            }else{
                ArrayList<HashMap<String, String>> songsList = new ArrayList<HashMap<String, String>>();
                for (int i = 0; i < Msg.length(); i++) {
                    // 新建一个 HashMap
                    HashMap<String, String> map = new HashMap<String, String>();
                    //每个子节点添加到HashMap关键= >值
                    map.put(KEY_title, Msg.getJSONObject(i).getString(KEY_title));
                    map.put(KEY_authorName, Msg.getJSONObject(i).getString(KEY_authorName));
                    //map.put(KEY_image, Msg1.getJSONObject(i).getString(KEY_image));
                    // HashList添加到数组列表
                    songsList.add(map);
                }

                adapter=new LazyAdapter(labelbook,songsList);
                labelbook.list.setAdapter(adapter);
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
        paras.put("labelId", label);
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
        String result = httpAgent.request("api/app/books-under-label", paras, "");
        try{
            JSONObject mess=new JSONObject(result);
            code = mess.getString("code");
            Msg = mess.getJSONArray("msg");
        }catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
