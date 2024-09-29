package model;

import java.util.ArrayList;
import java.util.List;

public class Alumno {
    private String dni;
    private String nombre;
    private List<Materia> materiasAprobadas;
    private Carrera carrera;


    public Alumno(String dni, String nombre) {
        this.dni = dni;
        this.nombre = nombre;
        this.materiasAprobadas = new ArrayList<>();
        this.carrera = carrera;
    }



    public String getNombre() {
        return nombre;
    }

    public String getDni() {
        return dni;
    }

    public void agregarMateria(Materia aprobadas){
        materiasAprobadas.add(aprobadas);
    }

    public String toString() {
        return "Alumno{" +
               "Dni="+dni+
                "Nombre=" + nombre +
                '}';
    }
}