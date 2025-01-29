package controller;

import model.Materia;
import model.MateriaListener;
import view.CrearMateriaView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class CrearMateriaController {
    private CrearMateriaView view;
    private List<Materia> listaMaterias;
    private List<MateriaListener> listeners;
    private InscribirMateriaController inscribirMateriaController;

    public CrearMateriaController(CrearMateriaView view) {
        this.view = view;
        this.listaMaterias = new ArrayList<>();
        this.listeners = new ArrayList<>();

        this.view.getBtnEnviar().addActionListener((ActionEvent e) -> crearMateria());
    }

    public void setMateria(Materia materia) {
        listaMaterias.add(materia);
    }

    public void addMateriaListener(MateriaListener listener) {
        listeners.add(listener);
    }

    public List<Materia> getListaMaterias() {
        return listaMaterias;
    }

    public void setInscribirMateriaController(InscribirMateriaController inscribirMateriaController) {
        this.inscribirMateriaController = inscribirMateriaController;
    }

    private void crearMateria() {
        String nombre = view.getTextNombre();
        String cuatrimestreStr = view.getTextCuatrimestre();
        boolean esObligatoria = view.getCheckObligatoria().isSelected();
        boolean esPromocionable = view.getCheckPromocion().isSelected();

        if (nombre.isEmpty() || cuatrimestreStr.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int cuatrimestre;
        try {
            cuatrimestre = Integer.parseInt(cuatrimestreStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El cuatrimestre debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Materia nuevaMateria = new Materia(nombre, cuatrimestre, esObligatoria, esPromocionable);

        // Agregar correlativas seleccionadas
        for (JCheckBox checkBox : view.getCheckboxCorrelativas()) {
            if (checkBox.isSelected()) {
                Materia correlativa = buscarMateria(checkBox.getText());
                if (correlativa != null) {
                    nuevaMateria.agregarCorrelativas(correlativa);
                }
            }
        }

        listaMaterias.add(nuevaMateria);

        // Notificar a los listeners
        for (MateriaListener listener : listeners) {
            listener.materiaAgregada(nuevaMateria);
        }

        if (inscribirMateriaController != null) {
            inscribirMateriaController.actualizarComboMaterias();
        }

        view.actualizarCorrelativas(listaMaterias);
        view.limpiarCampos();
        JOptionPane.showMessageDialog(null, "Materia creada exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }

    private Materia buscarMateria(String nombre) {
        return listaMaterias.stream()
                .filter(m -> m.getNombre().equalsIgnoreCase(nombre))
                .findFirst()
                .orElse(null);
    }
}
