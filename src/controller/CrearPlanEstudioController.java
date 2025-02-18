package controller;

import model.*;
import model.Planes.*;
import view.CrearPlanView;
import view.viewUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class CrearPlanEstudioController implements MateriaListener{
        private  List<PlanEstudio> listaPlanes;
        private  CrearMateriaController crearMateriaController;
        private List<PlanEstudioListener> listeners; // Lista de listeners
        private  CrearPlanView view;


        public CrearPlanEstudioController(CrearPlanView view ,CrearMateriaController crearMateriaController){
                this.view = view;
                this.listaPlanes = new ArrayList<>();
                this.crearMateriaController = crearMateriaController;
                this.listeners = new ArrayList<>();

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
                actualizarMateriasDisponibles();
        }

        public void crearPlan(){
                String nombre = view.getTextNombre();
                String tipoPlanSeleccionado = view.getTipoPlan();

                if (nombre.isEmpty()){
                        viewUtils.showScaledMessageDialog(null,"El nombre no puede estar vacio","Error",JOptionPane.ERROR_MESSAGE);
                        return;
                }

                // Determinar qué estrategia de plan usar
                PlanStrategy estrategia;
                switch (tipoPlanSeleccionado) {
                        case "Plan A":
                                estrategia = new PlanA();
                                break;
                        case "Plan B":
                                estrategia = new PlanB();
                                break;
                        case "Plan C":
                                estrategia = new PlanC();
                                break;
                        case "Plan D":
                                estrategia = new PlanD();
                                break;
                        case "Plan E":
                                estrategia = new PlanE();
                                break;
                        default:
                                viewUtils.showScaledMessageDialog(null, "Debe seleccionar un tipo de plan válido", "Error", JOptionPane.ERROR_MESSAGE);
                                return;
                }

                // Crear el plan de estudio con la estrategia seleccionada
                PlanEstudio planEstudio = new PlanEstudio(nombre, estrategia);

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

                for (PlanEstudioListener listener : listeners) {
                        listener.planEstudioCreado(planEstudio);
                }

                view.limpiarCampos();

                actualizarMateriasDisponibles();

                viewUtils.showScaledMessageDialog(null, "Plan creado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                view.resetCombos();

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

        public void addPlanEstudioListener(PlanEstudioListener listener) {
                listeners.add(listener);
        }

        public List<PlanEstudio> getListaPlanes() {
                return listaPlanes;
        }
}