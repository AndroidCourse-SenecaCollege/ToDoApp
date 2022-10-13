package com.example.todoapp_f22;

import android.os.Parcel;
import android.os.Parcelable;

public class ToDo implements Parcelable {

    String task;
    String data;

    public ToDo(String task, String data) {
        this.task = task;
        this.data = data;
    }

    protected ToDo(Parcel in) {
        task = in.readString();
        data = in.readString();
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
    }
}
