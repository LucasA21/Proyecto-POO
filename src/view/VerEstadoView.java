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
        gbc.gridy = 0; // Fila 0
        gbc.weightx = 0; // Sin expansión horizontal
        add(labelAlumno, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0; // Fila 0
        gbc.weightx = 1.0; // Expande el combo box horizontalmente
        add(comboAlumno, gbc);

        // Botón de enviar
        btnEnviar = new JButton("Enviar");
        btnEnviar.setFont(viewUtils.getScaledFont(new Font("Arial", Font.BOLD, 14), 0.015));
        btnEnviar.setPreferredSize(viewUtils.getProportionalSize(0.1, 0.04)); // Tamaño dinámico

        gbc.gridx = 0;
        gbc.gridy = 1; // Fila 1
        gbc.gridwidth = 2; // Ocupa dos columnas
        gbc.fill = GridBagConstraints.NONE; // No expandir el botón
        gbc.anchor = GridBagConstraints.CENTER; // Centrar el botón
        gbc.weightx = 0;
        gbc.weighty = 0;
        add(btnEnviar, gbc);

        // Etiqueta para el área de texto
        JLabel labelInfo = new JLabel("Información del alumno:");
        labelInfo.setFont(generalFont);

        gbc.gridx = 0;
        gbc.gridy = 2; // Fila 2
        gbc.gridwidth = 2; // Ocupa dos columnas
        gbc.fill = GridBagConstraints.HORIZONTAL; // Alinear con el área de texto
        gbc.anchor = GridBagConstraints.WEST; // Alinear a la izquierda
        add(labelInfo, gbc);

        // JTextArea para mostrar información del alumno
        textAreaInfo = new JTextArea(5, 20); // 5 filas, 20 columnas
        textAreaInfo.setEditable(false); // Hacerlo no editable
        textAreaInfo.setLineWrap(true);
        textAreaInfo.setWrapStyleWord(true);
        textAreaInfo.setFont(generalFont);
        JScrollPane scrollPane = new JScrollPane(textAreaInfo); // Para agregar scroll
        scrollPane.setPreferredSize(viewUtils.getProportionalSize(0.4, 0.3)); // Tamaño dinámico

        gbc.gridx = 0;
        gbc.gridy = 3; // Fila 3
        gbc.gridwidth = 2; // Ocupa dos columnas
        gbc.fill = GridBagConstraints.BOTH; // Permitir expansión en ambas direcciones
        gbc.weighty = 1.0; // Expande verticalmente
        add(scrollPane, gbc);
    }

    public void resetCombos() {
        comboAlumno.setSelectedItem(null);
    }

    public JComboBox<Alumno> getComboAlumno() {
        return comboAlumno;
    }

    public JButton getBtnEnviar() {
        return btnEnviar;
    }

    public void setInformacionAlumno(String info) {
        textAreaInfo.setText(info);
    }
}
