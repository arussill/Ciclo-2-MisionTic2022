//Clase Conexion, para permitir la conexion a la base de datos dbReto4

//Se importan las librerias necesarias
import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
   //Se crea un metodo static tipo Connection para no tener que instanciar objetos para realizar la conecion
   public static Connection conectar(){
       //Se define una variable tipo Connection
       Connection con = null;
       //Se encierra en un try-catch para evitar excepciones
       try {
           con = DriverManager.getConnection("jdbc:sqlite:dbReto4.db");//DriverManager clase usada para la conexion, se obtiene de la rita entre comilas
       } catch (Exception e) {
           System.out.println(e.getMessage());//e recolecta las exepciones que se presente y las muestra como mensaje
       }
       return con;// el metodo debe regresar una variable tipo Connection
   }
}
