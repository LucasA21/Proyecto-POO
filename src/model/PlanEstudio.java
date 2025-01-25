    package model;

    import java.util.ArrayList;
    import java.util.List;

    public class PlanEstudio {
        private String nombre;
        private TipoPLan tipoPlan;
        private List<Materia> materias;

        public PlanEstudio(String nombre, TipoPLan tipoPlan){
            this.nombre = nombre;
            this.tipoPlan = tipoPlan;
            this.materias = new ArrayList<>();
        }

        public String getNombre() {
            return nombre;
        }

        public TipoPLan getTipoPlan() {
            return tipoPlan;
        }

        public List<Materia> getMaterias() {
            return materias;
        }

        public void agregarMateria(Materia materia){
            materias.add(materia);
        }

        // verificar si el alumno puede inscribirse a una materia segun el plan

    }
