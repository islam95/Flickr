package Examples;

import javax.swing.*; //import javax Swing components
import java.awt.*; //import java AWT components
import java.awt.event.*; //import java AWT package for event listeners

/**
 * program that displays buttons to select one of games
 * @author  Islam Dudaev
 * @version 1.0, 13 December, 2014
 */
public class MainPanel extends JFrame implements ActionListener
{
    private JFrame frame;
    private JPanel panel; //allow to view and set a range of parameters cotrolling
    private Label label1, label2, label3; //graphical components, text area
    private JButton button1, button2; //graphical components, button

    /**
     * starts the application
     */
    public MainPanel()
    {
        frame = new JFrame("Game Main GUI");
        frame.setVisible(true);
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        frame.add(panel);

        //create text object named label1 and label2. add them on the control panel
        label1 = new Label("This is Flickr Mini Game Appliction.");
        label2 = new Label("Developed by Islam Dudaev");
        label3 = new Label("Click a button to play game.");
        panel.add(label1);
        panel.add(label2);
        panel.add(label3);

        button1 = new JButton("Guessing Game");
        panel.add(button1);
        button1.addActionListener(this);

        button2 = new JButton("Flickr Fighting");
        panel.add(button2);
        button2.addActionListener(this);

        //get java to size the window and make it visible
        pack();
    }

    //main method
    public static void main (String[] args)
    {
        new MainPanel();
    }

    //implemented methods
    /**
     * responds to a mouse click over the button
     * @param event Mouse event
     */
    public void actionPerformed (ActionEvent event)
    {
        if(event.getSource() == button1)
        {
       //     GamePanel guessingGame = new GamePanel();
        }

        //else if(event.getSource() == button1)
        //{
        //}
    }
}
