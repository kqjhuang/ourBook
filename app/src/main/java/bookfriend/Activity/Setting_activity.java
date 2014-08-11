package bookfriend.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import bookfriend.R;

public class Setting_activity extends Activity{
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.setting);
	}
}
