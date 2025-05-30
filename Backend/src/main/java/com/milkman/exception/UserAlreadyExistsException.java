package com.milkman.exception;

import com.milkman.types.ErrorType;
import org.springframework.http.HttpStatus;

public class UserAlreadyExistsException extends ApiException {
    public UserAlreadyExistsException(String phone) {
        super("User already registered with this phone: " + phone,
                "AUTH-400-PHONE",
                HttpStatus.BAD_REQUEST,
                "Phone number is already taken",
                ErrorType.BUSINESS);
    }
}
