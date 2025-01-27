package model.Planes;

import model.Alumno;
import model.Materia;
import model.PlanStrategy;

public class PlanC implements PlanStrategy {
    @Override
    public boolean puedeCursar(Materia materia, Alumno alumno) {
        for (Materia correlativa : materia.getCorrelativas()) {
            if (!alumno.getMateriasAprobadas().contains(correlativa)) {
                return false;
            }
        }

        int limiteCuatrimestre = materia.getCuatrimestre() - 5;

        for (int cuatrimestre = limiteCuatrimestre; cuatrimestre < materia.getCuatrimestre(); cuatrimestre++) {
            boolean encontrado = false;
            for (Materia finalAprobado : alumno.getFinalesAprobados()) {
                if (finalAprobado.getCuatrimestre() == cuatrimestre) {
                    encontrado = true;
                    break;
                }
            }
            if (!encontrado) {
                return false;
            }
        }
        return true;
    }
}