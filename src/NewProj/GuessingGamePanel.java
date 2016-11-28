package NewProj;

import java.util.*; //import java untility package for collections and properties
import java.io.*; //import java input/output package for file handling

import javax.swing.*; //import javax Swing components

import java.awt.*; //import java AWT components
import java.awt.event.*; //import java AWT package for event listeners
import com.flickr4java.flickr.photos.*; //import flickr4java package for flickr photos

/**
 * program that displays text and Flickr images by using the Flickr4Java package and the Flickr API
 * @author  Islam Dudaev
 * @version 1.0, 13 December, 2014
 */
public class GuessingGamePanel extends JFrame implements ActionListener
{
    //object variables
    private JPanel controlPanel; //allow to view and set a range of parameters cotrolling
    private JLabel label1, label2, label3; //graphical components, text for game instruction
    private JTextArea textArea; //graphical components, text area for displaying photo information
    private JButton button1, button2; //graphical components, button
    private JTextField textField1; //textfield to allow a single line of text input
    private PhotoPanel photoPanel; //declare PhotoPanel class named photoPanel
    private FlickrPhotoQuery photoListQuery; //declare FlickrPhotoQuery class named photoListQuery
    private PhotoTagList tagList; //declare PhotoTagList class named tagList
    private Container contentPane; //declare Container class named contentPane
    private int urlIndex=0; //the index of the URL being displayed

    /**
     * starts the application
     */
    public GuessingGamePanel()
    {
        //set a title at toolbar
        super("Guessing Game");

        tagList = new PhotoTagList();
        tagList.initGames(); //call initGames method from PhotoTagList class

        //create the window named controlPanel
        controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(0, 1)); //set window layout

        //create text object named label1 and label2. add them on the controlPanel
        label1 = new JLabel("Guess photo tag and enter the answer.");
        label2 = new JLabel("Hint: city name");
        controlPanel.add(label1);
        controlPanel.add(label2);

        //create input text field named textField1
        textField1 = new JTextField("");
        textField1.addActionListener(this); //add action listener to the textField
        controlPanel.add(textField1); //add textField on the controlPanel

        //create button named button1
        button1 = new JButton("click to change photo"); //set button name as "change photo"
        button1.addActionListener(this); //add action listener to the button1
        controlPanel.add(button1); //add button1 on the controlPanel

        //create button named button2
        button2 = new JButton("click to get more hint"); //set button name as "change photo"
        button2.addActionListener(this); //add action listener to the button2
        controlPanel.add(button2); //add button2 on the controlPanel

        //create text area for displaying photo information
        textArea = new JTextArea("");
        textArea.setEditable(false); //makes text area uneditable
        textArea.setLineWrap(true); //makes text line warpping
        controlPanel.add(textArea); //add textarea on the controlPanel

        //create text area named label3 for displaying message
        label3 = new JLabel("");
        controlPanel.add(label3);

        //initialise  photoPanel, photoListQuery. tagList
        photoPanel = new PhotoPanel();
        photoListQuery = new FlickrPhotoQuery();
        tagList = new PhotoTagList();

        //call changesGame method from PhotoTagList class
        tagList = new PhotoTagList();
        tagList.changeGame(photoListQuery, photoPanel);

        //set JFrame content pane components
        contentPane = getContentPane();
        contentPane.setPreferredSize(new Dimension(600, 250)); //set the size of the game panel
        contentPane.setLayout(new GridLayout(1, 2)); //set the layout one row, two columns
        contentPane.setLayout(new FlowLayout()); //set a layout style
        contentPane.add(photoPanel); //add photoPanel to window
        contentPane.add(controlPanel); //add controlPanel to window

        //get java to size the window and make it visible
        pack();
        setVisible(true);
    }

    //implemented methods
    /**
     * responds to input text over the text field and a mouse click over the button
     * @param event Mouse event
     */
    @Override
    public void actionPerformed(ActionEvent event)
    {
        //set condition if input text is equal to the event
        if (event.getSource() == textField1)
        {
            //get value of the text field
            String guess = event.getActionCommand();

            //display message by setting condition whether comparision is matching or not
            if (guess.equalsIgnoreCase(tagList.getCurrentGame()))
            {
                label3.setText("Your answer is " + guess + ".  " + "It's correct!");
            }
            else
            {
                label3.setText("Your answer is " + guess + ".  " + "It's wrong!");
            }

            //clear the input text field and text area for the next trial
            textArea.setText("");
            textField1.setText("");
        }

        //set condition else if button1 action is equal to the event
        else if (event.getSource() == button1)
        {
           urlIndex++; //go to next photo

           if (urlIndex >= photoListQuery.getPhotoURLs().size())
           {
              urlIndex=0;
           }

           //get new photo url and set image based on the statement
           String newPhotoURL = photoListQuery.getPhotoURLs().get(urlIndex);
           photoPanel.setImage(newPhotoURL);

           //clear text for the next photo dispaying
           label3.setText("");
           textArea.setText("");
        }

        //set condition else if button2 action is equal to the event
        else if (event.getSource() == button2)
        {
            //get photo which is currently dispayed
            Photo currentPhoto = photoListQuery.getPhoto(urlIndex);

            //condition to display discription and titile of current photo
            if (currentPhoto != null)
            {
                String message = "Description: " + currentPhoto.getDescription() + "\n" + "Title: " + currentPhoto.getTitle();
                textArea.setText(message);
            }

            //clear text for the next photo dispaying
            label3.setText("");
        }

        //check to print the answer of the game
        //System.out.println("The answer of the game");
        //System.out.println(tagList.getCurrentGame());
    }

    //main method
    public static void main (String[] args)
    {
        new GuessingGamePanel();

    }
}
