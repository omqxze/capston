package Board;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.capston.R;
import com.example.capston.databinding.ListviewMainCustomBinding;

import org.json.JSONObject;

import java.util.ArrayList;

import lombok.SneakyThrows;

public class BoardMainAdapter extends BaseAdapter {
    ListviewMainCustomBinding binding;
    Context con=null;
    LayoutInflater infla=null;
    ArrayList<JSONObject> info;

    public BoardMainAdapter(Context con, ArrayList<JSONObject> data){
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
        View view=infla.inflate(R.layout.listview_main_custom,null);
        String startArea=info.get(po).get("startArea").toString();
        String endArea=info.get(po).get("endArea").toString();
        TextView test=(TextView)view.findViewById(R.id.postname);
        test.setText(startArea+"에서 "+endArea+"까지");
        return view;
    }
}
