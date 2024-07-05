package com.hieuubuntu.configservice.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DefaultResponse<T> {
    private int code = 200;
    private String message;
    private boolean success;
    private T data;

    public static <T> DefaultResponse<T> success(T data) {
        DefaultResponse<T> response = new DefaultResponse<>();
        response.setSuccess(true);
        response.setMessage("Thành công");
        response.setData(data);
        return response;
    }

}
