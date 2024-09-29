package controller;

import model.Alumno;
import view.CrearAlumnoView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class CrearAlumnoController {
    private CrearAlumnoView view;
    private List<Alumno> listaAlumnos;

    public CrearAlumnoController(CrearAlumnoView view){
        this.view = view;
        this.listaAlumnos = new ArrayList<>();

        view.getBtnEnviar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crearAlumno();
            }
        });
    }

    private void crearAlumno(){
        String dni = view.getTextDni();
        String nombre = view.getTextNombre();

        Alumno nuevoAlumno = new Alumno(dni,nombre);
        listaAlumnos.add(nuevoAlumno);

        System.out.println("Alumno creado: " + nuevoAlumno);

        view.limpiarCampos();
    }

    public List<Alumno> getListaAlumnos() {
        return listaAlumnos;
    }
}
