package Modelo;

import java.util.ArrayList;

//CLASE PADRE
public class GradingSystem {

    //Atributos
    protected int registro;
    private int excelente = 0 , sobresaliente = 0, regular = 0, insuficiente = 0, deficiente = 0;
    private int biologia, geografia, matematicas, max=0, posicion = 0, pos = -99;
    private double aprobados = 0, mejor_nota = 0;
    protected String vector_nombre[]= {"armando", "nicolas", "daniel", "maria", "marcela", "alexandra"};
    protected String vector_materia[]= {"biologia", "geografia", "matematicas"};
    protected ArrayList<Student> estudiantes;
    
    //Metodos
    public double stat1 (){
        for (Student e : estudiantes){
            for(int i=0; i < e.getNotas().length; i++){ 
                //Rango de notas
                if (90<Float.parseFloat(e.getNotas()[i]) && Float.parseFloat(e.getNotas()[i])<=100){
                    excelente +=1;
                }else if (80<Float.parseFloat(e.getNotas()[i]) && Float.parseFloat(e.getNotas()[i])<=90){
                    sobresaliente +=1;
                }else if (60<Float.parseFloat(e.getNotas()[i]) && Float.parseFloat(e.getNotas()[i])<=80){
                    regular +=1;
                }else if (30<Float.parseFloat(e.getNotas()[i]) && Float.parseFloat(e.getNotas()[i])<=60){
                    insuficiente +=1;
                }else if (0<=Float.parseFloat(e.getNotas()[i]) && Float.parseFloat(e.getNotas()[i])<=30){
                    deficiente +=1;
                }
            }    
        }
        aprobados = (excelente + sobresaliente + regular)/(float)registro;
        aprobados = Math.round(aprobados*100.0)/100.0;
        return aprobados ;
    }

    public int stat2 (){
       return regular;
    }

    public String stat3 (){
        for (Student e: estudiantes){
            for(int i=0; i < e.getNotas().length; i++){
                if (Float.parseFloat(e.getGenero()) == 1.0 && 60<Float.parseFloat(e.getNotas()[i]) && Float.parseFloat(e.getNotas()[i])<=100){//Materia con mejor desempeño entre 
                                                                                                                                //genero f
                    if (i == 0){
                        biologia +=1;
                    }else if(i == 1){
                        geografia +=1;
                    }else if (i== 2){
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
        }
        return vector_materia[posicion];
    }    

    public String stat4 (){
        for (Student e:estudiantes){
            for(int i=0; i < e.getNotas().length; i++){
                if ((i+1.0) == 1.0){//Busca de la materia biologia correspondiente a el numero 1
                    if (Float.parseFloat(e.getNotas()[i])>mejor_nota){//la mejor nota 
                        mejor_nota = Float.parseFloat(e.getNotas()[i]);
                        pos = (int)(Float.parseFloat(e.getNombre()));//y el indicador del estudiantes de esa mejor nota 
                    } 
                }      
            }
        }
        return vector_nombre[pos-1];
    }
}
