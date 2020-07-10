package com.gpxcollection.domain;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseData {

    private String message;
    private Object data;

    public ResponseData(String message, Object data) {
        this.message = message;
        this.data = data;
    }

    public static ResponseEntity<ResponseData> SUCCESS (Object data) {
      ResponseData response = new ResponseData("success", data);
      return new ResponseEntity<ResponseData>(response, HttpStatus.OK);
    }

    public static ResponseEntity<ResponseData> FAILED (String message) {
        ResponseData response = new ResponseData(message, null);
        return new ResponseEntity<ResponseData>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseData{" +
                "message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
