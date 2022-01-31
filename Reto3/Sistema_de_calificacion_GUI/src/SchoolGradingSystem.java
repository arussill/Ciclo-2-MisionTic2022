//CLASE HIJO

public class SchoolGradingSystem extends GradingSystem{

    public void  loadData (String numRegistro, String textoCompleto){
        registro = Integer.parseInt(numRegistro);
        datos = new String[registro][4];
        String []linea = textoCompleto.split("\n");

        for (int i=0; i<datos.length; i++){
            entrada = linea[i].split(" ");
            datos[i] = entrada;
        }
    }

    
}
