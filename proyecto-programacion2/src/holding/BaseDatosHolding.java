package holding;

import java.io.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;


public class BaseDatosHolding{

    private HashMap<Integer,Usuario> users; //CAMBIAR NOMBRE A USUARIOS
    private HashMap<Integer,Empresa> empresas;
    private HashMap<Integer,AreasMercado> areasDeMercado;
    private HashMap<Integer,Ciudad> ciudades;


    public BaseDatosHolding(){
        //users = new ArrayList<>();
        users = new HashMap<>();
        empresas = new HashMap<>();

        //usuarios.add(new Admin("abc","Calle Falsa","123"));
        users.put(123,new Admin("abc","Calle Falsa","123"));
        //empresas.add(new Empresa("Terrabusi", LocalDate.now(),100));
        //usuarios.add(new Vendedor("Maxi","Calle Verdadera","321",LocalDate.now(),empresas.get(0)));
        areasDeMercado = new HashMap<>();
        ciudades = new HashMap<>();

    }
    public Usuario iniciarSesion(){
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
            //out.writeObject(MyEnumSingleton.INSTANCE);
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

    public HashMap<Integer,Empresa> listarEmpresas(){
        for (Map.Entry<Integer, Empresa> empresa : empresas.entrySet()) {
            Integer key = empresa.getKey();
            Empresa value = empresa.getValue();
            System.out.println(key + " " + value + '\n');
        }
        return empresas;
    }

    public HashMap<Integer,AreasMercado> listarAreasDeMercado(){
        for (Map.Entry<Integer, AreasMercado> area : areasDeMercado.entrySet()) {
            Integer key = area.getKey();
            AreasMercado value = area.getValue();
            System.out.println(key + " " + value + '\n');
        }
        return areasDeMercado;
    }
    public void agregarAsesorAEmpresa(Integer keyEmpresa, Asesor a, LocalDate fechaInicio){
        empresas.get(keyEmpresa).agregarAsesor(a,fechaInicio);
    }
    public HashMap<Integer, Usuario> listarUsuarios(Usuario usuarioBuscado) {
        HashMap<Integer, Usuario> usuariosAListar = new HashMap<>();
        for (int i = 0; i < users.size(); i++) {
            Usuario usuario = users.get(i);
            if (usuario.getClass().equals(usuarioBuscado.getClass())) {
                usuariosAListar.put(i, usuario);
                System.out.println(i + " - " + usuario.toString());
            }
        }
        return usuariosAListar;
    }

    public Empresa getEmpresa(int keyEmpresa){
        return empresas.get(keyEmpresa);
    }
    public boolean usuarioAsesoraAEmpresa(Asesor a,Empresa e){
        return e.esAsesoradoPor(a);
    }
}
