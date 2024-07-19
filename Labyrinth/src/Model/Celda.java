

public class Celda {
    int row; // Fila de la celda
    int col; // Columna de la celda
    boolean visited;

    public Celda(int row, int col) {
        this.row = row;
        this.col = col;
        this.visited = false;
    }

    @Override
    public int hashCode() {
        final int prime = 31; // Constante arbitraria para generar el código hash
        int result = 1; // Valor inicial del resultado
        result = prime * result + row; // Incorporamos la fila al código hash
        result = prime * result + col; // Incorporamos la columna al código hash
        return result; // Devolvemos el código hash generado
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    

    
}
