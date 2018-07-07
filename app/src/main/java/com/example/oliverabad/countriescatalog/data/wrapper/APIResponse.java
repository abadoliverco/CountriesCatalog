package com.example.oliverabad.countriescatalog.data.wrapper;


import com.google.gson.annotations.SerializedName;

/**
 * Created by oliverabad on 7/7/18.
 */

public class APIResponse {

    @SerializedName("IsSuccess")
    private boolean status;
    private String message;

    public APIResponse(boolean status, String message){
        this.status = status;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return status;
    }
}
