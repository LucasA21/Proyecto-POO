package view;

import model.Materia;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CrearMateriaView extends JPanel {

    JTextField textNombre;
    JTextField textCuatrimestre;
    JCheckBox checkObligatoria;
    JCheckBox checkPromocion;
    JPanel panelCorrelativas;
    JButton btnEnviar;

    public CrearMateriaView() {
        setBackground(Color.WHITE);
        setLayout(new GridBagLayout()); // Usamos GridBagLayout para un control más preciso
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Margen alrededor de los componentes
        gbc.fill = GridBagConstraints.HORIZONTAL; // Para que los componentes se expandan horizontalmente

        // Etiqueta y campo de texto para Nombre
        JLabel labelNombre = new JLabel("Nombre:");
        textNombre = new JTextField();

        gbc.gridx = 0; // Columna 0
        gbc.gridy = 0; // Fila 0
        gbc.weightx = 0; // Sin expansión extra horizontal
        add(labelNombre, gbc);

        gbc.gridx = 1; // Columna 1
        gbc.gridy = 0;
        gbc.weightx = 1.0; // Expande el campo de texto horizontalmente
        add(textNombre, gbc);


        JLabel labelCuatrimestre = new JLabel("Cuatrimestre:");
        textCuatrimestre = new JTextField();

        gbc.gridx = 0; // Columna 0
        gbc.gridy = 1; // Fila 0
        gbc.weightx = 0; // Sin expansión extra horizontal
        add(labelCuatrimestre, gbc);

        gbc.gridx = 1; // Columna 1
        gbc.gridy = 1;
        gbc.weightx = 1.0; // Expande el campo de texto horizontalmente
        add(textCuatrimestre, gbc);



        // Etiqueta y checkbox para Es obligatoria
        JLabel labelObligatoria = new JLabel("Es obligatoria?");
        checkObligatoria = new JCheckBox();

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0;
        add(labelObligatoria, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        add(checkObligatoria, gbc);

        // Etiqueta y checkbox para Tiene promoción
        JLabel labelPromocionable = new JLabel("Tiene promoción?");
        checkPromocion = new JCheckBox();

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(labelPromocionable, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        add(checkPromocion, gbc);

        // Etiqueta para Materias correlativas
        JLabel labelCorrelativas = new JLabel("Correlativas:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(labelCorrelativas, gbc);

        // Panel para las materias correlativas
        panelCorrelativas = new JPanel();
        panelCorrelativas.setLayout(new BoxLayout(panelCorrelativas, BoxLayout.Y_AXIS)); // Usamos BoxLayout para organizar las casillas verticalmente


        // Hacer el panel desplazable
        JScrollPane scrollPane = new JScrollPane(panelCorrelativas);
        scrollPane.setPreferredSize(new Dimension(200, 100)); // Ajustamos el tamaño del JScrollPane para que coincida con CrearPlanView

        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.BOTH; // Permitir que el JScrollPane ocupe todo el espacio disponible
        gbc.weighty = 1.0; // Permitir que el JScrollPane se expanda verticalmente si hay espacio
        add(scrollPane, gbc);

        // Botón de enviar
        btnEnviar = new JButton("Enviar");

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2; // Ocupa dos columnas
        gbc.fill = GridBagConstraints.NONE; // No expandir el botón
        gbc.anchor = GridBagConstraints.CENTER; // Centrar el botón
        gbc.weighty = 0; // No necesita expandirse
        add(btnEnviar, gbc);
    }

    public void actualizarCorrelativas(List<Materia> materiasDisponibles){
        panelCorrelativas.removeAll();

        for (Materia materia : materiasDisponibles) {
            JCheckBox checkBox = new JCheckBox(materia.getNombre());
            panelCorrelativas.add(checkBox);
        }

        panelCorrelativas.revalidate();
        panelCorrelativas.repaint();
    }

    public List<JCheckBox> getCheckboxCorrelativas() {
        List<JCheckBox> checkboxes = new ArrayList<>();
        for (Component c : panelCorrelativas.getComponents()) {
            if (c instanceof JCheckBox) {
                checkboxes.add((JCheckBox) c);
            }
        }
        return checkboxes;
    }

    public String getTextNombre() {
        return textNombre.getText();
    }

    public String getTextCuatrimestre() {
        return textCuatrimestre.getText();
    }

    public JCheckBox getCheckObligatoria() {
        return checkObligatoria;
    }

    public  JCheckBox getCheckPromocion() {
        return checkPromocion;
    }

    public JButton getBtnEnviar() {
        return btnEnviar;
    }

    public void limpiarCampos() {
        textNombre.setText("");
        textCuatrimestre.setText("");
        checkObligatoria.setSelected(false);
        checkPromocion.setSelected(false);


        for (Component c : panelCorrelativas.getComponents()) {
            if (c instanceof JCheckBox) {
                ((JCheckBox) c).setSelected(false);
            }
        }
    }

}
