package com.example.todoapp_f22;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class RecyclerListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_list);
        ArrayList<ToDo> list = MyApp.getList();
        ToDoRecyclerAdapter adapter = new ToDoRecyclerAdapter(this,list);
        recyclerView = findViewById(R.id.rlist);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

       // getIntent().getExtras().getParcelableArrayList("todolist");
    }
}