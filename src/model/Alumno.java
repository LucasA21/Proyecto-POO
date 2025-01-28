package model;

import java.util.ArrayList;
import java.util.List;

public class Alumno {
    private String dni;
    private String nombre;
    private List<Materia> materiasAprobadas;
    private List<Materia> finalesAprobados;
    private Carrera carrera;


    public Alumno(String dni, String nombre) {
        this.dni = dni;
        this.nombre = nombre;
        this.materiasAprobadas = new ArrayList<>();
        this.finalesAprobados = new ArrayList<>();
        this.carrera = carrera;
    }


    public String getNombre() {
        return nombre;
    }

    public String getDni() {
        return dni;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public List<Materia> getMateriasAprobadas() {
        return materiasAprobadas;
    }

    public List<Materia> getFinalesAprobados(){
        return finalesAprobados;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    public void agregarMateria(Materia aprobadas){
        materiasAprobadas.add(aprobadas);
    }

    public void agregarFinales(Materia finales) {finalesAprobados.add(finales);}


    // public void finalizoCarrera()


    @Override
    public String toString() {
        return this.nombre;
    }

}