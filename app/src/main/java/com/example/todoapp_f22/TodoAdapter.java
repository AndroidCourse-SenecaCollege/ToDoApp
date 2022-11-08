package com.example.todoapp_f22;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class TodoAdapter extends BaseAdapter {

        Context context;
        ArrayList<ToDo> toDos;

        public TodoAdapter(Context context, ArrayList<ToDo> toDos) {
            this.context = context;
            this.toDos = toDos;
        }
        @Override
        public int getCount() {
            return toDos.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null){
                view = LayoutInflater.from(context).inflate(R.layout.todo_list_row,null);
            }

            TextView taskText= (TextView) view.findViewById(R.id.task);
            TextView dateText = (TextView) view.findViewById(R.id.task_date);

            taskText.setText(toDos.get(i).task);
            dateText.setText(toDos.get(i).data);
            if (toDos.get(i).isArgent == 1){
                taskText.setTextColor(this.context.getResources().getColor(R.color.red,null));
                dateText.setTextColor(this.context.getResources().getColor(R.color.red,null));

            }else {
                taskText.setTextColor(this.context.getResources().getColor(R.color.green,null));
                dateText.setTextColor(this.context.getResources().getColor(R.color.green,null));

            }

            return  view;
        }

}
