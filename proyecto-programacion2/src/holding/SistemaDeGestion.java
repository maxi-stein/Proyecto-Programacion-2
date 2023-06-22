package holding;

import menues.MenuCrearEmpresa;

import java.io.*;
import java.util.HashMap;

public class SistemaDeGestion implements Serializable {

    private static HashMap<Integer,Usuario> usuarios;
    private static HashMap<Integer,Empresa> empresas;
    private static HashMap<Integer,AreasMercado> areasDeMercado;
    private static HashMap<Integer,Ciudad> ciudades;
    private static HashMap<Integer,Pais> paises;
    public SistemaDeGestion() throws IOException {
        usuarios = new HashMap<>();
        empresas = new HashMap<>();
        ciudades = new HashMap<>();
        paises = new HashMap<>();
        areasDeMercado = new HashMap<>();
        BaseDeDatosSingleton.getInstance();
        deserializarBD();
        BaseDeDatosSingleton.cargarDatosSerializados(usuarios,empresas,areasDeMercado,ciudades, paises);
    }
    public void run() throws IOException {
        int num;
        do{
            num=mostrarMenu();
            switch (num) {
                case 1:
                    Usuario loggedInUsuario = BaseDeDatosSingleton.iniciarSesion();
                    if(loggedInUsuario != null){
                        loggedInUsuario.proceder();
                    }
                    break;
                case 2:
                    BaseDeDatosSingleton.desloguearUsuario();
                    break;
            }
            serializarBD(BaseDeDatosSingleton.obtenerUsuarios(), BaseDeDatosSingleton.obtenerEmpresas(),
                        BaseDeDatosSingleton.obtenerAreasDeMercado(), BaseDeDatosSingleton.obtenerCiudades(),
                        BaseDeDatosSingleton.obtenerPaises());
        }while(num!=2);
    }
    public int mostrarMenu(){
        int opcion=0;
        System.out.print("1-Iniciar Sesion \n2-Salir del Sistema\n");
        while (opcion<1 || opcion>2){
            opcion = Consola.leerEntero();
        }
        return opcion;
    }
    private static void serializarBD(HashMap<Integer,Usuario> usuarios2, HashMap<Integer,Empresa> empresas2, HashMap<Integer,AreasMercado> areasDeMercado2,
                             HashMap<Integer,Ciudad> ciudades2, HashMap<Integer,Pais> paises2) throws IOException {

        try {
            ObjectOutputStream objUser = crearObjectOutputStream("Usuarios.bin");
            objUser.writeObject(usuarios2);
            objUser.close();
        }
        catch(IOException e){
                System.out.println("Error al serializar Usuarios");
        }
        try {
            ObjectOutputStream objArea = crearObjectOutputStream("AreasDeMercado.bin");
            objArea.writeObject(areasDeMercado2);
            objArea.close();
        }
        catch (IOException e) {
            System.out.println("Error al serializar areas de mercado");
        }
        try {
            ObjectOutputStream objCiudad = crearObjectOutputStream("Ciudades.bin");
            objCiudad.writeObject(ciudades2);
            objCiudad.close();
        }
        catch (IOException e) {
            System.out.println("Error al serializar ciudades");
        }
        try {
            ObjectOutputStream objPais = crearObjectOutputStream("Paises.bin");
            objPais.writeObject(paises2);
            objPais.close();
        }
        catch (IOException e) {
            System.out.println("Error al serializar pasies");
        }
        try {
            ObjectOutputStream objEmp = crearObjectOutputStream("Empresas.bin");
            objEmp.writeObject(empresas2);
            objEmp.close();
        }
        catch (IOException e) {
            System.out.println("Error al serializar Empresas");
        }
    }
    private static void deserializarBD() throws IOException {
            try{
                ObjectInputStream objUser = crearObjectInputStream("Usuarios.bin");
                usuarios = (HashMap<Integer, Usuario>) objUser.readObject();
                System.out.println("Lectura de usuarios exitosa");
                objUser.close();
            }catch (FileNotFoundException | ClassNotFoundException e) {
                System.out.println("Primer inicio de sesion. Se creará el usuario Admin con credenciales de acceso:" +
                        "Num de Usuario=1 - Contraseña=1");
            } 
            try {
                ObjectInputStream objArea = crearObjectInputStream("AreasDeMercado.bin");
                areasDeMercado = (HashMap<Integer, AreasMercado>) objArea.readObject();
                System.out.println("Cantidad de areas de mercado leidas en la deserializacion: "+areasDeMercado.size());
                System.out.println("Lectura de area de mercado exitosa");
                objArea.close();
            }catch (FileNotFoundException | ClassNotFoundException e){
                System.out.println("No hay areas de mercado registradas");
            }
            try {
                ObjectInputStream objCiudad = crearObjectInputStream("Ciudades.bin");
                ciudades = (HashMap<Integer, Ciudad>)  objCiudad.readObject();
                System.out.println("Lectura de Ciudades exitosa");
                objCiudad.close();
            }catch (FileNotFoundException | ClassNotFoundException e){
                System.out.println("No hay Ciudades registradas");
            }
            try{
                ObjectInputStream objPais = crearObjectInputStream("Paises.bin");
                paises = (HashMap<Integer, Pais>)  objPais.readObject();
                System.out.println("Lectura de Paises exitosa");
                objPais.close();
            }catch (FileNotFoundException | ClassNotFoundException e){
                System.out.println("No hay Paises registrados");
            }
            try{
                ObjectInputStream objEmp = crearObjectInputStream("Empresas.bin");
                empresas = (HashMap<Integer, Empresa>) objEmp.readObject();
                System.out.println("Lectura de Empresas exitosa");
                objEmp.close();
            }catch (FileNotFoundException | ClassNotFoundException e){
                System.out.println("No hay empresas registradas");
            }
        cargarDatosDefault();
    }
    private static ObjectOutputStream crearObjectOutputStream(String nomArchivo) throws IOException {
        return new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(nomArchivo)));
    }
    private static ObjectInputStream crearObjectInputStream(String nomArchivo) throws IOException {
        return new ObjectInputStream(new BufferedInputStream(new FileInputStream(nomArchivo)));
    }
    private static void cargarDatosDefault(){
        cargarUsuarioDefault();
        cargarAreaDeMercadoDefault();
        cargarPaisYCiudadDefault();
        cargarEmpresaDefault();
    }
    private  static void cargarUsuarioDefault(){
        if(usuarios.size() == 0){
            usuarios.put(1, new Admin("adminDefault", "Direccion default ", "1"));
            BaseDeDatosSingleton.agregarUsuario(usuarios.get(1));
            System.out.println("Usuario Admin Default creado");
        }
    }
    private  static void cargarAreaDeMercadoDefault(){
        if(areasDeMercado.size() == 0){
            BaseDeDatosSingleton.agregarAreaDeMercado(new AreasMercado("CONSTRUCCION", "Obras Publicas"));
            BaseDeDatosSingleton.agregarAreaDeMercado(new AreasMercado("METALURGICA", "Acero Industrial"));
            BaseDeDatosSingleton.agregarAreaDeMercado(new AreasMercado("ASESORAMIENTO", "Servicio de Consultoria"));
            areasDeMercado = BaseDeDatosSingleton.obtenerAreasDeMercado();
            System.out.println("Areas de Mercado default creadas");
        }
    }
    private static void cargarPaisYCiudadDefault(){
        if(paises.size() == 0){
            Pais paisDefault = new Pais("Argentina",487.2,50000000);
            BaseDeDatosSingleton.agregarPais(paisDefault);
            BaseDeDatosSingleton.agregarCiudad(new Ciudad("Buenos Aires",paisDefault));
            paises = BaseDeDatosSingleton.obtenerPaises();
            ciudades = BaseDeDatosSingleton.obtenerCiudades();
            System.out.println("Paises y Ciudades default creadas");
        }
    }
    private static void cargarEmpresaDefault(){
        if(empresas.size()==0) {
            System.out.println("Se procede a crear una primer empresa:");
            MenuCrearEmpresa m = new MenuCrearEmpresa();
            m.ejecutar();
            empresas = BaseDeDatosSingleton.obtenerEmpresas();
        }

    }
}
