package controller;

import model.Alumno;
import model.AlumnoListener;
import view.CrearAlumnoView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class CrearAlumnoController {
    private CrearAlumnoView view;
    private List<Alumno> listaAlumnos;
    private List<AlumnoListener> listeners;
    private VerEstadoController verEstadoController;
    private  InscribirAlumnoController inscribirAlumnoController;

    public CrearAlumnoController(CrearAlumnoView view) {
        this.view = view;
        this.listaAlumnos = new ArrayList<>();
        this.listeners = new ArrayList<>();


        view.getBtnEnviar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crearAlumno();
            }
        });
    }

    public void setVerEstadoController(VerEstadoController verEstadoController) {
        this.verEstadoController = verEstadoController; // Configurar el controlador de VerEstado
    }

    private void crearAlumno() {
        String dni = view.getTextDni();
        String nombre = view.getTextNombre();

        Alumno nuevoAlumno = new Alumno(dni, nombre);
        listaAlumnos.add(nuevoAlumno);

        // Notificar a los listeners
        for (AlumnoListener listener : listeners) {
            listener.alumnoAgregado(nuevoAlumno);
        }

        // Notificar al controlador de VerEstado para actualizar la lista
        if (verEstadoController != null) {
            verEstadoController.cargarListaAlumnos();
        }

        if (inscribirAlumnoController != null) {
            inscribirAlumnoController.actualizarComboAlumnos();
        }

        view.limpiarCampos();

        JOptionPane.showMessageDialog(null, "Alumno creado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }

    public void setAlumno(Alumno alumno) {
        listaAlumnos.add(alumno);
    }

    public void addAlumnoListener(AlumnoListener listener) {
        listeners.add(listener);
    }

    public void setInscribirAlumnoController(InscribirAlumnoController inscribirAlumnoController) {
        this.inscribirAlumnoController = inscribirAlumnoController;
    }

    public List<Alumno> getListaAlumnos() {
        return listaAlumnos;
    }
}
