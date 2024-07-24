# Proyecto Laberinto

## Descripción del problema

Un jugador está en la esquina superior izquierda (0,0) de un tablero m x n. En el tablero hay celdas transitables (`true`) y no transitables (`false`). El objetivo es encontrar un camino válido para ir a la esquina inferior derecha, sabiendo que solo se puede mover hacia abajo y hacia la derecha.

### Ejemplo 1:

Input:
[
[true, true, true, true],
[false, false, false, true],
[true, true, false, true],
[true, true, false, true]
]

Output: `[(0,0), (0,1), (0,2), (0,3), (1,3), (2,3), (3,3)]`

### Ejemplo 2:

Input:
[
[true, true, true, true],
[false, true, true, true],
[true, true, false, false],
[true, true, true, true]
]

Output: `[(0,0), (0,1), (1,1), (2,1), (3,1), (3,2), (3,3)]`

## Marco Teórico

### Programación Dinámica
La programación dinámica es una técnica de optimización que se utiliza para resolver problemas complejos dividiéndolos en subproblemas más simples y almacenando los resultados de estos subproblemas para evitar cálculos redundantes. En este proyecto, se usa para almacenar los resultados de las celdas visitadas y así mejorar la eficiencia del algoritmo.

### Búsqueda en Profundidad (DFS)
La búsqueda en profundidad (DFS) es un algoritmo de búsqueda que se adentra en las ramas del grafo hasta llegar al final de una rama antes de retroceder y explorar otras ramas. Es útil para problemas donde se necesita explorar todos los posibles caminos.

### Búsqueda en Anchura (BFS)
La búsqueda en anchura (BFS) es un algoritmo de búsqueda que explora todas las celdas adyacentes de una celda antes de profundizar en cada una de ellas. Es ideal para encontrar el camino más corto en un grafo no ponderado.

## Descripción de la propuesta de solución

### Herramientas y Lenguajes Utilizados
- **Lenguaje**: Java
- **Estructuras de Datos**: Lista de Adyacencia para representar el grafo, `List<String>` para almacenar los caminos.
- **Algoritmos**: DFS, BFS, Programación Dinámica.

### Solución Implementada

1. **Definición de la estructura del grafo**:
   - Usamos `Map<String, List<String>>` para representar el grafo, donde cada celda es una clave y sus valores son las celdas adyacentes transitables.

2. **Método `buildGraph`**:
   - Construimos el grafo a partir de una matriz de booleanos. Por cada celda transitable (`true`), añadimos las celdas adyacentes (abajo y derecha) al grafo.

3. **Método `addEdge`**:
   - Añade una arista (conexión) en el grafo entre dos celdas.

4. **Método `getPathDFS` y `dfs`**:
   - Implementa la búsqueda DFS. Utilizamos un `Set<String>` para rastrear las celdas visitadas y un `List<String>` para almacenar el camino.
   - El método `dfs` realiza la búsqueda recursiva. Si encuentra el final, construye el camino.

5. **Método `getPathBFS`**:
   - Implementa la búsqueda BFS. Utilizamos una `Queue<String>` para manejar la búsqueda en anchura y un `Map<String, String>` para rastrear los padres de cada celda, lo que nos permite construir el camino.

6. **Método `getBestPath`**:
   - Compara los caminos devueltos por DFS y BFS, y selecciona el más corto.

### Código Implementado

```java
import java.util.*;

public class LaberintoGraph {

    private Map<String, List<String>> graph = new HashMap<>();

    private void addEdge(String from, String to) {
        graph.computeIfAbsent(from, k -> new ArrayList<>()).add(to);
    }

    public void buildGraph(boolean[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j]) {
                    String cell = i + "," + j;
                    if (i + 1 < rows && grid[i + 1][j]) addEdge(cell, (i + 1) + "," + j);
                    if (j + 1 < cols && grid[i][j + 1]) addEdge(cell, i + "," + (j + 1));
                }
            }
        }
    }

    public List<String> getPathDFS() {
        List<String> path = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        String start = "0,0";
        String end = (graph.size() - 1) + "," + (graph.size() - 1);
        if (dfs(start, end, visited, path)) {
            Collections.reverse(path);
            return path;
        }
        return new ArrayList<>();
    }

    private boolean dfs(String current, String end, Set<String> visited, List<String> path) {
        if (!graph.containsKey(current) || visited.contains(current)) return false;
        visited.add(current);
        if (current.equals(end)) {
            path.add(current);
            return true;
        }
        for (String neighbor : graph.get(current)) {
            if (dfs(neighbor, end, visited, path)) {
                path.add(current);
                return true;
            }
        }
        return false;
    }

    public List<String> getPathBFS() {
        List<String> path = new ArrayList<>();
        Queue<String> queue = new LinkedList<>();
        Map<String, String> parent = new HashMap<>();
        String start = "0,0";
        String end = (graph.size() - 1) + "," + (graph.size() - 1);
        queue.add(start);
        parent.put(start, null);

        while (!queue.isEmpty()) {
            String current = queue.poll();
            if (current.equals(end)) {
                while (current != null) {
                    path.add(current);
                    current = parent.get(current);
                }
                Collections.reverse(path);
                return path;
            }
            for (String neighbor : graph.getOrDefault(current, new ArrayList<>())) {
                if (!parent.containsKey(neighbor)) {
                    queue.add(neighbor);
                    parent.put(neighbor, current);
                }
            }
        }
        return new ArrayList<>();
    }

    public List<String> getBestPath() {
        List<String> pathDFS = getPathDFS();
        List<String> pathBFS = getPathBFS();

        if (pathDFS.isEmpty()) return pathBFS;
        if (pathBFS.isEmpty()) return pathDFS;

        return (pathDFS.size() < pathBFS.size()) ? pathDFS : pathBFS;
    }

    public static void main(String[] args) {
        boolean[][] grid = {
            {true, true, true, true},
            {false, false, false, true},
            {true, true, false, true},
            {true, true, false, true}
        };

        LaberintoGraph laberinto = new LaberintoGraph();
        laberinto.buildGraph(grid);
        List<String> bestPath = laberinto.getBestPath();
        System.out.println("Mejor camino encontrado: " + bestPath);
    }
}
Conclusiones
Comparación de Métodos
Recursiva (DFS)

Pros: Fácil de implementar, buena para encontrar cualquier camino.
Contras: Puede ser ineficiente en términos de tiempo y espacio para laberintos grandes debido a la profundidad de la recursión.
Búsqueda en Anchura (BFS)

Pros: Garantiza encontrar el camino más corto en un grafo no ponderado.
Contras: Puede consumir más memoria debido a la necesidad de almacenar todos los nodos en el nivel actual.
Programación Dinámica

Pros: Optimiza el tiempo de ejecución al almacenar los resultados de subproblemas ya resueltos.
Contras: La implementación puede ser más compleja y requiere memoria adicional para almacenar los resultados.
Mejor Opción
La mejor opción para encontrar el camino más corto en un laberinto es la Búsqueda en Anchura (BFS). Esto se debe a que BFS explora todos los caminos posibles nivel por nivel, garantizando así el camino más corto en un grafo no ponderado. Aunque puede consumir más memoria, su capacidad para encontrar la solución óptima de manera consistente la hace superior a otros métodos en términos de encontrar el camino más corto.
