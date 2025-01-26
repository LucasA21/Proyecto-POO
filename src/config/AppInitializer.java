package config;

import controller.CrearAlumnoController;
import controller.CrearMateriaController;
import controller.CrearPlanEstudioController;
import controller.VerEstadoController;
import model.Alumno;
import model.Materia;
import view.*;

public class AppInitializer {

    public static Principal initializeApp() {
        // Crear vistas
        HomeView homeView = new HomeView();
        CrearAlumnoView crearAlumnoView = new CrearAlumnoView();
        VerEstadoView verEstadoView = new VerEstadoView();
        CrearMateriaView crearMateriaView = new CrearMateriaView();
        CrearPlanView crearPlanView = new CrearPlanView();
        CrearCarreraView crearCarreraView = new CrearCarreraView();
        InscribirAlumnoView inscribirAlumnoView = new InscribirAlumnoView();
        InscribirMateriaView inscribirMateriaView = new InscribirMateriaView();
        AgregarNotasView agregarNotasView = new AgregarNotasView();

        // Crear controladores
        CrearAlumnoController crearAlumnoController = new CrearAlumnoController(crearAlumnoView);
        VerEstadoController verEstadoController = new VerEstadoController(verEstadoView, crearAlumnoController);
        CrearMateriaController crearMateriaController = new CrearMateriaController(crearMateriaView);
        CrearPlanEstudioController crearPlanEstudioController = new CrearPlanEstudioController(crearPlanView, crearMateriaController);

        // Conectar controladores
        crearAlumnoController.setVerEstadoController(verEstadoController);

        // Precargar alumnos
        Alumno alumno1 = new Alumno("41951221", "Lucas Araya");
        Alumno alumno2 = new Alumno("43952231", "Abril Gonzalez");
        Alumno alumno3 = new Alumno("39721121", "Silvina Faraoni");
        crearAlumnoController.setAlumno(alumno1);
        crearAlumnoController.setAlumno(alumno2);
        crearAlumnoController.setAlumno(alumno3);

        // Notificar a VerEstadoController que actualice la lista
        verEstadoController.cargarListaAlumnos();


        // Precargas materias
        Materia materia1 = new Materia("Algebra",1,true,false);
        Materia materia2 = new Materia("Elementos de informatica",1,true,true);
        Materia materia3 = new Materia("Expresión de Problemas y Algoritmos",1,true,true);

        crearMateriaController.setMateria(materia1);
        crearMateriaController.setMateria(materia2);
        crearMateriaController.setMateria(materia3);

        crearMateriaView.actualizarCorrelativas(crearMateriaController.getListaMaterias());
        crearPlanView.actualizarMaterias(crearMateriaController.getListaMaterias());






        // Crear y devolver la ventana principal
        return new Principal(
                homeView,
                crearAlumnoView,
                verEstadoView,
                crearMateriaView,
                crearPlanView,
                crearCarreraView,
                inscribirAlumnoView,
                inscribirMateriaView,
                agregarNotasView,
                crearAlumnoController,
                verEstadoController,
                crearMateriaController,
                crearPlanEstudioController
        );
    }
}
