package leafeatingant;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author Andreas Manuel Korn
 */
public class Cell {

    private Rectangle2D.Float rectangle;
    Content content;

    public Cell(String s) {
        this.content = new Content(s);
    }
    
    public Cell(Rectangle2D.Float r){
        this.rectangle = r;
    }

    public Cell(Rectangle2D.Float r, String s) {
        this.rectangle = r;
        this.content = new Content(s);
    }
    
    public void changeContent(String s){
        this.content = new Content(s);
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.WHITE);
        g2d.fill(this.rectangle);
        this.content.paintComponent(g, this.rectangle.x, this.rectangle.y);
    }

    public Cell() {
        this.content = new Content();
    }

    public void emptyCell() {
        this.content = new Content(Content.EMPTY);
    }
}
