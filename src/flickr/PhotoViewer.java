package flickr;

import java.util.*;                         // For collections and properties.
import java.io.*;                           // For file handling.
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author Islam Dudaev
 */
public class PhotoViewer extends JFrame implements MouseListener
{
    ListOfPhotos list;
     
    /*
     * Starts the application
     */
    public PhotoViewer()
    {
        super("Photo viewer");                                         //set title
         
        list = new ListOfPhotos();                           //do query and get results
    
        PhotoPanel photoPanel = new PhotoPanel();                        //Create new panel and...
        photoPanel.setImage(list.getPhotoURLs().get(0));     //add first photo list
        photoPanel.addMouseListener(this);
         
        Container contentPane = getContentPane();
        contentPane.add(photoPanel);                                   //Add panel to window
 
        pack();                                                        //Size appropriately
        setVisible(true);                                              //Show
    }
     
     
     
    public void mouseClicked(MouseEvent e)
    {
        for(int i = 0; i < list.getPhotoURLs().size(); i++){
        ((PhotoPanel)e.getSource()).setImage(list.getPhotoURLs().get(i));//change the photo
        }
        //pack();       
    }
 
    public void mousePressed(MouseEvent e)
    {
    }
     
    public void mouseReleased(MouseEvent e)
    {
    }
     
    public void mouseEntered(MouseEvent e)
    {
    }
 
    public void mouseExited(MouseEvent e)
    {
    }
  
     
     
}