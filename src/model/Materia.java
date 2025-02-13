package model;

import java.util.ArrayList;
import java.util.List;

public class Materia {

    private String nombre;
    private int cuatrimestre;
    private boolean esObligatoria;
    private boolean esPromocionable;
    private List<Materia> correlativas;


    public Materia(String nombre, int cuatrimestre,boolean esObligatoria, boolean esPromocionable){
        this.nombre = nombre;
        this.esObligatoria = esObligatoria;
        this.esPromocionable = esPromocionable;
        if (cuatrimestre < 1) {
            throw new IllegalArgumentException("El cuatrimestre debe ser un número positivo.");
        }
        this.cuatrimestre = cuatrimestre;
        this.correlativas = new ArrayList<>();
    }


    public void agregarCorrelativas(Materia correlativa) {
        if (!correlativas.contains(correlativa)) {
            correlativas.add(correlativa);
        }
    }

    @Override
    public String toString() {
        return nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean esObligatoria(){return esObligatoria;}

    public boolean esPromocionable() {
        return esPromocionable;
    }

    public Integer getCuatrimestre() {return  cuatrimestre;}

    public List<Materia> getCorrelativas() {
        return correlativas;
    }

}


