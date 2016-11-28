/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package photoViewerApp;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author islam
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
