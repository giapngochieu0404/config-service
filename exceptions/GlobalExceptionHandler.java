package com.hieuubuntu.configservice.exceptions;

import com.hieuubuntu.configservice.dtos.DefaultResponse;
import com.hieuubuntu.configservice.exceptions.errorcode.ErrorCode;
import com.hieuubuntu.configservice.exceptions.type.AppException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    // Normal:
    @ExceptionHandler(value = Exception.class)
    ResponseEntity<DefaultResponse> handleAllExceptions(RuntimeException e) {
        log.error("RuntimeException:", e.toString());
        DefaultResponse response = new DefaultResponse();
        response.setSuccess(false);
        response.setCode(ErrorCode.DEFAULT_ERROR.getCode());
        response.setMessage(ErrorCode.DEFAULT_ERROR.getMessage());
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(value = AppException.class)
    ResponseEntity<DefaultResponse> handleAppExceptions(AppException e) {
        ErrorCode errorCode = e.getErrorCode();

        DefaultResponse response = new DefaultResponse();
        response.setSuccess(false);
        response.setCode(errorCode.getCode());
        response.setMessage(errorCode.getMessage());

        return ResponseEntity.status(errorCode.getStatusCode()).body(response);
    }
}
