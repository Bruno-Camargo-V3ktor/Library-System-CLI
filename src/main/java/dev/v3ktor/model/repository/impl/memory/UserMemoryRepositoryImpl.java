package dev.v3ktor.model.repository.impl.memory;

import dev.v3ktor.config.MemoryStorageConfig;
import dev.v3ktor.model.entity.User;
import dev.v3ktor.model.enums.UserHoles;
import dev.v3ktor.model.repository.UserRepository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class UserMemoryRepositoryImpl implements UserRepository {

    // ATRIBUTOS
    private Path userFile = MemoryStorageConfig.USERS_PATH;

    //MÉTODOS
    @Override
    public void save(User user)
    {
        var list = findAll();
        user.setId( list.isEmpty() ? 1 : list.getLast().getId() + 1 );

        try { Files.writeString(userFile, user.toString() + '\n', StandardOpenOption.APPEND); }
        catch (IOException e) { e.printStackTrace(); }
    }

    @Override
    public User findById(int id)
    {
       User user = null;

       try( BufferedReader reader = new BufferedReader( new FileReader(userFile.toFile())) )
       {
           var line = reader.readLine();
           while ( line != null && user == null ) {
               var u = conevertCSV(line);
               user = ( u.getId() == id ? u : null );
               line = reader.readLine();
           }
       }
       catch (IOException e) { e.printStackTrace(); }

       return user;
    }

    @Override
    public User findByUsername(String username)
    {
        User user = null;

        try( BufferedReader reader = new BufferedReader( new FileReader(userFile.toFile())) )
        {
            var line = reader.readLine();
            while ( line != null && user == null ) {
                var u = conevertCSV(line);
                user = ( u.getName().equals( username ) ? u : null );
                line = reader.readLine();
            }
        }
        catch (IOException e) { e.printStackTrace(); }

        return user;
    }

    @Override
    public List<User> findAll()
    {
        List<User> users = new ArrayList<>();

        try {
            users = Files.readAllLines(userFile)
                    .stream().map( this::conevertCSV ).toList();
        }
        catch (IOException e) { e.printStackTrace(); }

        return users;
    }

    @Override
    public User update(User user)
    {
        List<String> lines = new ArrayList<>();

        try { lines = Files.readAllLines(userFile); }
        catch (IOException e) { e.printStackTrace(); }

        for (int i = 0; i < lines.size(); i++) {
            if( conevertCSV( lines.get(i) ).getId() == user.getId() ) {
                lines.set(i, user.toString());
                break;
            }
        }

        var userString = new StringBuilder();
        lines.forEach( (line) -> { userString.append(line).append("\n"); });

        try { Files.writeString(userFile, userString.toString()); }
        catch (IOException e) { e.printStackTrace(); }

        return user;
    }

    @Override
    public void delete(User user)
    {
        List<String> lines = new ArrayList<>();

        try { lines = Files.readAllLines(userFile); }
        catch (IOException e) { e.printStackTrace(); }

        for (int i = 0; i < lines.size(); i++) {
            if( conevertCSV( lines.get(i) ).equals( user ) ) {
                lines.remove(i);
                break;
            }
        }

        var userString = new StringBuilder();
        lines.forEach( (line) -> { userString.append(line).append("\n"); });

        try { Files.writeString(userFile, userString.toString()); }
        catch (IOException e) { e.printStackTrace(); }
    }

    //Utils
    private User conevertCSV(String csv)
    {
        String[] atr = csv.split(";");

        Integer id =  Integer.parseInt( atr[0] );
        String name = atr[1];
        String password = atr[2];

        List<UserHoles> list = Arrays.stream(atr[3].split(", ")).map((hole) -> UserHoles.valueOf(hole)).toList();
        UserHoles[] holes =  new UserHoles[ list.size() ];

        for (int i = 0; i < list.size(); i++) { holes[i] = list.get(i); }

        return new User( id, name, password, list.toArray(holes) );
    }

}