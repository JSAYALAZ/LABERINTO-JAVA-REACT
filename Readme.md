![alt text](ups2.jpeg) 
## Proyecto Laberinto
Laberinto es una aplicación interactiva y educativa diseñada para resolver laberintos utilizando diferentes algoritmos de búsqueda y recorridos. Esta aplicación permite a los usuarios crear laberintos personalizados y visualizar cómo se resuelven en tiempo real, demostrando la naturaleza de los graphos.


## Autores
- José Ayala ayalaz1@est.ups.edu.ec
- Rafael Prieto pprietos@est.ups.edu.ec
- Steven Chimbo schimboc@est.ups.edu.ec

## Materia
Estructura de Datos

## Docente
Ing. Pablo Torres


## Requisitos

- Node.js
- JDK 22

## Puertos Habilitados

- Puerto 8080 para el backend
- Puerto 3000 para el frontend

## Instrucciones de Ejecución

### Frontend (React)

1. Navegar al directorio del cliente:

    ```bash
    cd laberinto-client
    ```
2. Instalacion de nextjs

    ```bash
    npm i react@latest
    ```

3. Instalar las dependencias:

    ```bash
    npm i
    ```

4. Ejecutar el servidor de desarrollo:

    ```bash
    npm run dev
    ```

### Backend (Java)

1. Navegar al directorio del backend:

    ```bash
    cd labyrinth
    ```

2. Limpiar el proyecto:

    ```bash
    ./mvnw clean
    ```

3. Ejecutar la aplicación Spring Boot:

    ```bash
    ./mvnw spring-boot:run
    ```



## Descripción del Problema
El problema consiste en encontrar el camino más corto entre dos puntos, A que es el inicio y B el destino hacia dónde queremos llegar, en un laberinto definido como una matriz de celdas. Cada celda puede ser transitable o no transitable. Utilizaremos una combinación de algoritmos de búsqueda y una interfaz web para visualización.

## Propuesta de Solución
Implementaremos y compararemos cuatro algoritmos distintos para resolver el problema:
1. **Método Recursivo Simple**: Explora todos los caminos posibles desde el punto de inicio hasta el destino de manera recursiva. Es sencillo pero ineficiente para laberintos grandes debido a su alta complejidad temporal (exponencial).
2. **Método de Programación Dinámica**: Utiliza una tabla de memorización para almacenar resultados parciales y evitar cálculos redundantes. Más eficiente que el método recursivo simple, pero puede ser complicado de implementar.
3. **Búsqueda en Anchura (BFS)**: Explora los nodos del laberinto nivel por nivel, garantizando encontrar el camino más corto en un laberinto no ponderado. Eficiente en términos de tiempo y memoria, ideal para este tipo de problemas.
4. **Búsqueda en Profundidad (DFS)**: Explora los caminos posibles profundizando en cada camino antes de retroceder. No garantiza encontrar el camino más corto, pero útil para explorar todos los posibles caminos.

## Implementación
### Estructura del Proyecto
- Se utilizará Java para implementar la lógica de los algoritmos.
- La interfaz gráfica se implementará usando REACT para visualizar el laberinto y los caminos encontrados.

## Marco Teórico
### BFS (Breadth-First Search)
BFS es un algoritmo de búsqueda en grafos que explora todos los nodos a un mismo nivel antes de pasar al siguiente nivel. Es útil para encontrar el camino más corto en un laberinto o grafo no ponderado.

### DFS (Depth-First Search)
DFS es un algoritmo de búsqueda en grafos que explora lo más profundamente posible en una rama antes de retroceder. Aunque no garantiza encontrar el camino más corto, es útil para explorar todas las posibles rutas.

### Programación Dinámica
La programación dinámica es una técnica de optimización que se utiliza para resolver problemas complejos dividiéndolos en subproblemas más simples. Utiliza una tabla para almacenar los resultados de los subproblemas ya resueltos para evitar cálculos redundantes.

### Métodos Recursivos
Los métodos recursivos implican que una función se llama a sí misma para resolver subproblemas. Aunque pueden ser intuitivos y fáciles de implementar, pueden ser ineficientes debido a la redundancia en los cálculos y la alta complejidad temporal.


### Visualización en la Web
La visualización del laberinto en REACT. La interacción entre la lógica de los algoritmos implementados en Java y la visualización en la web es crucial para proporcionar una experiencia de usuario intuitiva.

## Tecnologías Utilizadas

### Backend
- **Java**
- **Spring Boot**

