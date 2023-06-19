package holding;

import java.io.Serializable;

public class Pais implements CapazDeSerBloqueado, Serializable {
    private String nombre;
    private double pbi;
    private double numHabitantes;
    private boolean bloqueado;
    public Pais(String nombre, double pbi, double numHabitantes){
        this.nombre = nombre;
        this.pbi = pbi;
        this.numHabitantes = numHabitantes;
    }
    @Override
    public void setBloqueo(boolean valor) {
        bloqueado = valor;
    }
    @Override
    public boolean estaBloqueado() {
        return bloqueado;
    }
    public boolean esIgual(Pais p){
        return nombre.equals(p.nombre);
    }
    @Override
    public String toString() {
        return nombre +
                "(PBI:" + pbi +
                "- Habitantes=" + numHabitantes +")";
    }
    public String getNombre(){
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setPbi(double pbi) {
        this.pbi = pbi;
    }
    public void setNumHabitantes(double numHabitantes) {
        this.numHabitantes = numHabitantes;
    }
}
