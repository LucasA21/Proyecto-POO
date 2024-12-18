## Readme


### View Principal

![ViewPrincipal.png](assets/ViewPrincipal.png)

### Diagrama de clases

![Diagrama_de_clases.png](assets/Diagrama_de_clases.png)

### Modelos

- `Carrera:` La carrera esta compuesta por un nombre y  un tipo de  plan de estudio.
- `Plan de estudio:`El plan de estudio esta compuesto por un tipo de plan (A, B, C, D, E) y las materias.
- `Materias:` Las materias estan compuestas por un nombre, el cuatrimetre en el que se encuentra, un campo que verifica si es obligatoria y una lista de materias correlativas a la misma
- `Alumno:` El alumno esta compuesto por DNI para identificarlo, con un nombre y una lista de las materias aprobadas.

---
### Clases intermedias

- `InscripcionMateria:` Esta compuesta por Alumno y Materia 

---
### Funcionalidades

- `Crear plan de estudio:` se debe poder crear un plan de estudio, asignandole un tipo de plan y las materias.
- `Crear carrera:` se debe poder crear una carrera, asignandole un nombre y un tipo de plan de estudio.
- `Crear materia:` se debe poder crear una materia, asignandole un cuatrimestre, asignandole un nombre, si es obligatoria o no y si tiene materias correlativas.
- `Inscribir a carrera:` se debe poder inscribir un alumno a una carrera.
- `Inscribir a materia:` se debe poder inscribir a materias, eligiendo la carrera para elegir las materias que le correspondan.
- `Cargas notas:` se debe poder cargar si un alumno aprobo o promociono( si promociona no hay que verificar el final ) la cursada de la materia, y si aprobo el final.
- `Verificar si un alumno se graduo:` el sistema debe verificar si el alumno finalizo todas las materias obligatorias y la cantidad de materias optativas necesarias.

---

### Vistas

La idea de las vistas es usar un template para predefinir un mismo marco para todas las vistas y lo unico que cambie en cada vista es el contenido interno.

- `Ventana principal:` esta va a ser la vista general del proyecto, va a tener los siguientes botones:
  - **Agregar materias**
  - **Agregar carrera**
  - **Inscribir a carrera**
  - **Inscribir a materia**
  - **Cargar notas** 
  - **Verificar estado alumno**

- `Crear alumno`: en esta vista se va a tener un formulario con los siguientes campos:
  - **Dni**
  - **Nombre**


- `Crear materia:` en esta vista se va a tener un formulario con los siguientes campos:
  - **Nombre materia**
  - **Es obligatoria? (checkbox)**
  - **Es promocionable? (checkbox)**
  - **Tiene materias correlativas? (un toggle donde se va a poder seleccionar una o mas materias correlativas vinculadas)**

- `Crear Plan de estudio:` en esta vista se va a tener un formulario con los siguientes campos:
  - **Nombre plan estudio**
  - **toggle con los tipos de plan de estudio**
  - **toggle con las materias**

- `Crear carrera:` en esta vista se va a tener un formulario con los siguientes campos:
  - **Nombre carrera**
  - **toggle con los planes de estudio creados**
  
- `Inscribir a carrera:` en esta vista se va a tener un formulario con los siguiente campos:
  - **un toggle con los alumnos disponibles**
  - **un toggle con las carreras disponibles**

- `Inscribir a materia:` en esta vista se va a tener un formulario con los siguientes campos:
  - **un toggle con los alumnos disponibles**
  - **un toggle con las carreras disponibles**

- `Cargar notas:` en esta vista se va a tener un formulario con los siguientes campos:
  - **un toggle para seleccionar al alumno**
  - **un toggle para seleccionar la materia**
  - **un toggle para seleccionar si aprobo cursada, promociono o aprobo final**

- `Verificar estado alumno`: en esta vista se va a imprimir toda la informacion de un alumno especifico, con la carrera a la que esta inscripto, las materias aprobadas, y si ya esta graduado o no.
  - **un toggle para seleccionar el alumno**



## Relaciones
  - Carrera y Plan de estudio (1:1)
  - Plan de estudio y materia (1:1)
  - Alumno y Carrera (1,1)
  - Alumno y Libreta (1,1)
  - Libreta y Materia (1,N)
  - Materias correlativas --> Relacion recursiva con materia

## Patrones
  
  - Patron MVC
  - Patron observer: este patron fue utilizado para actualizar las materias dinamicamente
    en sus vistas correspondientes.

