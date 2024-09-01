package dev.v3ktor.exception;

public class InvalidCredentialsException extends RuntimeException {
    public InvalidCredentialsException() {
        //super("Invalid credentials");
        super("Credenciais Inválidas");
    }
}
