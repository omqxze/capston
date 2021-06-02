package Chat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.capston.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MyViewHolder>{
    private List<ChatData> chatList;
    private String name;
    public void addChat(ChatData chat){
        chatList.add(chat);
        notifyItemInserted(chatList.size()-1);
    }
    public ChatAdapter(List<ChatData> ch, Context con,String nick){
        chatList=ch;
        this.name=nick;
    }
    @NonNull
    @NotNull
    @Override
    public ChatAdapter.MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.row_chat,parent,false);

        MyViewHolder myViewHolder = new MyViewHolder(linearLayout);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ChatAdapter.MyViewHolder holder, int position) {
        ChatData chat = chatList.get(position);

        holder.nameText.setText(chat.getNickname());
        holder.msgText.setText(chat.getMsg());

        if (chat.getNickname().equals(this.name)){
            holder.msgText.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);
            holder.nameText.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);
        }
        else {
            holder.msgText.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
            holder.nameText.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
        }
    }

    @Override
    public int getItemCount() {
        return chatList == null ? 0: chatList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView nameText;
        public TextView msgText;
        public View rootView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            nameText = itemView.findViewById(R.id.TextView_nickname);
            msgText = itemView.findViewById(R.id.TextView_msg);
            rootView = itemView;

            itemView.setEnabled(true);
            itemView.setClickable(true);
        }
        }
}