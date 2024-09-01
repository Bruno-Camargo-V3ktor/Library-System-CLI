package dev.v3ktor.util;

public abstract class ViewUtils {

    //ATRIBUTOS
    public static StringBuilder head = new StringBuilder();


    //INIT
    static {
        head.append("╔════════════════════════════════════════╗\n");
        head.append("║            LIVRARIA VIRTUAL            ║\n");
        head.append("╚════════════════════════════════════════╝\n");


    }

    //MÉTODOS
    public static void clearScreen()
    {
        try { new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor(); }
        catch (Exception e) { throw new RuntimeException(e); }
    }

}
