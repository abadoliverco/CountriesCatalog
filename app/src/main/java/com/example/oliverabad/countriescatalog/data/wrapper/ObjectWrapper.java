package com.example.oliverabad.countriescatalog.data.wrapper;


/**
 * Created by oliverabad on 7/7/18.
 */

public class ObjectWrapper<T> extends APIResponse {

    private T data;

    public ObjectWrapper(boolean status, String message){
        super(status, message);
    }

    public T getData() {
        return data;
    }

}
