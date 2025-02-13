package view;

import model.Carrera;
import model.Materia;
import model.PlanEstudio;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CrearCarreraView extends JPanel {

    private JTextField textNombre;
    private JComboBox<PlanEstudio> comboPlanes;
    private JPanel panelObligatorias;
    private JPanel panelOptativas;
    private JSpinner spinnerCantidadOptativas;
    private JButton btnCrearCarrera;

    public CrearCarreraView() {
        setBackground(Color.WHITE);
        setLayout(new GridBagLayout()); // Usamos GridBagLayout para diseño adaptable
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Margen entre componentes
        gbc.fill = GridBagConstraints.HORIZONTAL; // Alineación horizontal

        Font generalFont = viewUtils.getScaledFont(new Font("Arial", Font.PLAIN, 14), 0.015);

        // Etiqueta y campo para Nombre de la carrera
        JLabel labelNombre = new JLabel("Nombre de la Carrera:");
        labelNombre.setFont(generalFont);
        textNombre = new JTextField();
        textNombre.setFont(generalFont);
        textNombre.setPreferredSize(viewUtils.getProportionalSize(0.3, 0.05));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        add(labelNombre, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        add(textNombre, gbc);

        // Etiqueta y combo para seleccionar Plan de Estudio
        JLabel labelPlanes = new JLabel("Asignar Plan de Estudio:");
        labelPlanes.setFont(generalFont);
        comboPlanes = new JComboBox<>();
        comboPlanes.setFont(generalFont);
        comboPlanes.setPreferredSize(viewUtils.getProportionalSize(0.3, 0.05));

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        add(labelPlanes, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        add(comboPlanes, gbc);

        // Panel de materias obligatorias
        JLabel labelObligatorias = new JLabel("Materias Obligatorias:");
        labelObligatorias.setFont(generalFont); // Aplicar tamaño dinámico
        panelObligatorias = new JPanel();
        panelObligatorias.setLayout(new BoxLayout(panelObligatorias, BoxLayout.Y_AXIS));
        panelObligatorias.setBackground(Color.WHITE);
        JScrollPane scrollObligatorias = new JScrollPane(panelObligatorias);
        scrollObligatorias.setPreferredSize(viewUtils.getProportionalSize(0.3, 0.2));

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.weighty = 0;
        add(labelObligatorias, gbc);

        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 0.5;
        add(scrollObligatorias, gbc);

        // Panel de materias optativas
        JLabel labelOptativas = new JLabel("Materias Optativas:");
        labelOptativas.setFont(generalFont); // Aplicar tamaño dinámico
        panelOptativas = new JPanel();
        panelOptativas.setLayout(new BoxLayout(panelOptativas, BoxLayout.Y_AXIS));
        panelOptativas.setBackground(Color.WHITE);
        JScrollPane scrollOptativas = new JScrollPane(panelOptativas);
        scrollOptativas.setPreferredSize(viewUtils.getProportionalSize(0.3, 0.2));

        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.weighty = 0;
        add(labelOptativas, gbc);

        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 0.5;
        add(scrollOptativas, gbc);

        // Campo para cantidad mínima de materias optativas
        JLabel labelCantidadOptativas = new JLabel("Cantidad mínima de Optativas:");
        labelCantidadOptativas.setFont(generalFont); // Aplicar tamaño dinámico
        spinnerCantidadOptativas = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1)); // Valores entre 1 y 100
        spinnerCantidadOptativas.setFont(generalFont);

        gbc.gridy = 6;
        gbc.gridwidth = 1;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(labelCantidadOptativas, gbc);

        gbc.gridx = 1;
        add(spinnerCantidadOptativas, gbc);

        // Botón para crear la carrera
        btnCrearCarrera = new JButton("Crear Carrera");
        btnCrearCarrera.setFont(generalFont);
        btnCrearCarrera.setPreferredSize(viewUtils.getProportionalSize(0.15, 0.05));

        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(btnCrearCarrera, gbc);
    }

    // Getters
    public JTextField getTextNombre() {
        return textNombre;
    }

    public JButton getBtnEnviar() {
        return btnCrearCarrera;
    }

    public JComboBox<PlanEstudio> getComboTipo() {
        return comboPlanes;
    }

    public List<JCheckBox> getCheckboxObligatorias() {
        return getCheckBoxList(panelObligatorias);
    }

    public List<JCheckBox> getCheckboxOptativas() {
        return getCheckBoxList(panelOptativas);
    }

    public int getCantidadOptativasRequeridas() {
        return (int) spinnerCantidadOptativas.getValue();
    }

    public void limpiarCampos() {
        textNombre.setText("");
        comboPlanes.setSelectedIndex(0);

        for (Component c : panelObligatorias.getComponents()) {
            if (c instanceof JCheckBox) {
                ((JCheckBox) c).setSelected(false);
            }
        }

        for (Component c : panelOptativas.getComponents()) {
            if (c instanceof JCheckBox) {
                ((JCheckBox) c).setSelected(false);
            }
        }

        spinnerCantidadOptativas.setValue(1);
    }

    public void actualizarMaterias(List<Materia> materiasObligatorias, List<Materia> materiasOptativas) {
        panelObligatorias.removeAll();
        panelOptativas.removeAll();

        Font generalFont = viewUtils.getScaledFont(new Font("Arial", Font.PLAIN, 14), 0.015);

        for (Materia materia : materiasObligatorias) {
            JCheckBox checkBoxObligatoria = new JCheckBox(materia.getNombre());
            checkBoxObligatoria.setFont(generalFont); // Aplicar tamaño dinámico
            checkBoxObligatoria.setBackground(Color.WHITE);
            panelObligatorias.add(checkBoxObligatoria);
        }

        for (Materia materia : materiasOptativas) {
            JCheckBox checkBoxOptativa = new JCheckBox(materia.getNombre());
            checkBoxOptativa.setFont(generalFont); // Aplicar tamaño dinámico
            checkBoxOptativa.setBackground(Color.WHITE);
            panelOptativas.add(checkBoxOptativa);
        }

        panelObligatorias.revalidate();
        panelObligatorias.repaint();
        panelOptativas.revalidate();
        panelOptativas.repaint();
    }

    // Método para obtener los checkboxes seleccionados
    private List<JCheckBox> getCheckBoxList(JPanel panel) {
        List<JCheckBox> checkboxes = new ArrayList<>();
        for (Component c : panel.getComponents()) {
            if (c instanceof JCheckBox) {
                checkboxes.add((JCheckBox) c);
            }
        }
        return checkboxes;
    }

    public void actualizarPlanes(List<PlanEstudio> planesDisponibles) {
        comboPlanes.removeAllItems(); // Limpia los elementos anteriores
        for (PlanEstudio plan : planesDisponibles) {
            comboPlanes.addItem(plan); // Agrega los planes de estudio disponibles
        }
    }

    public void resetCombos() {
        comboPlanes.setSelectedItem(null);
    }



}
