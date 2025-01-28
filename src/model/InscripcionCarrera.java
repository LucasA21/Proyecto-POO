package model;

public class InscripcionCarrera {
    private Alumno alumno;
    private Carrera carrera;

    public InscripcionCarrera(Alumno alumno, Carrera carrera) {
        this.alumno = alumno;
        this.carrera = carrera;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public Carrera getCarrera() {
        return carrera;
    }
}
