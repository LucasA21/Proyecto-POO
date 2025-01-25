package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import controller.CrearAlumnoController;
import controller.CrearMateriaController;
import controller.CrearPlanEstudioController;
import controller.VerEstadoController;

public class Principal extends JFrame {

    private JPanel leftPanel;
    private JPanel topLeftPanel;
    private JPanel rightPanel;
    private JPanel topRightPanel;
    private JPanel bottomRightPanel;
    private JPanel adminPanel;

    private final Color panelColor = new Color(27, 79, 114); // Azul oscuro
    private final Color buttonColor = new Color(40, 116, 166); // Azul más claro

    private VerEstadoController verEstadoController;
    private CrearMateriaController crearMateriaController;
    private CrearAlumnoController crearAlumnoController;
    private CrearPlanEstudioController crearPlanEstudioController;

    public Principal() {

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Creacion de views y controllers
        HomeView homeView = new HomeView();

        CrearAlumnoView crearAlumnoView = new CrearAlumnoView();
        crearAlumnoController = new CrearAlumnoController(crearAlumnoView);

        VerEstadoView verEstadoView = new VerEstadoView();
        verEstadoController  = new VerEstadoController(verEstadoView, crearAlumnoController);

        crearAlumnoController.setVerEstadoController(verEstadoController);

        CrearMateriaView crearMateriaView = new CrearMateriaView();
        crearMateriaController = new CrearMateriaController(crearMateriaView);

        CrearPlanView crearPlanView = new CrearPlanView();
        crearPlanEstudioController = new CrearPlanEstudioController(crearPlanView, crearMateriaController);

        CrearCarreraView crearCarreraView = new CrearCarreraView();

        InscribirAlumnoView inscribirAlumnoView = new InscribirAlumnoView();

        InscribirMateriaView inscribirMateriaView = new InscribirMateriaView();

        AgregarNotasView agregarNotasView = new AgregarNotasView();



        // Panel izquierdo
        leftPanel = new JPanel(new BorderLayout());
        leftPanel.setBackground(panelColor);
        leftPanel.setPreferredSize(viewUtils.getProportionalSize(0.12, 1)); // Reducir a 15% del ancho

        // Panel superior izquierdo
        topLeftPanel = new JPanel(new GridBagLayout());
        topLeftPanel.setBackground(panelColor);
        topLeftPanel.setPreferredSize(viewUtils.getProportionalSize(0.14, 0.20)); // Ajustar altura del título

        // Icono panel superior izquierdo
        ImageIcon icono = new ImageIcon("assets/icons/graduado.png");
        JLabel iconoLabel = new JLabel(viewUtils.getScaledIcon(icono.getImage(), 0.1), JLabel.CENTER); // Escalar el ícono

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 0.5;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 0, 5, 0); // Ajustar espacio superior e inferior
        topLeftPanel.add(iconoLabel, gbc);

        JSeparator separator = new JSeparator();
        separator.setForeground(Color.WHITE);
        separator.setPreferredSize(new Dimension(300, 2)); // Ajustar tamaño de la línea
        gbc.gridy = 1;
        gbc.insets = new Insets(10, 0, 0, 0); // Ajustar espacio superior de la línea
        topLeftPanel.add(separator, gbc);


        leftPanel.add(topLeftPanel, BorderLayout.NORTH);

        // Panel de botones
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setBackground(panelColor);

        GridBagConstraints gbcButtons = new GridBagConstraints();
        gbcButtons.gridx = 0;
        gbcButtons.gridy = GridBagConstraints.RELATIVE;
        gbcButtons.fill = GridBagConstraints.HORIZONTAL;
        gbcButtons.insets = new Insets(35, 0, 35, 0); // Espaciado entre botones

        // Crear botones, aca se vincula los botones con sus respectivos controllers

        JPanel btnIncio = crearBotonConIcono("Inicio", "assets/icons/home.png", e ->{
            mostrarVista(homeView, "Inicio");
        });

        JPanel btnCrearAlumno = crearBotonConIcono("Crear Alumno", "assets/icons/user.png", e -> {// Crea el controlador
            mostrarVista(crearAlumnoView, "Crear Alumno");
        });

        JPanel btnCrearMateria = crearBotonConIcono("Crear Materia", "assets/icons/materia.png", e -> {
            mostrarVista(crearMateriaView, "Crear Materia");
        });

        JPanel btnCrearPlan = crearBotonConIcono("Crear Plan de Estudio", "assets/icons/plan.png", e -> {
            mostrarVista(crearPlanView, "Crear Plan de Estudio");
        });

        JPanel btnCrearCarrera = crearBotonConIcono("Crear Carrera", "assets/icons/carrera.png", e -> {
            mostrarVista(crearCarreraView, "Crear Carrera");
        });

        JPanel btnInscribirAlumno = crearBotonConIcono("Inscribir Alumno", "assets/icons/inscribir_alumno.png", e -> {
            mostrarVista(inscribirAlumnoView, "Inscribir Alumno");
        });

        JPanel btnInscribirMateria = crearBotonConIcono("Inscribir Materia", "assets/icons/inscribir_materia.png", e -> {
            mostrarVista(inscribirMateriaView, "Inscribir Materia");
        });

        JPanel btnAgregarNotas = crearBotonConIcono("Agregar Notas", "assets/icons/notas.png", e -> {
            mostrarVista(agregarNotasView, "Agregar Notas");
        });

        JPanel btnVerEstado = crearBotonConIcono("Ver Estado Alumno", "assets/icons/estado.png", e -> {
            mostrarVista(verEstadoView, "Ver Estado Alumno");
        });

        // Agregar botones

        buttonPanel.add(btnIncio,gbcButtons);
        buttonPanel.add(btnCrearAlumno, gbcButtons);
        buttonPanel.add(btnCrearMateria, gbcButtons);
        buttonPanel.add(btnCrearPlan, gbcButtons);
        buttonPanel.add(btnCrearCarrera, gbcButtons);
        buttonPanel.add(btnInscribirAlumno, gbcButtons);
        buttonPanel.add(btnInscribirMateria,gbcButtons);
        buttonPanel.add(btnAgregarNotas, gbcButtons);
        buttonPanel.add(btnVerEstado, gbcButtons);

        leftPanel.add(buttonPanel, BorderLayout.CENTER);

        // Barra superior derecha
        topRightPanel = new JPanel(new BorderLayout());
        topRightPanel.setBackground(buttonColor);
        topRightPanel.setPreferredSize(viewUtils.getProportionalSize(1, 0.12)); // Ajustar altura

        adminPanel = new JPanel(new GridLayout(2, 1, 0, 10)); // Espaciado vertical entre elementos
        adminPanel.setBackground(panelColor);
        adminPanel.setPreferredSize(viewUtils.getProportionalSize(0.87, 0.12)); // Ajustar tamaño para alinear con la línea

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

        // Panel principal dinámico
        bottomRightPanel = new JPanel();
        bottomRightPanel.setBackground(Color.WHITE);
        bottomRightPanel.add(new HomeView());
        rightPanel = new JPanel(new BorderLayout());
        rightPanel.add(topRightPanel, BorderLayout.NORTH);
        rightPanel.add(bottomRightPanel, BorderLayout.CENTER);

        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);
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

    private void mostrarVista(JPanel view, String vista) {
        bottomRightPanel.removeAll();
        bottomRightPanel.add(view);
        bottomRightPanel.revalidate();
        bottomRightPanel.repaint();
    }
}
