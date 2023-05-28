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
        while(opcion != 4){
            switch (opcion){
                case 1:
                    mostrarEmpresaActual();
                    break;
                case 2:
                    mostrarCantVendCaptados();
                    break;
                case 3:
                    listarVendedoresCaptados();
                    break;
            }
            opcion = mostrarMenu();
        }
    }

    @Override
    public void mostrarInformacionUsuario() {

    }

    @Override
    public int mostrarMenu() {
        int opcion = 5;
        while(opcion>4 || opcion<1){
            System.out.print("1 - Mostrar empresa actual \n" +
                    "2 - Mostrar cantidad vendedores captados \n" +
                    "3 - Listar vendedores captados \n" +
                    "4 - Salir \n-");
            opcion = leerNum();
            if(opcion>4 || opcion<1) System.out.println("Opcion incorrecta, intente de nuevo.");
        }
        return opcion;
    }

    public int leerNum(){
        String numero = input.nextLine();
        return Integer.parseInt(numero);
    }

    public void mostrarEmpresaActual(){
        System.out.println(empresaTrabajo.displayInfo());
    }

    public void mostrarCantVendCaptados(){
        System.out.println(contadorVendedoresCapt());
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
