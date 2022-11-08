package com.example.todoapp_f22;

import android.content.Context;
import android.util.Log;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class FileStorageManager {

    static String fileName = "Tasks.txt";
    FileOutputStream fos;


    public void writeNewTaskToFile(Context context, ToDo newTodo){
    // Task1,11/11/2022 @ fix the door, 20/11/2022 @ go shopping, 21/11/2022
        try {
            fos = context.openFileOutput(fileName,Context.MODE_APPEND);
            String task = newTodo.task + "," + newTodo.data + "@";
            fos.write(task.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteAllTasks(Context context){
        try {
            fos = context.openFileOutput(fileName,Context.MODE_PRIVATE);
            fos.write("".getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<ToDo> readTasksFromFile(Context context){
        ArrayList<ToDo> allTasks = new ArrayList<>(0);
        StringBuffer stringBuffer = new StringBuffer();
        try {
            int read = 0;
            FileInputStream fis = context.openFileInput(fileName);
            InputStreamReader inputStreamReader = new InputStreamReader(fis, StandardCharsets.UTF_8);
            while ( (read = inputStreamReader.read() )!= -1){// A = 56
               stringBuffer.append ((char)read);
            }
            allTasks = convertStringtoToDoList(stringBuffer.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return allTasks;
    }

    private ArrayList<ToDo> convertStringtoToDoList (String filetext){
        ArrayList<ToDo> allTasks = new ArrayList<>(0);
        // Fix the door,19/10/2022@Go shopping,12/10/2022@
        int first = 0;
        for (int i = 0 ; i< filetext.toCharArray().length;i++){
            if (filetext.toCharArray()[i] == '@'){
               String oneTask = filetext.substring(first,i);
               first = i + 1;
               allTasks.add(ToDo.convertStringToTask(oneTask));
            }
        }

        return allTasks;
    }



}
