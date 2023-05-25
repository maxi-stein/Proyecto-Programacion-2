package holding;

import java.util.Scanner;

public class SistemaDeGestion {

    private SistemaDeGestion(){};



    public static void run(){
<<<<<<< HEAD
        
=======

        BaseDatosHolding bd = new BaseDatosHolding();

        int num;
        do{
            num = mostrarOpciones();
        }while(num != 1 && num!=2);

        if(num==1){
            Usuario loggedInUsuario = bd.iniciarSesion();
        }
>>>>>>> main
    }

    private static int mostrarOpciones(){
        System.out.print("1-Iniciar Sesion \n2-Salir del Sistema");
        System.out.println();
        return Consola.leerNum();

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
