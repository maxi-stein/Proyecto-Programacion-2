package holding;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Objects;

public class SistemaDeGestion implements Serializable {

    private static HashMap<Integer,Usuario> usuarios;
    private static HashMap<Integer,Empresa> empresas;
    private static HashMap<Integer,AreasMercado> areasDeMercado;
    private static HashMap<Integer,Ciudad> ciudades;
    public SistemaDeGestion(){
        BaseDeDatosSingleton bd = BaseDeDatosSingleton.getInstance();
        usuarios = new HashMap<>();
        empresas = new HashMap<>();
        empresas.put(1,new Empresa("Tenarix",LocalDate.now(),10));
        usuarios.put(0,new Admin("admin1","Calle 123","1"));
        bd.cargarDatosSerializados(usuarios,empresas,areasDeMercado,ciudades);
    }
    public void run(){
        int num;
        do{
            num=mostrarMenu();
            if (num == 1) {
                BaseDeDatosSingleton bd = BaseDeDatosSingleton.getInstance();
                Usuario loggedInUsuario = bd.iniciarSesion();
                if(Objects.nonNull(loggedInUsuario)){
                    loggedInUsuario.proceder();
                }
                num = mostrarMenu();
            }
        }while(num!=2);

    }
    public int mostrarMenu(){
        System.out.print("1-Iniciar Sesion \n2-Salir del Sistema \n-");
        System.out.println();
        return Consola.leerEntero();

    }
}
