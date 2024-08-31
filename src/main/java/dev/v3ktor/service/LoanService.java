package dev.v3ktor.service;


import dev.v3ktor.model.entity.Book;
import dev.v3ktor.model.entity.BookLoan;
import dev.v3ktor.model.entity.User;
import dev.v3ktor.model.enums.LoanStatus;
import dev.v3ktor.model.repository.BookLoanRepository;

import java.time.LocalDate;
import java.util.List;

public class LoanService {

    //ATRIBUTOS
    private final BookLoanRepository loanRepository;

    //CONSTRUTORES
    public LoanService(BookLoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    //MÉTODOS
    public List<BookLoan> getAll()
    {
        return loanRepository.findAll();
    }

    public List<BookLoan> getLoansByBookTitile( String title )
    {
        return loanRepository.findByBookTitle( title );
    }

    public List<BookLoan> getLoansByUser( User user )
    {
        return loanRepository.findByUser(user);
    }

    public void updateStatus(BookLoan loan, LoanStatus status)
    {
        loan.setStatus( status );
        loanRepository.update(loan);
    }

    public void createLoan( Book book, User user )
    {
        if( loanRepository.findByUser(user).size() >= 5 ) return;
        loanRepository.save( new BookLoan(null, book, user, LocalDate.now(), LoanStatus.BORROWED) );
    }

}
