package Modelo;
//CLASE HIJO

import java.util.ArrayList;

public class SchoolGradingSystem extends GradingSystem{

    //Atributos
    protected Student armando;
    protected Student daniel;
    protected Student nicolas;
    protected Student maria;
    protected Student marcela;
    protected Student alexandra;
    protected String []entrada;

    //Metodos
    public void  loadData (String numRegistro, String textoCompleto){
        registro = Integer.parseInt(numRegistro);
        String []linea = textoCompleto.split("\n");
        String []notas_1 = new String[3];
        String []notas_2 = new String[3];
        String []notas_3 = new String[3];
        String []notas_4 = new String[3];
        String []notas_5 = new String[3];
        String []notas_6 = new String[3];
        estudiantes = new ArrayList<Student>();
        
        int contador_1 =0, contador_2 =0, contador_3 =0, contador_4 =0, contador_5 =0, contador_6 =0;
        for (int i=0; i<linea.length; i++){
            entrada = linea[i].split(" ");
            if (entrada[0].equals("1.0")){
                notas_1[contador_1] = entrada[3];
                contador_1++;
                if (contador_1 == 3){
                   armando = new Student(entrada[0], entrada[1], notas_1);
                }
            }
            if (entrada[0].equals("2.0")){
                notas_2[contador_2] = entrada[3]; 
                contador_2++;
                if (contador_2 == 3){
                    nicolas = new Student(entrada[0], entrada[1], notas_2);
                }
            }
            if (entrada[0].equals("3.0")){
                notas_3[contador_3] = entrada[3];
                contador_3++;
                if (contador_3 == 3){ 
                    daniel = new Student(entrada[0],entrada[1], notas_3);
                }
            }
            if (entrada[0].equals("4.0")){
                notas_4[contador_4] = entrada[3];
                contador_4++;
                if (contador_4 == 3){
                    maria = new Student(entrada[0], entrada[1], notas_4);
                }
            }
            if (entrada[0].equals("5.0")){
                notas_5[contador_5] = entrada[3];
                contador_5++;
                if (contador_5 == 3){
                   marcela = new Student(entrada[0], entrada[1], notas_5);
                }
            }
            if (entrada[0].equals("6.0")){   
                notas_6[contador_6] = entrada[3];
                contador_6++;
                if (contador_6 == 3){
                    alexandra = new Student(entrada[0], entrada[1], notas_6);
                }
            }
        }
        
        estudiantes.add(armando);
        estudiantes.add(nicolas);
        estudiantes.add(daniel);
        estudiantes.add(maria);
        estudiantes.add(marcela);
        estudiantes.add(alexandra);

    }
}

    
