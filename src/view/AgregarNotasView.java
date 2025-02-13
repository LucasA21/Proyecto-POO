package view;

import model.Alumno;
import model.Materia;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class AgregarNotasView extends JPanel {

    private JComboBox<Alumno> comboAlumno;
    private JComboBox<Materia> comboMateria;
    private JComboBox<String> comboNota;
    private JComboBox<String> comboTipoNota;
    private JButton btnEnviar;

    public AgregarNotasView() {
        setBackground(Color.WHITE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        Font generalFont = viewUtils.getScaledFont(new Font("Arial", Font.PLAIN, 14), 0.015);

        // Alumno
        JLabel labelAlumno = new JLabel("Alumno:");
        labelAlumno.setFont(generalFont);
        comboAlumno = new JComboBox<>();
        comboAlumno.setFont(generalFont);
        comboAlumno.setPreferredSize(viewUtils.getProportionalSize(0.3, 0.05));

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(labelAlumno, gbc);

        gbc.gridx = 1;
        add(comboAlumno, gbc);

        // Materia
        JLabel labelMateria = new JLabel("Materia:");
        labelMateria.setFont(generalFont);
        comboMateria = new JComboBox<>();
        comboMateria.setFont(generalFont);
        comboMateria.setPreferredSize(viewUtils.getProportionalSize(0.3, 0.05));

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(labelMateria, gbc);

        gbc.gridx = 1;
        add(comboMateria, gbc);

        // Tipo de nota
        JLabel labelTipoNota = new JLabel("Tipo de Nota:");
        labelTipoNota.setFont(generalFont);
        comboTipoNota = new JComboBox<>(new String[]{"Cursada", "Final"});
        comboTipoNota.setFont(generalFont);
        comboTipoNota.setPreferredSize(viewUtils.getProportionalSize(0.3, 0.05));

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(labelTipoNota, gbc);

        gbc.gridx = 1;
        add(comboTipoNota, gbc);

        // Nota
        JLabel labelNota = new JLabel("Nota:");
        labelNota.setFont(generalFont);
        comboNota = new JComboBox<>();
        comboNota.setFont(generalFont);
        comboNota.setPreferredSize(viewUtils.getProportionalSize(0.3, 0.05));

        comboNota.setModel(new DefaultComboBoxModel<>(Arrays.stream(new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
                .map(String::valueOf)
                .toArray(String[]::new)));

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(labelNota, gbc);

        gbc.gridx = 1;
        add(comboNota, gbc);

        // Botón Enviar
        btnEnviar = new JButton("Enviar");
        btnEnviar.setFont(viewUtils.getScaledFont(new Font("Arial", Font.BOLD, 14), 0.015));
        btnEnviar.setPreferredSize(viewUtils.getProportionalSize(0.1, 0.04));

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        add(btnEnviar, gbc);
    }

    public void resetCombos() {
        comboAlumno.setSelectedItem(null);
        comboNota.setSelectedItem(null);
        comboMateria.setSelectedItem(null);
        comboTipoNota.setSelectedItem(null);
    }

    public JComboBox<Alumno> getComboAlumno() {
        return comboAlumno;
    }

    public JComboBox<Materia> getComboMateria() {
        return comboMateria;
    }

    public JComboBox<String> getComboNota() {
        return comboNota;
    }

    public JComboBox<String> getComboTipoNota() {
        return comboTipoNota;
    }

    public JButton getBtnEnviar() {
        return btnEnviar;
    }
}
