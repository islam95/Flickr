import com.flickr4java.flickr.*;
import com.flickr4java.flickr.photos.*;
import java.io.*;
import java.util.*;

/**
 * FlickrTagsFight Class - simple game that shows who has most tags.
 *
 * @author Islam 
 * @version v1.0, 12/12/2014
 */
public class FlickrTagsFight {

    /**
     * Runs the program as an application.
     * @param args Command line arguments (ignored).
     */
    public static void main(String[] args) {
        new FlickrTagsFight();
        }

    // --------------------------- Object variables -------------------------
    private Flickr flickr;              // Object providing link with Flickr.

    // ----------------------------- Constructor ----------------------------

    /**
     * This is constructor of the class.
     */
       public FlickrTagsFight()
       {
       }

    // --------------------------- Methods ---------------------------------
    /**
     * Initialises the connection with the Flickr service by using API key and
     * secret stored in a local properties file.
     *
     * @return True if Flickr connection successfully established.
     */
    public boolean initFlickrConnection() {
        // Extract the API key and secret from a local properties file.
        Properties properties = null;
        try {
            properties = new Properties();
            properties.load(new FileInputStream("flickr.properties"));
        } catch (IOException e) {
            System.err.println("Problem reading flickr properties file.");
            return false;
        }
        /**
         * initialize flicker object based on the APIkey and secret read from properties file.
         */
        flickr = new Flickr(properties.getProperty("apiKey"),
                properties.getProperty("secret"),
                new REST());
        return true;
    }

    /**
     * This method is responsible for connecting to flicker, passing the two tags and then collecting information for comparison, which tag is more popular.
     * @param tag1 - value for first tag to search from flicker
     * @param tag2 - value of second tag to search from flicker
     * @return String data for result or error if there an error processing the request
     */
    public String compareTags(String tag1, String tag2)
    {
        String result = "";
        // Provides access to Flickr's 'photos' API calls.
        PhotosInterface photos = flickr.getPhotosInterface();

        // Handles a collection of photos returned by a query.
        PhotoList<Photo> photoList1 = null;
        PhotoList<Photo> photoList2 = null;

        SearchParameters criteria1 = new SearchParameters();
        SearchParameters criteria2 = new SearchParameters();

        criteria1.setTags(new String[]{tag1});
        criteria2.setTags(new String[]{tag2});
        /**
         * The exception is handled here because the connection to flicker may throw exception when trying to search. If exception is not handled here, it will crash the program
         */
        try {
            photoList1 = photos.search(criteria1, 250, 1);
            photoList2 = photos.search(criteria2, 250, 1);
        } catch (FlickrException e) {
            System.err.println("Problem performing flickr query: " + e);
            return ("Problem performing flickr query: " + e.getErrorMessage());
        }

        result += "'" + tag1 + "' tag has " + photoList1.getTotal() + " photos.<br>";
        result += "'" + tag2 + "' tag has " + photoList2.getTotal() + " photos.<br>";

        int num1 = photoList1.getTotal();
        int num2 = photoList2.getTotal();



        // If else statements to find the winner with the most tags.
        if(num1 < num2){
            result += "<b>Difference</b> is: " + (num2 - num1) + "!<br>";
            result += "Tag <b>'" + tag2 + "'</b> wins!<br>";
        } else if(num1 > num2){
            result += "<b>Difference</b> is: " + (num1 - num2) + "!<br>";
            result += "Tag <b>'" + tag1 + "'</b> wins!<br>";
        } else if(num1 == num2){
            result += "It is a draw!<br>";
        }

        return result;
    }

    /**
     * This method reads tags from console {keyboard} and then passed them on to flicker to search and then does the comparison
     * @return True if query performed successfully.
     */
    public boolean tags() {
        // Provides access to Flickr's 'photos' API calls.
        PhotosInterface photos = flickr.getPhotosInterface();

        // Handles a collection of photos returned by a query.
        PhotoList<Photo> photoList1 = null;
        PhotoList<Photo> photoList2 = null;

        SearchParameters criteria1 = new SearchParameters();
        SearchParameters criteria2 = new SearchParameters();

        Scanner scan = new Scanner(System.in);    // Scanner for input
        String tag1, tag2;   // strings for iput of tags by user
        System.out.println("Enter first tag: ");
        tag1 = scan.nextLine();
        System.out.println("Enter second tag: ");
        tag2 = scan.nextLine();

        criteria1.setTags(new String[]{tag1});
        criteria2.setTags(new String[]{tag2});

        try {
            photoList1 = photos.search(criteria1, 250, 1);
            photoList2 = photos.search(criteria2, 250, 1);
        } catch (FlickrException e) {
            System.err.println("Problem performing flickr query: " + e);
            return false;
        }
        // Printing out the results. Total of tag1 and total of tag2.
        System.out.println("'" + tag1 + "' tag has " + photoList1.getTotal() + " photos.");
        System.out.println("'" + tag2 + "' tag has " + photoList2.getTotal() + " photos.");

        int num1 = photoList1.getTotal();
        int num2 = photoList2.getTotal();

        // If else statements to find the winner with the most tags.
        if(num1 < num2){
            System.out.println("Difference: " + (num2 - num1) + "!");
            System.out.println("Tag '" + tag2 + "' wins!");
        } else if(num1 > num2){
            System.out.println("Difference: " + (num1 - num2) + "!");
            System.out.println("Tag '" + tag1 + "' wins!");
        } else if(num1 == num2){
            System.out.println("It is a draw!");
        }

        return true;
    }

}
