import javax.swing.*;

import config.AppInitializer;
import view.Principal;


public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Principal ventana = AppInitializer.initializeApp();
                ventana.setVisible(true);
            }
        });
    }
}

