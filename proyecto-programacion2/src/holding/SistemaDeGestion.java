package holding;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashMap;

public class SistemaDeGestion implements Serializable {

    private static HashMap<Integer,Usuario> usuarios;
    private static HashMap<Integer,Empresa> empresas;
    private static HashMap<Integer,AreasMercado> areasDeMercado;
    private static HashMap<Integer,Ciudad> ciudades;
    private static HashMap<Integer,Pais> paises;
    public SistemaDeGestion(){
        BaseDeDatosSingleton bd = BaseDeDatosSingleton.getInstance();
        usuarios = new HashMap<>();
        empresas = new HashMap<>();
        ciudades = new HashMap<>();
        paises = new HashMap<>();
        areasDeMercado = new HashMap<>();
        empresas.put(1,new Empresa("Tenarix",LocalDate.now(),10));
        usuarios.put(0,new Admin("admin1","Calle 123","1"));
        Pais p = new Pais("Uruguay", 1200, 1000000);
        paises.put(1, p);
        Ciudad c = new Ciudad("Monte", p);
        ciudades.put(1,c);
        AreasMercado am = new AreasMercado("Construccion", "Obras");
        areasDeMercado.put(1,am);
        
        bd.cargarDatosSerializados(usuarios,empresas,areasDeMercado,ciudades, paises);
    }
    public void run(){
        int num;

        do{
            num=mostrarMenu();
            if (num == 1) {
                BaseDeDatosSingleton bd = BaseDeDatosSingleton.getInstance();
                Usuario loggedInUsuario = bd.iniciarSesion();
                if(loggedInUsuario != null){
                    loggedInUsuario.proceder();
                }
            }
        }while(num!=2);

    }
    public int mostrarMenu(){
        System.out.print("1-Iniciar Sesion \n2-Salir del Sistema\n");
        return Consola.leerEntero();
    }
}
