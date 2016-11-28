package NewProj;
//import javax package for Swing components and for image input and output
import javax.swing.*;
import javax.imageio.*;

//import java package for networking applications, and for AWT components, and for input and output
import java.net.*;
import java.awt.*;
import java.awt.image.*;
import java.io.*;

/**
 * program that displays a photograph from a URL
 * @author Islam Dudaev
 * @version 1.0, 13 December, 2014
 */
public class PhotoPanel extends JPanel
{
   //object variables
    private BufferedImage image;

    /**
     * method sets the image from a URL
     * @param url String image URL
     */
    public void setImage(String url)
    {
        try
        {
            image = ImageIO.read(new URL(url));
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        repaint();
    }

    /**
     * method tells the layout manager size of the image
     * @return the dimensions of the preferred size which are the dimensions of the current image.
     */
    @Override
    public Dimension getPreferredSize()
    {
        return new Dimension(image.getWidth(), image.getHeight());
    }

    /**
     * method tells the panal how to paint itself
     * @param g A Graphics object to draw with
     */
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);
    }
}
