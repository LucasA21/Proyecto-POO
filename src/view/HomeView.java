package view;

import javax.swing.*;
import java.awt.*;

public class HomeView extends JPanel{

        public  HomeView(){
            setBackground(Color.white);
            JLabel inicio = new JLabel("<html><center><br><br><br>Bienvenido al Sistema de Gestión Universitaria." +
                    "<br><br>Aquí puedes realizar las siguientes acciones:" +
                    "<br>- Crear Alumno<br>- Crear Materia<br>- Crear Plan de Estudio<br>- Crear Carrera<br>- " +
                    "Inscribir Alumno<br>- Agregar Notas<br>- Ver Estado de Alumno</center></html>", JLabel.CENTER);
            add(inicio);

        }
}
