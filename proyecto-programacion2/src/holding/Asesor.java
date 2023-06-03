package holding;

import java.time.LocalDate;
import java.util.ArrayList;

public class Asesor extends Usuario{
    private String titulacion;
    private ArrayList<AreasMercado> mercadosCubiertos;
    private ArrayList<Empresa> empresasAsesoradas;
    private ArrayList<LocalDate> fechasInicio;

    public Asesor(String nombre, String direccion, String pass, String titulacion) {
        super(nombre, direccion, pass);
        this.titulacion = titulacion;
        mercadosCubiertos = new ArrayList<>();
        empresasAsesoradas = new ArrayList<>();
        fechasInicio = new ArrayList<>();
    }
    @Override
    public void proceder() {
        int opcion = mostrarMenu();
        while(opcion != 4){
            switch (opcion){
                case 1:
                    mostrarInformacion();
                    break;
                case 2:
                    listarEmpresas();
                    break;
                case 3:
                    listarAreasMercado();
                    break;
            }
            opcion = mostrarMenu();
            if(opcion>4 || opcion<1) System.out.println("Opcion incorrecta, intente de nuevo.");
        }

    }

    @Override
    public void mostrarInformacion() {
        System.out.println("Datos del Usuario: "+this.toString()+" || Titulacion: "+ titulacion);
    }


    @Override
    public int mostrarMenu() {
        int opcion=0;
        while (opcion < 1 || opcion > 4) {
            System.out.print("1-Mostrar Datos de Usuario. \n" +
                    "2-Listar Empresas Asesoradas. \n" +
                    "3-Listar Areas de Mercado asesoradas. \n" +
                    "4-Salir.-");
            opcion = Consola.leerEntero();
        }
        return opcion;
    }

    public void listarEmpresas(){
        if(empresasAsesoradas.isEmpty()){
            System.out.println("No hay empresas que asesore el usuario.");
        }
        else {
            for (int i = 0; i < empresasAsesoradas.size(); i++) {
                System.out.println(empresasAsesoradas.get(i).getNombre() + "(desde " + fechasInicio.get(i) + ")");
            }
            System.out.println();
        }
    }

    public void listarAreasMercado(){
        if(mercadosCubiertos.isEmpty()){
            System.out.println("El usuario no asesora ningun area de mercado aún.");
        }
        else {
            System.out.println("AREAS:");
            for (AreasMercado area : mercadosCubiertos) {
                //System.out.println(area.name() + " - " + area.getDescripcion());
            }
            System.out.println();
        }
    }

    public void agregarEmpresaAsesorada(Empresa empresa,LocalDate fechaInicio){
        empresasAsesoradas.add((empresa));
        fechasInicio.add((fechaInicio));
    }

    public void eliminarEmpresaAsesorada(Empresa empresa,LocalDate fechaInicio){
        empresasAsesoradas.remove((empresa));
        fechasInicio.remove((fechaInicio));
    }

    public void agregarAreaMercadoCubierto(AreasMercado area){
        mercadosCubiertos.add((area));
    }

    public void eliminarAreaMercadoCubierto(AreasMercado area){
        mercadosCubiertos.remove((area));
    }
}
