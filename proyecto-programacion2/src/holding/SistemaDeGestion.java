package holding;

public class SistemaDeGestion implements CapazDeVisualizarMenu {

    private BaseDatosHolding bd;
    public SistemaDeGestion(){
        bd = new BaseDatosHolding();
    }
    public void run(){

        int num;
        do{
            num = mostrarMenu();
        }while(num != 1 && num!=2);

        if(num==1){
            Usuario loggedInUsuario = bd.iniciarSesion();
            loggedInUsuario.proceder();
        }

    }

    public int mostrarMenu(){
        System.out.print("1-Iniciar Sesion \n2-Salir del Sistema \n-");
        System.out.println();
        return Consola.leerEntero();

    }

}
