package holding;

import java.util.Scanner;

public class SistemaDeGestion {

    private SistemaDeGestion(){};

    public static void run(){
        
    }

    private static int mostrarOpciones(){

        return 0;
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
