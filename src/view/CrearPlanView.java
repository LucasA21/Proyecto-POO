package view;

import model.Materia;
import model.TipoPLan;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CrearPlanView extends JPanel {

    JTextField textNombre;
    JComboBox comboTipo;
    JPanel panelMaterias;
    JButton btnEnviar;

    public CrearPlanView() {
        setBackground(Color.WHITE);
        setLayout(new GridBagLayout()); // Usamos GridBagLayout para mayor control
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL; // Para que los componentes se ajusten horizontalmente
        gbc.insets = new Insets(10, 10, 10, 10); // Margen entre componentes

        // Etiqueta y campo de texto para Nombre
        JLabel labelNombre = new JLabel("Nombre:");
        textNombre = new JTextField();

        gbc.gridx = 0; // Columna 0
        gbc.gridy = 0; // Fila 0
        gbc.weightx = 0; // Sin expansión extra horizontal
        add(labelNombre, gbc);

        gbc.gridx = 1; // Columna 1
        gbc.gridy = 0;
        gbc.weightx = 1.0; // Expande el campo de texto horizontalmente
        add(textNombre, gbc);

        // Etiqueta y JComboBox para Tipo de plan
        JLabel labelTipo = new JLabel("Tipo de plan:");
        comboTipo = new JComboBox<>(TipoPLan.values());

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        add(labelTipo, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        add(comboTipo, gbc);

        // Etiqueta para la lista de materias
        JLabel labelMaterias = new JLabel("Materias:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0;
        add(labelMaterias, gbc);

        panelMaterias = new JPanel();
        panelMaterias.setLayout(new BoxLayout(panelMaterias, BoxLayout.Y_AXIS));

        // Hacer el panel desplazable
        JScrollPane scrollPane = new JScrollPane(panelMaterias);
        scrollPane.setPreferredSize(new Dimension(200, 100)); // Ajustamos el tamaño preferido del JScrollPane

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.BOTH; // Para que el JScrollPane ocupe todo el espacio disponible
        gbc.weighty = 1.0; // Permitir que el JScrollPane se expanda verticalmente si hay espacio
        add(scrollPane, gbc);

        // Botón de enviar
        btnEnviar = new JButton("Enviar");

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2; // Ocupa dos columnas
        gbc.fill = GridBagConstraints.NONE; // No expandir el botón
        gbc.anchor = GridBagConstraints.CENTER; // Centrar el botón
        gbc.weightx = 0;
        gbc.weighty = 0;
        add(btnEnviar, gbc);
    }

    public void actualizarMaterias(List<Materia> materiasDisponibles){
        panelMaterias.removeAll();

        for (Materia materia : materiasDisponibles) {
            JCheckBox checkBox = new JCheckBox(materia.getNombre());
            panelMaterias.add(checkBox);
        }

        panelMaterias.revalidate();
        panelMaterias.repaint();
    }

    public List<JCheckBox> getCheckboxMaterias() {
        List<JCheckBox> checkboxes = new ArrayList<>();
        for (Component c : panelMaterias.getComponents()) {
            if (c instanceof JCheckBox) {
                checkboxes.add((JCheckBox) c);
            }
        }
        return checkboxes;
    }

    public String getTextNombre() {
        return textNombre.getText();
    }

    public TipoPLan getTipoPlan(){
        return (TipoPLan) comboTipo.getSelectedItem();
    }

    public JButton getBtnEnviar() {
        return btnEnviar;
    }

    public void limpiarCampos() {
        textNombre.setText("");
        comboTipo.setSelectedIndex(0);

        for (Component c : panelMaterias.getComponents()) {
            if (c instanceof JCheckBox) {
                ((JCheckBox) c).setSelected(false);
            }
        }
    }
}
