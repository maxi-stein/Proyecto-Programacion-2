package holding;

import java.util.Scanner;

public class Consola {
    private static Scanner sc = new Scanner(System.in);

    private Consola(){}

    public static String leerString(){
        return sc.nextLine();
    }

    public static int leerEntero(){
        String lectura = sc.nextLine();
        return Integer.parseInt(lectura);
    }
}
