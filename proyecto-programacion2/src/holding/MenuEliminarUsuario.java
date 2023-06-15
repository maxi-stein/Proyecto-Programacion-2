package holding;

import java.util.HashMap;

public class MenuEliminarUsuario implements CapazDeEjecutarAccionMenu{
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
        Integer key = 1;
        do{
            key = Consola.leerEntero();
        }while (key<1 || key>usuarios.size());
        eliminarUsuario(bd,usuarios.get(key));

    }
    public void listarTiposDeUsuario(){
        for(int i=1;i<=usuarios.size();i++){
            if(usuarios.get(i) != null){
                System.out.println(i+" - "+ usuarios.get(i).getClass().getSimpleName());
            }
        }
    }
}
