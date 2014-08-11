package bookfriend.Fragment;



import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.os.Bundle;
import android.app.Fragment;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;

import bookfriend.Activity.Owner_book;
import bookfriend.AsyncTask.SearchTask;
import bookfriend.R;
import bookfriend.tools.CleanableEditText;


public class Search_fragment extends Fragment implements OnClickListener{

	public static final String KEY_title = "bookName";
	public static final String KEY_authorName = "authorName";  
	//public static final String KEY_image = "imageUrl";
    private CleanableEditText A;
    public JSONArray Msg1 = new JSONArray();
    public LinearLayout linearLayout1;
    public LinearLayout linearLayout2;
    public ListView list;

    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.search_fragment, container, false);
        
        A =  (CleanableEditText) rootView.findViewById(R.id.search_edit);
        linearLayout1 = (LinearLayout) rootView.findViewById(R.id.linearLayout1);
        linearLayout2 = (LinearLayout) rootView.findViewById(R.id.linearLayout2);
        linearLayout1.setVisibility(View.INVISIBLE);
        list=(ListView)rootView.findViewById(R.id.list);
        return rootView;
        
    }
    
	 @Override  
	public void onActivityCreated(Bundle savedInstanceState) {  
	        super.onActivityCreated(savedInstanceState);  
	        Button button = (Button) getActivity().findViewById(R.id.search_but);  
	        button.setOnClickListener(this);
	        list.setOnItemClickListener(new OnItemClickListener() {  
	        	  
	            @Override  
	            public void onItemClick(AdapterView<?> parent, View view,  
	                    int position, long id) {  
	            	JSONObject M = null;
	            	try {
						 M = Msg1.getJSONObject(position);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						Log.e("message", e.toString());
					}
	            	Intent intent = new Intent();
                    intent.setClass(getActivity(), Owner_book.class);
	    			intent.putExtra("json", M.toString()); 
	    			getActivity().startActivity(intent);        
	                        
	            }  
	        });     
	 }
	 
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.search_but:
			search();
			break;
		default:
			break;
		}
	}

	
	private void search() {
		// TODO Auto-generated method stub
		try{
            SearchTask network = new SearchTask(A.getText().toString(),this);
			network.execute();
			}catch(Exception e){
			}
	}
}