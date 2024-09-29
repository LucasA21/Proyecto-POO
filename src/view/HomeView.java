package view;

import javax.swing.*;
import java.awt.*;

public class HomeView extends JPanel {

    public HomeView() {
        setBackground(Color.white);
        setLayout(new BorderLayout());

        JLabel inicio = new JLabel("<html><br><br>Bienvenido!<br><br><br></html>", JLabel.CENTER);

        inicio.setFont(new Font("Arial", Font.PLAIN, 20));
        add(inicio, BorderLayout.NORTH);

        // Crear y configurar el ícono
        ImageIcon icono = new ImageIcon("assets/icons/universidad.png");
        Image img = icono.getImage();
        Image resizedImage = img.getScaledInstance(220, 220, Image.SCALE_SMOOTH);
        icono = new ImageIcon(resizedImage);

        JLabel iconoLabel = new JLabel(icono, JLabel.CENTER);
        add(iconoLabel, BorderLayout.CENTER); // Agregar el ícono al panel
    }
}
