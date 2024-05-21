package com.apnacart.products.dto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ApiResponse<T> {
    private HttpStatus status;
    private String message;
    private T data;

    private ApiResponse(Builder<T> builder){
        this.status = builder.status;
        this.data = builder.data;
        this.message = builder.message;
    }

    public static Builder<?> builder(){
        return new Builder();
    }

    public static class Builder<T>{
        private HttpStatus status;
        private String message;
        private T data;

        public ResponseEntity<ApiResponse<T>> build(){
            return new ResponseEntity<ApiResponse<T>>(new ApiResponse<T>(this),this.status);
        }

        public Builder setStatus(HttpStatus status) {
            this.status = status;
            return this;
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder setData(T data) {
            this.data = data;
            return this;
        }
    }
}

class ApiResponseData{
    private HttpStatus status;
    private String message;
    private Object data;
}
