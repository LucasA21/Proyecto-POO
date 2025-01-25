package view;

import javax.swing.*;
import java.awt.*;

public class CrearAlumnoView extends JPanel {

    private JTextField textNombre;
    private JTextField textDni;
    private JButton btnEnviar;

    public CrearAlumnoView() {
        setBackground(Color.WHITE);
        setLayout(new GridBagLayout()); // Usamos GridBagLayout para un control más preciso
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Margen alrededor de los componentes
        gbc.fill = GridBagConstraints.HORIZONTAL; // Para que los componentes se expandan horizontalmente

        // Etiqueta y campo de texto para Nombre
        JLabel labelNombre = new JLabel("Nombre:");
        labelNombre.setFont(viewUtils.getScaledFont(new Font("Arial", Font.PLAIN, 14), 0.015));
        textNombre = new JTextField();
        textNombre.setPreferredSize(viewUtils.getProportionalSize(0.3, 0.05)); // Ajustamos el tamaño dinámico
        textNombre.setFont(viewUtils.getScaledFont(new Font("Arial", Font.PLAIN, 14), 0.015)); // Ajustar fuente

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
        labelDni.setFont(viewUtils.getScaledFont(new Font("Arial", Font.PLAIN, 14), 0.015));
        textDni = new JTextField();
        textDni.setPreferredSize(viewUtils.getProportionalSize(0.3, 0.05)); // Ajustamos el tamaño dinámico
        textDni.setFont(viewUtils.getScaledFont(new Font("Arial", Font.PLAIN, 14), 0.015)); // Ajustar fuente

        gbc.gridx = 0; // Columna 0
        gbc.gridy = 1; // Fila 1
        gbc.weightx = 0; // Sin expansión extra horizontal
        add(labelDni, gbc);

        gbc.gridx = 1; // Columna 1
        gbc.gridy = 1;
        gbc.weightx = 1.0; // Expande el campo de texto horizontalmente
        add(textDni, gbc);

        // Botón de enviar
        btnEnviar = new JButton("Enviar");
        btnEnviar.setFont(viewUtils.getScaledFont(new Font("Arial", Font.BOLD, 14), 0.015));
        btnEnviar.setPreferredSize(viewUtils.getProportionalSize(0.1, 0.04)); // Ajustamos el tamaño dinámico

        gbc.gridx = 0; // Columna 0
        gbc.gridy = 2; // Fila 2
        gbc.gridwidth = 2; // Ocupa dos columnas
        gbc.fill = GridBagConstraints.NONE; // No expandir el botón
        gbc.anchor = GridBagConstraints.CENTER; // Centrar el botón
        gbc.weightx = 0;
        gbc.weighty = 0;
        add(btnEnviar, gbc);
    }

    public String getTextDni() {
        return textDni.getText();
    }

    public String getTextNombre() {
        return textNombre.getText();
    }

    public JButton getBtnEnviar() {
        return btnEnviar;
    }

    public void limpiarCampos() {
        textNombre.setText("");
        textDni.setText("");
    }
}
