package dev.v3ktor.domain;

import dev.v3ktor.model.entity.Author;
import dev.v3ktor.model.entity.Book;
import dev.v3ktor.model.entity.BookLoan;
import dev.v3ktor.model.entity.User;
import dev.v3ktor.model.repository.AuthorRepository;
import dev.v3ktor.model.repository.BookLoanRepository;
import dev.v3ktor.model.repository.BookRepository;
import dev.v3ktor.model.repository.UserRepository;
import dev.v3ktor.service.AuthorService;
import dev.v3ktor.service.BookService;
import dev.v3ktor.service.LoanService;
import dev.v3ktor.service.UserService;

import java.util.List;

public class Library {

    //Atributos
    private final BookService bookService;
    private final UserService userService;
    private final AuthorService authorService;
    private final LoanService loanService;

    private User user;

    //Construtores
    public Library(BookRepository bookRepository, UserRepository userRepository, AuthorRepository authorRepository, BookLoanRepository bookLoanRepository) {
        this.bookService = new BookService( bookRepository );
        this.userService = new UserService( userRepository );
        this.authorService = new AuthorService( authorRepository );
        this.loanService = new LoanService( bookLoanRepository );
    }

    //GETTERS & SETTERS
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public AuthorService authorService() { return authorService; }
    public BookService bookService() { return bookService; }
    public LoanService loanService() { return loanService; }
    public UserService userService() { return userService; }


}
