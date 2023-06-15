package holding;

import java.util.HashMap;

public class MenuEliminarUsuario implements CapazDeEjecutarAccionMenu{
    private HashMap<Integer,Usuario> usuarios;
    private Admin adminTemporal;
    @Override
    public void ejecutar(BaseDatosHolding bd) {

        usuarios = new HashMap<>();
        usuarios.put(1, new Admin());
        usuarios.put(2, new Vendedor());
        usuarios.put(3, new Asesor());
        int key = eleccionTipoDeUsuario();
        eleccionDeUsuario(key, bd);

    }

    private int eleccionTipoDeUsuario(){
        System.out.println("Elija el tipo de usuario a eliminar");
        listarTiposDeUsuario();

        int key = 1;
        do{
            key = Consola.leerEntero();
        }while (key<1 || key>usuarios.size());
        return key;
    }

    private int eleccionDeUsuario(int key, BaseDatosHolding bd){
        //switch (key)
        //case 1:
        //    bd.listarUsuarios(Admin );
            return 1;
    }
    public void listarTiposDeUsuario(){
        for(int i=1;i<=usuarios.size();i++){
            if(usuarios.get(i) != null){
                System.out.println(i+" - "+ usuarios.get(i).getClass().getSimpleName());
            }
        }
    }
}
