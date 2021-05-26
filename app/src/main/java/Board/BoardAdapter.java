package Board;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.databinding.DataBindingUtil;

import com.example.capston.R;
import com.example.capston.databinding.ListviewCustomBinding;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.ArrayList;

import lombok.SneakyThrows;

public class BoardAdapter extends BaseAdapter {
    ListviewCustomBinding binding;
    Context con=null;
    LayoutInflater infla=null;
    ArrayList<JSONObject> info;

    public BoardAdapter(Context con, ArrayList<JSONObject> data){
        this.con=con;
        this.info=data;
        infla=LayoutInflater.from(con);
    }

    @Override
    public int getCount(){
        return info.size();
    }

    @Override
    public long getItemId(int po){
        return po;
    }

    @Override
    public JSONObject getItem(int po){
        return info.get(po);
    }

    @SneakyThrows
    @Override
    public View getView(int po, View cview, ViewGroup parent){
        View view=infla.inflate(R.layout.listview_custom,null);
        String startArea=info.get(po).get("startArea").toString();
        String endArea=info.get(po).get("endArea").toString();
        String contents=info.get(po).get("contents").toString();
        TextView textView1=(TextView)view.findViewById(R.id.PostName);
        textView1.setText(startArea+"에서 "+endArea+"까지");
        TextView textView2=(TextView)view.findViewById(R.id.postContents);
        textView2.setText(contents);
        return view;
    }
}
