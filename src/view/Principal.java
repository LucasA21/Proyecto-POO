package view;

import javax.swing.*;
import java.awt.*;

public class Principal extends JFrame {
    private JPanel leftPanel;
    private JPanel topLeftPanel;
    private JPanel rightPanel;
    private JPanel topRightPanel;
    private JPanel bottomRightPanel;

    private final Color panelColor = new Color(12, 9, 118); // Azul oscuro
    private final Color buttonColor = new Color(78, 74, 194); // Azul más claro
    private JLabel sectionTitle; // Título de la sección actual

    public Principal() {
        setTitle("Sistema de Gestión Universitaria");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Crear panel izquierdo
        leftPanel = new JPanel(new BorderLayout());
        leftPanel.setBackground(panelColor);
        leftPanel.setPreferredSize(new Dimension(200, getHeight()));

        // Panel superior del lado izquierdo con título "Gestión Universitaria"
        topLeftPanel = new JPanel(new GridBagLayout());
        topLeftPanel.setBackground(panelColor);
        topLeftPanel.setPreferredSize(new Dimension(200, 70));

        // Crear las dos líneas de texto "Gestión" y "Universitaria"
        JLabel gestionLabel = new JLabel("Gestión", JLabel.CENTER);
        gestionLabel.setFont(new Font("Arial", Font.BOLD, 18));
        gestionLabel.setForeground(Color.WHITE);

        JLabel universitariaLabel = new JLabel("Universitaria", JLabel.CENTER);
        universitariaLabel.setFont(new Font("Arial", Font.BOLD, 18));
        universitariaLabel.setForeground(Color.WHITE);

        // Añadir las etiquetas con un layout GridBag para que estén centradas
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 0.5;
        gbc.anchor = GridBagConstraints.CENTER;
        topLeftPanel.add(gestionLabel, gbc);

        gbc.gridy = 1;
        topLeftPanel.add(universitariaLabel, gbc);

        // Línea de separación
        JSeparator separator = new JSeparator();
        separator.setForeground(Color.WHITE); // Color de la línea
        separator.setPreferredSize(new Dimension(150, 1)); // Ajustar el ancho
        gbc.gridy = 2; // Posición para el separador
        topLeftPanel.add(separator, gbc);

        leftPanel.add(topLeftPanel, BorderLayout.NORTH);

        // Panel de botones con iconos
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setBackground(panelColor);

        GridBagConstraints gbcButtons = new GridBagConstraints();
        gbcButtons.gridx = 0;
        gbcButtons.gridy = GridBagConstraints.RELATIVE;
        gbcButtons.fill = GridBagConstraints.HORIZONTAL;

        // Crear botones con iconos a la izquierda
        JPanel btnCrearAlumno = crearPanelConIcono("Crear Alumno", "assets/icons/user.png");
        JPanel btnCrearMateria = crearPanelConIcono("Crear Materia", "assets/icons/materia.png");
        JPanel btnCrearPlan = crearPanelConIcono("Crear Plan de Estudio", "assets/icons/plan.png");
        JPanel btnCrearCarrera = crearPanelConIcono("Crear Carrera", "assets/icons/carrera.png");
        JPanel btnInscribirAlumno = crearPanelConIcono("Inscribir Alumno", "assets/icons/inscribir_alumno.png");
        JPanel btnAgregarNotas = crearPanelConIcono("Agregar Notas", "assets/icons/exam.png");
        JPanel btnVerEstado = crearPanelConIcono("Ver Estado Alumno", "assets/icons/estadoalumno.png");

        // Agregar botones al panel de botones
        buttonPanel.add(btnCrearAlumno, gbcButtons);
        buttonPanel.add(btnCrearMateria, gbcButtons);
        buttonPanel.add(btnCrearPlan, gbcButtons);
        buttonPanel.add(btnCrearCarrera, gbcButtons);
        buttonPanel.add(btnInscribirAlumno, gbcButtons);
        buttonPanel.add(btnAgregarNotas, gbcButtons);
        buttonPanel.add(btnVerEstado, gbcButtons);

        // Agregar el panel de botones al panel izquierdo
        leftPanel.add(buttonPanel, BorderLayout.CENTER);

        // Panel superior derecho con fondo blanco y título de la sección actual
        topRightPanel = new JPanel();
        topRightPanel.setBackground(Color.WHITE); // Fondo blanco
        topRightPanel.setPreferredSize(new Dimension(getWidth(), 50));

        sectionTitle = new JLabel("Inicio", JLabel.CENTER);
        sectionTitle.setFont(new Font("Arial", Font.BOLD, 18));
        topRightPanel.add(sectionTitle);

        // Panel inferior derecho para el contenido dinámico
        bottomRightPanel = new JPanel();
        bottomRightPanel.setBackground(Color.LIGHT_GRAY);
        bottomRightPanel.add(new JLabel("Contenido inicial"));

        // Panel derecho que contiene el superior y el inferior
        rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());
        rightPanel.add(topRightPanel, BorderLayout.NORTH); // Panel superior derecho
        rightPanel.add(bottomRightPanel, BorderLayout.CENTER); // Panel dinámico inferior

        // Agregar paneles a la ventana principal
        add(leftPanel, BorderLayout.WEST);   // Panel izquierdo con botones
        add(rightPanel, BorderLayout.CENTER); // Panel derecho con contenido
    }

    // Método para cambiar el contenido del panel inferior derecho y el título de la sección
    private void mostrarVista(String vista) {
        bottomRightPanel.removeAll(); // Limpiar el panel derecho
        sectionTitle.setText(vista); // Cambiar el título de la sección

        // Cambiar la vista según el botón presionado
        bottomRightPanel.add(new JLabel("Vista para " + vista));

        bottomRightPanel.revalidate(); // Actualizar el panel
        bottomRightPanel.repaint();    // Repaint para que se muestre el nuevo contenido
    }

    // Método para crear un panel con un icono a la izquierda y un botón a la derecha
    private JPanel crearPanelConIcono(String texto, String rutaIcono) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(panelColor);
        panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0)); // Eliminar márgenes

        // Crear el icono y redimensionarlo
        ImageIcon icono = new ImageIcon(rutaIcono);
        Image img = icono.getImage();
        Image resizedImage = img.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH); // Cambiar tamaño a 20x20 píxeles
        icono = new ImageIcon(resizedImage);

        // Crear un panel para el icono
        JPanel iconoPanel = new JPanel();
        iconoPanel.setBackground(buttonColor); // Color de fondo igual al botón
        iconoPanel.add(new JLabel(icono)); // Agregar el icono al panel

        // Crear el botón
        JButton button = new JButton(texto);
        button.setFocusPainted(false); // Elimina el borde del foco
        button.setBackground(buttonColor); // Color más claro que el fondo
        button.setForeground(Color.WHITE); // Texto en blanco
        button.setBorderPainted(false); // Sin bordes
        button.setOpaque(true); // Para asegurarse de que el color de fondo se muestre
        button.setFont(new Font("Arial", Font.PLAIN, 14)); // Texto más pequeño
        button.setHorizontalAlignment(SwingConstants.LEFT); // Alineación horizontal a la izquierda
        button.setPreferredSize(new Dimension(160, 40)); // Tamaño del botón
        button.setMargin(new Insets(0, 0, 0, 0)); // Eliminar márgenes del botón

        // Agregar el panel del icono y el botón al panel principal
        panel.add(iconoPanel, BorderLayout.WEST);
        panel.add(button, BorderLayout.CENTER);

        return panel;
    }
}
