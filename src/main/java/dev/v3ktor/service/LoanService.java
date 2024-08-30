package dev.v3ktor.service;


import dev.v3ktor.model.repository.BookLoanRepository;

public class LoanService {

    //ATRIBUTOS
    private final BookLoanRepository loanRepository;

    //CONSTRUTORES
    public LoanService(BookLoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }


}
