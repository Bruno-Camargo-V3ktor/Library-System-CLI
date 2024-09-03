package dev.v3ktor.view.screens;

import dev.v3ktor.domain.Library;
import dev.v3ktor.exception.InvalidCredentialsException;
import dev.v3ktor.model.entity.User;
import dev.v3ktor.model.enums.UserHoles;
import dev.v3ktor.util.Props;
import dev.v3ktor.util.ViewUtils;
import dev.v3ktor.view.View;
import dev.v3ktor.view.ViewManager;

import java.util.Scanner;

public class UserManageScreen extends View {

    //ATRIBUTOS
    private Scanner sc = new Scanner(System.in);
    private Library library;
    private User user;

    //CONSTRUTORES
    public UserManageScreen(ViewManager manager, Props props) {
        super(manager, props);
    }

    //MÉTODOS
    @Override
    public void render()
    {
        library = props.get( "libary", Library.class );
        user = library.getUser();

        System.out.println( ViewUtils.head );

        if( user.getHoles().contains( UserHoles.ADMIN ) ) userAdminView();
        else userClientView();

        ViewUtils.clearScreen();
    }

    @Override
    public void close()
    {
        ViewUtils.clearScreen();
    }

    private void userClientView()
    {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║             MEU CADASTRO               ║");
        System.out.println("║           ----------------             ║");
        System.out.println("║                                        ║");
        System.out.println("║   [1] Atualizar Infos.    [2] Voltar   ║");
        System.out.println("╚════════════════════════════════════════╝");

        Integer op = null;
        while ( op == null )
        {
            System.out.print(" ║--> ");
            try { op = Integer.parseInt(sc.nextLine()); }
            catch (RuntimeException e) { op = null; System.out.println("Opção Invalida, tente novamente...\n"); }
        }

        switch (op)
        {
            case 1:
                var username = "";
                var password = "";

                while( true )
                {
                    System.out.println("╔════════════════════════════════════════╗");
                    System.out.println("║              ATUALIZAÇÃO               ║");
                    System.out.println("║           ---------------              ║");
                    System.out.println("╚════════════════════════════════════════╝");

                    System.out.println("║    DEIXE EM BRANCO OS CAMPOS QUE NÃO   ║ ");
                    System.out.println("║              DESEJA ALTERA             ║ \n");

                    System.out.print("║ USERNAME -->  "); username = sc.nextLine();
                    System.out.print("║ PASSWORD -->  "); password = sc.nextLine();

                    try
                    {
                        var newUser = new User(
                                user.getId(),
                                username.length() > 0 ? username : user.getName(),
                                password.length() > 0 ? String.valueOf( password.hashCode() ) : user.getPassword(),
                                user.getHoles().toArray(new UserHoles[0])
                        );
                        library.userService().updateUser( user, newUser );

                        System.out.println("║          ATUALIZADO COM SUCESSO        ║ "); sc.nextLine();
                        library.setUser( newUser );
                    }

                    catch (InvalidCredentialsException e)
                    {
                        System.out.println();
                        System.err.println( e.getMessage() + "... Aperte enter para tenta novamente" ); sc.nextLine();
                        System.out.println();
                        continue;
                    }

                    break;
                }

                break;

            case 2:
                manager.to("home", props);
                break;
        }

    }

    private void userAdminView()
    {

    }

}
