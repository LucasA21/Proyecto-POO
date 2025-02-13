package view;

import model.Alumno;
import model.Carrera;

import javax.swing.*;
import java.awt.*;

public class InscribirAlumnoView extends JPanel {

    private JComboBox<Alumno> comboAlumno;
    private JComboBox<Carrera> comboCarrera;
    private JButton btnEnviar;

    public InscribirAlumnoView() {
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
        comboAlumno = new JComboBox<>();
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

        // Etiqueta y combo box para Carrera
        JLabel labelCarrera = new JLabel("Carrera:");
        labelCarrera.setFont(generalFont);
        comboCarrera = new JComboBox<>(); // Asignar a la variable de instancia
        comboCarrera.setFont(generalFont);
        comboCarrera.setPreferredSize(viewUtils.getProportionalSize(0.3, 0.05)); // Tamaño dinámico

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        add(labelCarrera, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        add(comboCarrera, gbc);

        // Botón de enviar
        btnEnviar = new JButton("Enviar");
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

    // Métodos adicionales en InscribirAlumnoView

    public JComboBox<Alumno> getComboAlumno() {
        return comboAlumno; // Devuelve el combo box de alumnos
    }

    public JComboBox<Carrera> getComboCarrera() {
        return comboCarrera; // Devuelve el combo box de carreras
    }

    public JButton getBtnEnviar() {
        return btnEnviar; // Devuelve el botón de enviar
    }

    public void resetCombos() {
        comboAlumno.setSelectedItem(null);
        comboCarrera.setSelectedItem(null);
    }



}
