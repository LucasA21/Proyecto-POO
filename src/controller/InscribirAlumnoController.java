package controller;

import model.*;
import view.InscribirAlumnoView;
import view.viewUtils;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class InscribirAlumnoController implements AlumnoListener, CarreraListener {

    private InscribirAlumnoView view;
    private List<Alumno> listaAlumnos;
    private List<Carrera> listaCarreras;
    private List<InscripcionCarrera> listaInscripciones;

    public InscribirAlumnoController(InscribirAlumnoView view,
                                     CrearAlumnoController crearAlumnoController,
                                     CrearCarreraController crearCarreraController) {
        this.view = view;
        this.listaAlumnos = crearAlumnoController.getListaAlumnos();
        this.listaCarreras = crearCarreraController.getListaCarreras();
        this.listaInscripciones = new ArrayList<>();

        crearAlumnoController.addAlumnoListener(this);
        crearCarreraController.addCarreraListener(this);

        actualizarComboAlumnos();
        actualizarComboCarreras();

        this.view.getBtnEnviar().addActionListener(e -> inscribirAlumno());
    }

    public void actualizarComboAlumnos() {
        JComboBox<Alumno> comboAlumno = view.getComboAlumno();
        comboAlumno.removeAllItems();
        for (Alumno alumno : listaAlumnos) {
            comboAlumno.addItem(alumno);
        }
    }

    public void actualizarComboCarreras() {
        JComboBox<Carrera> comboCarrera = view.getComboCarrera();
        comboCarrera.removeAllItems();
        for (Carrera carrera : listaCarreras) {
            comboCarrera.addItem(carrera);
        }
    }



    private void inscribirAlumno() {
        Alumno alumnoSeleccionado = (Alumno) view.getComboAlumno().getSelectedItem();
        Carrera carreraSeleccionada = (Carrera) view.getComboCarrera().getSelectedItem();

        if (alumnoSeleccionado == null) {
            viewUtils.showScaledMessageDialog(null, "Debe seleccionar un alumno", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (carreraSeleccionada == null) {
            viewUtils.showScaledMessageDialog(null, "Debe seleccionar una carrera", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validar si ya está inscrito
        for (InscripcionCarrera inscripcion : listaInscripciones) {
            if (inscripcion.getAlumno().equals(alumnoSeleccionado) &&
                    inscripcion.getCarrera().equals(carreraSeleccionada)) {
                viewUtils.showScaledMessageDialog(null, "El alumno ya está inscrito en esta carrera", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        // Crear inscripción
        InscripcionCarrera nuevaInscripcion = new InscripcionCarrera(alumnoSeleccionado, carreraSeleccionada);
        listaInscripciones.add(nuevaInscripcion);

        alumnoSeleccionado.setCarrera(carreraSeleccionada);

        // Imprimir el estado después de asignar la carrera
        System.out.println("Alumno " + alumnoSeleccionado.getNombre() + " inscrito en carrera: " + carreraSeleccionada.getNombre());
        System.out.println("Plan de estudio asignado: " + carreraSeleccionada.getPlanEstudio().getNombre());
        System.out.println("Materias en el plan de estudio de la carrera: " + carreraSeleccionada.getPlanEstudio().getMaterias());


        viewUtils.showScaledMessageDialog(null, "Inscripción realizada exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        view.resetCombos();
    }

    @Override
    public void alumnoAgregado(Alumno alumno) {
        if (!listaAlumnos.contains(alumno)) { // Verificar duplicados
            listaAlumnos.add(alumno);
            actualizarComboAlumnos();
        }
    }

    @Override
    public void carreraAgregada(Carrera carrera) {
        actualizarComboCarreras();
    }

}
