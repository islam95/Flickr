package flickr;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * Class to view photos.
 * 
 * @author Islam Dudaev
 */
public class PhotoPanel extends JPanel{
    
    private BufferedImage img;
    
  
    public void setImage(String url){
    
        try{
            img = ImageIO.read(new URL(url));
        }
        catch(IOException e){
            e.printStackTrace();
        }
        
        repaint();
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(img, 0, 0, null);
    }
    
    @Override
    public Dimension getPreferredSize(){
        return new Dimension(img.getWidth(), img.getHeight());
    }
    
}

