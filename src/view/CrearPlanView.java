package view;

import model.Materia;
import model.TipoPlan;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CrearPlanView extends JPanel {

    private JTextField textNombre;
    private JComboBox<TipoPlan> comboTipo;
    private JPanel panelMaterias;
    private JButton btnEnviar;

    public CrearPlanView() {
        setBackground(Color.WHITE);
        setLayout(new GridBagLayout()); // Usamos GridBagLayout para mayor control
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL; // Para que los componentes se ajusten horizontalmente
        gbc.insets = new Insets(10, 10, 10, 10); // Margen entre componentes

        Font generalFont = viewUtils.getScaledFont(new Font("Arial", Font.PLAIN, 14), 0.015);

        // Etiqueta y campo de texto para Nombre
        JLabel labelNombre = new JLabel("Nombre:");
        labelNombre.setFont(generalFont);
        textNombre = new JTextField();
        textNombre.setFont(generalFont);
        textNombre.setPreferredSize(viewUtils.getProportionalSize(0.3, 0.05)); // Ajustamos el tamaño dinámico

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
        labelTipo.setFont(generalFont);
        comboTipo = new JComboBox<>(TipoPlan.values());
        comboTipo.setFont(generalFont);

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
        labelMaterias.setFont(generalFont);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0;
        add(labelMaterias, gbc);

        // Panel para las materias
        panelMaterias = new JPanel();
        panelMaterias.setLayout(new BoxLayout(panelMaterias, BoxLayout.Y_AXIS));
        panelMaterias.setBackground(Color.WHITE);

        // Hacer el panel desplazable
        JScrollPane scrollPane = new JScrollPane(panelMaterias);
        scrollPane.setPreferredSize(viewUtils.getProportionalSize(0.3, 0.2)); // Ajustamos el tamaño preferido del JScrollPane

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.BOTH; // Para que el JScrollPane ocupe todo el espacio disponible
        gbc.weighty = 1.0; // Permitir que el JScrollPane se expanda verticalmente si hay espacio
        add(scrollPane, gbc);

        // Botón de enviar
        btnEnviar = new JButton("Enviar");
        btnEnviar.setFont(viewUtils.getScaledFont(new Font("Arial", Font.BOLD, 14), 0.015));
        btnEnviar.setPreferredSize(viewUtils.getProportionalSize(0.1, 0.04)); // Ajustamos el tamaño dinámico

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2; // Ocupa dos columnas
        gbc.fill = GridBagConstraints.NONE; // No expandir el botón
        gbc.anchor = GridBagConstraints.CENTER; // Centrar el botón
        gbc.weightx = 0;
        gbc.weighty = 0;
        add(btnEnviar, gbc);
    }

    public void actualizarMaterias(List<Materia> materiasDisponibles) {
        panelMaterias.removeAll();

        Font checkFont = viewUtils.getScaledFont(new Font("Arial", Font.PLAIN, 14), 0.015);
        for (Materia materia : materiasDisponibles) {
            JCheckBox checkBox = new JCheckBox(materia.getNombre());
            checkBox.setFont(checkFont);
            checkBox.setBackground(Color.WHITE);

            // Opcional: Cambiar el tamaño del ícono del `JCheckBox`
            Icon emptyIcon = new ImageIcon(new ImageIcon("assets/icons/checkbox_empty.png")
                    .getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
            Icon filledIcon = new ImageIcon(new ImageIcon("assets/icons/checkbox_filled.png")
                    .getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
            checkBox.setIcon(emptyIcon);
            checkBox.setSelectedIcon(filledIcon);

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

    public TipoPlan getTipoPlan() {
        return (TipoPlan) comboTipo.getSelectedItem();
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
