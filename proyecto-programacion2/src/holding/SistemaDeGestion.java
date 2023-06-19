package holding;

import java.io.*;
import java.util.HashMap;

public class SistemaDeGestion implements Serializable {

    private static HashMap<Integer,Usuario> usuarios;
    private static HashMap<Integer,Empresa> empresas;
    private static HashMap<Integer,AreasMercado> areasDeMercado;
    private static HashMap<Integer,Ciudad> ciudades;
    private static HashMap<Integer,Pais> paises;
    public SistemaDeGestion() throws IOException {
        BaseDeDatosSingleton bd = BaseDeDatosSingleton.getInstance();
        usuarios = new HashMap<>();
        empresas = new HashMap<>();
        ciudades = new HashMap<>();
        paises = new HashMap<>();
        areasDeMercado = new HashMap<>();
        deserializarBD();
        /*empresas.put(1,new Empresa("Tenarix",LocalDate.now(),10));
        usuarios.put(0,new Admin("admin1","Calle 123","1"));
        Pais p = new Pais("Uruguay", 1200, 1000000);
        paises.put(1, p);
        Ciudad c = new Ciudad("Monte", p);
        ciudades.put(1,c);
        AreasMercado am = new AreasMercado("Construccion", "Obras");
        areasDeMercado.put(1,am);*/

        bd.cargarDatosSerializados(usuarios,empresas,areasDeMercado,ciudades, paises);

    }
    public void run() throws IOException {
        int num;
        BaseDeDatosSingleton bd = BaseDeDatosSingleton.getInstance();
        do{
            num=mostrarMenu();
            if (num == 1) {

                Usuario loggedInUsuario = bd.iniciarSesion();
                if(loggedInUsuario != null){
                    loggedInUsuario.proceder();
                }
            }
        }while(num!=2);
        serializarBD(bd.obtenerUsuarios(), bd.obtenerEmpresas(),bd.obtenerAreasDeMercado(),bd.obtenerCiudades(), bd.obtenerPaises());

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
            ObjectOutputStream ObjUser =  new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("Usuarios.bin")));
            ObjectOutputStream objEmp =  new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("Empresas.bin")));
            ObjectOutputStream objArea =  new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("AreasDeMercado.bin")));
            ObjectOutputStream objCiudad =  new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("Ciudades.bin")));
            ObjectOutputStream objPais =  new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("Paises.bin")));

            ObjUser.writeObject(usuarios2);
            objEmp.writeObject(empresas2);
            objArea.writeObject(areasDeMercado2);
            objCiudad.writeObject(ciudades2);
            objPais.writeObject(paises2);

            ObjUser.close();
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
            try{
                ObjectInputStream objUser = new ObjectInputStream((new BufferedInputStream(new FileInputStream("Usuarios.bin"))));
                usuarios = (HashMap<Integer, Usuario>)  objUser.readObject();
                objUser.close();
            }catch (FileNotFoundException e) {
                System.out.println("Error de E/S: " + e.getMessage());
                System.out.println("~ BASE DE USUARIOS NO HALLADA ~");
                Admin admin = new Admin("admin1", "Calle 123", "1");
                usuarios.put(usuarios.size()+1, admin);
                System.out.println("Se ha creado el Admin Default");
            }
            ObjectInputStream objEmp = new ObjectInputStream((new BufferedInputStream(new FileInputStream("Empresas.bin"))));
            ObjectInputStream objArea = new ObjectInputStream((new BufferedInputStream(new FileInputStream("AreasDeMercado.bin"))));
            ObjectInputStream objCiudad = new ObjectInputStream((new BufferedInputStream(new FileInputStream("Ciudades.bin"))));
            ObjectInputStream objPais = new ObjectInputStream((new BufferedInputStream(new FileInputStream("Paises.bin"))));

            empresas = (HashMap<Integer, Empresa>) objEmp.readObject();
            areasDeMercado = (HashMap<Integer, AreasMercado>) objArea.readObject();
            ciudades = (HashMap<Integer, Ciudad>)  objCiudad.readObject();
            paises = (HashMap<Integer, Pais>)  objPais.readObject();

            objEmp.close();
            objArea.close();
            objCiudad.close();
            objPais.close();

        } catch (FileNotFoundException | ClassNotFoundException e) {
            System.out.println("Error de E/S: " + e.getMessage());
            System.out.println("BASE NO RECUPERADA");
        }
    }
}
