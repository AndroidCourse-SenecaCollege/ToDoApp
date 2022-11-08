package com.example.todoapp_f22;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ActivityResultLauncher<Intent> newToDOActivityResultLauncher;
    ListView baseAdapterList;
    TodoAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyApp.setList(((MyApp)getApplication()).storageManager.readTasksFromFile(MainActivity.this));
        baseAdapterList = (ListView) findViewById(R.id.simpleListView);
        adapter = new TodoAdapter(this,MyApp.getList());
        baseAdapterList.setAdapter(adapter);

        baseAdapterList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("TODO","Base Adapter Item Click Listener");
            }
        });

        newToDOActivityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        // when the app get the result (image)
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent data = result.getData();
                            Bundle bundle = data.getExtras();
                            ToDo newTodo = bundle.getParcelable("newTodo");
                            MyApp.addNewTodo(newTodo);
                            ((MyApp)getApplication()).storageManager.writeNewTaskToFile(MainActivity.this,newTodo);
                            adapter.notifyDataSetChanged();
                        }
                    }
                });

    }



    public void addNewTask(View view) {
       Intent addToDoIntent = new Intent(this, AddToDoActivity.class);
        newToDOActivityResultLauncher.launch(addToDoIntent);
    }



    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.todo_mune,menu);
        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()){
            case R.id.add:{
                Intent myIntent = new Intent(this,AddToDoActivity.class);
                newToDOActivityResultLauncher.launch(myIntent);
                break;
            }
            case R.id.rlist:{
                Intent myIntent = new Intent(this,RecyclerListActivity.class);
                Bundle bundle = new Bundle();
//                bundle.putParcelableArrayList("todolist",MyApp.getList());
//                myIntent.putExtra("bundle",bundle);
                startActivity(myIntent);
            }

            case R.id.deleteAllTask:
            {
                ((MyApp)getApplication()).storageManager.deleteAllTasks(MainActivity.this);
                MyApp.setList(new ArrayList<ToDo>(0));
                adapter.toDos = MyApp.getList();
                adapter.notifyDataSetChanged();
            }
        }
        return true;
    }

}