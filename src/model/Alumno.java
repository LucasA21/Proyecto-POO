package model;

import java.util.ArrayList;
import java.util.List;

public class Alumno {
    private String dni;
    private String nombre;
    private Carrera carrera;
    private List<AlumnoMateria> historialAcademico;

    public Alumno(String dni, String nombre) {
        this.dni = dni;
        this.nombre = nombre;
        this.historialAcademico = new ArrayList<>();
    }


    public AlumnoMateria getAlumnoMateria(Materia materia) {
        for (AlumnoMateria am : historialAcademico) {
            if (am.getMateria().equals(materia)) {
                return am;
            }
        }
        return null; // Si no encuentra la relación
    }

    public List<Materia> getMateriasAprobadas() {
        List<Materia> aprobadas = new ArrayList<>();
        for (AlumnoMateria am : historialAcademico) {
            if (am.isAproboCursada()) {
                aprobadas.add(am.getMateria());
            }
        }
        return aprobadas;
    }

    public List<Materia> getFinalesAprobados() {
        List<Materia> finales = new ArrayList<>();
        for (AlumnoMateria am : historialAcademico) {
            if (am.isAproboFinal()) {
                finales.add(am.getMateria());
            }
        }
        return finales;
    }

    private AlumnoMateria buscarAlumnoMateria(Materia materia) {
        for (AlumnoMateria alumnoMateria : historialAcademico) {
            if (alumnoMateria.getMateria().equals(materia)) {
                return alumnoMateria;
            }
        }
        return null;
    }

    public List<AlumnoMateria> getHistorialAcademico() {
        return historialAcademico;
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

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }


    @Override
    public String toString() {
        return nombre;
    }
}
