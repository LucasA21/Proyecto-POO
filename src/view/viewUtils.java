package view;
import javax.swing.*;
import java.awt.*;

    public class viewUtils {

        public static Dimension getProportionalSize(double widthFactor, double heightFactor) {
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            int width = (int) (screenSize.width * widthFactor);
            int height = (int) (screenSize.height * heightFactor);
            return new Dimension(width, height);
        }

        public static Font getScaledFont(Font baseFont, double widthFactor) {
            int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
            int fontSize = (int) (screenWidth * widthFactor); // Escala basada en el ancho de la pantalla
            return baseFont.deriveFont((float) fontSize);
        }

        public static ImageIcon getScaledIcon(Image image, double widthFactor) {
            int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
            int size = (int) (screenWidth * widthFactor); // Escala basada en el ancho de la pantalla
            Image resizedImage = image.getScaledInstance(size, size, Image.SCALE_SMOOTH);
            return new ImageIcon(resizedImage);
        }
    }
