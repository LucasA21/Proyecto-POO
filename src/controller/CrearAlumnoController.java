package controller;

import model.Alumno;
import view.CrearAlumnoView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class CrearAlumnoController {
    private CrearAlumnoView view;
    private List<Alumno> listaAlumnos;
    private VerEstadoController verEstadoController; // Referencia al controlador de VerEstado

    public CrearAlumnoController(CrearAlumnoView view) {
        this.view = view;
        this.listaAlumnos = new ArrayList<>();

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
        view.limpiarCampos();

        // Notificar al controlador de VerEstado para actualizar la lista
        if (verEstadoController != null) {
            verEstadoController.cargarListaAlumnos();
        }

        JOptionPane.showMessageDialog(null, "Alumno creado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }

    public void setAlumno(Alumno alumno) {
        listaAlumnos.add(alumno);
    }

    public List<Alumno> getListaAlumnos() {
        return listaAlumnos;
    }
}
