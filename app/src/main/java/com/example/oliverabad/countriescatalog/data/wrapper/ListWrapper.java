package com.example.oliverabad.countriescatalog.data.wrapper;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by oliverabad on 7/7/18.
 */

public class ListWrapper<T> extends APIResponse {
    @SerializedName("Response")
    private List<T> data;

    public ListWrapper(boolean status, String message){
        super(status, message);
    }

    public List<T> getData(){
        return data;
    }
}
