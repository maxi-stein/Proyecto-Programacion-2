package holding;

import java.util.ArrayList;

public class Pais implements ISede {
    private boolean esSede;
    private String nombre;
    private double pbi;
    private double numHabitantes;
    private ArrayList<Ciudad> ciudades;


    public Pais(String nombre, double pbi, double numHabitantes){
        this.nombre = nombre;
        this.pbi = pbi;
        this.numHabitantes = numHabitantes;
        esSede = false;
    }

    @Override
    public void hacerSede() {
        esSede = true;
    }

    @Override
    public void eliminarSede() {
        esSede = false;
    }
}
