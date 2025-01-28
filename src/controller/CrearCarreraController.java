package controller;

import model.*;
import view.CrearCarreraView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class CrearCarreraController implements PlanEstudioListener {

    private CrearCarreraView view;
    private List<Carrera> listaCarreras;
    private List<PlanEstudio> listaPlanesEstudio;
    private List<CarreraListener> listeners;
    private InscribirAlumnoController inscribirAlumnoController;

    public CrearCarreraController(CrearCarreraView view, CrearPlanEstudioController crearPlanEstudioController) {
        this.view = view;
        this.listaCarreras = new ArrayList<>();
        this.listaPlanesEstudio = crearPlanEstudioController.getListaPlanes();
        this.listeners = new ArrayList<>();

        // Registrar como listener de planes de estudio
        crearPlanEstudioController.addPlanEstudioListener(this);

        // Agregar listener al combo para actualizar materias al seleccionar un plan
        this.view.getComboTipo().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PlanEstudio planSeleccionado = (PlanEstudio) view.getComboTipo().getSelectedItem();
                if (planSeleccionado != null) {
                    actualizarMaterias(planSeleccionado);
                }
            }
        });

        // Botón para crear carrera
        this.view.getBtnEnviar().addActionListener((ActionEvent e) -> crearCarrera());
    }

    private void actualizarComboPlanes() {
        JComboBox<PlanEstudio> comboPlanes = view.getComboTipo();
        comboPlanes.removeAllItems(); // Limpia los elementos previos en el combo box

        // Añadir planes de estudio evitando duplicados
        for (PlanEstudio plan : listaPlanesEstudio) {
            comboPlanes.addItem(plan); // Agrega solo una vez cada plan
        }

        view.actualizarPlanes(listaPlanesEstudio);
    }

    private void actualizarMaterias(PlanEstudio planSeleccionado) {
        // Filtrar las materias del plan en obligatorias y optativas usando el booleano esObligatoria
        List<Materia> materiasObligatorias = new ArrayList<>();
        List<Materia> materiasOptativas = new ArrayList<>();

        for (Materia materia : planSeleccionado.getMaterias()) {
            if (materia.esObligatoria()) {
                materiasObligatorias.add(materia);
            } else {
                materiasOptativas.add(materia);
            }
        }

        // Actualizar la vista con las materias separadas
        view.actualizarMaterias(materiasObligatorias, materiasOptativas);
    }

    public void crearCarrera() {
        String nombreCarrera = view.getTextNombre().getText();

        if (nombreCarrera.isEmpty()) {
            JOptionPane.showMessageDialog(null, "El nombre de la carrera no puede estar vacío", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        PlanEstudio planSeleccionado = (PlanEstudio) view.getComboTipo().getSelectedItem();
        if (planSeleccionado == null) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un plan de estudio", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Carrera nuevaCarrera = new Carrera(nombreCarrera, planSeleccionado, view.getCantidadOptativasRequeridas());

        // Agregar materias obligatorias seleccionadas
        for (JCheckBox checkBox : view.getCheckboxObligatorias()) {
            if (checkBox.isSelected()) {
                Materia materia = buscarMateria(planSeleccionado.getMaterias(), checkBox.getText());
                if (materia != null) {
                    nuevaCarrera.agregarMateriaObligatoria(materia);
                }
            }
        }

        // Agregar materias optativas seleccionadas
        for (JCheckBox checkBox : view.getCheckboxOptativas()) {
            if (checkBox.isSelected()) {
                Materia materia = buscarMateria(planSeleccionado.getMaterias(), checkBox.getText());
                if (materia != null) {
                    nuevaCarrera.agregarMateriaOptativa(materia);
                }
            }
        }

        listaCarreras.add(nuevaCarrera);


        for (CarreraListener listener : listeners) {
            listener.carreraAgregada(nuevaCarrera);
        }

        view.limpiarCampos();

        JOptionPane.showMessageDialog(null, "Carrera creada exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }

    private Materia buscarMateria(List<Materia> materias, String nombreMateria) {
        for (Materia materia : materias) {
            if (materia.getNombre().equalsIgnoreCase(nombreMateria)) {
                return materia;
            }
        }
        return null; // Retorna null si no encuentra la materia
    }

    @Override
    public void planEstudioCreado(PlanEstudio planEstudio) {
        if (!listaPlanesEstudio.contains(planEstudio)) { // Evita duplicados
            listaPlanesEstudio.add(planEstudio);
        }
        actualizarComboPlanes();
    }

    public void addCarreraListener(CarreraListener listener) {
        listeners.add(listener);
    }

    public List<Carrera> getListaCarreras() {
        return listaCarreras;
    }

    public void setInscribirAlumnoController(InscribirAlumnoController inscribirAlumnoController) {
        this.inscribirAlumnoController = inscribirAlumnoController;
    }

}
