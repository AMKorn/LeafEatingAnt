package leafeatingant;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

/**
 * Clase encargada de trabajar con las celdas.
 * @author Andreas Manuel Korn
 */
public class Cell {

    private Rectangle2D.Float rectangle; // La forma de la celda
    private Content content; // Almacena el contenido de cada celda
    boolean empty; // Booleano para saber si en ella hay hoja o no.
    
    /**
     * Constructor de la clase celda. La establece como hoja por defecto
     * y como no vacía.
     * @param r 
     */
    public Cell(Rectangle2D.Float r){
        this.rectangle = r;
        content = new Content(Content.LEAF);
        empty = false;
    }
    
    /**
     * Método para cambiar el contenido de una celda. Si la vacía, la marca 
     * como tal.
     * @param cont - el directorio donde se encuentra el icono del contenido
     */
    public void changeContent(String cont){
        this.content = new Content(cont);
        if(cont.equals(Content.EMPTY)){
            empty = true;
        }
    }
    
    /**
     * Método que devuelve true si la casilla está vacía.
     * @return empty
     */
    public boolean isEmpty(){
        return empty;
    }

    public void paintComponent(Graphics g) {
        this.content.paintComponent(g, this.rectangle.x, this.rectangle.y);
    }
}
