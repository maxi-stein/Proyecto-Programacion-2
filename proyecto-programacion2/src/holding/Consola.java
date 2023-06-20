package holding;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Scanner;

public class Consola {
    private static Scanner sc = new Scanner(System.in);

    private Consola(){}

    public static String leerString(){
        return sc.nextLine();
    }

    public static int leerEntero(){
        String lectura = null;
        int entero = 0;
        while (lectura == null){
            try{
                lectura = sc.nextLine();
                entero = Integer.parseInt(lectura);
            }
            catch (NumberFormatException nfe){
                System.out.println("Ingrese un valor correcto!");
            }
        }
        return entero;
    }

    public static LocalDate leerFecha() {
        String dateString = sc.nextLine();
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
        String lectura = null;
        double entero = 0;
        while (lectura == null){
            try{
                lectura = sc.nextLine();
                entero = Double.parseDouble(lectura);
            }
            catch (NumberFormatException nfe){
                System.out.println("Ingrese un valor correcto!");
            }
        }
        return entero;
    }
}
