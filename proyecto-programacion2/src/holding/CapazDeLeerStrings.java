package holding;

import java.util.Scanner;

public interface CapazDeLeerStrings {
    Scanner input = new Scanner(System.in);
    static String leerString(){
        return input.nextLine();
    }
}
