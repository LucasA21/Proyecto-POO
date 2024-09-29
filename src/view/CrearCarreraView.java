package view;

import javax.swing.*;
import java.awt.*;

public class CrearCarreraView extends JPanel {

    public CrearCarreraView() {
        setBackground(Color.WHITE);
        setLayout(new GridBagLayout()); // Usamos GridBagLayout para mayor control
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL; // Para que los componentes se ajusten horizontalmente
        gbc.insets = new Insets(10, 10, 10, 10); // Margen entre componentes

        // Etiqueta y campo de texto para Nombre
        JLabel labelNombre = new JLabel("Nombre:");
        JTextField textNombre = new JTextField();
        textNombre.setPreferredSize(new Dimension(200, 20));

        gbc.gridx = 0; // Columna 0
        gbc.gridy = 0; // Fila 0
        gbc.weightx = 0; // Sin expansión extra horizontal
        add(labelNombre, gbc);

        gbc.gridx = 1; // Columna 1
        gbc.gridy = 0;
        gbc.weightx = 1.0; // Expande el campo de texto horizontalmente
        add(textNombre, gbc);

        // Etiqueta y JComboBox para Tipo de plan
        JLabel labelTipo = new JLabel("Asignar Plan:");
        JComboBox comboTipo = new JComboBox();
        comboTipo.setPreferredSize(new Dimension(100,20));

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        add(labelTipo, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        add(comboTipo, gbc);


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
