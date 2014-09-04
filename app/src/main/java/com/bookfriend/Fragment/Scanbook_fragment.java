package com.bookfriend.Fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bookfriend.AsyncTask.ShareTask;
import com.bookfriend.R;

import com.bookfriend.tools.book.BookInfo;


public class Scanbook_fragment extends Fragment implements OnClickListener{
    public BookInfo book;
    public Button button;
    public Scanbook_fragment(BookInfo A){
    	book = A ;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.scanbook, container, false);
        button =(Button) rootView.findViewById(R.id.share_button);
        if(book.getTitle().isEmpty()){
        	Toast.makeText(getActivity(),"请扫描书籍", Toast.LENGTH_LONG).show();
        	rootView.findViewById(R.id.linear).setVisibility(View.GONE);
        }else{
        button.setVisibility(View.VISIBLE);
        ((TextView) rootView.findViewById(R.id.bookview_title)).setText(book.getTitle());
        ((TextView) rootView.findViewById(R.id.bookview_author)).setText(book.getAuthor());
        ((TextView) rootView.findViewById(R.id.bookview_publisher)).setText(book.getPublisher());
        ((TextView) rootView.findViewById(R.id.bookview_publisherdate)).setText(book.getPublishDate());
        ((TextView) rootView.findViewById(R.id.bookview_isbn)).setText(book.getISBN());
        ((TextView) rootView.findViewById(R.id.bookview_summary)).setText(book.getSummary());
        ((ImageView) rootView.findViewById(R.id.bookview_cover)).setImageBitmap(book.getBitmap());
        }

        return rootView;
    }
    
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.share_button:
			share();
			break;
		default:
			break;
		}
	}
	
	 @Override  
	public void onActivityCreated(Bundle savedInstanceState) {  
	        super.onActivityCreated(savedInstanceState);
	        button.setOnClickListener(this);
	 }
	private void share() {
		// TODO Auto-generated method stub
		try{
            ShareTask network = new ShareTask(book.getTitle(),book.getAuthor(),
	    	book.getSummary(),book.getPublisher(),
	    	book.getISBN10(),book.getISBN(),book.getImage(),book.getPrice(),this);
		    network.execute();
		}catch(Exception e){
			Toast.makeText(getActivity(), "分享失败", Toast.LENGTH_LONG).show();
		}
	}


}
