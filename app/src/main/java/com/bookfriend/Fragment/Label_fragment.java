package com.bookfriend.Fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.bookfriend.Activity.LabelBook_activity;
import com.bookfriend.AsyncTask.AddLabelTask;
import com.bookfriend.AsyncTask.LabelTask;
import com.bookfriend.R;

public class Label_fragment extends Fragment implements OnQueryTextListener {
	 
	    public ListView list;
	    private SearchView sv;
        public JSONArray Msg1;
	    @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	            Bundle savedInstanceState) {
	        View rootView = inflater.inflate(R.layout.label_fragment, container, false);
	        sv = (SearchView)rootView.findViewById(R.id.sv);
	        sv.setIconifiedByDefault(false);
	        //为该SearchView组件设置事件监听器
	        sv.setOnQueryTextListener(this);
	        //设置该SearchView显示搜索按钮
	        sv.setSubmitButtonEnabled(true);
	        //设置该SearchView内默认显示的提示文本
	        sv.setQueryHint("添加标签");
	        searchlabel();
	        list=(ListView)rootView.findViewById(R.id.list1);
	        return rootView;
	        
	    }
	    
	    @Override  
		public void onActivityCreated(Bundle savedInstanceState) {  
		        super.onActivityCreated(savedInstanceState);  
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
		            	intent.setClass(getActivity(), LabelBook_activity.class);
		    			intent.putExtra("json", M.toString()); 
		    			getActivity().startActivity(intent);          
		            }  
		        });     
		 }
	    
	    
		@Override
		public boolean onQueryTextChange(String newText) {
			// TODO Auto-generated method stub
			return false;
		}

	     //单击搜索按钮时激发该方法
	    @Override
	    public boolean onQueryTextSubmit(String query) {
	        // TODO Auto-generated method stub
			try{
                AddLabelTask network = new AddLabelTask(query,this);
				network.execute();
				}catch(Exception e){
					e.printStackTrace();
					Log.e("message", e.getMessage());
				}
			searchlabel();
	        return true;
	    }
	    
		private void searchlabel() {
			// TODO Auto-generated method stub
			try{
				LabelTask network = new LabelTask(this);
				network.execute();
				}catch(Exception e){
					e.printStackTrace();
					Log.e("message", e.getMessage());
				}
		}



}
