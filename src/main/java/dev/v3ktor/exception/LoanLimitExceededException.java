package dev.v3ktor.exception;

public class LoanLimitExceededException extends RuntimeException {

    public LoanLimitExceededException() {
        //super("User is at the limit of active loans");
        super("Usuario esta no limite de empréstimos ativos");
    }

}
