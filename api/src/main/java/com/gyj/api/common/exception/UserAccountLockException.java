package com.gyj.api.common.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * 登录成功处理器
 */
public class UserAccountLockException  extends AuthenticationException {

    public UserAccountLockException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public UserAccountLockException(String msg) {
        super(msg);
    }
}
