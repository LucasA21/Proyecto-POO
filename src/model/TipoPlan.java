package model;

import model.Planes.*;

public enum TipoPlan {
    PLAN_A(new PlanA(), "Aprobó las cursadas de las correlativas"),
    PLAN_B(new PlanB(), "Aprobó los finales de las correlativas"),
    PLAN_C(new PlanC(), "Aprobó las cursadas de las correlativas y los finales de todas las materias de 5 cuatrimestres previos"),
    PLAN_D(new PlanD(), "Aprobó las cursadas de las correlativas y los finales de todas las materias de 3 cuatrimestres previos"),
    PLAN_E(new PlanE(), "Aprobó los finales de las correlativas y los finales de todas las materias de 3 cuatrimestres previos");

    private final PlanStrategy estrategia;

    TipoPlan(PlanStrategy estrategia, String descripcion) {
        this.estrategia = estrategia;
    }

    public PlanStrategy getEstrategia() {
        return estrategia;
    }

}
