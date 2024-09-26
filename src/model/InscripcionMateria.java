package model;

public class InscripcionMateria {
    private  Alumno alumno;
    private  Materia materia;
    private  boolean aproboCursada;
    private  boolean promociono;
    private  boolean aproboFinal;

    public InscripcionMateria(Alumno alumno, Materia materia){
        this.alumno = alumno;
        this.materia = materia;
        this.aproboCursada = false;
        this.aproboFinal = false;
        this.promociono = false;
    }
}
