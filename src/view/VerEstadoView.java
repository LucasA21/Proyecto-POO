package view;

import javax.swing.*;
import java.awt.*;

public class VerEstadoView extends JPanel {

    public VerEstadoView() {
        setBackground(Color.WHITE);
        setLayout(new GridLayout(3, 2, 10, 15)); // Configuramos el layout en una cuadrícula de 3 filas y 2 columnas

        // Etiquetas y campos de texto
        JLabel labelEstado = new JLabel("Aca va el resumen del alumno:");


        // Añadir componentes al panel
        add(labelEstado);
    }
}
