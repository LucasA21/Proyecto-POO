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


    // Aca hay que implementar un metodo para verificar si el alumno puede promocionar. ( nota >= 7 && esPromocionable = true)

    // Aca hay que implementar un metodo para verificar si el alumno aprueba la cursada ( nota >= 4 )

    // Aca hay que implementar un metodo para verificar si el alumno aprobo el final (nota >= 4 && aproboCursada = true)



    public String getNombre() {
        return nombre;
    }

    public boolean esObligatoria(){return esObligatoria;}

    public Integer getCuatrimestre() {return  cuatrimestre;}

    public List<Materia> getCorrelativas() {
        return correlativas;
    }

    public void agregarCorrelativas(Materia correlativa) {
        if (!correlativas.contains(correlativa)) {
            correlativas.add(correlativa);
        }
    }
}


