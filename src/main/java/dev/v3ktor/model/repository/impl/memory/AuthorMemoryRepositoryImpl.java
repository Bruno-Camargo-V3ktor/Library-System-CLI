package dev.v3ktor.model.repository.impl.memory;

import dev.v3ktor.config.MemoryStorageConfig;
import dev.v3ktor.model.entity.Author;
import dev.v3ktor.model.entity.Book;
import dev.v3ktor.model.repository.AuthorRepository;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class AuthorMemoryRepositoryImpl implements AuthorRepository {

    //ATRIBUTOS
    private Path authorFile = MemoryStorageConfig.AUTHORS_PATH;

    //MÉTODOS
    @Override
    public void save(Author author)
    {
        var list = findAll();
        author.setId( list.isEmpty() ? 1 :  list.getLast().getId() + 1);

        try{ Files.writeString(authorFile, author.toString() + '\n', StandardOpenOption.APPEND); }
        catch (IOException e) { e.printStackTrace(); }
    }

    @Override
    public Author findById(Integer id)
    {
        Author author = null;

        try ( BufferedReader reader = new BufferedReader( new FileReader( authorFile.toFile() ) ) ) {

            var line = reader.readLine();
            while (line != null && author == null)
            {
                var a = convertCSV(line);
                if ( a.getId() == id ) author = a;
                line = reader.readLine();
            }

        }
        catch (IOException e) { throw new RuntimeException(e); }


        return author;
    }

    @Override
    public Author findByBook(Book book)
    {
        Author author = null;

        try ( BufferedReader reader = new BufferedReader( new FileReader( authorFile.toFile() ) ) ) {

            var line = reader.readLine();
            while (line != null && author == null)
            {
                var a = convertCSV(line);
                if( a.equals( book.getAuthor() )) author = a;
                line = reader.readLine();
            }

        }
        catch (IOException e) { throw new RuntimeException(e); }

        return author;
    }

    @Override
    public List<Author> findAll()
    {
        List<Author> authors = new ArrayList<>();

        try {
            List<String> lines = Files.readAllLines(authorFile);
            authors = lines.stream().map((line) -> convertCSV(line)).toList();
        }
        catch (Exception e) { e.printStackTrace(); }

        return authors;
    }

    @Override
    public List<Author> findByName(String name)
    {
        return findAll().stream().filter(author -> author.getName().contains(name)).toList();
    }

    @Override
    public Author update(Author author)
    {
        List<String> lines = new ArrayList<>();

        try { lines = Files.readAllLines(authorFile); }
        catch (Exception e) { e.printStackTrace(); }

        for (int i = 0; i < lines.size(); i++) {
            var a = convertCSV( lines.get(i) );
            if ( a.getId() == author.getId() ) {
                lines.set(i, author.toString());
                break;
            }
        }

        try {
            var csv = new StringBuilder();
            lines.forEach( (line) -> csv.append(line).append("\n") );
            Files.writeString(authorFile, csv.toString());
        }
        catch (Exception e) { e.printStackTrace(); }

        return author;
    }

    @Override
    public void delete(Author author)
    {
        List<String> lines = new ArrayList<>();

        try { lines = Files.readAllLines(authorFile); }
        catch (Exception e) { e.printStackTrace(); }

        for (int i = 0; i < lines.size(); i++) {
            var a = convertCSV( lines.get(i) );
            if ( a.equals( author ) ) {
                lines.remove(i);
                break;
            }
        }

        try {
            var csv = new StringBuilder();
            lines.forEach( (line) -> csv.append(line).append("\n") );
            Files.writeString(authorFile, csv.toString());
        }
        catch (Exception e) { e.printStackTrace(); }

    }

    // UTILS
    public Author convertCSV(String csv)
    {
        String[] atr = csv.split(";");

        var id = Integer.parseInt(atr[0]);
        var name = atr[1];
        var birthDate = LocalDate.parse(atr[2]);

        return new Author(id, name, birthDate);
    }

}
