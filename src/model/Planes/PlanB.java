package model.Planes;

import model.Alumno;
import model.Materia;
import model.PlanStrategy;

public class PlanB implements PlanStrategy {

    @Override
    public boolean puedeCursar(Materia materia, Alumno alumno){
        for (Materia correlativa: materia.getCorrelativas()) {
            if (!alumno.getFinalesAprobados().contains(correlativa)) {
                return  false;
            }
        }
        return true;
    }
}
