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




        JLabel labelAlumno = new JLabel("Alumno:");
        JComboBox comboAlumno = new JComboBox();
        comboAlumno.setPreferredSize(new Dimension(200,20));

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        add(labelAlumno, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        add(comboAlumno, gbc);


        JLabel labelMateria = new JLabel("Materia:");
        JComboBox comboMateria = new JComboBox();
        comboMateria.setPreferredSize(new Dimension(200,20));

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0;
        add(labelMateria, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        add(comboMateria, gbc);


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
