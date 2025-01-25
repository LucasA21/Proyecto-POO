package view;

import javax.swing.*;
import java.awt.*;

public class InscribirMateriaView extends JPanel {

    public InscribirMateriaView() {
        setBackground(Color.WHITE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL; // Para que los componentes se ajusten horizontalmente
        gbc.insets = new Insets(10, 10, 10, 10); // Margen entre componentes

        // Fuente general escalada
        Font generalFont = viewUtils.getScaledFont(new Font("Arial", Font.PLAIN, 14), 0.015);

        // Etiqueta y combo box para Alumno
        JLabel labelAlumno = new JLabel("Alumno:");
        labelAlumno.setFont(generalFont);
        JComboBox<String> comboAlumno = new JComboBox<>();
        comboAlumno.setFont(generalFont);
        comboAlumno.setPreferredSize(viewUtils.getProportionalSize(0.3, 0.05)); // Tamaño dinámico

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        add(labelAlumno, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        add(comboAlumno, gbc);

        // Etiqueta y combo box para Materia
        JLabel labelMateria = new JLabel("Materia:");
        labelMateria.setFont(generalFont);
        JComboBox<String> comboMateria = new JComboBox<>();
        comboMateria.setFont(generalFont);
        comboMateria.setPreferredSize(viewUtils.getProportionalSize(0.3, 0.05)); // Tamaño dinámico

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        add(labelMateria, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        add(comboMateria, gbc);

        // Botón de enviar
        JButton btnEnviar = new JButton("Enviar");
        btnEnviar.setFont(viewUtils.getScaledFont(new Font("Arial", Font.BOLD, 14), 0.015));
        btnEnviar.setPreferredSize(viewUtils.getProportionalSize(0.1, 0.04)); // Tamaño dinámico

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2; // Ocupa dos columnas
        gbc.fill = GridBagConstraints.NONE; // No expandir el botón
        gbc.anchor = GridBagConstraints.CENTER; // Centrar el botón
        gbc.weightx = 0;
        gbc.weighty = 0;
        add(btnEnviar, gbc);
    }
}
