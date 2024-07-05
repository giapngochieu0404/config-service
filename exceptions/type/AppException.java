package com.hieuubuntu.configservice.exceptions.type;

import com.hieuubuntu.configservice.exceptions.errorcode.ErrorCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppException extends RuntimeException {
    public AppException(ErrorCode errorCode) {
        super();
        this.errorCode = errorCode;
    }

    private ErrorCode errorCode;
}
