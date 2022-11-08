package com.example.todoapp_f22;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ToDoRecyclerAdapter extends RecyclerView.Adapter<ToDoRecyclerAdapter.viewHolder> {

    Context mContex;
    ArrayList<ToDo> list;

    public ToDoRecyclerAdapter(Context mContex, ArrayList<ToDo> list) {
        this.mContex = mContex;
        this.list = list;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater myInflater = LayoutInflater.from(mContex);
        View view = myInflater.inflate(R.layout.todo_list_row_recycler,parent,false);

        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.task.setText(list.get(position).task);
        holder.date.setText(list.get(position).data);

        if (list.get(position).isArgent == 1){
            holder.task.setTextColor(this.mContex.getResources().getColor(R.color.red,null));
            holder.date.setTextColor(this.mContex.getResources().getColor(R.color.red,null));

        }else {
            holder.task.setTextColor(this.mContex.getResources().getColor(R.color.green,null));
            holder.date.setTextColor(this.mContex.getResources().getColor(R.color.green,null));

        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder{

        public TextView task;
        public  TextView date;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            task = itemView.findViewById(R.id.task);
            date = itemView.findViewById(R.id.task_date);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("TODO", "Recycler Adapter Item Click Listener");
                }
            });
        }
    }
}
