package dev.v3ktor.view.screens;

import dev.v3ktor.domain.Library;
import dev.v3ktor.model.enums.UserHoles;
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
        var libary = props.get( "libary", Library.class );
        var user = libary.getUser();

        System.out.println( ViewUtils.head );

        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║             MENU PRINCIPAL             ║");

        System.out.println( !user.getHoles().contains( UserHoles.ADMIN )
                        ?  "║                (CLIENT)                ║"
                        :  "║                (ADMIN)                 ║"
                        );


        System.out.println("║           -----------------            ║");
        System.out.println("║                                        ║");
        System.out.println("║ [1] Livros [2] Emprestimos [3] Autores ║");
        System.out.println("║       [4] Cadastros  [5] Logout        ║");
        System.out.println("╚════════════════════════════════════════╝");


        Integer op = null;
        while(op == null)
        {
            System.out.print( "\n ║--> " );
            try
            {
                op = Integer.parseInt( sc.nextLine() );
                if( op < 1 || op > 5 ) throw new RuntimeException();
            }
            catch (RuntimeException e)
            {
                op = null;
                System.out.print("Opção Invalida... tente novamente\n");
            }

        }

        switch( op )
        {
            case 1:
                break;

            case 2:
                break;

            case 3:
                break;

            case 4:
                manager.to("userManager", props);
                break;

            case 5:
                libary.setUser(null);
                manager.to("login", props);
                break;
        }

    }

    @Override
    public void close() {
        ViewUtils.clearScreen();
    }


}
