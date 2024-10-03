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

    // Creacion de paneles principales

    private JPanel leftPanel;
    private JPanel topLeftPanel;
    private JPanel rightPanel;
    private JPanel topRightPanel;
    private JPanel bottomRightPanel;
    private JPanel sectionPanel;
    private JPanel adminPanel;

    // Colores globales

    private final Color panelColor = new Color(27, 79, 114); // Azul oscuro
    private final Color buttonColor = new Color(40, 116, 166); // Azul más claro

    private final JLabel sectionTitle; // Título de la sección actual

    // Controllers

    private VerEstadoController verEstadoController;
    private CrearMateriaController crearMateriaController;
    private CrearAlumnoController crearAlumnoController;
    private CrearPlanEstudioController crearPlanEstudioController;


    public Principal() {
        setTitle("Sistema de Gestión Universitaria");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());


        // Creacion de views y controllers
        HomeView homeView = new HomeView();

        CrearAlumnoView crearAlumnoView = new CrearAlumnoView();
        crearAlumnoController = new CrearAlumnoController(crearAlumnoView);

        CrearMateriaView crearMateriaView = new CrearMateriaView();
        crearMateriaController = new CrearMateriaController(crearMateriaView);

        CrearPlanView crearPlanView = new CrearPlanView();
        crearPlanEstudioController = new CrearPlanEstudioController(crearPlanView, crearMateriaController);

        CrearCarreraView crearCarreraView = new CrearCarreraView();

        InscribirAlumnoView inscribirAlumnoView = new InscribirAlumnoView();

        InscribirMateriaView inscribirMateriaView = new InscribirMateriaView();

        AgregarNotasView agregarNotasView = new AgregarNotasView();

        VerEstadoView verEstadoView = new VerEstadoView();
        verEstadoController  = new VerEstadoController(crearAlumnoController,verEstadoView);



        // Creacion panel izquierdo

        leftPanel = new JPanel(new BorderLayout());
        leftPanel.setBackground(panelColor);
        leftPanel.setPreferredSize(new Dimension(190, getHeight()));

        // Panel superior del lado izquierdo con título "Gestión Universitaria"

        topLeftPanel = new JPanel(new GridBagLayout());
        topLeftPanel.setBackground(panelColor);
        topLeftPanel.setPreferredSize(new Dimension(200, 70));

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

        // Agregar el panel de botones al panel izquierdo
        leftPanel.add(buttonPanel, BorderLayout.CENTER);

        // Panel superior derecho con fondo blanco
        topRightPanel = new JPanel();
        topRightPanel.setLayout(new BorderLayout());
        topRightPanel.setBackground(Color.WHITE);
        topRightPanel.setPreferredSize(new Dimension(getWidth(), 100));

        // Panel para el título de la sección
        sectionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        sectionPanel.setBackground(Color.WHITE);
        sectionPanel.setPreferredSize(new Dimension(getWidth(), 30));

        sectionTitle = new JLabel("Inicio", JLabel.LEFT);
        sectionTitle.setFont(new Font("Arial",Font.PLAIN, 15));
        sectionPanel.add(sectionTitle);

        // Panel de administración y fecha
        adminPanel = new JPanel(new GridLayout(2, 1));
        adminPanel.setBackground(buttonColor);

        JLabel adminLabel = new JLabel("Administración", JLabel.CENTER);
        adminLabel.setForeground(Color.WHITE);
        adminPanel.add(adminLabel);

        String fechaActual = new SimpleDateFormat("'Hoy es' EEEE dd 'de' MMMM 'de' yyyy").format(new Date());
        JLabel fechaLabel = new JLabel(fechaActual, JLabel.CENTER);
        fechaLabel.setForeground(Color.lightGray);
        fechaLabel.setFont(new Font("Arial", Font.ITALIC, 18));
        adminPanel.add(fechaLabel);


        topRightPanel.add(sectionPanel, BorderLayout.NORTH);
        topRightPanel.add(adminPanel, BorderLayout.CENTER);

        // Creacion del panel dinamico, se inicializa con la HomeView

        bottomRightPanel = new JPanel();
        bottomRightPanel.setBackground(Color.white);
        bottomRightPanel.add(new HomeView());
        rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());
        rightPanel.add(topRightPanel, BorderLayout.NORTH);
        rightPanel.add(bottomRightPanel, BorderLayout.CENTER);

        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);
    }

    /* Este metodo recarga el panel derecho con una vista especifica y setea el nombre de la vista
     En el titulo de la seccion.*/

    private void mostrarVista(JPanel view, String vista) {
        bottomRightPanel.removeAll();
        sectionTitle.setText(vista);
        bottomRightPanel.add(view);
        bottomRightPanel.revalidate();
        bottomRightPanel.repaint();
    }

    private JPanel crearBotonConIcono(String texto, String rutaIcono, ActionListener actionListener) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(panelColor);
        panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        ImageIcon icono = new ImageIcon(rutaIcono);
        Image img = icono.getImage();
        Image resizedImage = img.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        icono = new ImageIcon(resizedImage);

        JPanel iconoPanel = new JPanel();
        iconoPanel.setBackground(buttonColor);
        iconoPanel.add(new JLabel(icono));

        JButton button = new JButton(texto);
        button.setFocusPainted(false);
        button.setBackground(buttonColor);
        button.setForeground(Color.WHITE);
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.setFont(new Font("Arial", Font.PLAIN, 14));
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setPreferredSize(new Dimension(160, 40));
        button.setMargin(new Insets(0, 0, 0, 0));
        button.addActionListener(actionListener);

        panel.add(iconoPanel, BorderLayout.WEST);
        panel.add(button, BorderLayout.CENTER);

        return panel;
    }
}