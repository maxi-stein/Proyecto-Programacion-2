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

        writeObject(usuarios2,"usuarios.dat");
        writeObject(areasDeMercado2,"areasDeMercado.dat");
        writeObject(ciudades2,"ciudades.dat");
        writeObject(paises2,"paises.dat");
        writeObject(empresas2,"empresas.dat");
    }
    private static void deserializarBD() throws IOException {
        usuarios = readObject(usuarios,"usuarios.dat");
        areasDeMercado = readObject(areasDeMercado,"areasDeMercado.dat");
        ciudades = readObject(ciudades,"ciudades.dat");
        paises = readObject(paises,"paises.dat");
        empresas = readObject(empresas,"empresas.dat");

        cargarDatosDefault();
    }
    private static ObjectOutputStream crearObjectOutputStream(String nomArchivo) throws IOException {
        return new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(nomArchivo)));
    }
    private static ObjectInputStream crearObjectInputStream(String nomArchivo) throws IOException {
        return new ObjectInputStream(new BufferedInputStream(new FileInputStream(nomArchivo)));
    }
    private static <T> void writeObject(T objetoAEscribir,String nombreArchivo) throws IOException {
        try{
            ObjectOutputStream oos = crearObjectOutputStream(nombreArchivo);
            oos.writeObject(objetoAEscribir);
            oos.close();
        }catch (IOException e){
            System.out.println("Error al serializar "+nombreArchivo);
            System.out.println("Error: "+e.getMessage());
        }
    }
    private static <T> T readObject(T objetoALeer,String nombreArchivo) throws IOException {
        try{
            ObjectInputStream ois = crearObjectInputStream(nombreArchivo);
            objetoALeer =  (T) ois.readObject();
            System.out.println("Lectura de "+nombreArchivo+" exitosa");
            ois.close();
            return objetoALeer;
        }catch (IOException | ClassNotFoundException e){
            System.out.println("No se encontró "+nombreArchivo);
            return null;
        }
    }
    private static void cargarDatosDefault(){
        cargarUsuarioDefault();
        cargarAreaDeMercadoDefault();
        cargarPaisYCiudadDefault();
        cargarEmpresaDefault();
    }
    private  static void cargarUsuarioDefault(){
        if(usuarios == null){
            usuarios = new HashMap<>();
            usuarios.put(1, new Admin("adminDefault", "Direccion default ", "1"));
            BaseDeDatosSingleton.agregarUsuario(usuarios.get(1));
            System.out.println("Usuario Admin Default creado con Numero de Usuario = 1 y contraseña = 1");
        }
    }
    private  static void cargarAreaDeMercadoDefault(){
        if(areasDeMercado == null){
            areasDeMercado = new HashMap<>();
            BaseDeDatosSingleton.agregarAreaDeMercado(new AreasMercado("CONSTRUCCION", "Obras Publicas"));
            BaseDeDatosSingleton.agregarAreaDeMercado(new AreasMercado("METALURGICA", "Acero Industrial"));
            BaseDeDatosSingleton.agregarAreaDeMercado(new AreasMercado("ASESORAMIENTO", "Servicio de Consultoria"));
            areasDeMercado = BaseDeDatosSingleton.obtenerAreasDeMercado();
            System.out.println("Areas de Mercado default creadas");
        }
    }
    private static void cargarPaisYCiudadDefault(){
        if(paises == null || ciudades == null){
            ciudades = new HashMap<>();
            paises = new HashMap<>();
            Pais paisDefault = new Pais("Argentina",487.2,50000000);
            BaseDeDatosSingleton.agregarPais(paisDefault);
            BaseDeDatosSingleton.agregarCiudad(new Ciudad("Buenos Aires",paisDefault));
            paises = BaseDeDatosSingleton.obtenerPaises();
            ciudades = BaseDeDatosSingleton.obtenerCiudades();
            System.out.println("Paises y Ciudades default creadas");
        }
    }
    private static void cargarEmpresaDefault(){
        if(empresas == null) {
            empresas = new HashMap<>();
            System.out.println("Se procede a crear una primer empresa:");
            MenuCrearEmpresa m = new MenuCrearEmpresa();
            m.ejecutar();
            empresas = BaseDeDatosSingleton.obtenerEmpresas();
        }
    }
}
