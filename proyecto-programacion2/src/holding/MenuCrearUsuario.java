package holding;

import java.time.LocalDate;
import java.util.HashMap;

public class MenuCrearUsuario implements CapazDeEjecutarAccionMenu{
    private HashMap<Integer,Usuario> usuarios;
    private Admin adminTemporal;
    @Override
    public void ejecutar(BaseDatosHolding bd) {
        adminTemporal = new Admin();
        usuarios = new HashMap<>();
        usuarios.put(1, new Admin());
        usuarios.put(2, new Vendedor());
        usuarios.put(3, new Asesor());
        eleccionTipoDeUsuario(bd);
    }
    private void eleccionTipoDeUsuario(BaseDatosHolding bd){
        System.out.println("Elija el tipo de usuario a crear");
        listarTiposDeUsuario();
        int key = 1;
        do{
            key = Consola.leerEntero();
        }while (key<1 || key>usuarios.size());
        if(key == 1){
            crearAdmin(bd);
        }
        else if(key == 2){
            crearVendedor(bd);
        }
        else{
            crearAsesor(bd);
        }
    }
    public void crearAdmin(BaseDatosHolding bd){
        System.out.println("Ingrese el nombre: ");
        String nombre = Consola.leerString();
        System.out.println("Ingrese la direccion: ");
        String direccion = Consola.leerString();
        System.out.println("Ingrese la contrasenia: ");
        String contrasena = Consola.leerString();
        Admin a = new Admin(nombre,direccion,contrasena);
        a.mostrarCredenciales();
        adminTemporal.agregarUsuario(a,bd);

    }
    public void crearVendedor(BaseDatosHolding bd){
        System.out.println("Ingrese el nombre: ");
        String nombre = Consola.leerString();
        System.out.println("Ingrese la direccion: ");
        String direccion = Consola.leerString();
        System.out.println("Ingrese la contrasenia: ");
        String contrasena = Consola.leerString();
        System.out.println("Elija la empresa en la que trabaja: ");
        HashMap<Integer, Empresa> empresas = bd.listarEmpresas();
        Integer key = 0;
        do {
            key = Consola.leerEntero();
        } while (key < 0 || key > empresas.size());

        Vendedor v = new Vendedor(nombre, direccion, contrasena, LocalDate.now(), empresas.get(key));
        v.mostrarCredenciales();
        adminTemporal.agregarUsuario(v,bd);
    }

    public void crearAsesor(BaseDatosHolding bd){
        //creamos el objeto Asesor
        System.out.println("Ingrese el nombre: ");
        String nombre = Consola.leerString();
        System.out.println("Ingrese la direccion: ");
        String direccion = Consola.leerString();
        System.out.println("Ingrese la contrasenia: ");
        String contrasena = Consola.leerString();
        System.out.println("Ingrese la titulacion");
        String titulacion = Consola.leerString();
        System.out.println("Elija el area de mercado que asesora");
        HashMap <Integer,AreasMercado> areas = bd.listarAreasDeMercado();
        Integer keyArea = 0;
        do {
            keyArea = Consola.leerEntero();
        } while (keyArea < 0 || keyArea > areas.size());
        Asesor asesor = new Asesor(nombre,direccion,contrasena,titulacion);

        //se agrega el area de mercado cubierta
        asesor.agregarAreaMercadoCubierto(areas.get(keyArea));

        //se agrega la empresa para la que trabaja y se establece VinculacionAsesorEmpresa
        System.out.println("Elija la empresa que asesora: ");
        HashMap<Integer, Empresa> empresas = bd.listarEmpresas();
        Integer keyEmpresa = 0;
        Boolean compatible;
        do {
            keyEmpresa = Consola.leerEntero();
            compatible = empresas.get(keyEmpresa).areaCompatible(areas.get(keyArea));
            if(!compatible) {
                System.out.println("El area de mercado no es compatible con la Empresa");
            }
        } while (keyEmpresa < 0 || keyEmpresa > empresas.size() || !compatible);
        System.out.println("Ingresa la fecha de inicio (dd/mm/aaaa): ");
        LocalDate fechaInicio = Consola.leerFecha();

        bd.agregarAsesorAEmpresa(keyEmpresa,asesor,fechaInicio);//se agrega el asesor a la empresa
        adminTemporal.agregarUsuario(asesor,bd);//se agrega el asesor a la base de datos de usuarios
    }

    public void listarTiposDeUsuario(){
        for(int i=1;i<=usuarios.size();i++){
            if(usuarios.get(i) != null){
                System.out.println(i+" - "+ usuarios.get(i).getClass().getSimpleName());
            }

        }
    }



}
