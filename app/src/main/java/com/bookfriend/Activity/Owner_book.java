package com.bookfriend.Activity;

import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.bookfriend.R;

public class Owner_book extends Activity implements OnClickListener{
    private Button go_back;
    private TextView title;
    private TextView author;
    private TextView publisher;
    private TextView publisherdate;
    private TextView isbn;
    private TextView summary;
    private TextView wechatNum;
    private TextView userEmail;
    private TextView userName;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.owner_book);
        go_back = (Button) findViewById(R.id.button_back);
        go_back.setOnClickListener(this);
        Intent intent = getIntent();
        String json = intent.getStringExtra("json");
        JSONObject js = null;
        try {
            js = new JSONObject(json);
        } catch (JSONException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        title = (TextView) findViewById(R.id.bookview_title1);
        author = (TextView) findViewById(R.id.bookview_author1);
        publisher = (TextView) findViewById(R.id.bookview_publisherdate1);
        publisherdate = (TextView) findViewById(R.id.bookview_publisherdate1);
        isbn = (TextView) findViewById(R.id.bookview_isbn1);
        summary = (TextView) findViewById(R.id.bookview_summary1);
        wechatNum = (TextView) findViewById(R.id.bookview_wechatNum1);
        userEmail = (TextView) findViewById(R.id.bookview_userEmail1);
        userName = (TextView) findViewById(R.id.bookview_userName1);

        try {
            title.setText(js.getString("authorName"));
            author.setText(js.getString("bookName"));
            publisher.setText(js.getString("authorName"));
            publisherdate.setText(js.getString("authorName"));
            isbn.setText(js.getString("authorName"));
            summary.setText(js.getString("authorName"));
            userName.setText(js.getJSONObject("owner").getString("userName"));
            userEmail.setText(js.getJSONObject("owner").getString("userEmail"));
            wechatNum.setText(js.getJSONObject("owner").getString("wechatNum"));

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.button_back:
                goback();
                break;
            default:
                break;
        }

    }

    private void goback() {
        // TODO Auto-generated method stub
        this.finish();

    }
}
