package leafeatingant;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

/**
 *
 * @author Andreas Manuel Korn
 */
public class LeafEatingAnt extends JFrame implements KeyListener {

    public Board board;

    public LeafEatingAnt() {
        super("La hormiga come hojas");
        board = new Board();
        this.getContentPane().add(board);
        this.setSize(board.getPreferredSize());
//        this.setBackground(Color.BLACK);
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
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent ke) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        System.out.println("I'm here");
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        int key = ke.getKeyCode();
        if (key == ke.VK_UP) {
            board.changeDirection("UP");
        } else if (key == ke.VK_DOWN) {
            board.changeDirection("DOWN");
        } else if (key == ke.VK_LEFT) {
            board.changeDirection("LEFT");
        } else if (key == ke.VK_RIGHT) {
            board.changeDirection("RIGHT");
        }
    }

}
