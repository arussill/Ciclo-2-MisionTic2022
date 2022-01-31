//CLASE HIJO
import java.util.Scanner;
public class SchoolGradingSystem extends GradingSystem{
    //Atributos
    private Scanner leer;

    public void  loadData (){
        leer = new Scanner(System.in);
        registro = Integer.parseInt(leer.nextLine());
        datos = new String[registro][4];
        for (int i=0; i<datos.length; i++){
            entrada = leer.nextLine().split(" ");//lee el str de datos y los convierte en vector
            datos[i] = entrada;//agrega los datos ya separados antes, siguen en forma de str en una datos
        }  
    }

    
}
