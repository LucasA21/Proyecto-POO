package view;

import javax.swing.*;
import java.awt.*;

public class CrearAlumnoView extends JPanel {

    public CrearAlumnoView() {
        setBackground(Color.WHITE);
        setLayout(new GridBagLayout()); // Usamos GridBagLayout para un control más preciso
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Margen alrededor de los componentes
        gbc.fill = GridBagConstraints.HORIZONTAL; // Para que los componentes se expandan horizontalmente

        // Etiqueta y campo de texto para Nombre
        JLabel labelNombre = new JLabel("Nombre:");
        JTextField textNombre = new JTextField();
        textNombre.setPreferredSize(new Dimension(200, 20)); // Ajustamos el tamaño preferido

        gbc.gridx = 0; // Columna 0
        gbc.gridy = 0; // Fila 0
        gbc.weightx = 0; // Sin expansión extra horizontal
        add(labelNombre, gbc);

        gbc.gridx = 1; // Columna 1
        gbc.gridy = 0;
        gbc.weightx = 1.0; // Expande el campo de texto horizontalmente
        add(textNombre, gbc);

        // Etiqueta y campo de texto para DNI
        JLabel labelDni = new JLabel("DNI:");
        JTextField textDni = new JTextField();
        textDni.setPreferredSize(new Dimension(200, 20)); // Ajustamos el tamaño preferido

        gbc.gridx = 0; // Columna 0
        gbc.gridy = 1; // Fila 1
        gbc.weightx = 0; // Sin expansión extra horizontal
        add(labelDni, gbc);

        gbc.gridx = 1; // Columna 1
        gbc.gridy = 1;
        gbc.weightx = 1.0; // Expande el campo de texto horizontalmente
        add(textDni, gbc);

        // Botón de enviar
        JButton btnEnviar = new JButton("Enviar");

        gbc.gridx = 0; // Columna 0
        gbc.gridy = 2; // Fila 2
        gbc.gridwidth = 2; // Ocupa dos columnas
        gbc.fill = GridBagConstraints.NONE; // No expandir el botón
        gbc.anchor = GridBagConstraints.CENTER; // Centrar el botón
        gbc.weighty = 0; // No necesita expandirse
        add(btnEnviar, gbc);
    }
}
