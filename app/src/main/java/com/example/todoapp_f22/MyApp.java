package com.example.todoapp_f22;

import android.app.Application;

import java.util.ArrayList;

public class MyApp extends Application {
   static private ArrayList toDoArrayList = new ArrayList<>(0);


   static ArrayList<ToDo> getList(){
       return toDoArrayList;
   }

   static void addNewTodo(ToDo td){
       toDoArrayList.add(td);

   }
}
