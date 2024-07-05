package com.hieuubuntu.configservice.exceptions.errorcode;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    DEFAULT_ERROR(1000, "Có lỗi xảy ra. Vui lòng liên hệ Admin", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_PARAMS_REQUEST(1001, "Tham số không hợp lệ", HttpStatus.BAD_REQUEST),
    UNAUTHENTICATED(1002, "unauthenticated", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1003, "Bạn không có quyền truy cập", HttpStatus.FORBIDDEN),
    NOT_PERMISSION(1004, "Bạn không có quyền: {name}", HttpStatus.FORBIDDEN)
   ;

    private int code;
    private String message;
    private HttpStatusCode statusCode;
}
