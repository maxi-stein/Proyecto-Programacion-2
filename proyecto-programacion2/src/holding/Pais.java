package holding;

public class Pais {
    private static int CONTADOR = 0;
    private int codigoPais;
    private String nombre;
    private double pbi;
    private double numHabitantes;
    public Pais(String nombre, double pbi, double numHabitantes){
        CONTADOR++;
        codigoPais=CONTADOR;
        this.nombre = nombre;
        this.pbi = pbi;
        this.numHabitantes = numHabitantes;
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
}
