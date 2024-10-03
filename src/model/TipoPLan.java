package model;

public enum TipoPLan {
    PLAN_A("Aprobó las cursadas de las correlativas"),
    PLAN_B("Aprobó los finales de las correlativas"),
    PLAN_C("Aprobó las cursadas y los finales de todas las materias de 5 cuatrimestres previos"),
    PLAN_D("Aprobó las cursadas y los finales de todas las materias de 3 cuatrimestres previos"),
    PLAN_E("Aprobó los finales de las correlativas y los finales de todas las materias de 3 cuatrimestres previos");

    private String descripcion;

    TipoPLan(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
