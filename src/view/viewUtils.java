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

        public static void showScaledMessageDialog(Component parent, String message, String title, int messageType) {
            // Obtener tamaño de la pantalla
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            int screenWidth = screenSize.width;
            int screenHeight = screenSize.height;

            double widthFactor = 0.3; // 30% del ancho de la pantalla
            double heightFactor = 0.3; // 30% del alto de la pantalla

            int maxWidth = (int) (screenWidth * widthFactor);
            int maxHeight = (int) (screenHeight * heightFactor);

            //JLabel con HTML para el formato y centrado del texto
            JLabel label = new JLabel("<html><div style='text-align: center;'>" + message.replace("\n", "<br>") + "</div></html>");
            label.setFont(getScaledFont(new Font("Arial", Font.PLAIN, 14), 0.015)); // Tamaño responsivo de la fuente
            label.setHorizontalAlignment(SwingConstants.CENTER);

            // Medir tamaño del texto y ajustarlo al cuadro de diálogo
            FontMetrics metrics = label.getFontMetrics(label.getFont());
            int textWidth = metrics.stringWidth(message);
            int textHeight = metrics.getHeight() * (message.split("\n").length + 1);

            int finalWidth = Math.min(maxWidth, textWidth + 60); // Ajuste dinámico del ancho
            int finalHeight = Math.min(maxHeight, textHeight + 90); // Ajuste dinámico de la altura

            // Crear y mostrar el JOptionPane
            JOptionPane optionPane = new JOptionPane(label, messageType);
            JDialog dialog = optionPane.createDialog(parent, title);
            dialog.setSize(finalWidth, finalHeight);
            dialog.setResizable(false);
            dialog.setLocationRelativeTo(parent); // Centrar en la pantalla
            dialog.setVisible(true);
        }




    }
