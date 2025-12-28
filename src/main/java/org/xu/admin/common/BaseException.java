package org.xu.admin.common;

import lombok.Getter;

@Getter
public class BaseException extends RuntimeException {
    private final Integer code;

    public BaseException(String msg) {
        super(msg);
        this.code = 500;
    }

    public BaseException(Integer code, String msg) {
        super(msg);
        this.code = code;
    }
}