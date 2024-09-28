package controller;

import model.Alumno;

import java.util.ArrayList;
import java.util.List;

public class AlumnoController {
    private List<Alumno> listaAlumnos;

    public AlumnoController(){
        this.listaAlumnos = new ArrayList<>();
    }



    public List<Alumno> getListaAlumnos() {
        return listaAlumnos;
    }
}
