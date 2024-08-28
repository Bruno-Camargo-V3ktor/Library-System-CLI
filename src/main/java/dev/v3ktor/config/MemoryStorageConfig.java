package dev.v3ktor.config;

import dev.v3ktor.model.entity.Author;
import dev.v3ktor.model.entity.Book;
import dev.v3ktor.model.entity.BookLoan;
import dev.v3ktor.model.entity.User;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public abstract class MemoryStorageConfig {

    //ATRIBUTOS
    public static Path  GLOBAL_PATH;
    public static Path BOOKS_PATH;
    public static Path USERS_PATH;
    public static Path AUTHORS_PATH;
    public static Path LOANS_PATH;

    //Métodos
    public static void setGlobalPath(String path) {
        GLOBAL_PATH = Paths.get(path);
        BOOKS_PATH = Paths.get( path + "\\books.lock");
        USERS_PATH = Paths.get( path + "\\users.lock");
        AUTHORS_PATH = Paths.get( path + "\\authors.lock");
        LOANS_PATH = Paths.get( path + "\\loans.lock");
    }

    public static void init() throws IOException {

        if( !Files.exists(GLOBAL_PATH) ) {
            Files.createDirectory( GLOBAL_PATH );
            Files.createFile( BOOKS_PATH );
            Files.createFile( USERS_PATH );
            Files.createFile( AUTHORS_PATH );
            Files.createFile( LOANS_PATH );
        }

    }

    public static void init(List<User> users, List<Book> books, List<Author> authors, List<BookLoan> loans) throws IOException {

        if( !Files.exists(GLOBAL_PATH) ) {
            Files.createDirectory( GLOBAL_PATH );
            Files.createFile( BOOKS_PATH );
            Files.createFile( USERS_PATH );
            Files.createFile( AUTHORS_PATH );
            Files.createFile( LOANS_PATH );

            populationFile(USERS_PATH, users);
            populationFile(BOOKS_PATH, books);
            populationFile(AUTHORS_PATH, authors);
            populationFile(LOANS_PATH, loans);
        }

    }

    private static void populationFile( Path path, List<?> objects ) throws IOException {
        if( objects == null ) return;

        var strWrite = new StringBuilder("");
        objects.forEach( (object) ->
        {
            strWrite.append( object.toString() );
        } );

        try { Files.write( path, strWrite.toString().getBytes("UTF-8") ); }
        catch (IOException e) { throw new RuntimeException(e); }
    }

}