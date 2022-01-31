//CLASE PADRE
public class GradingSystem {

    //Atributos
    protected String [][]datos;
    protected String []entrada;
    protected int registro;
    private int excelente, sobresaliente, regular, insuficiente, deficiente;
    private int biologia, geografia, matematicas, max=0, posicion = 0, pos = -99;
    private double aprobados, mejor_nota = 0;
    protected String vector_nombre[]= {"armando", "nicolas", "daniel", "maria", "marcela", "alexandra"};
    protected String vector_materia[]= {"biologia", "geografia", "matematicas"};
    
    
     //Metodos
    public double stat1 (){
        //En esta parte reseteo los valores para que elpproceso del calculo en la segunda pestaña de GUI
        excelente = 0 ;
        sobresaliente = 0;
        regular = 0;
        insuficiente = 0;
        deficiente = 0;
        for(int i=0; i < datos.length; i++){ 
            //Rango de notas
            if (90<Float.parseFloat(datos[i][3]) && Float.parseFloat(datos[i][3])<=100){
                excelente +=1;
            }else if (80<Float.parseFloat(datos[i][3]) && Float.parseFloat(datos[i][3])<=90){
                sobresaliente +=1;
            }else if (60<Float.parseFloat(datos[i][3]) && Float.parseFloat(datos[i][3])<=80){
                regular +=1;
            }else if (30<Float.parseFloat(datos[i][3]) && Float.parseFloat(datos[i][3])<=60){
                insuficiente +=1;
            }else if (0<=Float.parseFloat(datos[i][3]) && Float.parseFloat(datos[i][3])<=30){
                deficiente +=1;
            }
        }    
        aprobados = (excelente + sobresaliente + regular)/(float)registro;
        aprobados = Math.round(aprobados*100.0)/100.0;//Redondeo a dos cifras decimales
        return aprobados ;
    }

    public int stat2 (){
       return regular;
    }

    public String stat3 (){
        //En esta parte reseteo los valores para que elpproceso del calculo en la segunda pestaña de GUI
        biologia = 0;
        geografia = 0;
        matematicas = 0;
        for(int i=0; i < datos.length; i++){
            if (Float.parseFloat(datos[i][1]) == 1.0 && 60<Float.parseFloat(datos[i][3]) && Float.parseFloat(datos[i][3])<=100){//Materia con mejor desempeño entre 
                                                                                                                            //genero f
                if (Float.parseFloat(datos[i][2]) == 1.0){
                    biologia +=1;
                }else if(Float.parseFloat(datos[i][2]) == 2.0){
                    geografia +=1;
                }else if (Float.parseFloat(datos[i][2]) == 3.0){
                    matematicas +=1;
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
        }
        return vector_materia[posicion];
    }    

    public String stat4 (){
        for(int i=0; i < datos.length; i++){
            if (Float.parseFloat(datos[i][2]) == 1.0){//Busca de la materia biologia correspondiente a el numero 1
                if (Float.parseFloat(datos[i][3])>mejor_nota){//la mejor nota 
                    mejor_nota = Float.parseFloat(datos[i][3]);
                    pos = (int)(Float.parseFloat(datos[i][0]));//y el indicador del estudiante de esa mejor nota 
                } 
            }      
        }
        return vector_nombre[pos-1];//resto 1 para que me de la posicon del vector que esta arriba
    }
}
