package controller;

import model.*;
import view.VerEstadoView;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class VerEstadoController {

    private CrearAlumnoController crearAlumnoController;
    private VerEstadoView verEstadoView;

    public VerEstadoController(VerEstadoView verEstadoView, CrearAlumnoController crearAlumnoController) {
        this.verEstadoView = verEstadoView;
        this.crearAlumnoController = crearAlumnoController;

        cargarListaAlumnos();

        verEstadoView.getBtnEnviar().addActionListener(e -> mostrarInformacionAlumno());
    }

    public void cargarListaAlumnos() {
        List<Alumno> listaAlumnos = crearAlumnoController.getListaAlumnos();
        JComboBox<Alumno> comboAlumno = verEstadoView.getComboAlumno();
        comboAlumno.removeAllItems();
        for (Alumno alumno : listaAlumnos) {
            comboAlumno.addItem(alumno);
        }
    }

    private void mostrarInformacionAlumno() {
        Alumno alumnoSeleccionado = (Alumno) verEstadoView.getComboAlumno().getSelectedItem();
        if (alumnoSeleccionado != null) {
            StringBuilder info = new StringBuilder();
            info.append("📌 Nombre: ").append(alumnoSeleccionado.getNombre()).append("\n");
            info.append("📌 DNI: ").append(alumnoSeleccionado.getDni()).append("\n\n");

            //Carrera Inscripta
            Carrera carrera = alumnoSeleccionado.getCarrera();
            if (carrera != null) {
                info.append("🎓 Carrera Inscripta: ").append(carrera.getNombre()).append("\n");
                info.append("📖 Plan de Estudios: ").append(carrera.getPlanEstudio().getNombre()).append("\n\n");
            } else {
                info.append("❌ No está inscrito en ninguna carrera.\n\n");
            }

            //Materias Inscritas
            List<Materia> materiasInscritas = alumnoSeleccionado.getHistorialAcademico().stream()
                    .map(AlumnoMateria::getMateria)
                    .collect(Collectors.toList());

            if (!materiasInscritas.isEmpty()) {
                info.append("📚 Materias Inscritas:\n");
                for (Materia materia : materiasInscritas) {
                    info.append("   ➤ ").append(materia.getNombre()).append("\n");
                }
                info.append("\n");
            } else {
                info.append("📚 No tiene materias inscritas.\n\n");
            }

            //Materias Aprobadas (cursada aprobada)
            List<Materia> materiasAprobadas = alumnoSeleccionado.getMateriasAprobadas();
            if (!materiasAprobadas.isEmpty()) {
                info.append("✅ Materias con Cursada Aprobada:\n");
                for (Materia materia : materiasAprobadas) {
                    info.append("   ✔ ").append(materia.getNombre()).append("\n");
                }
                info.append("\n");
            } else {
                info.append("❌ No tiene materias con cursada aprobada.\n\n");
            }

            //Materias Finalizadas (final aprobado)
            List<Materia> finalesAprobados = alumnoSeleccionado.getFinalesAprobados();
            if (!finalesAprobados.isEmpty()) {
                info.append("🏆 Materias con Final Aprobado:\n");
                for (Materia materia : finalesAprobados) {
                    info.append("   🏅 ").append(materia.getNombre()).append("\n");
                }
                info.append("\n");
            } else {
                info.append("❌ No tiene materias finalizadas.\n\n");
            }

            //Verificacion si el alumno finalizó la carrera
            if (carrera != null) {
                boolean finalizoCarrera = verificarFinalizacionCarrera(alumnoSeleccionado, carrera);
                if (finalizoCarrera) {
                    info.append("🎉 ¡El alumno finalizó la carrera! 🎓\n");
                } else {
                    info.append("📌 Aún no finalizó la carrera.\n");
                }
            }

            verEstadoView.setInformacionAlumno(info.toString());
            verEstadoView.resetCombos();
        }
    }

    private boolean verificarFinalizacionCarrera(Alumno alumno, Carrera carrera) {

        List<Materia> materiasObligatorias = carrera.getMateriasObligatorias();
        List<Materia> materiasOptativas = carrera.getMateriasOptativas();

        List<Materia> todasLasMaterias = new ArrayList<>();
        todasLasMaterias.addAll(materiasObligatorias);
        todasLasMaterias.addAll(materiasOptativas);

        List<Materia> finalesAprobados = alumno.getFinalesAprobados();

        for (Materia materia : todasLasMaterias) {
            if (!finalesAprobados.contains(materia)) {
                System.out.println("Falta aprobar la materia: " + materia.getNombre());
                return false;
            }
        }

        for (Materia materia : todasLasMaterias) {
            for (Materia correlativa : materia.getCorrelativas()) {
                if (!finalesAprobados.contains(correlativa)) {
                    System.out.println("Falta aprobar la correlativa " + correlativa.getNombre() + " para " + materia.getNombre());
                    return false;
                }
            }
        }

        long optativasAprobadas = finalesAprobados.stream()
                .filter(materiasOptativas::contains)
                .count();

        if (optativasAprobadas < carrera.getCantidadOptativasRequeridas()) {
            System.out.println("No se han aprobado suficientes materias optativas.");
            return false;
        }

        return true;
    }







}
