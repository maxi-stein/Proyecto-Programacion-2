package holding;

import java.util.Objects;

public class SistemaDeGestion {

    private BaseDatosHolding bd;
    public SistemaDeGestion(){
        bd = new BaseDatosHolding();

    }
    public void run(){
        int num;
        
        do{
            num=mostrarMenu();
            if (num == 1) {
                Usuario loggedInUsuario = bd.iniciarSesion();
                if(Objects.nonNull(loggedInUsuario)){loggedInUsuario.proceder();}
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
