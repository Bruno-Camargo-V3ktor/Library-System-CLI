package dev.v3ktor.model.repository.impl.memory;

import dev.v3ktor.config.MemoryStorageConfig;
import dev.v3ktor.model.entity.User;
import dev.v3ktor.model.enums.UserHoles;
import dev.v3ktor.model.repository.UserRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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
        try {
            String[] atr = Files.readAllLines(userFile).getLast().split(";");
            user.setId( Integer.parseInt( atr[0] ) + 1  );

            StringBuilder users = new StringBuilder( Files.readString(userFile) );
            users.append(user.toString());

            Files.write(userFile, users.toString().getBytes("UTF-8"));
        }
        catch (IOException e) { e.printStackTrace(); }
    }

    @Override
    public User findById(int id)
    {
        List<String> lines = new ArrayList<>();

        try { lines = Files.readAllLines(userFile); }
        catch (IOException e) { e.printStackTrace(); }

        var userCSV = lines.stream().filter( (line) -> {

            String[] atr = line.split(";");
            return Objects.equals(atr[0], String.valueOf(id));

        } ).findFirst();

        if(userCSV.isEmpty()) return null;

        return conevertCSV( userCSV.get() );
    }

    @Override
    public User findByUsername(String username)
    {
        List<String> lines = new ArrayList<>();

        try { lines = Files.readAllLines(userFile); }
        catch (IOException e) { e.printStackTrace(); }

        var userCSV = lines.stream().filter( (line) -> {

            String[] atr = line.split(";");
            return Objects.equals(atr[1], username);

        } ).findFirst();

        if(userCSV.isEmpty()) return null;

        return conevertCSV( userCSV.get() );
    }

    @Override
    public User update(User user)
    {
        List<String> lines = new ArrayList<>();

        try { lines = Files.readAllLines(userFile); }
        catch (IOException e) { e.printStackTrace(); }

        for (int i = 0; i < lines.size(); i++) {

            String[] atr = lines.get(i).split(";");

            if( Objects.equals(atr[0], String.valueOf(user.getId())) ){
                lines.set(i, user.toString().replace("\n", ""));
                break;
            }
        }

        var userString = new StringBuilder();
        lines.forEach( (line) -> { userString.append(line).append("\n"); });

        try {
            Files.write( userFile,
                    userString.toString().getBytes("UTF-8") );
        }
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

            String[] atr = lines.get(i).split(";");

            if( Objects.equals(atr[0], String.valueOf(user.getId())) ){
                lines.remove(i);
                break;
            }
        }

        var userString = new StringBuilder();
        lines.forEach( (line) -> { userString.append(line).append("\n"); });

        try {
            Files.write( userFile,
                    userString.toString().getBytes("UTF-8") );
        }
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