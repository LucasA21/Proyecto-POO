## Readme

### Modelos

- `Carrera:` La carrera esta compuesta por un nombre , las materias y  un tipo de  plan de estudio.
- `Plan de estudio:`El plan de estudio esta compuesto por un tipo de plan (A, B, C, D, E).
- `Materias:` Las materias estan compuestas por un nombre, un campo que verifica si es obligatoria y una lista de materias correlativas a la misma
- `Alumno`: El alumno esta compuesto por DNI para identificarlo, con un nombre y una lista de las materias aprobadas.

---

### Funcionalidades

- `Crear carrera`: se debe poder crear una carrera, asignandole un nombre, las materias obligatorias, las materias optativas y un tipo de plan de estudio.
- `Crear materia`: se debe poder crear una materia, asignandole un nombre, si es obligatoria o no y si tiene materias correlativas.
- `Asginar alumno`: se debe poder inscribir un alumno a una carrera y el alumno se debe poder anotar a las materias ( el sistema debe mostrar al alumno las materias a las que se puede anotar ).
- `Cargas notas`: se debe poder cargar si un alumno aprobo o promociono( si promociona no hay que verificar el final ) la cursada de la materia, y si aprobo el final.
- 'Verificar si un alumno se graduo': el sistema debe verificar si el alumno finalizo todas las materias obligatorias y la cantidad de materias optativas necesarias.

---

### Vistas

La idea de las vistas es usar un template para predefinir un mismo marco para todas las vistas y lo unico que cambie en cada vista es el contenido interno.

- `Ventana principal`: esta va a ser la vista general del proyecto, va a tener los siguientes botones:
  - **Agregar materias**
  - **Agregar carrera**
  - **Inscribir alumno**
  - **Cargar notas** 
  - **Verificar estado alumno**

- `Agregar materias`: en esta vista se va a tener un formulario con los siguientes campos:
  - **Nombre materia**
  - **Es obligatoria? (checkbox)**
  - **Tiene materias correlativas? (un toggle donde se va a poder seleccionar una o mas materias correlativas vinculadas)**

- `Agregar carrera`: en esta vista se va a tener un formulario con los siguientes campos:
  - **Nombre carrera**
  - **Tipo plan de estudio (un toggle con los tipos de plan)**
  - **Agregar materias obligatorias (un toggle para seleccionar una o mas materias)**
  - **Agregar materias optativas (un toggle para sleeccionar una o mas materias)**
  
- `Inscribir alumno`: en esta vista se va a tener un formulario con los siguiente campos:
  - **un toggle con los alumnos disponibles o un string para ingresar el dni del alumno**
  - **un toggle con las carreras disponibles**

- `Cargar notas`: en esta vista se va a tener un formulario con los siguientes campos:
  - **un toggle para seleccionar al alumno**
  - **un toggle para seleccionar la materia**
  - **un toggle para seleccionar si aprobo cursada, promociono o aprobo final**

- `Verificar estado alumno`: en esta vista se va a imprimir toda la informacion de un alumno especifico, con la carrera a la que esta inscripto, las materias aprobadas, y si ya esta graduado o no.
  - **un toggle para seleccionar el alumno**
