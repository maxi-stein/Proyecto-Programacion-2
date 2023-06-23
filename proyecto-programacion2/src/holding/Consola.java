package holding;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

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
            }
        }
        return lectura;
    }

    public static LocalDate leerFecha() {
        String dateString = sc.next();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;

        while (date == null) {
            try {
                date = dateFormat.parse(dateString);
            } catch (ParseException e) {
                System.out.println("Fecha incorrecta, intente de nuevo.");
                dateString = sc.nextLine();
            }
        }
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
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
            }
        }
        return lectura;
    }
}
