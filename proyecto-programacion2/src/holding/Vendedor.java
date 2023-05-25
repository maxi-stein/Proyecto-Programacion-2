package holding;

import java.time.LocalDate;
import java.util.ArrayList;

public class Vendedor extends Usuario {

    private LocalDate fechaCaptado;
    private ArrayList<Vendedor> vendedoresCaptados;

    public Vendedor(String nombre, String direccion, String pass, LocalDate fechaCaptado) {
        super(nombre, direccion, pass);
        this.fechaCaptado = fechaCaptado;
        vendedoresCaptados = new ArrayList<>();
    }

    public void captarVendedor(){

    }

    public int contadorVendedoresCapt(){
        return 0;
    }
}
