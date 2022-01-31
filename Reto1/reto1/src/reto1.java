import java.util.Scanner;
public class reto1 {
    public static void main(String[] args) throws Exception {
    /**Declaraciones y definiciones */
        Scanner leer;
        leer = new Scanner(System.in);
        String vector_nombre[]= {"armando", "nicolas", "daniel", "maria", "marcela", "alexandra"};
        String vector_materia[]= {"biologia", "geografia", "matematicas"};
        int registro; String [][]datos; String []entrada; float [][]matriz; String mejor_desempeno;  
        int excelente = 0, sobresaliente = 0, regular = 0, insuficiente = 0, deficiente = 0, aprobados;
        int biologia = 0, geografia = 0, matematicas = 0, max = 0, posicion = 0;
        float procentaje_aprobados , mejor_nota = 0, estudiante = 0;

        /**Entrada de datos por teclado */
        registro = Integer.parseInt(leer.nextLine());
        // leer = new Scanner(System.in);//vuelvo a leer para no salga error de string vacio
        datos = new String[registro][4];
        matriz = new float[registro][4];
        for (int i=0; i<datos.length; i++){
            entrada = leer.nextLine().split(" ");//lee el str de datos y los convierte en vector
            datos[i] = entrada;//agrega los datos ya separados antes, siguen en forma de str en una matriz
        }     
        for(int i=0; i < datos.length; i++){ 
            for(int j=0; j < datos[i].length; j++){ 
                matriz[i][j] = Float.parseFloat(datos[i][j]);//convierte mis datos en float 
            } 

            if (90<matriz[i][3] && matriz[i][3]<=100){//Rango de notas
                excelente +=1;
            }else if (80<matriz[i][3] && matriz[i][3]<=90){
                sobresaliente +=1;
            }else if (60<matriz[i][3] && matriz[i][3]<=80){
                regular +=1;
            }else if (30<matriz[i][3] && matriz[i][3]<=60){
                insuficiente +=1;
            }else if (0<=matriz[i][3] && matriz[i][3]<=30){
                deficiente +=1;
            }else{
                System.out.println("Fuera de rango");
            }

            if ((int)matriz[i][1] == 1 && 60<matriz[i][3] && matriz[i][3]<=100){//Materia con mejor desempeño entre 
                                                                                //genero f
                if ((int)matriz[i][2] == 1){
                    biologia +=1;                        
                }else if((int)matriz[i][2] == 2){
                    geografia +=1;
                }else if ((int)matriz[i][2] == 3){
                    matematicas +=1;
                }else{
                    System.out.println("No es una materia");
                }
                int []materia = {biologia, geografia, matematicas};//agreaga los valores anteriores a un vector 
                for (int index=0; index<materia.length; index++){//recore vector anterior
                    if(materia[index]>max){//compara cual de esos valores es el mayor
                        max = materia[index];
                        posicion = index;//sabiendo la poscion se usa para buscar el str de la materia en el 
                                        //vector_materia
                    }
                }
            }
            if (matriz[i][2] == 1){//Busca de la materia biologia correspondiente a el numero 1
                if (matriz[i][3]>mejor_nota){//la mejor nota 
                    mejor_nota = matriz[i][3];
                    estudiante = matriz[i][0];//y el indicador del estudiante de esa mejor nota 
                }   
            }
        } 

        aprobados = excelente + sobresaliente + regular;
        procentaje_aprobados = (aprobados/(float)registro);
        mejor_desempeno = vector_nombre[((int)estudiante)-1];//como el vector_nombre las posiciones empiezan desde 0 y 
                                                            //los indicativos de estudiantes desde 1 a este ultimo se 
                                                            //le resta para poder encontrar el str correspondiente al 
                                                            //nombre del estudiante en el vector
       
        //close leer
        leer.close();

        /**Salida*/
        System.out.println((Math.round(procentaje_aprobados*100)/100.0));//salida 1; el metodo Math.round es para 
                                                                        //que me de bien el redondeo con coma
        System.out.println(regular);//Salida 2
        System.out.println(vector_materia[posicion]);//salida 3
        System.out.print(mejor_desempeno);//salida 4
    }
}
