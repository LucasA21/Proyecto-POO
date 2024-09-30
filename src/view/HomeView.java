package view;

import javax.swing.*;
import java.awt.*;

public class HomeView extends JPanel {

    public HomeView() {
        setBackground(Color.white);
        setLayout(new BorderLayout());

        JLabel inicio = new JLabel("<html><br><br><br><br>Bienvenido!<br><br><br></html>", JLabel.CENTER);

        inicio.setFont(new Font("Arial", Font.PLAIN, 20));
        add(inicio, BorderLayout.NORTH);

        // Crear y configurar el ícono
        ImageIcon icono = new ImageIcon("assets/icons/universidad2.png");
        Image img = icono.getImage();
        Image resizedImage = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        icono = new ImageIcon(resizedImage);

        JLabel iconoLabel = new JLabel(icono, JLabel.CENTER);
        add(iconoLabel, BorderLayout.CENTER); // Agregar el ícono al panel


        JLabel inicio2  = new JLabel("<html><br><br>Sistema Gestion Universitaria<br><br><br></html>", JLabel.CENTER);
        add(inicio2, BorderLayout.SOUTH);

        inicio2.setFont(new Font("Arial", Font.PLAIN, 20));
        add(inicio, BorderLayout.NORTH);
    }
}
