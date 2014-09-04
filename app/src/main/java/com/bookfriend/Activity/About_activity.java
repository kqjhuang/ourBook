package com.bookfriend.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import com.bookfriend.R;

public class About_activity extends Activity{
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.about);
	}
}
