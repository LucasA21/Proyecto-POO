package model;

import java.util.ArrayList;
import java.util.List;

public class PlanEstudio {

    private String tipoPlan;
    private List<Materia> materias;

    public PlanEstudio(String tipoPlan){
        this.tipoPlan = tipoPlan;
        this.materias = new ArrayList<>();
    }

}
