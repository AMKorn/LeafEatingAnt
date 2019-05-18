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
    private static final int SIDE = SIZE / DIMENSION;
    private Cell Board[][];

    private enum direction {
        UP, DOWN, LEFT, RIGHT
    }
    private static direction currentDirection;

    private Random rnd = new Random();
    private Graphics g;
    private static int numOfLeaves = DIMENSION * DIMENSION - 1;

    private int[] posAnt;

    public Board() {
        this.posAnt = new int[2];
        Board = new Cell[DIMENSION][DIMENSION];
        int y = 0;
        for (int i = 0; i < DIMENSION; i++) {
            int x = 0;
            for (int j = 0; j < DIMENSION; j++) {
                Rectangle2D.Float rectangle = new Rectangle2D.Float(x, y, SIDE, SIDE);
                Board[i][j] = new Cell(rectangle/*, Content.LEAF*/);
                x += SIDE;
            }
            y += SIDE;
        }
        posAnt[0] = rnd.nextInt(DIMENSION);
        posAnt[1] = rnd.nextInt(DIMENSION);
        Board[posAnt[0]][posAnt[1]].changeContent(Content.ANT_U);
        currentDirection = direction.UP;
    }

    public int getNumOfLeaves() {
        return numOfLeaves;
    }

    public void removeLeaf() {
        numOfLeaves--;
    }

//    public int[] getPosAnt(){
//        return posAnt;
//    }
    public void changeDirection(String s) {
        switch (s) {
            case "UP":
                Board[posAnt[0]][posAnt[1]].changeContent(Content.ANT_U);
                currentDirection = direction.UP;
                break;
            case "DOWN":
                Board[posAnt[0]][posAnt[1]].changeContent(Content.ANT_D);
                currentDirection = direction.DOWN;
                break;
            case "LEFT":
                Board[posAnt[0]][posAnt[1]].changeContent(Content.ANT_L);
                currentDirection = direction.LEFT;
                break;
            case "RIGHT":
                Board[posAnt[0]][posAnt[1]].changeContent(Content.ANT_R);
                currentDirection = direction.RIGHT;
                break;
        }
    }

    public void moveAnt() {
        try{
            switch (currentDirection) {
            case UP:
                if (!Board[posAnt[0] - 1][posAnt[1]].isEmpty()) {
                    removeLeaf();
                }
                Board[posAnt[0] - 1][posAnt[1]].changeContent(Content.ANT_U);
                Board[posAnt[0]][posAnt[1]].changeContent(Content.EMPTY);
                posAnt[0]--;
                break;
            case DOWN:
                if (!Board[posAnt[0] + 1][posAnt[1]].isEmpty()) {
                    removeLeaf();
                }
                Board[posAnt[0] + 1][posAnt[1]].changeContent(Content.ANT_D);
                Board[posAnt[0]][posAnt[1]].changeContent(Content.EMPTY);
                posAnt[0]++;
                break;
            case LEFT:
                if (!Board[posAnt[0]][posAnt[1] - 1].isEmpty()) {
                    removeLeaf();
                }
                Board[posAnt[0]][posAnt[1] - 1].changeContent(Content.ANT_L);
                Board[posAnt[0]][posAnt[1]].changeContent(Content.EMPTY);
                posAnt[1]--;
                break;
            case RIGHT:
                if (!Board[posAnt[0]][posAnt[1] + 1].isEmpty()) {
                    removeLeaf();
                }
                Board[posAnt[0]][posAnt[1] + 1].changeContent(Content.ANT_R);
                Board[posAnt[0]][posAnt[1]].changeContent(Content.EMPTY);
                posAnt[1]++;
                break;
            }
        } catch (ArrayIndexOutOfBoundsException e){
        } catch (Exception e){
            System.err.println(e);
        }
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
