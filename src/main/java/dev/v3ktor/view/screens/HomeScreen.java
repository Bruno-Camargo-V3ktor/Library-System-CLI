package dev.v3ktor.view.screens;

import dev.v3ktor.util.Props;
import dev.v3ktor.util.ViewUtils;
import dev.v3ktor.view.View;
import dev.v3ktor.view.ViewManager;

import java.util.Scanner;

public class HomeScreen extends View {

    //ATRIBUTOS
    private Scanner sc = new Scanner(System.in);

    //CONSTRUTORES
    public HomeScreen(ViewManager manager, Props props) {
        super(manager, props);
    }

    //MÉTODOS
    @Override
    public void render() {
        System.out.println( ViewUtils.head );

        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║             MENU PRINCIPAL             ║");
        System.out.println("║           -----------------            ║");
        System.out.println("║                                        ║");
        //System.out.println("║ [1] Login    [2] Cadastra    [3] Sair  ║");
        System.out.println("╚════════════════════════════════════════╝");

        sc.nextLine();
    }

    @Override
    public void close() {
        ViewUtils.clearScreen();
    }

}
