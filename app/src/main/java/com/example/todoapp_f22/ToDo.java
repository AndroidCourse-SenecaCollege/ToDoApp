package com.example.todoapp_f22;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

public class ToDo implements Parcelable {

    String task;
    String data;
    int isArgent;

    public ToDo(String task, String data) {
        this.task = task;
        this.data = data;
        isArgent = 0;
    }
    public ToDo(String task, String data, int ia) {
        this.task = task;
        this.data = data;
        isArgent = ia;
    }

    protected ToDo(Parcel in) {
        task = in.readString();
        data = in.readString();
        isArgent = in.readInt();
    }

    public static final Creator<ToDo> CREATOR = new Creator<ToDo>() {
        @Override
        public ToDo createFromParcel(Parcel in) {
            return new ToDo(in);
        }

        @Override
        public ToDo[] newArray(int size) {
            return new ToDo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(task);
        parcel.writeString(data);
        parcel.writeInt(isArgent);
    }

    public static ToDo convertStringToTask(String tstring){
        int length = tstring.toCharArray().length;
        ToDo t = new ToDo("","");
        for (int i = 0 ; i< tstring.toCharArray().length;i++){
            if (tstring.toCharArray()[i] == ','){
                String task = tstring.substring(0, i);
                String date = tstring.substring(i+1, length );
               t = new ToDo(task,date);
               break;
            }
        }
        return t;
    }
}
