package holding;

import java.time.LocalDate;
import java.util.ArrayList;

public class Vendedor extends Usuario {

    private LocalDate fechaCaptado;
    private ArrayList<Vendedor> vendedoresCaptados;
    private Empresa empresaTrabajo;


    public Vendedor(String nombre, String direccion, String pass, LocalDate fechaCaptado,Empresa empresaTrabajo) {
        super(nombre, direccion, pass);
        this.fechaCaptado = fechaCaptado;
        this.empresaTrabajo = empresaTrabajo;
        vendedoresCaptados = new ArrayList<>();
    }

    public void captarVendedor(Vendedor vendedor){
        vendedoresCaptados.add(vendedor);
    }
    public int contadorVendedoresCapt(){
        return vendedoresCaptados.size();
    }
    @Override
    public void proceder() {
        int opcion = mostrarMenu();
        while(opcion != 5){
            switch (opcion){
                case 1:
                    mostrarInformacion();
                    break;
                case 2:
                    mostrarEmpresaActual();
                    break;
                case 3:
                    mostrarCantVendCaptados();
                    break;
                case 4:
                    listarVendedoresCaptados();
                    break;
            }
            opcion = mostrarMenu();
        }
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("Datos del Usuario: "+toString()+" || Fecha de Ingreso: "+ fechaCaptado);
        System.out.println("Empresa: "+ empresaTrabajo);;
    }

    @Override
    public int mostrarMenu() {
        int opcion = 0;
        while(opcion>5 || opcion<1){
            System.out.print("1 - Mostrar Datos de Usuario. \n" +
                    "2 - Mostrar empresa actual. \n" +
                    "3 - Mostrar cantidad vendedores captados. \n" +
                    "4 - Listar vendedores captados. \n" +
                    "5 - Salir.-  \n-");
            opcion = Consola.leerEntero();
            if(opcion>5 || opcion<1) System.out.println("Opcion incorrecta, intente de nuevo.");
        }
        return opcion;
    }
    public void mostrarEmpresaActual(){
        System.out.println(empresaTrabajo.displayInfo());
    }

    public void mostrarCantVendCaptados(){
        System.out.println("La cantidad de vendedores captados es: "+contadorVendedoresCapt());
    }

    private void listarVendedoresCaptados(){
        if(vendedoresCaptados.size()==0){
            System.out.println("Aun no posee vendedores captados");
        }
        else {
            for (Vendedor v : vendedoresCaptados) {
                System.out.println(v.toString());
            }
        }
    }
}
