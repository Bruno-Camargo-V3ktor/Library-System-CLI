package dev.v3ktor;

import dev.v3ktor.config.MemoryStorageConfig;

public class Application {
    public static void main(String[] args) {

        try {
            MemoryStorageConfig.setGlobalPath("C:\\Library-CLI");
            MemoryStorageConfig.init();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
}