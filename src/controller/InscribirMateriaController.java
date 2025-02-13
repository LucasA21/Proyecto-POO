package controller;

import model.*;
import view.InscribirMateriaView;
import view.viewUtils;

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

        crearAlumnoController.addAlumnoListener(this);
        crearMateriaController.addMateriaListener(this);


        actualizarComboAlumnos();
        actualizarComboMaterias();

        this.view.getBtnEnviar().addActionListener((ActionEvent e) -> inscribirMateria());

        this.view.getComboAlumno().addActionListener((ActionEvent e) -> actualizarMateriasParaAlumnoSeleccionado());
    }

    public void actualizarComboAlumnos() {
        JComboBox<Alumno> comboAlumno = view.getComboAlumno();
        comboAlumno.removeAllItems();
        for (Alumno alumno : listaAlumnos) {
            comboAlumno.addItem(alumno);
        }
    }

    public void actualizarComboMaterias() {
        JComboBox<Materia> comboMateria = view.getComboMateria();
        comboMateria.removeAllItems();

        for (Materia materia : materiasDisponibles) {
            comboMateria.addItem(materia);
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
                List<Materia> materias = planEstudio.getMateriasDisponiblesParaAlumno(alumnoSeleccionado);

                materiasDisponibles = new ArrayList<>(materias);
            } else {
                System.err.println("El alumno no tiene un plan de estudios asignado.");
                materiasDisponibles = new ArrayList<>();
            }
        } else {
            System.err.println("El alumno no tiene una carrera asignada.");
            materiasDisponibles = new ArrayList<>();
        }

        actualizarComboMaterias();
    }




    private void inscribirMateria() {
        int indiceAlumno = view.getComboAlumno().getSelectedIndex();
        if (indiceAlumno < 0 || indiceAlumno >= listaAlumnos.size()) {
            viewUtils.showScaledMessageDialog(view, "Debe seleccionar un alumno.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Alumno alumnoSeleccionado = listaAlumnos.get(indiceAlumno);

        Materia materiaSeleccionada = (Materia) view.getComboMateria().getSelectedItem();
        if (materiaSeleccionada == null) {
            viewUtils.showScaledMessageDialog(view, "Debe seleccionar una materia.", "Error", JOptionPane.ERROR_MESSAGE);
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

            viewUtils.showScaledMessageDialog(view, "Inscripción realizada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            viewUtils.showScaledMessageDialog(view, "El alumno ya está inscrito en esta materia.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        view.resetCombos();

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
        actualizarComboMaterias();
    }

}
