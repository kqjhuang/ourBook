package bookfriend.AsyncTask;

import android.os.AsyncTask;
import android.view.View;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import bookfriend.Fragment.Search_fragment;
import bookfriend.http.HttpAgent;
import bookfriend.listview.LazyAdapter;

/**
 * Created by gamezheng on 2014/8/8.
 */
public class SearchTask extends AsyncTask<String, Integer, Integer> {

    private LazyAdapter adapter;
    public HttpAgent httpAgent = new HttpAgent();
    private String title;
    public JSONArray Msg;
    public HashMap<String, Object> paras = new HashMap<String, Object>();
    private String code = "";
    private Search_fragment search;
    public static final String KEY_title = "bookName";
    public static final String KEY_authorName = "authorName";
    //public static final String KEY_image = "imageUrl";

    public SearchTask(String A, Search_fragment B) {
        title = A;
        search = B;
    }

    /**
     * 执行任务后调用
     */
    @Override
    protected void onPostExecute(Integer result) {
        // TODO Auto-generated method stub
        super.onPostExecute(result);

        if (!code.equals("200")) {
            Toast.makeText(search.getActivity(), "网络错误", Toast.LENGTH_LONG).show();
        } else {
            search.Msg1 = getMsg();
            try {
                if (search.Msg1.length() == 0) {
                    Toast.makeText(search.getActivity(), "找不到", Toast.LENGTH_LONG).show();
                } else {
                    search.linearLayout1.setVisibility(View.VISIBLE);
                    search.linearLayout2.setVisibility(View.INVISIBLE);
                    ArrayList<HashMap<String, String>> songsList = new ArrayList<HashMap<String, String>>();
                    for (int i = 0; i < Msg.length(); i++) {
                        HashMap<String, String> map = new HashMap<String, String>();
                        map.put(KEY_title, search.Msg1.getJSONObject(i).getString(KEY_title));
                        map.put(KEY_authorName, search.Msg1.getJSONObject(i).getString(KEY_authorName));
                        //  map.put(search.KEY_image, Msg1.getJSONObject(i).getString(search.KEY_image));
                        songsList.add(map);
                    }
                    adapter = new LazyAdapter(search, songsList);
                    search.list.setAdapter(adapter);
                }
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
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
        paras.put("title", title);
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
        String result = httpAgent.request("api/app/books-for-query", paras, "");
        try {
            JSONObject mess = new JSONObject(result);
            code = mess.getString("code");
            Msg = mess.getJSONArray("msg");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public JSONArray getMsg() {
        return this.Msg;
    }
}
