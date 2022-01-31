package Modelo;
//Clase student
public class Student {

    private String nombre;
    private String genero;
    private String[] notas;

    Student (String nombre, String genero, String [] notas){
        this.nombre = nombre;
        this.genero = genero;
        this.notas = notas; 
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGenero() {
        return this.genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String[] getNotas() {
        return this.notas;
    }

    public void setNotas(String[] notas) {
        this.notas = notas;
    }
    
}
