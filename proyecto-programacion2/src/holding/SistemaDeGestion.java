package holding;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Objects;

public class SistemaDeGestion implements Serializable {

    private static HashMap<Integer,Usuario> usuarios;
    private static HashMap<Integer,Empresa> empresas;
    private static HashMap<Integer,AreasMercado> areasDeMercado;
    private static HashMap<Integer,Ciudad> ciudades;
    public SistemaDeGestion(){
        BaseDeDatosSingleton bd = BaseDeDatosSingleton.getInstance();
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
