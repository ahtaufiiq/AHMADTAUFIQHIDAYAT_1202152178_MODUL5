package com.example.ataufiq.ahmad_taufiq_hidayat_1202152178_modul5;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ataufiq on 25/03/18.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private Context context;
    private List<ListItem> list;
    int color;

    public ListAdapter(Context cntx, List<ListItem> list, int color) {
        this.context = cntx;
        this.list = list;
        this.color = color;
    }

    //ViewHolder
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //membuat view baru
        View view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        ViewHolder hldr = new ViewHolder(view);
        return hldr;
    }

    //Setting Value to layout
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ListItem data = list.get(position);
        holder.ToDo.setText(data.getTodo());
        holder.Description.setText(data.getDesc());
        holder.Priority.setText(data.getPrior());
        holder.cardv.setCardBackgroundColor(context.getResources().getColor(this.color));
    }

    //get list size
    @Override
    public int getItemCount() {
        return list.size();
    }

    //Get postion list
    public ListItem getData(int position) {
        return list.get(position);
    }

    //delete Data
    public void deleteData(int i) {
        //remove item
        list.remove(i);
        //Notification removed data
        notifyItemRemoved(i);
        notifyItemRangeChanged(i, list.size());
    }

    //Find View by ID
    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView ToDo, Description, Priority;
        public CardView cardv;

        public ViewHolder(View itemView) {
            super(itemView);

            ToDo = itemView.findViewById(R.id.tv_todo);
            Description = itemView.findViewById(R.id.tv_description);
            Priority = itemView.findViewById(R.id.tv_priority);
            cardv = itemView.findViewById(R.id.card);
        }
    }
}
