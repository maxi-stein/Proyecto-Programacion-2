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
        deserializarBD();
        BaseDeDatosSingleton.cargarDatosSerializados(usuarios,empresas,areasDeMercado,ciudades, paises);

    }
    public void run() throws IOException {
        int num;
        do{
            num=mostrarMenu();
            if (num == 1) {

                Usuario loggedInUsuario = BaseDeDatosSingleton.iniciarSesion();
                if(loggedInUsuario != null){
                    loggedInUsuario.proceder();
                }
                BaseDeDatosSingleton.desloguearUsuario();
                serializarBD(BaseDeDatosSingleton.obtenerUsuarios(), BaseDeDatosSingleton.obtenerEmpresas(),
                        BaseDeDatosSingleton.obtenerAreasDeMercado(), BaseDeDatosSingleton.obtenerCiudades(),
                        BaseDeDatosSingleton.obtenerPaises());
            }
        }while(num!=2);
    }
    public int mostrarMenu(){
        System.out.print("1-Iniciar Sesion \n2-Salir del Sistema\n");
        return Consola.leerEntero();
    }
    public static void serializarBD(HashMap<Integer,Usuario> usuarios2,
                             HashMap<Integer,Empresa> empresas2,
                             HashMap<Integer,AreasMercado> areasDeMercado2,
                             HashMap<Integer,Ciudad> ciudades2, HashMap<Integer,Pais> paises2) throws IOException {

        try{
            ObjectOutputStream objUser = crearObjectOutputStream("Usuarios.bin");
            objUser.writeObject(usuarios2);

            ObjectOutputStream objEmp = crearObjectOutputStream("Empresas.bin");
            objEmp.writeObject(empresas2);

            ObjectOutputStream objArea = crearObjectOutputStream("AreasDeMercado.bin");
            objArea.writeObject(areasDeMercado2);

            ObjectOutputStream objCiudad = crearObjectOutputStream("Ciudades.bin");
            objCiudad.writeObject(ciudades2);

            ObjectOutputStream objPais = crearObjectOutputStream("Paises.bin");
            objPais.writeObject(paises2);

            objUser.close();
            objEmp.close();
            objArea.close();
            objCiudad.close();
            objPais.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void deserializarBD() throws IOException, FileNotFoundException {
            try{
                ObjectInputStream objUser = new ObjectInputStream((new BufferedInputStream(new FileInputStream("Usuarios.bin"))));
                usuarios = (HashMap<Integer, Usuario>) objUser.readObject();
                objUser.close();
            }catch (FileNotFoundException e) {
                System.out.println("Primer inicio de sesion. Se creará el usuario Admin con credenciales de acceso:" +
                        "Num de Usuario=1 - Contraseña=1");
            }
            try {
                ObjectInputStream objArea = new ObjectInputStream((new BufferedInputStream(new FileInputStream("AreasDeMercado.bin"))));
                areasDeMercado = (HashMap<Integer, AreasMercado>) objArea.readObject();
                objArea.close();
            }catch (FileNotFoundException | ClassNotFoundException e){
                System.out.println("No hay areas de mercado registradas. Se cargaran las areas default.");
            }
            try {
                ObjectInputStream objCiudad = new ObjectInputStream((new BufferedInputStream(new FileInputStream("Ciudades.bin"))));
                ciudades = (HashMap<Integer, Ciudad>)  objCiudad.readObject();
                objCiudad.close();
            }catch (FileNotFoundException | ClassNotFoundException e){
                System.out.println("No hay Ciudades registradas. Se cargaran valores default.");
            }
            try{
                ObjectInputStream objPais = new ObjectInputStream((new BufferedInputStream(new FileInputStream("Paises.bin"))));
                paises = (HashMap<Integer, Pais>)  objPais.readObject();
                objPais.close();
            }catch (FileNotFoundException | ClassNotFoundException e){
                System.out.println("No hay Paises registrados. Se cargaran valores default.");
            }
            try{
                ObjectInputStream objEmp = new ObjectInputStream((new BufferedInputStream(new FileInputStream("Empresas.bin"))));
                empresas = (HashMap<Integer, Empresa>) objEmp.readObject();
                objEmp.close();
            }catch (FileNotFoundException | ClassNotFoundException e){
                System.out.println("No hay empresas registradas. Se procede a crear una primera empresa");
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
        usuarios.put(usuarios.size()+1, new Admin("adminDefault", " ", "1"));
    }
    private  static void cargarAreaDeMercadoDefault(){
        if(areasDeMercado.size() == 0){
            BaseDeDatosSingleton.agregarAreaDeMercado(new AreasMercado("CONSTRUCCION", "Obras Publicas"));
            BaseDeDatosSingleton.agregarAreaDeMercado(new AreasMercado("METALURGICA", "Acero Industrial"));
            BaseDeDatosSingleton.agregarAreaDeMercado(new AreasMercado("ASESORAMIENTO", "Servicio de Consultoria"));
        }
    }
    private static void cargarPaisYCiudadDefault(){
        if(paises.size() == 0){
            Pais paisDefault = new Pais("Argentina",487.2,50000000);
            BaseDeDatosSingleton.agregarPais(paisDefault);
            BaseDeDatosSingleton.agregarCiudad(new Ciudad("Buenos Aires",paisDefault));
        }
    }
    private static void cargarEmpresaDefault(){
        if(empresas.size()==0) {
            MenuCrearEmpresa m = new MenuCrearEmpresa();
            m.ejecutar();
        }
    }
}
