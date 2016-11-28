
package photoViewerApp;

import java.util.*;                         // For collections and properties.
import java.io.*;                           // For file handling.
 
import javax.swing.*;
import java.awt.*;
 
import java.awt.event.*;

/**
 *
 * @author islam
 */
public class PhotoViewerApp extends JFrame implements MouseListener
{
    PhotoListQuery photoListQuery;
    int urlIndex=0; //the index of the URL being displayed
     
     
    //Main method
    public static void main(String[] args)
    {
        new PhotoViewerApp();
    }
     
     
    /*
     * Starts the application
     */
    public PhotoViewerApp()
    {
        super("Photo viewer");                                         //set title
         
        photoListQuery=new PhotoListQuery();                           //do query and get results
    
        PhotoPanel photoPanel=new PhotoPanel();                        //Create new panel and...
        photoPanel.setImage(photoListQuery.getPhotoURLs().get(0));     //add first photo list
        photoPanel.addMouseListener(this);
         
        Container contentPane = getContentPane();
        contentPane.add(photoPanel);                                   //Add panel to window
 
        pack();                                                        //Size appropriately
        setVisible(true);                                              //Show
    }
     
     
     
    public void mouseClicked(MouseEvent e)
    {
       urlIndex++;                                                           //go to next photo
       System.out.println(urlIndex);
        if (urlIndex>=photoListQuery.getPhotoURLs().size())
        {
            urlIndex=0;
        }
        ((PhotoPanel)e.getSource()).setImage(photoListQuery.getPhotoURLs().get(urlIndex));//change the photo
        pack();       
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