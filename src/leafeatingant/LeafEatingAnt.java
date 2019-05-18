/**
 * Este juego consiste en una hormiga colocada aleatoriamente en un taablero
 * 20x20 y tiene que comerse todas las hojas. La dirección se controla con las
 * flechas, y se mueve pulsando espacio.
 * */
package leafeatingant;

import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

/**
 * La clase principal es la encargada de construir la ventana y del seguimiento 
 * de las teclas.
 * @author Andreas Manuel Korn
 */
public class LeafEatingAnt extends JFrame implements KeyListener {

    public Board board;

    public LeafEatingAnt() {
        super("La hormiga come hojas");
        board = new Board();
        this.getContentPane().add(board);
        this.setSize(board.getPreferredSize());
        this.pack();
        this.setResizable(false);
        this.addKeyListener(this);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        LeafEatingAnt lea = new LeafEatingAnt();
        lea.setVisible(true);
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        // No hay función
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        // No hay función
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        int key = ke.getKeyCode();
        switch (key) { // Comprueba la tecla pulsada.
            case KeyEvent.VK_UP:
                board.changeDirection("UP");
                break;
            case KeyEvent.VK_DOWN:
                board.changeDirection("DOWN");
                break;
            case KeyEvent.VK_LEFT:
                board.changeDirection("LEFT");
                break;
            case KeyEvent.VK_RIGHT:
                board.changeDirection("RIGHT");
                break;
            case KeyEvent.VK_SPACE:
                board.moveAnt(); // La hormiga no se mueve hasta que se pulse espacio.
                break;
            default:
                break;
        }
        board.repaint();
        if (board.getNumOfLeaves() == 0) {
            Toolkit.getDefaultToolkit().beep();
            ImageIcon icon = new ImageIcon("sprites/hoja.png");
            JOptionPane.showMessageDialog(null, "¡Te has comido todas las hojas!", // Salta un mensaje de victoria cuando la hormiga se come
                    "¡Victoria!", JOptionPane.INFORMATION_MESSAGE, icon);          // todas las hojas.
            System.exit(0); // Cierra el programa cuando se han comido todas las hojas.
        }
    }

}
