//CLASE HIJO

public class SchoolGradingSystem extends GradingSystem{

    public void  loadData (int registro, String textoCompleto){//paso los argumentos desde el controlador de la vista en la parte procesar
        super.registro = registro;//registro es el tamaño de filas en la matriz de datos
        datos = new String[registro][4];
        String []linea = textoCompleto.split("\n"); //como el texto que llega esta completo hay que dividirlos por salto de linea para que quede en filas

        for (int i=0; i<datos.length; i++){
            entrada = linea[i].split(" ");//aqui las lineas divididas anteriormente se dividen por espacio y cada valor se ingresa en la matriz datos
            datos[i] = entrada;//ingreso de datos en la matriz datos
        }
    }

    
}
