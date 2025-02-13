package controller;

import model.*;
import view.AgregarNotasView;
import view.viewUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class AgregarNotasController implements AlumnoListener {

    private AgregarNotasView view;
    private List<Alumno> listaAlumnos;

    public AgregarNotasController(AgregarNotasView view, CrearAlumnoController crearAlumnoController) {
        this.view = view;
        this.listaAlumnos = crearAlumnoController.getListaAlumnos();

        crearAlumnoController.addAlumnoListener(this);

        actualizarComboAlumnos();

        this.view.getComboAlumno().addActionListener(e -> actualizarComboMaterias());

        this.view.getBtnEnviar().addActionListener((ActionEvent e) -> agregarNota());
    }

    public void actualizarComboAlumnos() {
        JComboBox<Alumno> comboAlumno = view.getComboAlumno();
        comboAlumno.removeAllItems();
        for (Alumno alumno : listaAlumnos) {
            comboAlumno.addItem(alumno);
        }
    }

    public void actualizarComboMaterias() {
        Alumno alumnoSeleccionado = (Alumno) view.getComboAlumno().getSelectedItem();
        if (alumnoSeleccionado == null) {
            return;
        }

        List<AlumnoMateria> materiasAlumno = alumnoSeleccionado.getHistorialAcademico();
        JComboBox<Materia> comboMateria = view.getComboMateria();
        comboMateria.removeAllItems();

        for (AlumnoMateria alumnoMateria : materiasAlumno) {
            comboMateria.addItem(alumnoMateria.getMateria());
        }
    }

    private void agregarNota() {
        Alumno alumnoSeleccionado = (Alumno) view.getComboAlumno().getSelectedItem();
        Materia materiaSeleccionada = (Materia) view.getComboMateria().getSelectedItem();
        String tipoNota = (String) view.getComboTipoNota().getSelectedItem();
        String notaStr = (String) view.getComboNota().getSelectedItem();

        if (alumnoSeleccionado == null || materiaSeleccionada == null) {
            viewUtils.showScaledMessageDialog(view, "Debe seleccionar un alumno y una materia.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (tipoNota == null || notaStr == null) {
            viewUtils.showScaledMessageDialog(view, "Debe seleccionar el tipo de nota y una calificación válida.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int nota;
        try {
            nota = Integer.parseInt(notaStr);
        } catch (NumberFormatException e) {
            viewUtils.showScaledMessageDialog(view, "La nota debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        AlumnoMateria alumnoMateria = alumnoSeleccionado.getAlumnoMateria(materiaSeleccionada);

        if (alumnoMateria == null) {
            viewUtils.showScaledMessageDialog(view, "El alumno no está inscrito en esta materia.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        System.out.println("Registrando nota... " + tipoNota + " para " + materiaSeleccionada.getNombre() + " - Alumno: " + alumnoSeleccionado.getNombre());

        if ("Cursada".equals(tipoNota)) {
            alumnoMateria.aprobarCursada(nota);
            viewUtils.showScaledMessageDialog(view, "Nota de cursada registrada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else if ("Final".equals(tipoNota)) {
            if (!alumnoMateria.isAproboCursada()) {
                viewUtils.showScaledMessageDialog(view, "El alumno no puede rendir final sin aprobar la cursada.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (alumnoMateria.isAproboFinal()) {
                viewUtils.showScaledMessageDialog(view, "El alumno ya aprobó el final de esta materia.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (materiaSeleccionada.esPromocionable() && alumnoMateria.getNotaCursada() >= 7) {
                viewUtils.showScaledMessageDialog(view, "No es necesario registrar el final, la materia ya fue promocionada.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            alumnoMateria.aprobarFinal(nota);
            viewUtils.showScaledMessageDialog(view, "Nota de final registrada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            view.resetCombos();
        }


        actualizarComboMaterias();
    }


    @Override
    public void alumnoAgregado(Alumno alumno) {
        if (!listaAlumnos.contains(alumno)) {
            listaAlumnos.add(alumno);
            actualizarComboAlumnos();
        }
    }
}
