package dev.v3ktor.model.repository.impl.memory;

import dev.v3ktor.config.MemoryStorageConfig;
import dev.v3ktor.model.entity.Author;
import dev.v3ktor.model.entity.Book;
import dev.v3ktor.model.repository.AuthorRepository;
import dev.v3ktor.model.repository.BookRepository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookMemoryRepositoryImpl implements BookRepository {


    //ATRIBUTOS
    private Path bookFile = MemoryStorageConfig.BOOKS_PATH;
    private AuthorRepository authorRepository;

    //CONSTRUTORES
    public BookMemoryRepositoryImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    //MÉTODOS
    @Override
    public void save(Book book)
    {
        var list = findAll();
        book.setId( list.isEmpty() ? 1 : list.getLast().getId() + 1 );

        try { Files.writeString(bookFile, book.toString() + '\n', StandardOpenOption.APPEND); }
        catch (IOException e) { e.printStackTrace(); }
    }

    @Override
    public Book findById(int id)
    {
        Book book = null;

        try( BufferedReader reader = new BufferedReader( new FileReader( bookFile.toFile() ) ) )
        {
            var line = reader.readLine();
            while( line != null && book == null ) {
                var atr = line.split(";");
                if( Integer.parseInt( atr[0] ) == id ) book = convertCSV( line );

                line = reader.readLine();
            }
        }
        catch (IOException e) { e.printStackTrace(); }

        return book;
    }

    @Override
    public List<Book> findByAuthor(Author author)
    {
        List<Book> books = new ArrayList<>();

        try( BufferedReader reader = new BufferedReader( new FileReader( bookFile.toFile() ) ) )
        {
            var line = reader.readLine();
            while( line != null) {
                var atr = line.split(";");
                if( Integer.parseInt( atr[5] ) == author.getId() ) books.add( convertCSV( line ) );

                line = reader.readLine();
            }
        }
        catch (IOException e) { e.printStackTrace(); }

        return books;
    }

    @Override
    public List<Book> findByTitle(String title)
    {
        List<Book> books = new ArrayList<>();

        try( BufferedReader reader = new BufferedReader( new FileReader( bookFile.toFile() ) ) )
        {
            var line = reader.readLine();
            while( line != null) {
                var atr = line.split(";");
                if( atr[1].contains(title) ) books.add( convertCSV( line ) );

                line = reader.readLine();
            }
        }
        catch (IOException e) { e.printStackTrace(); }

        return books;
    }

    @Override
    public List<Book> findAll()
    {
        List<Book> books = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(bookFile);
            books = lines.stream().map( this::convertCSV ).toList();
        }
        catch (IOException e) { e.printStackTrace(); }

        return books;
    }

    @Override
    public void update(Book book)
    {
        List<String> lines = new ArrayList<>();

        try { lines = Files.readAllLines(bookFile); }
        catch (IOException e) { e.printStackTrace(); }

        for (int i = 0; i < lines.size(); i++) {
            var atr = lines.get(i).split(";");
            if( Integer.parseInt(atr[0]) == book.getId() ) {
                book.setUpdateDate( LocalDate.now() );
                lines.set(i, book.toString());
                break;
            }
        }

        try {
            var csv = new StringBuilder();
            lines.forEach( (line) -> csv.append( line ).append('\n') );
            Files.writeString(bookFile, csv.toString());
        }
        catch (IOException e) { e.printStackTrace(); }
    }

    @Override
    public void delete(Book book)
    {
        List<String> lines = new ArrayList<>();

        try { lines = Files.readAllLines(bookFile); }
        catch (IOException e) { e.printStackTrace(); }

        for (int i = 0; i < lines.size(); i++) {
            var b = convertCSV( lines.get(i) );
            if( b.equals(book) ) {
                lines.remove(i);
                break;
            }
        }

        try {
            var csv = new StringBuilder();
            lines.forEach( (line) -> csv.append( line ).append('\n') );
            Files.writeString(bookFile, csv.toString());
        }
        catch (IOException e) { e.printStackTrace(); }
    }

    //UTILS
    private Book convertCSV(String csv)
    {
        String[] atr = csv.split(";");

        Integer id = Integer.parseInt( atr[0] );
        String title = atr[1];
        LocalDate creationDate = LocalDate.parse(atr[2]);
        LocalDate updateDate = LocalDate.parse(atr[3]);
        boolean isAvailable = Boolean.parseBoolean( atr[4] );
        Author author = authorRepository.findById( Integer.parseInt( atr[5] ) );

        return new Book(id, title, isAvailable, creationDate, updateDate, author);
    }

}