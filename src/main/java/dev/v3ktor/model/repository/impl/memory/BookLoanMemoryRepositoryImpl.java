package dev.v3ktor.model.repository.impl.memory;

import dev.v3ktor.config.MemoryStorageConfig;
import dev.v3ktor.model.entity.Book;
import dev.v3ktor.model.entity.BookLoan;
import dev.v3ktor.model.entity.User;
import dev.v3ktor.model.enums.LoanStatus;
import dev.v3ktor.model.repository.BookLoanRepository;
import dev.v3ktor.model.repository.BookRepository;
import dev.v3ktor.model.repository.UserRepository;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookLoanMemoryRepositoryImpl implements BookLoanRepository {

    //ATRIBUTOS
    private Path bookloanFile = MemoryStorageConfig.LOANS_PATH;
    private BookRepository bookRepository;
    private UserRepository userRepository;

    //CONSTRUTORES
    public BookLoanMemoryRepositoryImpl(BookRepository bookRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    //MÉTODOS
    @Override
    public void save(BookLoan bookLoan) {

        var list = findAll();
        bookLoan.setId( list.isEmpty() ? 1 : list.getLast().getId() + 1 );

        try { Files.writeString(bookloanFile, bookLoan.toString() + '\n', StandardOpenOption.APPEND); }
        catch (IOException e) { e.printStackTrace(); }

    }

    @Override
    public BookLoan findById(Integer id) {
        BookLoan loan = null;

        try( BufferedReader reader = new BufferedReader( new FileReader(bookloanFile.toFile())) )
        {

            var line = reader.readLine();
            while ( line != null && loan == null )
            {
                var atr = line.split(";");
                if( Integer.parseInt( atr[0] ) == id ) { loan = convertCSV(line); }

                line = reader.readLine();
            }

        }
        catch (IOException e) { e.printStackTrace(); }

        return loan;
    }

    @Override
    public List<BookLoan> findByUser(User user) {
        List<BookLoan> loans = new ArrayList<>();

        var list = findAll();
        for (int i = 0; i < loans.size(); i++) {
            var l = list.get(i);
            if( l.getUser().equals(user) ) loans.add( l );
        }

        return loans;
    }

    @Override
    public List<BookLoan> findByBookTitle(String title) {
        List<BookLoan> loans = new ArrayList<>();

        var list = findAll();
        for (int i = 0; i < loans.size(); i++) {
            var l = list.get(i);
            if( l.getBook().getTitle().contains( title ) ) loans.add( l );
        }

        return loans;
    }

    @Override
    public List<BookLoan> findByStatus(String status) {
        List<BookLoan> loans = new ArrayList<>();

        var list = findAll();
        for (int i = 0; i < loans.size(); i++) {
            var l = list.get(i);
            if( l.getStatus().equals( LoanStatus.valueOf( status ) ) ) loans.add( l );
        }

        return loans;
    }

    @Override
    public List<BookLoan> findAll() {

        List<BookLoan> loans = new ArrayList<>();

        try { loans =  Files.readAllLines(bookloanFile).stream().map( (this::convertCSV ) ).toList(); }
        catch (IOException e) { e.printStackTrace(); }

        return  loans;
    }

    @Override
    public void update(BookLoan bookLoan) {

        List<String> lines = new ArrayList<>();
        try { lines = Files.readAllLines(bookloanFile); }
        catch (IOException e) { e.printStackTrace(); }

        for (int i = 0; i < lines.size(); i++) {
            var loan = convertCSV(lines.get(i));
            if( loan.getId() == bookLoan.getId() ) {
                lines.set(i, bookLoan.toString());
                break;
            }
        }

        try {
            var csv = new StringBuilder();
            lines.forEach( (line) -> csv.append(csv).append('\n') );
            Files.writeString(bookloanFile, csv);
        }
        catch (IOException e) { e.printStackTrace(); }

    }

    @Override
    public void delete(BookLoan bookLoan) {

        List<String> lines = new ArrayList<>();
        try { lines = Files.readAllLines(bookloanFile); }
        catch (IOException e) { e.printStackTrace(); }

        for (int i = 0; i < lines.size(); i++) {
            var loan = convertCSV(lines.get(i));
            if( loan.equals( bookLoan ) ) { lines.remove(i); break; }
        }

        try {
            var csv = new StringBuilder();
            lines.forEach( (line) -> csv.append(csv).append('\n') );
            Files.writeString(bookloanFile, csv);
        }
        catch (IOException e) { e.printStackTrace(); }

    }

    //UTILS
    private BookLoan convertCSV(String csv) {
        var atr = csv.split(";");

        Integer id = Integer.parseInt( atr[0] );
        Book book = bookRepository.findById( Integer.parseInt( atr[1] ) );
        User user = userRepository.findById( Integer.parseInt( atr[2] ) );
        LocalDate date = LocalDate.parse( atr[3] );
        LoanStatus status = LoanStatus.valueOf( atr[4] );

        return new BookLoan(id, book, user, date, status);
    }

}