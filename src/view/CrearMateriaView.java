package view;

import model.Materia;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CrearMateriaView extends JPanel {

    private JTextField textNombre;
    private JTextField textCuatrimestre;
    private JCheckBox checkObligatoria;
    private JCheckBox checkPromocion;
    private JPanel panelCorrelativas;
    private JButton btnEnviar;

    public CrearMateriaView() {
        setBackground(Color.WHITE);
        setLayout(new GridBagLayout()); // Usamos GridBagLayout para un control más preciso
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Margen alrededor de los componentes
        gbc.fill = GridBagConstraints.HORIZONTAL; // Para que los componentes se expandan horizontalmente

        Font generalFont = viewUtils.getScaledFont(new Font("Arial", Font.PLAIN, 14), 0.015); // Fuente general

        // Etiqueta y campo de texto para Nombre
        JLabel labelNombre = new JLabel("Nombre:");
        labelNombre.setFont(generalFont);
        textNombre = new JTextField();
        textNombre.setPreferredSize(viewUtils.getProportionalSize(0.3, 0.05)); // Ajustamos el tamaño dinámico
        textNombre.setFont(generalFont);

        gbc.gridx = 0; // Columna 0
        gbc.gridy = 0; // Fila 0
        gbc.weightx = 0; // Sin expansión extra horizontal
        add(labelNombre, gbc);

        gbc.gridx = 1; // Columna 1
        gbc.gridy = 0;
        gbc.weightx = 1.0; // Expande el campo de texto horizontalmente
        add(textNombre, gbc);

        // Etiqueta y campo de texto para Cuatrimestre
        JLabel labelCuatrimestre = new JLabel("Cuatrimestre:");
        labelCuatrimestre.setFont(generalFont);
        textCuatrimestre = new JTextField();
        textCuatrimestre.setPreferredSize(viewUtils.getProportionalSize(0.3, 0.05)); // Ajustamos el tamaño dinámico
        textCuatrimestre.setFont(generalFont);

        gbc.gridx = 0; // Columna 0
        gbc.gridy = 1; // Fila 1
        gbc.weightx = 0; // Sin expansión extra horizontal
        add(labelCuatrimestre, gbc);

        gbc.gridx = 1; // Columna 1
        gbc.gridy = 1;
        gbc.weightx = 1.0; // Expande el campo de texto horizontalmente
        add(textCuatrimestre, gbc);

        // checkbox para Es obligatoria
        checkObligatoria = crearCheckBox("Obligatoria", generalFont);


        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        add(checkObligatoria, gbc);

        // checkbox para Tiene promoción
        checkPromocion = crearCheckBox("Promoción", generalFont);


        gbc.gridx = 1;
        gbc.gridy = 3;
        add(checkPromocion, gbc);

        // Etiqueta para Materias correlativas
        JLabel labelCorrelativas = new JLabel("Correlativas:");
        labelCorrelativas.setFont(generalFont);

        gbc.gridx = 0;
        gbc.gridy = 4;
        add(labelCorrelativas, gbc);

        // Panel para las materias correlativas
        panelCorrelativas = new JPanel();
        panelCorrelativas.setLayout(new BoxLayout(panelCorrelativas, BoxLayout.Y_AXIS)); // Usamos BoxLayout para organizar las casillas verticalmente
        panelCorrelativas.setBackground(Color.WHITE);

        // Hacer el panel desplazable
        JScrollPane scrollPane = new JScrollPane(panelCorrelativas);
        scrollPane.setPreferredSize(viewUtils.getProportionalSize(0.4, 0.3)); // Ajustamos el tamaño del JScrollPane

        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.BOTH; // Permitir que el JScrollPane ocupe todo el espacio disponible
        gbc.weighty = 1.0; // Permitir que el JScrollPane se expanda verticalmente si hay espacio
        add(scrollPane, gbc);

        // Botón de enviar
        btnEnviar = new JButton("Enviar");
        btnEnviar.setFont(viewUtils.getScaledFont(new Font("Arial", Font.BOLD, 14), 0.015));
        btnEnviar.setPreferredSize(viewUtils.getProportionalSize(0.1, 0.04)); // Ajustamos el tamaño dinámico

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2; // Ocupa dos columnas
        gbc.fill = GridBagConstraints.NONE; // No expandir el botón
        gbc.anchor = GridBagConstraints.CENTER; // Centrar el botón
        gbc.weighty = 0; // No necesita expandirse
        add(btnEnviar, gbc);
    }

    private JCheckBox crearCheckBox(String text, Font font) {
        JCheckBox checkBox = new JCheckBox(text);
        checkBox.setBackground(Color.WHITE);
        checkBox.setFont(font);

        // Opcional: Crear un ícono personalizado para agrandar el tamaño del checkbox
        Icon emptyIcon = new ImageIcon(new ImageIcon("assets/icons/checkbox_empty.png")
                .getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
        Icon filledIcon = new ImageIcon(new ImageIcon("assets/icons/checkbox_filled.png")
                .getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));

        checkBox.setIcon(emptyIcon);
        checkBox.setSelectedIcon(filledIcon);

        return checkBox;
    }

    public void actualizarCorrelativas(List<Materia> materiasDisponibles) {
        panelCorrelativas.removeAll();

        for (Materia materia : materiasDisponibles) {
            JCheckBox checkBox = crearCheckBox(materia.getNombre(), viewUtils.getScaledFont(new Font("Arial", Font.PLAIN, 14), 0.015));
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

    public JCheckBox getCheckPromocion() {
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
