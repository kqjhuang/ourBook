package com.bookfriend.listview;
import java.util.ArrayList;
import java.util.HashMap;


import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bookfriend.Fragment.Search_fragment;
import com.bookfriend.R;

public class LazyAdapter extends BaseAdapter {
    
    private Fragment activity;
    private ArrayList<HashMap<String, String>> data;
    private static LayoutInflater inflater=null;
    
    public LazyAdapter(Fragment fragment, ArrayList<HashMap<String, String>> d) {
        activity = fragment;
        data=d;
        inflater = (LayoutInflater)activity.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    public LazyAdapter(Activity fragment, ArrayList<HashMap<String, String>> d) {
        
        data=d;
        inflater = (LayoutInflater)fragment.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    
    

    public int getCount() {
        return data.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }
    
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        if(convertView==null)
            vi = inflater.inflate(R.layout.itemlayout, null);

        TextView title = (TextView)vi.findViewById(R.id.title); // 标题
        TextView artist = (TextView)vi.findViewById(R.id.artist); // 歌手名
        ImageView thumb_image=(ImageView)vi.findViewById(R.id.list_image); // 缩略图
        
        HashMap<String, String> song = new HashMap<String, String>();
        song = data.get(position);
        
        // 设置ListView的相关值
        title.setText(song.get(Search_fragment.KEY_title));
        artist.setText(song.get(Search_fragment.KEY_authorName));
        //thumb_image.setImageBitmap(song.get(search_fragment.KEY_image));
        
        return vi;
    }
    
}