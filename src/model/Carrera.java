package model;

import java.util.ArrayList;
import java.util.List;

public class Carrera {
    private String nombre;
    private PlanEstudio planEstudio;
    private List<Materia> materiasObligatorias; // Materias obligatorias de la carrera
    private List<Materia> materiasOptativas; // Materias optativas de la carrera
    private int cantidadOptativasRequeridas;

    public Carrera(String nombre, PlanEstudio planEstudio, int cantidadOptativasRequeridas) {
        this.nombre = nombre;
        this.planEstudio = planEstudio;
        this.materiasObligatorias = new ArrayList<>();
        this.materiasOptativas = new ArrayList<>();
        this.cantidadOptativasRequeridas = cantidadOptativasRequeridas;
    }

    public String getNombre() {
        return nombre;
    }

    public PlanEstudio getPlanEstudio() {
        return planEstudio;
    }

    public void setPlanEstudio(PlanEstudio planEstudio) {
        this.planEstudio = planEstudio;
    }

    public List<Materia> getMateriasObligatorias() {
        return materiasObligatorias;
    }

    public void agregarMateriaObligatoria(Materia materia) {
        materiasObligatorias.add(materia);
    }

    public List<Materia> getMateriasOptativas() {
        return materiasOptativas;
    }

    public void agregarMateriaOptativa(Materia materia) {
        materiasOptativas.add(materia);
    }

    public int getCantidadOptativasRequeridas() {
        return cantidadOptativasRequeridas;
    }

    public void setCantidadOptativasRequeridas(int cantidadOptativasRequeridas) {
        this.cantidadOptativasRequeridas = cantidadOptativasRequeridas;
    }
    @Override
    public String toString() {
        return nombre;
    }

}
