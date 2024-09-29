package view;

import javax.swing.*;
import java.awt.*;

public class CrearMateriaView extends JPanel {

    public CrearMateriaView() {
        setBackground(Color.WHITE);
        setLayout(new GridLayout(3, 2, 10, 15)); // Configuramos el layout en una cuadrícula de 3 filas y 2 columnas

        // Etiquetas y campos de texto
        JLabel labelNombre = new JLabel("Nombre:");
        JTextField textNombre = new JTextField();

        JLabel labelDni = new JLabel("DNI:");
        JTextField textDni = new JTextField();

        JButton btnEnviar = new JButton("Enviar");

        // Añadir componentes al panel
        add(labelNombre);
        add(textNombre);
        add(labelDni);
        add(textDni);
        add(new JLabel()); // Añadir un espacio vacío
        add(btnEnviar);
    }
}
