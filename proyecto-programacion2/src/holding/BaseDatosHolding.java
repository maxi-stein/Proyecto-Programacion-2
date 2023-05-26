package holding;

import java.util.ArrayList;
import java.util.Scanner;


public class BaseDatosHolding {
    private ArrayList<Usuario> usuarios;

    public BaseDatosHolding(){
        ArrayList<Usuario> usuarios = new ArrayList<>();
        usuarios.add(new Admin("Mati","Calle Falsa","123"));
        usuarios.add(new Admin("Maxi","Calle Verdadera","321"));
    }
    public  Usuario iniciarSesion(){
        System.out.println("Usuario:");
        String user = Consola.leerString();
        System.out.println("Contraseña:");
        String pass = Consola.leerString();

        return autentificarUsuario(user,pass);
    }

    private Usuario autentificarUsuario(String user,String pass){
        Usuario usuarioEncontrado = null;
        for(Usuario usuario : usuarios){
            if(usuario.validarNombre(user)){
                if(usuario.validarPass(pass)){
                    usuarioEncontrado = usuario;
                }
            }
        }
        return usuarioEncontrado;
    }

    private class Consola {
        private Consola(){};
        private static Scanner input = new Scanner(System.in);

        public static int leerNum(){
            String numero = input.nextLine();
            return Integer.parseInt(numero);
        }

        public static String leerString(){
            return input.nextLine();
        }

    }
}
