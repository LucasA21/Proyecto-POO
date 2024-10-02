package controller;

import model.Materia;
import view.CrearMateriaView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class CrearMateriaController {
    private CrearMateriaView view;
    private List<Materia> listaMaterias;

    public CrearMateriaController(CrearMateriaView view){
        this.view = view;
        this.listaMaterias = new ArrayList<>();

        this.view.getBtnEnviar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crearMateria();
            }
        });
    }

    public void crearMateria(){
        String nombre = view.getTextNombre();
        String cuatrimestre = view.getTextCuatrimestre();
        boolean esObligatoria = view.getCheckObligatoria().isSelected();
        boolean esPromocionable = view.getCheckPromocion().isSelected();

        Materia nuevaMateria = new Materia(nombre,cuatrimestre,esObligatoria,esPromocionable);

        if (nombre.isEmpty()){
            JOptionPane.showMessageDialog(null,"El nombre no puede estar vacio","Error",JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (cuatrimestre.isEmpty()){
            JOptionPane.showMessageDialog(null,"El nombre no puede estar vacio","Error",JOptionPane.ERROR_MESSAGE);
            return;
        }

        List<Materia> corellativasSeleccionadas = new ArrayList<>();
        for (JCheckBox checkBox : view.getCheckboxCorrelativas()) {
            if (checkBox.isSelected()){
                Materia correlativa = buscarMateria(checkBox.getText());
                if (correlativa != null){
                    corellativasSeleccionadas.add(correlativa);
                }
            }
        }

        for (Materia correlativa: corellativasSeleccionadas) {
            nuevaMateria.agregarCorrelativas(correlativa);
        }

        listaMaterias.add(nuevaMateria);

        view.actualizarCorrelativas(listaMaterias);

        view.limpiarCampos();

        JOptionPane.showMessageDialog(null, "Materia creada exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }

    private Materia buscarMateria(String nombre){
        for (Materia materia : listaMaterias){
            if (materia.getNombre().equalsIgnoreCase(nombre)) {
                return materia;
            }
        }
        return null;
    }

}
