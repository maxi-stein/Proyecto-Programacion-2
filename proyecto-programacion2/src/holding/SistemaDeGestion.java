package holding;

public class SistemaDeGestion implements CapazDeLeerEnteros,CapazDeVisualizarMenu {

    public SistemaDeGestion(){}
    public void run(){

        BaseDatosHolding bd = new BaseDatosHolding();

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
        return leerNum();

    }

    public int leerNum(){
        String numero = input.nextLine();
        return Integer.parseInt(numero);
    }

}
