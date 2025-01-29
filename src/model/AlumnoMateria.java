package model;

import java.util.List;

public class AlumnoMateria {
    private Materia materia;
    private boolean aproboCursada;
    private boolean aproboFinal;
    private int notaCursada;
    private int notaFinal;

    public AlumnoMateria(Materia materia) {
        this.materia = materia;
        this.aproboCursada = false;
        this.aproboFinal = false;
        this.notaCursada = 0;
        this.notaFinal = 0;
    }

    public Materia getMateria() {
        return materia;
    }

    public boolean isAproboCursada() {
        return aproboCursada;
    }

    public boolean isAproboFinal() {
        return aproboFinal;
    }

    public void aprobarCursada(int nota) {
        if (nota >= 4) {
            this.aproboCursada = true;
            this.notaCursada = nota;
        }
    }

    public void aprobarFinal(int nota) {
        if (nota >= 4 && aproboCursada) {
            this.aproboFinal = true;
            this.notaFinal = nota;
        }
    }

    public int getNotaCursada() {
        return notaCursada;
    }

    public int getNotaFinal() {
        return notaFinal;
    }
}
