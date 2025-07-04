package com.smtcoders.api;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {

    private boolean success;
    private String message;
    private T data;


    private int statusCode;
    private String token;
    private String role;

    public ApiResponse(String s, Object o, int i) {
    }
    public ApiResponse(String s, Object object, int code, String token, String role){

    }

}