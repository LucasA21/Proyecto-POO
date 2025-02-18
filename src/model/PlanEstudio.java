    package model;

    import java.util.ArrayList;
    import java.util.List;

    public class PlanEstudio {
        private String nombre;
        private PlanStrategy estrategia;
        private List<Materia> materias;

        public PlanEstudio(String nombre, PlanStrategy estrategia){
            this.nombre = nombre;
            this.estrategia = estrategia;
            this.materias = new ArrayList<>();
        }

        public void agregarMateria(Materia materia){
            materias.add(materia);
        }

        public List<Materia> getMateriasDisponiblesParaAlumno(Alumno alumno) {
            System.out.println("Buscando materias disponibles para: " + alumno.getNombre());

            if (materias.isEmpty()) {
                System.err.println("El plan de estudio no tiene materias.");
            }

            List<Materia> disponibles = new ArrayList<>();

            for (Materia materia : materias) {
                boolean puedeCursar = estrategia.puedeCursar(materia, alumno);
                System.out.println("Evaluando materia: " + materia.getNombre() + " | Puede cursar: " + puedeCursar);

                if (puedeCursar) {
                    disponibles.add(materia);
                }
            }

            System.out.println("Materias disponibles para " + alumno.getNombre() + ": " + disponibles);

            return disponibles;
        }

        public String getNombre() {
            return nombre;
        }

        public List<Materia> getMaterias() {
            return materias;
        }

        @Override
        public String toString() {
            return nombre;
        }


    }
