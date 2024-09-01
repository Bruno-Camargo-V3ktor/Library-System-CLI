package dev.v3ktor.service;

import dev.v3ktor.exception.UserNotPermissionException;
import dev.v3ktor.model.entity.Author;
import dev.v3ktor.model.entity.Book;
import dev.v3ktor.model.entity.User;
import dev.v3ktor.model.enums.UserHoles;
import dev.v3ktor.model.repository.BookRepository;

import java.time.LocalDate;
import java.util.List;

public class BookService {

    //ATRIBUTOS
    private final BookRepository bookRepository;

    //CONSTRUTORES
    public BookService( BookRepository bookRepository ) {
        this.bookRepository = bookRepository;
    }

    //MÉTODOS
    public Book getById( int id )
    {
        return bookRepository.findById( id );
    }

    public List<Book> getAllBooks()
    {
        return bookRepository.findAll();
    }

    public List<Book> getAllBooksIsAvailable( boolean available )
    {
        return bookRepository.findAll().stream().filter( (book) -> book.isAvailable() == available ).toList();
    }

    public List<Book> getBooksByTitle( String title )
    {
        return bookRepository.findByTitle(title);
    }

    public List<Book> getBooksByAuthor( Author author )
    {
        return bookRepository.findByAuthor(author);
    }

    public void updateBook( User actualUser, Book book )
    {
        if( !actualUser.getHoles().contains( UserHoles.ADMIN ) ) throw new UserNotPermissionException("Atualizar cadastro de Livros");
        bookRepository.update(book);
    }

    public void createBook( User actualUser, String title, Author author )
    {
        if( !actualUser.getHoles().contains( UserHoles.ADMIN ) ) throw new UserNotPermissionException("Criar cadastro de um novo Livro");
        bookRepository.save( new Book(null, title, true, LocalDate.now(), LocalDate.now(), author) );
    }

    public void duplicateBook( User actualUser, int id )
    {
        if( !actualUser.getHoles().contains( UserHoles.ADMIN ) ) throw new UserNotPermissionException("Duplicar o cadastro de um Livro");

        var book = bookRepository.findById(id);
        book.setId(null);

        bookRepository.save( book );
    }

}
