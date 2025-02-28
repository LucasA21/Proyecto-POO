package config;

import controller.*;
import model.*;
import model.Planes.PlanA;
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
        CrearCarreraController crearCarreraController = new CrearCarreraController(crearCarreraView, crearPlanEstudioController);
        InscribirAlumnoController inscribirAlumnoController = new InscribirAlumnoController(inscribirAlumnoView, crearAlumnoController, crearCarreraController);
        InscribirMateriaController inscribirMateriaController = new InscribirMateriaController(inscribirMateriaView, crearAlumnoController,crearMateriaController);
        AgregarNotasController agregarNotasController = new AgregarNotasController(agregarNotasView, crearAlumnoController);



        // Conectar controladores
        crearAlumnoController.setVerEstadoController(verEstadoController);
        crearAlumnoController.setInscribirAlumnoController(inscribirAlumnoController);
        crearAlumnoController.setInscribirMateriaController(inscribirMateriaController);
        crearAlumnoController.setAgregarNotasController(agregarNotasController);
        crearCarreraController.setInscribirAlumnoController(inscribirAlumnoController);
        crearAlumnoController.addAlumnoListener(inscribirMateriaController);
        crearAlumnoController.addAlumnoListener(inscribirAlumnoController);
        crearMateriaController.addMateriaListener(inscribirMateriaController);
        crearMateriaController.setInscribirMateriaController(inscribirMateriaController);
        crearAlumnoController.addAlumnoListener(agregarNotasController);

        // Precargar alumnos
        Alumno alumno1 = new Alumno("41951221", "Lucas Araya");
        Alumno alumno2 = new Alumno("43952231", "Abril Gonzalez");
        Alumno alumno3 = new Alumno("39721121", "Silvina Faraoni");


        crearAlumnoController.setAlumno(alumno1);
        crearAlumnoController.setAlumno(alumno2);
        crearAlumnoController.setAlumno(alumno3);


        // Actualizar las vistas correspondientes
        verEstadoController.cargarListaAlumnos();
        inscribirAlumnoController.actualizarComboAlumnos();
        inscribirMateriaController.actualizarComboAlumnos();
        agregarNotasController.actualizarComboAlumnos();

        // Precargas materias
        Materia materia1 = new Materia("Algebra", 1, true, false);
        Materia materia2 = new Materia("Elementos de informática", 1, true, true);
        Materia materia3 = new Materia("Expresión de Problemas y Algoritmos", 1, true, true);
        Materia materia4 = new Materia("Inglés", 1, false, true);
        Materia materia5 = new Materia("Seminario 1", 1, false, true);

        // Configurar correlativas
        materia3.agregarCorrelativas(materia2); // Ejemplo: "Expresión de Problemas" depende de "Elementos de Informática"

        crearMateriaController.setMateria(materia1);
        crearMateriaController.setMateria(materia2);
        crearMateriaController.setMateria(materia3);
        crearMateriaController.setMateria(materia4);
        crearMateriaController.setMateria(materia5);

        crearMateriaView.actualizarCorrelativas(crearMateriaController.getListaMaterias());
        crearPlanView.actualizarMaterias(crearMateriaController.getListaMaterias());

        // Precargar plan de estudio
        crearPlanEstudioController.addPlanEstudioListener(crearCarreraController);
        PlanEstudio planEstudio1 = new PlanEstudio("Plan Informática 2025 - (PLAN A)", new PlanA());
        planEstudio1.agregarMateria(materia1);
        planEstudio1.agregarMateria(materia2);
        planEstudio1.agregarMateria(materia3);
        planEstudio1.agregarMateria(materia4);
        planEstudio1.agregarMateria(materia5);


        if (!crearPlanEstudioController.getListaPlanes().contains(planEstudio1)) {
            crearPlanEstudioController.getListaPlanes().add(planEstudio1);
        }


        // Precargar carrera
        crearCarreraController.addCarreraListener(inscribirAlumnoController);
        Carrera carrera1 = new Carrera("Analista en Sistemas - (PLAN A)", planEstudio1, 2); // 2 optativas requeridas
        carrera1.agregarMateriaObligatoria(materia1);
        carrera1.agregarMateriaObligatoria(materia2);
        carrera1.agregarMateriaObligatoria(materia3);
        carrera1.agregarMateriaOptativa(materia4);
        carrera1.agregarMateriaOptativa(materia5);

        crearCarreraController.getListaCarreras().add(carrera1); // Agregar la carrera
        crearCarreraController.planEstudioCreado(planEstudio1);


        // Actualizar vistas con datos precargados
        inscribirAlumnoController.actualizarComboCarreras();


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
                crearPlanEstudioController,
                crearCarreraController,
                inscribirAlumnoController,
                inscribirMateriaController
        );
    }
}
