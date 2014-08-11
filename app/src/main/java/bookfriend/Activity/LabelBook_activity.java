package bookfriend.Activity;



import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;
import bookfriend.AsyncTask.LabelBookTask;
import bookfriend.R;


public class LabelBook_activity extends Activity implements OnClickListener{

    public ListView list;
	private Button go_back;
	public JSONArray Msg1;

	public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        requestWindowFeature(Window.FEATURE_NO_TITLE);
	        setContentView(R.layout.activity_labelbook);
	        
	        go_back = (Button) findViewById(R.id.button_back);
	        go_back.setOnClickListener(this);
	        
	        Intent intent = getIntent();  
	        String json = intent.getStringExtra("json"); 
	        JSONObject js;
			try {
				js = new JSONObject(json);
				search(js.getString("id"));
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			list=(ListView) findViewById(R.id.listforlabels); 
			//b=this;
			
	 }
	 
		@Override
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
		
	 private void search(String string) {
		// TODO Auto-generated method stub
			try{

                LabelBookTask network = new LabelBookTask(string,this);
				network.execute();
				}catch(Exception e){
					e.printStackTrace();

				}
	}

}
