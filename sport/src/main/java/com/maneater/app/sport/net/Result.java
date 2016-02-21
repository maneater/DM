package com.maneater.app.sport.net;

import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import org.json.JSONObject;

public class Result<T> {

    public Result(String message, int result) {
        this.message = message;
        this.result = result;
    }

    @Expose
    private String message;
    @Expose
    private int result;
    @Expose
    private T data;

    public Result() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return this.result == 1;
    }

    public String toJson() {
        return new GsonBuilder().enableComplexMapKeySerialization().create().toJson(this).toString();
    }
}
