package view;

import javax.swing.*;
import java.awt.*;

public class CrearPlanView extends JPanel {

    public CrearPlanView() {
        setBackground(Color.WHITE);
        setLayout(new GridBagLayout()); // Usamos GridBagLayout para mayor control
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL; // Para que los componentes se ajusten horizontalmente
        gbc.insets = new Insets(10, 10, 10, 10); // Margen entre componentes

        // Etiqueta y campo de texto para Nombre
        JLabel labelNombre = new JLabel("Nombre:");
        JTextField textNombre = new JTextField();

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
        JComboBox comboTipo = new JComboBox();

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

        // Panel con JCheckBox para las materias
        JPanel panelMaterias = new JPanel();
        panelMaterias.setLayout(new BoxLayout(panelMaterias, BoxLayout.Y_AXIS)); // Usamos BoxLayout para organizar las casillas verticalmente
        String[] materias = {"Matemáticas", "Física", "Química", "Historia", "Biología", "Lengua", "Geografía"};

        for (String materia : materias) {
            JCheckBox checkBox = new JCheckBox(materia);
            panelMaterias.add(checkBox);
        }

        // Hacer el panel desplazable
        JScrollPane scrollPane = new JScrollPane(panelMaterias);
        scrollPane.setPreferredSize(new Dimension(200, 100)); // Ajustamos el tamaño preferido del JScrollPane

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.BOTH; // Para que el JScrollPane ocupe todo el espacio disponible
        gbc.weighty = 1.0; // Permitir que el JScrollPane se expanda verticalmente si hay espacio
        add(scrollPane, gbc);

        // Botón de enviar
        JButton btnEnviar = new JButton("Enviar");

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2; // Ocupa dos columnas
        gbc.fill = GridBagConstraints.NONE; // No expandir el botón
        gbc.anchor = GridBagConstraints.CENTER; // Centrar el botón
        gbc.weightx = 0;
        gbc.weighty = 0;
        add(btnEnviar, gbc);
    }
}