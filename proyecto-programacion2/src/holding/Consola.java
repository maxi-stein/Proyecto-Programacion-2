package holding;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Date;
import java.util.Scanner;
import java.time.LocalDate;

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
}
