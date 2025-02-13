package model.Planes;

import model.Alumno;
import model.Materia;
import model.PlanStrategy;

public class PlanB implements PlanStrategy {

    @Override
    public boolean puedeCursar(Materia materia, Alumno alumno) {
        System.out.println("Verificando si " + alumno.getNombre() + " puede cursar " + materia.getNombre());

        // Si el alumno es nuevo y la materia no tiene correlativas, se permite inscripción
        if (materia.getCorrelativas().isEmpty() && alumno.getHistorialAcademico().isEmpty()) {
            System.out.println(materia.getNombre() + " no tiene correlativas y el alumno es nuevo. Se puede cursar.");
            return true;
        }

        for (Materia correlativa : materia.getCorrelativas()) {
            boolean aproboFinal = alumno.getFinalesAprobados().contains(correlativa);
            System.out.println("Correlativa requerida: " + correlativa.getNombre() + " | ¿Final aprobado? " + aproboFinal);

            if (!aproboFinal) {
                System.out.println("No puede cursar " + materia.getNombre() + " porque no aprobó el final de " + correlativa.getNombre());
                return false;
            }
        }

        return true;
    }
}