### Frontend
- **React**
- **Next.js**
- **Tailwind CSS**
- **Axios**

## Criterio por Integrante de la Propuesta
Cada integrante del equipo evaluará los siguientes criterios:
- **José Ayala**: Eficiencia del Algoritmo en cuanto al tiempo de ejecución y uso de memoria.
- **Steven Chimbo**: Claridad del Código en cuanto facilidad de lectura y mantenimiento.
- **Rafael Prieto**: Correctitud del Algoritmo en cuanto a la Verificación de que el algoritmo encuentra el camino correcto.

## Capturas de la Implementación Final de la UI
![alt text](interfaz1.png)
![alt text](interfaz2.png)


## Conclusiones
### Comparación de Métodos
- **Recursiva (DFS)**
  - Pros: Fácil de implementar, buena para encontrar cualquier camino.
  - Contras: Puede ser ineficiente en términos de tiempo y espacio para laberintos grandes debido a la profundidad de la recursión.

- **Búsqueda en Anchura (BFS)**
  - Pros: Garantiza encontrar el camino más corto en un grafo no ponderado.
  - Contras: Puede consumir más memoria debido a la necesidad de almacenar todos los nodos en el nivel actual.

- **Programación Dinámica**
  - Pros: Optimiza el tiempo de ejecución al almacenar los resultados de subproblemas ya resueltos.
  - Contras: La implementación puede ser más compleja y requiere memoria adicional para almacenar los resultados.

### Mejor Opción
La mejor opción para encontrar el camino más corto en un laberinto es la Búsqueda en Anchura (BFS). Esto se debe a que BFS explora todos los caminos posibles nivel por nivel, garantizando así el camino más corto en un grafo no ponderado. Aunque puede consumir más memoria, su capacidad para encontrar la solución óptima de manera consistente la hace superior a otros métodos en términos de encontrar el camino más corto.

### Consideraciones
#### Posibles Mejoras
- Implementación del algoritmo A* para considerar laberintos con pesos en las celdas.
- Optimización de la memoria utilizando estructuras de datos más eficientes.
- Extensión a laberintos tridimensionales.

Cada estudiante podría enfocarse en:
- **Steven Chimbo**: Investigación y documentación de algoritmos de búsqueda.
- **José Ayala**: Desarrollo de la interfaz de usuario y visualización de rutas.
- **Rafael Prieto**: Pruebas y validación de los algoritmos implementados.

## Conclusiones
### Conclusion de cada estudiante

- *José Ayala*:
Me enfoque principalmente en el desarrollo de la interfaz de usuario.Trabajar en la interfaz me permitió comprender la importancia de una buena visualización y usabilidad en el análisis de algoritmos. La capacidad de ver los caminos generados por los diferentes métodos en tiempo real fue esencial para apreciar las diferencias en eficiencia y efectividad de cada algoritmo. La integración de los componentes y la facilidad de uso del programa son aspectos clave que mejoran significativamente la experiencia del usuario.

Este proyecto me enseñó que una interfaz gráfica bien diseñada es crucial para la comprensión de los algoritmos. La facilidad de interacción y visualización clara de los resultados hace que el análisis y la comparación de métodos sean efectivos. Además, la colaboración con mis compañeros resaltó la importancia del trabajo en equipo para lograr un producto final funcional.

- *Rafael Prieto*:
Me enfoque principalmente en la codificación de los métodos de búsqueda y resolución del laberinto.Implementar los métodos de búsqueda como DFS, BFS, recursividad y dinámico me permitió profundizar en el funcionamiento interno de estos algoritmos. Observando sus resultados en la interfaz gráfica, pude ver claramente los pros y los contras de cada uno. Este proceso me enseñó la importancia de la eficiencia del algoritmo.

Este proyecto demostró la importancia de implementar y comparar diferentes algoritmos para entender sus eficiencias. La posibilidad de ver los resultados en una interfaz clara y funcional me ayudó a apreciar mejor el impacto de cada método en la resolución de laberintos. La colaboración con el equipo fue esencial para integrar las diferentes partes del proyecto de la mejor manera.

- *Steven Chimbo*
Me enfoque en el desarrollo del README, las especificaciones del proyecto y el informe. Documentar el proyecto fue una experiencia significativa, ya que me permitió consolidar y explicar claramente cada parte del proyecto y su funcionamiento. La redacción del README y el informe me ayudaron a entender la importancia de las especificaciones que debe tener un programa en relacion con Estructura de Datos. Esto facilita el uso del programa y también proporciona un recurso valioso para futuros desarrollos.