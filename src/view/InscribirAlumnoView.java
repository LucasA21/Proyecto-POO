package view;

import javax.swing.*;
import java.awt.*;

public class InscribirAlumnoView extends JPanel {

    public InscribirAlumnoView() {
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


        JLabel labelCarrera = new JLabel("Asignar Carrera:");
        JComboBox comboCarrera = new JComboBox();
        comboCarrera.setPreferredSize(new Dimension(200,20));

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0;
        add(labelCarrera, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        add(comboCarrera, gbc);


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
