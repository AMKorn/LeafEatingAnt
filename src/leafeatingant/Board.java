package leafeatingant;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.util.Random;
import javax.swing.JPanel;

/**
 *
 * @author Andreas Manuel Korn
 */
public class Board extends JPanel {

    private static final int DIMENSION = 20;
    private static final int SIZE = 800;
    public static final int SIDE = SIZE / DIMENSION;
    private Cell Board[][];

    private Random rnd = new Random();

    private int[] posAnt = {rnd.nextInt(DIMENSION), rnd.nextInt(DIMENSION)};
    
    public Board() {
        this.posAnt = new int[2];
        Board = new Cell[DIMENSION][DIMENSION];
        int y = 0;
        for (int i = 0; i < DIMENSION; i++) {
            int x = 0;
            for (int j = 0; j < DIMENSION; j++) {
                Rectangle2D.Float rectangle = new Rectangle2D.Float(x, y, SIDE, SIDE);
                Board[i][j] = new Cell(rectangle, Content.LEAF);
                x += SIDE;
            }
            y += SIDE;
        }
        Board[posAnt[0]][posAnt[1]].changeContent(Content.ANT_U);
    }

    @Override
    public void paintComponent(Graphics g) {
        for (int i = 0; i < DIMENSION; i++) {
            for (int j = 0; j < DIMENSION; j++) {
                Board[i][j].paintComponent(g);
            }
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(SIZE, SIZE);
    }
}
