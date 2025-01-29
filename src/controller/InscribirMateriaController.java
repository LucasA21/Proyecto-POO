package controller;

import model.*;
import view.InscribirMateriaView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class InscribirMateriaController implements AlumnoListener, MateriaListener {

    private InscribirMateriaView view;
    private List<Alumno> listaAlumnos;
    private List<Materia> materiasDisponibles;

    public InscribirMateriaController(InscribirMateriaView view, CrearAlumnoController crearAlumnoController, CrearMateriaController crearMateriaController) {
        this.view = view;
        this.listaAlumnos = crearAlumnoController.getListaAlumnos();
        this.materiasDisponibles = crearMateriaController.getListaMaterias();

        // Registrar listeners
        crearAlumnoController.addAlumnoListener(this);
        crearMateriaController.addMateriaListener(this);

        // Actualizar combos al iniciar
        actualizarComboAlumnos();
        actualizarComboMaterias();

        // Configurar botón de enviar
        this.view.getBtnEnviar().addActionListener((ActionEvent e) -> inscribirMateria());

        // Listener para actualizar materias al seleccionar un alumno
        this.view.getComboAlumno().addActionListener((ActionEvent e) -> actualizarMateriasParaAlumnoSeleccionado());
    }

    public void actualizarComboAlumnos() {
        JComboBox<Alumno> comboAlumno = view.getComboAlumno();
        comboAlumno.removeAllItems(); // Limpia el combo box
        for (Alumno alumno : listaAlumnos) {
            comboAlumno.addItem(alumno); // Agrega los objetos Alumno directamente
        }
    }


    private void actualizarMateriasParaAlumnoSeleccionado() {
        int indiceSeleccionado = view.getComboAlumno().getSelectedIndex();
        if (indiceSeleccionado < 0 || indiceSeleccionado >= listaAlumnos.size()) {
            return;
        }

        Alumno alumnoSeleccionado = listaAlumnos.get(indiceSeleccionado);

        if (alumnoSeleccionado.getCarrera() != null) {
            PlanEstudio planEstudio = alumnoSeleccionado.getCarrera().getPlanEstudio();
            if (planEstudio != null) {
                materiasDisponibles = planEstudio.getMateriasDisponiblesParaAlumno(alumnoSeleccionado);
            } else {
                System.err.println("El alumno no tiene un plan de estudios asignado.");
                materiasDisponibles = new ArrayList<>();
            }
        } else {
            System.err.println("El alumno no tiene una carrera asignada.");
            materiasDisponibles = new ArrayList<>();
        }

        view.actualizarMaterias(materiasDisponibles);
    }


    public void actualizarComboMaterias() {
        JComboBox<Materia> comboMateria = view.getComboMateria();
        comboMateria.removeAllItems(); // Limpia el combo box

        for (Materia materia : materiasDisponibles) {
            comboMateria.addItem(materia); // Agrega las materias al combo
        }
    }


    private void inscribirMateria() {
        int indiceAlumno = view.getComboAlumno().getSelectedIndex();
        if (indiceAlumno < 0 || indiceAlumno >= listaAlumnos.size()) {
            JOptionPane.showMessageDialog(view, "Debe seleccionar un alumno.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Alumno alumnoSeleccionado = listaAlumnos.get(indiceAlumno);

        Materia materiaSeleccionada = (Materia) view.getComboMateria().getSelectedItem();
        if (materiaSeleccionada == null) {
            JOptionPane.showMessageDialog(view, "Debe seleccionar una materia.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        AlumnoMateria alumnoMateria = alumnoSeleccionado.getHistorialAcademico()
                .stream()
                .filter(am -> am.getMateria().equals(materiaSeleccionada))
                .findFirst()
                .orElse(null);

        if (alumnoMateria == null) {
            alumnoMateria = new AlumnoMateria(materiaSeleccionada);
            alumnoSeleccionado.getHistorialAcademico().add(alumnoMateria);

            JOptionPane.showMessageDialog(view, "Inscripción realizada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(view, "El alumno ya está inscrito en esta materia.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        actualizarMateriasParaAlumnoSeleccionado();
    }


    @Override
    public void alumnoAgregado(Alumno alumno) {
        System.out.println("Alumno agregado: " + alumno.getNombre());
        if (!listaAlumnos.contains(alumno)) {
            listaAlumnos.add(alumno);
            actualizarComboAlumnos();
        }
    }


    @Override
    public void materiaAgregada(Materia materia) {
        if (!materiasDisponibles.contains(materia)) {
            materiasDisponibles.add(materia);
        }
        view.actualizarMaterias(materiasDisponibles); // Refleja el cambio en el combo box
    }

}
