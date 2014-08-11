package bookfriend.Fragment;

import org.json.JSONArray;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import bookfriend.AsyncTask.MybookTask;
import bookfriend.R;


public class Mybook_fragment extends Fragment{

    public ListView list;
    public JSONArray Msg1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.book_fragment, container, false);
        searchbook();
        list=(ListView)rootView.findViewById(R.id.list2);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        list.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
// book情况  这里代码还没写
            }
        });
    }

    private void searchbook() {
        // TODO Auto-generated method stub
        try{
            MybookTask network = new MybookTask(this);
            network.execute();
        }catch(Exception e){
            e.printStackTrace();
            Log.e("message", e.getMessage());
        }
    }

}
