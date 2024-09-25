package model;

import java.util.ArrayList;
import java.util.List;

public class PlanEstudio {

    private String tipoPlan;
    private List<Materia> materiasObligatorias;
    private List<Materia> materiasOptativas;

    public PlanEstudio(String tipoPlan){
        this.tipoPlan = tipoPlan;
        this.materiasObligatorias = new ArrayList<>();
    }

}
