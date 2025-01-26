package view;

import controller.CrearAlumnoController;
import controller.CrearMateriaController;
import controller.CrearPlanEstudioController;
import controller.VerEstadoController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Principal extends JFrame {

    private JPanel leftPanel;
    private JPanel topLeftPanel;
    private JPanel rightPanel;
    private JPanel topRightPanel;
    private JPanel bottomRightPanel;
    private JPanel adminPanel;

    private final Color panelColor = new Color(27, 79, 114); // Azul oscuro
    private final Color buttonColor = new Color(40, 116, 166); // Azul más claro

    public Principal(
            HomeView homeView,
            CrearAlumnoView crearAlumnoView,
            VerEstadoView verEstadoView,
            CrearMateriaView crearMateriaView,
            CrearPlanView crearPlanView,
            CrearCarreraView crearCarreraView,
            InscribirAlumnoView inscribirAlumnoView,
            InscribirMateriaView inscribirMateriaView,
            AgregarNotasView agregarNotasView,
            CrearAlumnoController crearAlumnoController,
            VerEstadoController verEstadoController,
            CrearMateriaController crearMateriaController,
            CrearPlanEstudioController crearPlanEstudioController
    ) {
        // Configurar ventana principal
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel izquierdo
        leftPanel = new JPanel(new BorderLayout());
        leftPanel.setBackground(panelColor);
        leftPanel.setPreferredSize(viewUtils.getProportionalSize(0.12, 1));

        // Panel superior izquierdo con icono
        topLeftPanel = new JPanel(new GridBagLayout());
        topLeftPanel.setBackground(panelColor);
        topLeftPanel.setPreferredSize(viewUtils.getProportionalSize(0.14, 0.20));
        configurarPanelIzquierdoSuperior();

        // Panel de botones (navegación)
        JPanel buttonPanel = crearPanelBotones(
                homeView, crearAlumnoView, verEstadoView, crearMateriaView, crearPlanView,
                crearCarreraView, inscribirAlumnoView, inscribirMateriaView, agregarNotasView
        );
        leftPanel.add(buttonPanel, BorderLayout.CENTER);

        // Barra superior derecha
        configurarBarraSuperiorDerecha();

        // Panel principal dinámico
        bottomRightPanel = new JPanel();
        bottomRightPanel.setBackground(Color.WHITE);
        bottomRightPanel.add(homeView);
        rightPanel = new JPanel(new BorderLayout());
        rightPanel.add(topRightPanel, BorderLayout.NORTH);
        rightPanel.add(bottomRightPanel, BorderLayout.CENTER);

        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);
    }

    private void configurarPanelIzquierdoSuperior() {
        ImageIcon icono = new ImageIcon("assets/icons/graduado.png");
        JLabel iconoLabel = new JLabel(viewUtils.getScaledIcon(icono.getImage(), 0.1), JLabel.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 0.5;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 0, 5, 0);
        topLeftPanel.add(iconoLabel, gbc);

        JSeparator separator = new JSeparator();
        separator.setForeground(Color.WHITE);
        separator.setPreferredSize(new Dimension(300, 2));
        gbc.gridy = 1;
        gbc.insets = new Insets(10, 0, 0, 0);
        topLeftPanel.add(separator, gbc);
        leftPanel.add(topLeftPanel, BorderLayout.NORTH);
    }

    private void configurarBarraSuperiorDerecha() {
        topRightPanel = new JPanel(new BorderLayout());
        topRightPanel.setBackground(buttonColor);
        topRightPanel.setPreferredSize(viewUtils.getProportionalSize(1, 0.12));

        adminPanel = new JPanel(new GridLayout(2, 1, 0, 10));
        adminPanel.setBackground(panelColor);
        adminPanel.setPreferredSize(viewUtils.getProportionalSize(0.87, 0.12));

        JLabel adminLabel = new JLabel("Administración", JLabel.CENTER);
        adminLabel.setForeground(Color.WHITE);
        adminLabel.setFont(viewUtils.getScaledFont(new Font("Arial", Font.BOLD, 14), 0.015));

        String fechaActual = new SimpleDateFormat("'Hoy es' EEEE dd 'de' MMMM 'de' yyyy").format(new Date());
        JLabel fechaLabel = new JLabel(fechaActual, JLabel.CENTER);
        fechaLabel.setForeground(Color.LIGHT_GRAY);
        fechaLabel.setFont(viewUtils.getScaledFont(new Font("Arial", Font.ITALIC, 12), 0.012));

        adminPanel.add(adminLabel);
        adminPanel.add(fechaLabel);
        topRightPanel.add(adminPanel, BorderLayout.CENTER);
    }

    private JPanel crearPanelBotones(
            HomeView homeView, CrearAlumnoView crearAlumnoView, VerEstadoView verEstadoView,
            CrearMateriaView crearMateriaView, CrearPlanView crearPlanView, CrearCarreraView crearCarreraView,
            InscribirAlumnoView inscribirAlumnoView, InscribirMateriaView inscribirMateriaView, AgregarNotasView agregarNotasView
    ) {
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setBackground(panelColor);

        GridBagConstraints gbcButtons = new GridBagConstraints();
        gbcButtons.gridx = 0;
        gbcButtons.gridy = GridBagConstraints.RELATIVE;
        gbcButtons.fill = GridBagConstraints.HORIZONTAL;
        gbcButtons.insets = new Insets(30, 0, 30, 0);

        buttonPanel.add(crearBotonConIcono("Inicio", "assets/icons/home.png", e -> mostrarVista(homeView)), gbcButtons);
        buttonPanel.add(crearBotonConIcono("Crear Alumno", "assets/icons/user.png", e -> mostrarVista(crearAlumnoView)), gbcButtons);
        buttonPanel.add(crearBotonConIcono("Crear Materia", "assets/icons/materia.png", e -> mostrarVista(crearMateriaView)), gbcButtons);
        buttonPanel.add(crearBotonConIcono("Crear Plan Estudio", "assets/icons/plan.png", e -> mostrarVista(crearPlanView)), gbcButtons);
        buttonPanel.add(crearBotonConIcono("Crear Carrera", "assets/icons/carrera.png", e -> mostrarVista(crearCarreraView)), gbcButtons);
        buttonPanel.add(crearBotonConIcono("Inscribir Alumno", "assets/icons/inscribir_alumno.png", e -> mostrarVista(inscribirAlumnoView)), gbcButtons);
        buttonPanel.add(crearBotonConIcono("Inscribir Materia", "assets/icons/inscribir_materia.png", e -> mostrarVista(inscribirMateriaView)), gbcButtons);
        buttonPanel.add(crearBotonConIcono("Agregar Notas", "assets/icons/notas.png", e -> mostrarVista(agregarNotasView)), gbcButtons);
        buttonPanel.add(crearBotonConIcono("Ver Estado Alumno", "assets/icons/estado.png", e -> mostrarVista(verEstadoView)), gbcButtons);

        return buttonPanel;
    }

    private JPanel crearBotonConIcono(String texto, String rutaIcono, ActionListener actionListener) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(panelColor);

        ImageIcon icono = new ImageIcon(rutaIcono);
        JLabel iconoLabel = new JLabel(viewUtils.getScaledIcon(icono.getImage(), 0.019), JLabel.CENTER);
        iconoLabel.setOpaque(true);
        iconoLabel.setBackground(panelColor);

        JButton button = new JButton(texto);
        button.setFocusPainted(false);
        button.setBackground(panelColor);
        button.setForeground(Color.WHITE);
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.setFont(viewUtils.getScaledFont(new Font("Arial", Font.PLAIN, 6), 0.009));
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.addActionListener(actionListener);

        panel.add(iconoLabel, BorderLayout.WEST);
        panel.add(button, BorderLayout.CENTER);

        return panel;
    }

    private void mostrarVista(JPanel view) {
        bottomRightPanel.removeAll();
        bottomRightPanel.add(view);
        bottomRightPanel.revalidate();
        bottomRightPanel.repaint();
    }
}
