package model.Planes;

import model.Alumno;
import model.Materia;
import model.PlanStrategy;

public class PlanC implements PlanStrategy {
    @Override
    public boolean puedeCursar(Materia materia, Alumno alumno) {
        System.out.println("Verificando si " + alumno.getNombre() + " puede cursar " + materia.getNombre());

        if (materia.getCorrelativas().isEmpty() && alumno.getHistorialAcademico().isEmpty()) {
            System.out.println(materia.getNombre() + " no tiene correlativas y el alumno es nuevo. Se puede cursar.");
            return true;
        }

        for (Materia correlativa : materia.getCorrelativas()) {
            boolean aprobada = alumno.getMateriasAprobadas().contains(correlativa);
            System.out.println("Correlativa requerida: " + correlativa.getNombre() + " | ¿Aprobada? " + aprobada);

            if (!aprobada) {
                System.out.println("No puede cursar " + materia.getNombre() + " porque no aprobó " + correlativa.getNombre());
                return false;
            }
        }

        int limiteCuatrimestre = materia.getCuatrimestre() - 5;
        System.out.println("Verificando finales de los últimos 5 cuatrimestres...");

        // Si el alumno no tiene finales aprobados aún, permitir cursar materias del primer cuatrimestre
        if (alumno.getFinalesAprobados().isEmpty()) {
            System.out.println("El alumno aún no tiene finales aprobados. Se permite inscripción en materias iniciales.");
            return true;
        }

        for (int cuatrimestre = limiteCuatrimestre; cuatrimestre < materia.getCuatrimestre(); cuatrimestre++) {
            boolean encontrado = false;
            for (Materia finalAprobado : alumno.getFinalesAprobados()) {
                if (finalAprobado.getCuatrimestre() == cuatrimestre) {
                    encontrado = true;
                    break;
                }
            }
            if (!encontrado) {
                System.out.println("No puede cursar " + materia.getNombre() + " porque no tiene finales aprobados en cuatrimestre " + cuatrimestre);
                return false;
            }
        }

        return true;
    }
}
