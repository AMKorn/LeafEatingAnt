package leafeatingant;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.util.Random;
import javax.swing.JPanel;

/**
 * Clase encargada de trabajar con el tablero y cada situación del juego.
 * @author Andreas Manuel Korn
 */
public class Board extends JPanel {

    private static final int DIMENSION = 20; // Número de celdas
    private static final int SIDE = 40; // Pixeles que ocupa cada icono
    private static final int SIZE = SIDE * DIMENSION; // Pixeles que ocupa en total cada lado de la ventana
    private Cell Board[][]; // Declaración de que un tablero es un array bidimensional de celdas

    private enum direction {
        UP, DOWN, LEFT, RIGHT
    }

    private int numOfLeaves = DIMENSION * DIMENSION - 1; // Cantidad de hojas en el tablero. Es la cantidad de celdas en el tablero, menos la celda de la hormiga.
    private direction currentDirection; // Almacena la dirección actual de la hormiga
    private int[] posAnt; // Almacena la posición actual de la hormiga

    private Random rnd = new Random();

    /**
     * Constructor del tablero. Coloca automáticamente las hojas y luego coloca 
     * la hormiga en una posición aleatoria.
     */
    public Board() {
        this.posAnt = new int[2];
        Board = new Cell[DIMENSION][DIMENSION];
        int y = 0;
        for (int i = 0; i < DIMENSION; i++) {
            int x = 0;
            for (int j = 0; j < DIMENSION; j++) {
                Rectangle2D.Float rectangle = new Rectangle2D.Float(x, y, SIDE, SIDE);
                Board[i][j] = new Cell(rectangle);
                x += SIDE;
            }
            y += SIDE;
        }
        posAnt[0] = rnd.nextInt(DIMENSION);
        posAnt[1] = rnd.nextInt(DIMENSION);
        Board[posAnt[0]][posAnt[1]].changeContent(Content.ANT_U); // Coloca la hormiga en una posición aleatoria.
        currentDirection = direction.UP; // Establece la dirección de la hormiga hacia arriba por defecto
    }

    /**
     * Getter del número de hojas
     * @return numOfLeaves
     */
    public int getNumOfLeaves() {
        return numOfLeaves;
    }

    /**
     * Método para cambiar la dirección de la hormiga. Recibe un String que 
     * debe indicar la dirección deseada.
     * @param dir - "UP", "DOWN", "LEFT" or "RIGHT"
     */
    public void changeDirection(String dir) {
        switch (dir) {
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

    /**
     * Método para mover la hormiga hacia la dirección a la que mira.
     */
    public void moveAnt() {
        try {
            switch (currentDirection) {
                case UP:
                    if (!Board[posAnt[0] - 1][posAnt[1]].isEmpty()) { // En cada caso, mira si la celda de destino está vacía y,
                        numOfLeaves--; // en el caso de que no lo esté, resta una al valor de hojas total.
                    }
                    Board[posAnt[0] - 1][posAnt[1]].changeContent(Content.ANT_U); // Cambia el contenido de la celda de destino hacia la hormiga en la misma dirección.
                    Board[posAnt[0]][posAnt[1]].changeContent(Content.EMPTY); // Vacía acordemente la celda de la que viene la hormiga
                    posAnt[0]--; // Finalmente modifica acordemente la posición de la hormiga.
                    break;
                case DOWN:
                    if (!Board[posAnt[0] + 1][posAnt[1]].isEmpty()) {
                        numOfLeaves--;
                    }
                    Board[posAnt[0] + 1][posAnt[1]].changeContent(Content.ANT_D);
                    Board[posAnt[0]][posAnt[1]].changeContent(Content.EMPTY);
                    posAnt[0]++;
                    break;
                case LEFT:
                    if (!Board[posAnt[0]][posAnt[1] - 1].isEmpty()) {
                        numOfLeaves--;
                    }
                    Board[posAnt[0]][posAnt[1] - 1].changeContent(Content.ANT_L);
                    Board[posAnt[0]][posAnt[1]].changeContent(Content.EMPTY);
                    posAnt[1]--;
                    break;
                case RIGHT:
                    if (!Board[posAnt[0]][posAnt[1] + 1].isEmpty()) {
                        numOfLeaves--;
                    }
                    Board[posAnt[0]][posAnt[1] + 1].changeContent(Content.ANT_R);
                    Board[posAnt[0]][posAnt[1]].changeContent(Content.EMPTY);
                    posAnt[1]++;
                    break;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            // Catcher vacío para que no salte error en el caso de que la hormiga se choque con los límites del tablero.
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
