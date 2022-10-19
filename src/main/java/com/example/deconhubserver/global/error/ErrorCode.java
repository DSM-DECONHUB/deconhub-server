package com.example.deconhubserver.global.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {

    BAD_REQUEST(400, "Bad Request"),
    PASSWORD_MISS_MATCHED(409, "Password Miss Match"),

    USER_NOT_FOUND(404, "User Not Found"),
    USER_ALREADY_EXISTS(409, "User Already Exists"),

    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),

    ROLE_NOT_FOUND(404,"Role Not Found");

    private final int status;
    private final String message;
}
