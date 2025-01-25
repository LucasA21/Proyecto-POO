package view;

import javax.swing.*;
import java.awt.*;

public class HomeView extends JPanel {

    public HomeView() {
        setBackground(Color.white);
        setLayout(new BorderLayout());


            // Crear y configurar el ícono escalado
            ImageIcon icono = new ImageIcon("assets/icons/icono-principal.png");
            JLabel iconoLabel = new JLabel(viewUtils.getScaledIcon(icono.getImage(), 0.47), JLabel.CENTER);
            add(iconoLabel, BorderLayout.CENTER);

        }
    }


