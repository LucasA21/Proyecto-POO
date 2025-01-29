    package model;

    import java.util.ArrayList;
    import java.util.List;

    public class PlanEstudio {
        private String nombre;
        private TipoPlan tipoPlan;
        private List<Materia> materias;

        public PlanEstudio(String nombre, TipoPlan tipoPlan){
            this.nombre = nombre;
            this.tipoPlan = tipoPlan;
            this.materias = new ArrayList<>();
        }

        public String getNombre() {
            return nombre;
        }

        public TipoPlan getTipoPlan() {
            return tipoPlan;
        }

        public List<Materia> getMaterias() {
            return materias;
        }

        public void agregarMateria(Materia materia){
            materias.add(materia);
        }

        public boolean puedeCursar(Materia materia, Alumno alumno){
            return  tipoPlan.getEstrategia().puedeCursar(materia,alumno);
        }

        public List<Materia> getMateriasDisponiblesParaAlumno(Alumno alumno) {
            List<Materia> disponibles = new ArrayList<>();
            for (Materia materia : materias) {
                if (tipoPlan.getEstrategia().puedeCursar(materia, alumno)) {
                    disponibles.add(materia);
                }
            }
            return disponibles;
        }


        @Override
        public String toString() {
            return nombre;
        }


    }
