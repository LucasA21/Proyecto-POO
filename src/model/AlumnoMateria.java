package model;

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
        this.notaCursada = -1;
        this.notaFinal = -1;
    }


    public void aprobarCursada(int nota) {
        System.out.println("Registrando nota de cursada: " + nota + " en " + materia.getNombre());
        this.notaCursada = nota;

        if (nota >= 4) {
            this.aproboCursada = true;
            System.out.println("Cursada aprobada para " + materia.getNombre());

            //Si la materia es promocionable y la nota es 7 o más, se aprueba automáticamente el final
            if (nota >= 7 && materia.esPromocionable()) {
                this.aproboFinal = true;
                this.notaFinal = nota; // Se toma la misma nota de cursada
                System.out.println("Materia promocionada: " + materia.getNombre() + " (Final aprobado automáticamente con nota: " + nota + ")");
            }
        } else {
            this.aproboCursada = false;
            System.out.println("Cursada NO aprobada para " + materia.getNombre() + ". Nota: " + nota);
        }
    }

    public void aprobarFinal(int nota) {
        if (!aproboCursada) {
            System.out.println("No se puede aprobar final sin haber aprobado la cursada.");
            return;
        }
        if (aproboFinal) {
            System.out.println("El final ya está aprobado, no es necesario volver a registrarlo.");
            return;
        }

        System.out.println("Registrando nota de final: " + nota + " en " + materia.getNombre());
        this.notaFinal = nota;
        if (nota >= 4) {
            this.aproboFinal = true;
            System.out.println("Final aprobado para " + materia.getNombre() + " con nota: " + notaFinal);
        } else {
            this.aproboFinal = false;
            System.out.println("Final NO aprobado para " + materia.getNombre() + ". Nota: " + notaFinal);
        }
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

    public int getNotaCursada() {
        return notaCursada;
    }
}
