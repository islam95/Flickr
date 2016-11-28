import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * Main Class - GUI application class.
 *
 * @author Islam 
 * @version v1.0, 17/12/2014
 */
public class Main extends JPanel implements ActionListener {
    /**
     * Class variables for GUI application
     */
	private Button btnCheckFlicker;
	private TextField txtTag1InputField;
	private TextField txtTag2InputField;
	private Image imgVersus;
	private Image imgLogo;
	private JLabel lblMessage;

	/**
	 * This is constructor method that draws the interface components as well by calling loadLayout methods
	 */
	public Main()
	{
		loadLayout();
	}

	/**
	 * This method is responsible for painting the interface for objects like image, text. It is called everytime the screen is refreshed like minimize and maximize or another window is shown
	 * on top of it and it is shown back.
	 * @param g - This is graphics object provided by system for the screen being shown and it is used to draw items like images and textx if needed. Also to set background color etc.
	 */public void paintComponent(Graphics g) {
      super.paintComponent(g); // to call the parent constructor

       try
      {
          /**
           * This loads the logo image from disk. If the image is not present on disk it will through an exception. Once the image is loaded, the graphics object draws it on the coordinates provided
           */
    	  imgLogo = ImageIO.read(new File("logo.jpg"));
    	  g.drawImage(imgLogo, 50, 12, null);
    	  /**
           * This loads the VS image from disk. If the image is not present on disk it will through an exception. Once the image is loaded, the graphics object draws it on the coordinates provided
           */
    	  imgVersus = ImageIO.read(new File("vs.png"));
    	  g.drawImage(imgVersus, 192, 45, null);
      }
      catch(Exception ex)
      {
    	  System.err.println("Error in loading image");
      }

   }

	/**
	 * This method is responsible for adding the UI components like input controls {textfield, button} and label. It also adds listener event for button so that the click event can be handled
	 */
	private void loadLayout()
	{
	    /**
	     * The layout is reset to add controls
	     */
		this.setLayout(null);

		txtTag1InputField = new TextField();
		txtTag1InputField.setBounds(40, 50, 150, 30); //{x, y, width, height}
		txtTag1InputField.setBackground(Color.WHITE);
		txtTag1InputField.setVisible(true);

		this.add(txtTag1InputField);

		txtTag2InputField = new TextField();
		txtTag2InputField.setBounds(240, 50, 150, 30);//{x, y, width, height}
		txtTag2InputField.setBackground(Color.WHITE);
		txtTag2InputField.setVisible(true);

		this.add(txtTag2InputField);

		btnCheckFlicker = new Button();
		btnCheckFlicker.setLabel("Check from Flicker");
		btnCheckFlicker.addActionListener(this);
		btnCheckFlicker.setBounds(150, 100, 120, 30);//{x, y, width, height}
		btnCheckFlicker.setBackground(Color.ORANGE);
		btnCheckFlicker.setVisible(true);

		this.add(btnCheckFlicker);


		lblMessage = new JLabel();
		lblMessage.setBounds(100, 130, 300, 80);//{x, y, width, height}
		lblMessage.setForeground(Color.RED);
		lblMessage.setVisible(true);

		this.add(lblMessage);
	}


   /**
    * This is main method from where the GUI application starts.
    * @param args - this is list of command line arguments passed to the program, by default this list is empty
    */
   public static void main(String[] args) {
       /**
        * This is the parent frame in which every thing is drawn like panel and UI controls
        */
      JFrame frame = new JFrame();
      frame.setTitle("Flicker App");
      frame.setSize(450, 300);
      /**
       * This creates the main panel and adds that to the frame. Also sets the visibility of the frame to true.
       */
      Container contentPane = frame.getContentPane();
      contentPane.add(new Main());
      frame.setVisible(true);
   }

   /**
    * This is listener method called when there is a click event
    * @param arg0 - It is the event data passed from the listener like source of the event.
    */
@Override
public void actionPerformed(ActionEvent arg0) {
    /**
     * We clear the label field so that anything written before the click event is performed is cleared.
     */
	lblMessage.setText("");
	/**
	 * We check that both the tag input fields are not empty. If both or anyone of them is empty, an error will shown the method will return without connecting to Flicker.
	 */
	if (txtTag1InputField.getText().isEmpty() || txtTag2InputField.getText().isEmpty())
	{

		lblMessage.setBounds(100, 130, 300, 80);//{x, y, width, height}
		lblMessage.setForeground(Color.RED);//Setting the text color to red and indicates the error
		/**
		 * We use HTML because the java swing label control allows us to style the text using HTML
		 */
		lblMessage.setText("<html><b>Error:</b> Both fields should be filled to proceed</html>");
		return;
	}
	/**
	 * We change the position of the label control as the data in result is longer than the error message so it should be positoned properly for central alignment. We set the color to blue to indicate
	 * that this is not an error but the data received from Flicker.
	 */
	lblMessage.setBounds(130, 130, 300, 80);
	lblMessage.setForeground(Color.BLUE);
	/**
	 * An object of flicker connection class is created
	 */
	FlickrTagsFight flicker = new FlickrTagsFight();
	/**
	 * We check if the connection to flicker is made using the properties file provided with the application
	 */
	if (!flicker.initFlickrConnection())
	{

		lblMessage.setText("Error: Flicker connection could not be established");
		return;
	}
	/**
	 * We call method on class compareTags and pass both tag values. The result returned from this method is styled using HTML
	 */
	lblMessage.setText("<html>" + flicker.compareTags(txtTag1InputField.getText(), txtTag2InputField.getText()) + "</html>");
}
}
