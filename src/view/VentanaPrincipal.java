package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaPrincipal extends JFrame {

    private JPanel panelLateral;
    private JPanel panelPrincipal;
    private JLabel tituloLabel;

    public VentanaPrincipal() {
        setTitle("Sistema de Gestión Universitaria");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel lateral
        panelLateral = new JPanel();
        panelLateral.setLayout(new GridBagLayout()); // GridBagLayout para controlar los botones
        panelLateral.setBackground(new Color(85, 0, 130));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH; // Ajustar ambos ejes
        gbc.insets = new Insets(10, 10, 10, 10); // Espaciado entre botones
        gbc.weightx = 1.0; // Expandir botones horizontalmente
        gbc.weighty = 0.1; // Expandir un poco verticalmente

        // Aca use HTML para los saltos de linea
        String[] buttonLabels = {
                "<html><center>Crear<br>Materia</center></html>",
                "<html><center>Crear<br>Plan estudio</center></html>",
                "<html><center>Crear<br>Carrera</center></html>",
                "<html><center>Inscribir<br>Carrera</center></html>",
                "<html><center>Inscribir<br>Materia</center></html>",
                "<html><center>Cargar<br>Notas</center></html>",
                "<html><center>Estado<br>Alumno</center></html>"
        };

        for (int i = 0; i < buttonLabels.length; i++) {
            gbc.gridx = 0;
            gbc.gridy = i;
            JButton button = new JButton(buttonLabels[i]);
            button.setBackground(new Color(138, 43, 226)); // Tono violeta para los botones
            button.setForeground(Color.WHITE); // Color del texto
            button.setFocusPainted(false);
            panelLateral.add(button, gbc);

            // Acción de los botones
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mostrarSeccion(button.getText());
                }
            });
        }

        // Panel principal
        panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());

        // Título de la aplicación
        tituloLabel = new JLabel("Sistema de Gestión Universitaria", SwingConstants.CENTER);
        tituloLabel.setOpaque(true);
        tituloLabel.setBackground(new Color(85, 0, 130)); // Mismo color violeta que el lateral
        tituloLabel.setForeground(Color.WHITE);
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 26));
        panelPrincipal.add(tituloLabel, BorderLayout.NORTH);

        // Label inicial que mostrará el texto de las secciones seleccionadas
        JLabel seccionLabel = new JLabel("Sección: Ninguna", SwingConstants.CENTER);
        seccionLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        panelPrincipal.add(seccionLabel, BorderLayout.CENTER);

        // Añadir paneles a la ventana principal con un JSplitPane
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panelLateral, panelPrincipal);
        splitPane.setResizeWeight(0.2); // Peso inicial del panel lateral
        add(splitPane, BorderLayout.CENTER);

        // Ajustar tamaño de la ventana
        setSize(800, 600);
        setLocationRelativeTo(null); // Centrar en pantalla
        setVisible(true);
    }

    // Método para actualizar la sección mostrada
    private void mostrarSeccion(String seccion) {
        panelPrincipal.removeAll(); // Eliminar el contenido anterior
        panelPrincipal.add(tituloLabel, BorderLayout.NORTH); // Mantener el título
        JLabel seccionLabel = new JLabel("Sección: " + seccion, SwingConstants.CENTER);
        seccionLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        panelPrincipal.add(seccionLabel, BorderLayout.CENTER);
        panelPrincipal.revalidate();
        panelPrincipal.repaint();
    }
}
