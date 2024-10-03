package controller;

import model.Materia;
import model.MateriaListener;
import model.PlanEstudio;
import model.TipoPLan;
import view.CrearPlanView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class CrearPlanEstudioController implements MateriaListener{
        private  List<PlanEstudio> listaPlanes;
        private  CrearMateriaController crearMateriaController;
        private  CrearPlanView view;


        public CrearPlanEstudioController(CrearPlanView view ,CrearMateriaController crearMateriaController){
                this.view = view;
                this.listaPlanes = new ArrayList<>();
                this.crearMateriaController = crearMateriaController;

                crearMateriaController.addMateriaListener(this);
                actualizarMateriasDisponibles();

                this.view.getBtnEnviar().addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                crearPlan();
                        }
                });
        }

        public void actualizarMateriasDisponibles(){
                List<Materia> listaMaterias = crearMateriaController.getListaMaterias();
                view.actualizarMaterias(listaMaterias);
        }

        @Override
        public void materiaAgregada(Materia materia) {
                actualizarMateriasDisponibles();  // Actualiza la lista de materias disponibles
        }

        public void crearPlan(){
                String nombre = view.getTextNombre();
                TipoPLan tipoPLan = view.getTipoPlan();

                if (nombre.isEmpty()){
                        JOptionPane.showMessageDialog(null,"El nombre no puede estar vacio","Error",JOptionPane.ERROR_MESSAGE);
                        return;
                }

                PlanEstudio planEstudio = new PlanEstudio(nombre,tipoPLan);

                List<Materia> materiasSeleccionadas = new ArrayList<>();
                for (JCheckBox checkBox: view.getCheckboxMaterias()){
                        if (checkBox.isSelected()){
                                Materia materia = buscarMateria(checkBox.getText());
                                if (materia != null){
                                        materiasSeleccionadas.add(materia);
                                }
                        }
                }

                for (Materia materia: materiasSeleccionadas){
                        planEstudio.agregarMateria(materia);
                }

                listaPlanes.add(planEstudio);

                view.limpiarCampos();

                actualizarMateriasDisponibles();

                JOptionPane.showMessageDialog(null, "Plan creado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);

        }

        private Materia buscarMateria(String nombre){
                List<Materia> listaMaterias = crearMateriaController.getListaMaterias();
                for (Materia materia: listaMaterias) {
                        if (materia.getNombre().equalsIgnoreCase(nombre)){
                                return materia;
                        }
                }
                return null;
        }

}