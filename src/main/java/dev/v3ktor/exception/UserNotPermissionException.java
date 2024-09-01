package dev.v3ktor.exception;

public class UserNotPermissionException extends RuntimeException {
    public UserNotPermissionException(String msg) {
        //super("do not have permission to perform this operation: " + msg);
        super("Não tem permissão para realizar esta operação: " + msg);
    }
}
