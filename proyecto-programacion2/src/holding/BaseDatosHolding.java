package holding;

import java.io.*;
import java.util.HashMap;


public class BaseDatosHolding{

    private HashMap<Integer,Usuario> users; //CAMBIAR NOMBRE A USUARIOS
    private HashMap<Integer,Empresa> empresas;


    public BaseDatosHolding(){
        //users = new ArrayList<>();
        users = new HashMap<>();
        empresas = new HashMap<>();

        //usuarios.add(new Admin("abc","Calle Falsa","123"));
        users.put(123,new Admin("abc","Calle Falsa","123"));
        //empresas.add(new Empresa("Terrabusi", LocalDate.now(),100));
        //usuarios.add(new Vendedor("Maxi","Calle Verdadera","321",LocalDate.now(),empresas.get(0)));

    }
    public  Usuario iniciarSesion(){
        Usuario usuarioIN = null;
        int intentos = 0;
        while(usuarioIN == null && intentos < 3 ) {
            intentos ++;

            System.out.println("Numero de Usuario:");
            int user = Consola.leerEntero();
            //String user = Consola.leerString();
            System.out.println("Contraseña:");
            String pass = Consola.leerString();

            try{
                usuarioIN =  autenticarUsuario(user,pass);
            }catch (NullPointerException e) {
                System.out.println("Usuario o contraseña incorrecta");
            }
        }
        return usuarioIN;
    }

    private Usuario autenticarUsuario(int userCod, String pass){
        Usuario usuarioEncontrado = null;
        if(users.get(userCod).validarPass(pass)){
                usuarioEncontrado = users.get(userCod);
            }
        return usuarioEncontrado;
    }

    public void agregarUsuario(Usuario u){
        users.put(u.getCodigoUsuario(),u);
    }

    private void serializarBD(){
        try {
            FileOutputStream fileOut = new FileOutputStream("User.txt");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(users);
            out.close();
            fileOut.close();
            System.out.println("HashMap serializado se guardó en User.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deserizalizarBD(){

        try {
            FileInputStream fileIn = new FileInputStream("User.txt");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            users = (HashMap<Integer, Usuario>) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
