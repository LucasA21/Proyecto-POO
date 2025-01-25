package controller;

import model.Alumno;
import view.VerEstadoView;

import javax.swing.*;
import java.util.List;

public class VerEstadoController {

    private CrearAlumnoController crearAlumnoController;
    private VerEstadoView verEstadoView;

    public VerEstadoController(VerEstadoView verEstadoView, CrearAlumnoController crearAlumnoController) {
        this.verEstadoView = verEstadoView;
        this.crearAlumnoController = crearAlumnoController;

        // Cargar la lista de alumnos en el ComboBox
        cargarListaAlumnos();

        // Agregar listener para el botón enviar
        verEstadoView.getBtnEnviar().addActionListener(e -> mostrarInformacionAlumno());
    }

    public void cargarListaAlumnos() {
        List<Alumno> listaAlumnos = crearAlumnoController.getListaAlumnos();
        JComboBox<Alumno> comboAlumno = verEstadoView.getComboAlumno();
        comboAlumno.removeAllItems(); // Limpiar cualquier ítem previo
        for (Alumno alumno : listaAlumnos) {
            comboAlumno.addItem(alumno); // Agregar los alumnos al ComboBox
        }
    }

    private void mostrarInformacionAlumno() {
        Alumno alumnoSeleccionado = (Alumno) verEstadoView.getComboAlumno().getSelectedItem();
        if (alumnoSeleccionado != null) {
            // Crear la información para mostrar
            String info = "Nombre: " + alumnoSeleccionado.getNombre() + "\n" +
                    "DNI: " + alumnoSeleccionado.getDni();
            // Actualizar el JTextArea con la información del alumno
            verEstadoView.setInformacionAlumno(info);
        }
    }
}
