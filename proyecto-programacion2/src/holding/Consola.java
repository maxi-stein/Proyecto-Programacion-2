package holding;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Esta clase permite al usuario ingresar por consola valores del tipo int, double o String
 */
public class Consola {
    private static Scanner sc = new Scanner(System.in);
    private Consola(){}
    public static String leerString(){
        return sc.nextLine();
    }
    public static int leerEntero(){
        Integer lectura = null;
        while (lectura == null){
            try{
                lectura = sc.nextInt();
                sc.nextLine();
            }
            catch (NumberFormatException  | InputMismatchException e){
                System.out.println("Ingrese un valor correcto!");
                sc.nextLine();
            }
        }
        return lectura;
    }

    public static LocalDate leerFecha() {
        LocalDate fechaActual = LocalDate.now();
        LocalDate fechaIngresada = null;

        while (fechaIngresada == null || fechaIngresada.isAfter(fechaActual)) {
            System.out.print("Ingrese una fecha (dd/MM/yyyy): ");
            String dateString = sc.nextLine();

            try {
                fechaIngresada = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            } catch (DateTimeParseException e) {
                System.out.println("Fecha incorrecta, intente de nuevo.");
            }
        }

        return fechaIngresada;
    }
    public static double leerDouble(){
        Double lectura = null;
        while (lectura == null){
            try{
                lectura = sc.nextDouble();
                sc.nextLine();
            }
            catch (NumberFormatException | InputMismatchException e){
                System.out.println("Ingrese un valor correcto!");
                sc.nextLine();
            }
        }
        return lectura;
    }
}
