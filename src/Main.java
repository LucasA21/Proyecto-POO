import javax.swing.*;
import view.Principal;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Principal ventana = new Principal();
                ventana.setVisible(true);
            }
        });
    }
}