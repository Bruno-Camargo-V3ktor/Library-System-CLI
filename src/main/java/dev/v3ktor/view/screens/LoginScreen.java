package dev.v3ktor.view.screens;

import dev.v3ktor.domain.Library;
import dev.v3ktor.exception.InvalidCredentialsException;
import dev.v3ktor.model.entity.User;
import dev.v3ktor.util.Props;
import dev.v3ktor.util.ViewUtils;
import dev.v3ktor.view.View;
import dev.v3ktor.view.ViewManager;

import java.util.List;
import java.util.Scanner;

public class LoginScreen extends View {

    //ATRIBUTOS
    private Scanner sc = new Scanner(System.in);

    //CONSTRUTORES
    public LoginScreen(ViewManager manager, Props props) {
        super(manager, props);
    }

    //MÉTODOS
    @Override
    public void render() {
        var libary = props.get("libary", Library.class);

        System.out.println( ViewUtils.head );

        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║             TELA DE LOGIN              ║");
        System.out.println("║             -------------              ║");
        System.out.println("║                                        ║");
        System.out.println("║ [1] Login    [2] Cadastra    [3] Sair  ║");
        System.out.println("╚════════════════════════════════════════╝");

        Integer op = null;
        while (op == null)
        {
            System.out.print( "\n ║--> " );
            try {
                op = Integer.valueOf( sc.nextLine() );
                if( op < 1 || op > 3) throw new RuntimeException();
            }
            catch ( RuntimeException e ) { System.out.println("\nOpção Invalida tente novamente"); op = null; }
        }

        System.out.println();

        switch (op)
        {
            case 1:
                System.out.println("╔════════════════════════════════════════╗");
                System.out.println("║                 LOGIN                  ║");
                System.out.println("║             -------------              ║");
                System.out.println("╚════════════════════════════════════════╝");

                System.out.print("║ USERNAME -->  "); var username = sc.nextLine();
                System.out.print("║ PASSWORD -->  "); var password = sc.nextLine();

                try
                {
                    var user = libary.userService().login(username, password);
                    libary.setUser(user);
                    manager.to("home", props);
                }
                catch (InvalidCredentialsException e)
                {
                    System.err.print("\nCredencias Invalidas... Aperte enter para tenta novamente"); sc.nextLine();
                    manager.to("login", props);
                }

                break;

            case 2:
                username = null; password = null;
                while(username == null || password == null)
                {
                    System.out.println("╔════════════════════════════════════════╗");
                    System.out.println("║                CADASTRO                ║");
                    System.out.println("║             --------------             ║");
                    System.out.println("╚════════════════════════════════════════╝");

                    System.out.print("║ USERNAME -->  "); username = sc.nextLine();
                    System.out.print("║ PASSWORD -->  "); password = sc.nextLine();

                    try
                    {
                        var user = new User(null, username, String.valueOf(password.hashCode()) );
                        libary.userService().createUser(null, user);
                        libary.setUser(user);

                        System.out.println("\n║   CADASTRO REALIZADO COM SUCESSO   ║");
                        System.out.print    ("║       APERTE ENTER PARA ENTRA      ║"); sc.nextLine();

                        manager.to("home", props);
                    }

                    catch (InvalidCredentialsException e)
                    {
                        System.out.println();

                        System.err.println( e.getMessage() + "... Aperte enter para tenta novamente" ); sc.nextLine();
                        username = null;
                        password = null;

                        System.out.println();
                    }
                }

                break;

            case 3:
                System.out.println("╔════════════════════════════════════════╗");
                System.out.println("║         Encerrando o sistema...        ║");
                System.out.println("║             -------------              ║");
                System.out.println("╚════════════════════════════════════════╝");

                manager.close();
        }

    }

    @Override
    public void close() {
        ViewUtils.clearScreen();
    }

}
