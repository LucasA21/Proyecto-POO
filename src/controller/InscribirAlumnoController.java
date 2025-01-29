package controller;

import model.*;
import view.InscribirAlumnoView;

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


        // Registrar listeners
        crearAlumnoController.addAlumnoListener(this);
        crearCarreraController.addCarreraListener(this);

        // Actualizar combos
        actualizarComboAlumnos();
        actualizarComboCarreras();

        // Agregar acción al botón de enviar
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
            JOptionPane.showMessageDialog(null, "Debe seleccionar un alumno", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (carreraSeleccionada == null) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una carrera", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validar si ya está inscrito
        for (InscripcionCarrera inscripcion : listaInscripciones) {
            if (inscripcion.getAlumno().equals(alumnoSeleccionado) &&
                    inscripcion.getCarrera().equals(carreraSeleccionada)) {
                JOptionPane.showMessageDialog(null, "El alumno ya está inscrito en esta carrera", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        // Crear inscripción
        InscripcionCarrera nuevaInscripcion = new InscripcionCarrera(alumnoSeleccionado, carreraSeleccionada);
        listaInscripciones.add(nuevaInscripcion);

        alumnoSeleccionado.setCarrera(carreraSeleccionada);

        JOptionPane.showMessageDialog(null, "Inscripción realizada exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
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


    public List<InscripcionCarrera> getListaInscripciones() {
        return listaInscripciones;
    }
}
