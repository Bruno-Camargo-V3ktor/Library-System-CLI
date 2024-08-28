package dev.v3ktor;

import dev.v3ktor.config.MemoryStorageConfig;
import dev.v3ktor.model.entity.Author;
import dev.v3ktor.model.entity.Book;
import dev.v3ktor.model.entity.User;
import dev.v3ktor.model.enums.UserHoles;
import dev.v3ktor.model.repository.AuthorRepository;
import dev.v3ktor.model.repository.impl.memory.AuthorMemoryRepositoryImpl;

import java.time.LocalDate;
import java.util.List;

public class Application {
    public static void main(String[] args) {

        try {
            MemoryStorageConfig.setGlobalPath("C:\\Library-CLI");
            MemoryStorageConfig.init(
              List.of( new User(1, "admin", String.valueOf("admin".hashCode()), UserHoles.ADMIN) ),null, null, null
            );
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
}