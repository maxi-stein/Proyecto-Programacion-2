package menues;

import holding.BaseDeDatosSingleton;
import holding.Ciudad;
import holding.Consola;
import holding.Pais;
import java.util.HashMap;

public class MenuCrearCiudad implements CapazDeEjecutarAccionMenu{
    private static BaseDeDatosSingleton bd;
    public MenuCrearCiudad(){
        bd = BaseDeDatosSingleton.getInstance();
    }
    @Override
    public void ejecutar() {
        System.out.println("Seleccione el pais origen de la ciudad a crear:");
        bd.listarPaises();
        HashMap<Integer, Pais> paises = bd.obtenerPaises();
        Pais p = seleccionarPais(paises);
        System.out.println("Determine el nombre de la ciudad: ");
        String nombre = Consola.leerString().toUpperCase();
        Ciudad ciudad = new Ciudad(nombre,p);
        if(bd.ciudadYaExiste(ciudad)){
            System.out.println("La ciudad "+ciudad.getNombre()+" asociada al pais "+ciudad.getPaisOrigen()+ " ya existe!");
        }
        else{
            bd.agregarCiudad(ciudad);
        }
    }
    private Pais seleccionarPais(HashMap<Integer, Pais> paises){
        int key = 0;
        do {
            key = Consola.leerEntero();
        } while (key < 0 || key > paises.size());
        return paises.get(key);
    }
}
