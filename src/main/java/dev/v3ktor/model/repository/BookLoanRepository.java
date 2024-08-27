package dev.v3ktor.model.repository;

import dev.v3ktor.model.entity.BookLoan;
import dev.v3ktor.model.entity.User;
import java.util.List;

public interface BookLoanRepository {

    // C -> Create
    void save(BookLoan bookLoan);

    // R -> Read
    BookLoan findById(Integer id);
    List<BookLoan> findAll();
    List<BookLoan> findByUser(User user);
    List<BookLoan> findByBookTitle(String title);
    List<BookLoan> findByStatus(String status);

    // U -> Update
    BookLoan update(BookLoan bookLoan);

    // D -> Delete
    void delete(BookLoan bookLoan);

}
