package model.Planes;

import model.Alumno;
import model.Materia;
import model.PlanStrategy;

public class PlanA implements PlanStrategy {

    @Override
    public boolean puedeCursar(Materia materia, Alumno alumno){
        for (Materia correlativa: materia.getCorrelativas()) {
            if (!alumno.getMateriasAprobadas().contains(correlativa)) {
                return  false;
            }
        }
        return  true;
    }
}
