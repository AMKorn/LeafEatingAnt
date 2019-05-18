package leafeatingant;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Andreas Manuel Korn
 */
public class Content {
    
    public static final String ANT_R = "sprites/hormiga_e.png";
    public static final String ANT_L = "sprites/hormiga_o.png";
    public static final String ANT_U = "sprites/hormiga_n.png";
    public static final String ANT_D = "sprites/hormiga_s.png";
    public static final String LEAF = "sprites/hoja.png";
    public static final String EMPTY = "sprites/nada.png";
    
    private BufferedImage img;
    
    public Content(String s){
        try {
            img = ImageIO.read(new File(s));
        } catch (IOException e){
            System.err.println(e);
        }
    }
    
    public Content(){
        try {
            img = ImageIO.read(new File(EMPTY));
        } catch (IOException e){
            System.err.println(e);
        }
    }
    
    void paintComponent(Graphics g, float x, float y) {
        g.drawImage(img,(int) x, (int) y, null);
    }
}
