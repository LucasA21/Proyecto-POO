package controller;

import model.Alumno;
import model.AlumnoListener;
import view.CrearAlumnoView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class CrearAlumnoController {
    private CrearAlumnoView view;
    private List<Alumno> listaAlumnos;
    private List<AlumnoListener> listeners;
    private VerEstadoController verEstadoController;
    private InscribirAlumnoController inscribirAlumnoController;
    private InscribirMateriaController inscribirMateriaController;

    public CrearAlumnoController(CrearAlumnoView view) {
        this.view = view;
        this.listaAlumnos = new ArrayList<>();
        this.listeners = new ArrayList<>();

        view.getBtnEnviar().addActionListener((ActionEvent e) -> crearAlumno());
    }

    public void setVerEstadoController(VerEstadoController verEstadoController) {
        this.verEstadoController = verEstadoController;
    }

    public void setInscribirAlumnoController(InscribirAlumnoController inscribirAlumnoController) {
        this.inscribirAlumnoController = inscribirAlumnoController;
    }

    public void  setInscribirMateriaController(InscribirMateriaController inscribirMateriaController){
        this.inscribirMateriaController = inscribirMateriaController;
    }

    public void setAlumno(Alumno alumno) {
        listaAlumnos.add(alumno);
    }

    public void addAlumnoListener(AlumnoListener listener) {
        if (!listeners.contains(listener)) {
            listeners.add(listener);
            System.out.println("Listener registrado: " + listener.getClass().getSimpleName());
        }
    }


    public List<Alumno> getListaAlumnos() {
        return listaAlumnos;
    }

    private void crearAlumno() {
        String dni = view.getTextDni();
        String nombre = view.getTextNombre();

        if (dni.isEmpty() || nombre.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Alumno nuevoAlumno = new Alumno(dni, nombre);
        listaAlumnos.add(nuevoAlumno);

        // Notificar a los listeners
        for (AlumnoListener listener : listeners) {
            listener.alumnoAgregado(nuevoAlumno);
        }

        // Actualizar vistas conectadas
        if (verEstadoController != null) {
            verEstadoController.cargarListaAlumnos();
        }
        if (inscribirAlumnoController != null) {
            inscribirAlumnoController.actualizarComboAlumnos();
        }
        if (inscribirMateriaController != null){
            inscribirMateriaController.actualizarComboAlumnos();
        }

        view.limpiarCampos();
        JOptionPane.showMessageDialog(null, "Alumno creado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }
}
