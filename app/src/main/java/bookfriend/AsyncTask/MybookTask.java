package bookfriend.AsyncTask;

import android.os.AsyncTask;
import android.widget.Adapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import bookfriend.Fragment.Mybook_fragment;
import bookfriend.http.HttpAgent;
import bookfriend.listview.LazyAdapter;

/**
 * Created by gamezheng on 2014/8/11.
 */
public class MybookTask extends AsyncTask<String, Integer, Integer> {
    //ProgressDialog pd;
    public HttpAgent httpAgent = new HttpAgent();
    public HashMap<String, Object> paras = new HashMap<String, Object>();
    private Mybook_fragment mybook_fragment;
    private String title ="";
    public JSONArray Msg;
    private String code = "";
    private LazyAdapter adapter;
    public static final String KEY_title = "bookName";
    public static final String KEY_authorName = "authorName";

    public MybookTask(Mybook_fragment mybook_fragment) {
        this.mybook_fragment = mybook_fragment;
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
            Toast.makeText(mybook_fragment.getActivity(), "网络错误", Toast.LENGTH_LONG).show();
        }
        mybook_fragment.Msg1 = Msg;
        try {
            if(Msg.length() ==0)
            {
                Toast.makeText(mybook_fragment.getActivity(), "找不到", Toast.LENGTH_LONG).show();

            }else{
                ArrayList<HashMap<String, String>> songsList = new ArrayList<HashMap<String, String>>();
                for (int i = 0; i < Msg.length(); i++) {
                    // 新建一个 HashMap
                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put(KEY_title, Msg.getJSONObject(i).getString("bookName"));
                    map.put(KEY_authorName, Msg.getJSONObject(i).getString("authorName"));
                    songsList.add(map);
                }
                adapter=new LazyAdapter(mybook_fragment,songsList);
                mybook_fragment.list.setAdapter(adapter);
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
        String result = httpAgent.request("api/app/mybook", paras, "");
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

