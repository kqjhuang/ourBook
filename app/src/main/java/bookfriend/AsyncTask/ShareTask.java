package bookfriend.AsyncTask;

import android.os.AsyncTask;
import android.view.View;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.HashMap;

import bookfriend.Fragment.Scanbook_fragment;
import bookfriend.http.HttpAgent;

/**
 * Created by gamezheng on 2014/8/8.
 */
public class ShareTask extends AsyncTask<String, Integer, Integer> {
    public HttpAgent httpAgent = new HttpAgent();
    private String title, author, summary, publisher, isbn10, isbn13, image, price;
    public HashMap<String, Object> paras = new HashMap<String, Object>();
    private String code = "";

    private Scanbook_fragment scanbook;
    public ShareTask(String A, String B, String C, String D,
                     String E, String F, String G, String H,Scanbook_fragment I) {
        title = A;
        author = B;
        summary = C;
        publisher = D;
        isbn10 = E;
        isbn13 = F;
        image = G;
        price = H;
        scanbook = I;
    }

    /**
     * 执行任务后调用
     */
    @Override
    protected void onPostExecute(Integer result) {
        // TODO Auto-generated method stub
        super.onPostExecute(result);
        //pd.dismiss();// 消失
        if (code.equals("200")) {
            Toast.makeText(scanbook.getActivity(), "分享成功!", Toast.LENGTH_LONG).show();
            scanbook.button.setVisibility(View.INVISIBLE);
        } else {
            Toast.makeText(scanbook.getActivity(), "分享失败", Toast.LENGTH_LONG).show();
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
        paras.put("author", author);
        paras.put("summary", summary);
        paras.put("publisher", publisher);
        paras.put("isbn10", isbn10);
        paras.put("isbn13", isbn13);
        paras.put("image", image);
        paras.put("price", price);

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
        String result = httpAgent.request("api/app/sharebook", paras, "");
        try {
            JSONObject mess = new JSONObject(result);
            code = mess.getString("code");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
