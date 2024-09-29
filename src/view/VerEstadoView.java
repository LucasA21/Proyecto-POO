package view;

import model.Alumno;

import javax.swing.*;
import java.awt.*;

public class VerEstadoView extends JPanel {

    private JComboBox<Alumno> comboAlumno;
    private JButton btnEnviar;
    private JTextArea textAreaInfo;

    public VerEstadoView() {
        setBackground(Color.WHITE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel labelAlumno = new JLabel("Alumno");
        comboAlumno = new JComboBox<>();
        comboAlumno.setPreferredSize(new Dimension(200, 20));

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        add(labelAlumno, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        add(comboAlumno, gbc);

        // Botón de enviar
        btnEnviar = new JButton("Enviar");

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.weightx = 0;
        gbc.weighty = 0;
        add(btnEnviar, gbc);

        // JTextArea para mostrar información del alumno
        textAreaInfo = new JTextArea(5, 20); // 5 filas, 20 columnas
        textAreaInfo.setEditable(false); // Hacerlo no editable
        textAreaInfo.setLineWrap(true);
        textAreaInfo.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(textAreaInfo); // Para agregar scroll
        scrollPane.setPreferredSize(new Dimension(250, 100)); // Tamaño del área de texto

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        add(scrollPane, gbc);
    }

    // Getters para acceder a los componentes
    public JComboBox<Alumno> getComboAlumno() {
        return comboAlumno;
    }

    public JButton getBtnEnviar() {
        return btnEnviar;
    }

    // Método para actualizar la información del alumno
    public void setInformacionAlumno(String info) {
        textAreaInfo.setText(info);
    }
}
