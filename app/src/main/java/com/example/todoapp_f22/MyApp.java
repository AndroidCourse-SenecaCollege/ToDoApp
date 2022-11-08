package com.example.todoapp_f22;

import android.app.Application;

import java.util.ArrayList;

public class MyApp extends Application {
   static private ArrayList toDoArrayList = new ArrayList<>(0);

   FileStorageManager storageManager = new FileStorageManager();

   static void setList(ArrayList<ToDo> list){
       toDoArrayList = list;
   }

   static ArrayList<ToDo> getList(){
       return toDoArrayList;
   }

   static void addNewTodo(ToDo td){
       toDoArrayList.add(td);

   }
}
