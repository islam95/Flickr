package flickr;

import java.util.*;                 		// For collections and properties.
import java.io.*;                       	// For file handling.

import com.flickr4java.flickr.*;        	// For Flickr4Java classes.
import com.flickr4java.flickr.photos.*; 	// For photos interface.
import com.flickr4java.flickr.people.*; 	// For people interface.

/**
 *
 * @author Islam Dudaev
 */
public class NameIt {

    // --------------------------- Object variables -------------------------
    private Flickr flickr;                  // Object providing link with Flickr.

    private String contributor;             // Name of photo contributor

    // ----------------------------- Constructor ----------------------------
    /**
     * Sets up the Flickr connection and performs a simple query.
     */
    public NameIt() {
        if (initFlickrConnection()) {
            contributor = "7539598@N04";
            getInfo();
            
        }
    }

    // --------------------------- Private methods --------------------------
    /**
     * Initialises the connection with the Flickr service by using API key and
     * secret stored in a local properties file.
     *
     * @return True if Flickr connection successfully established.
     */
    private boolean initFlickrConnection() {
        // Extract the API key and secret from a local properties file.
        Properties properties = null;
        try {
            properties = new Properties();
            properties.load(new FileInputStream("flickr.properties"));
        } catch (IOException e) {
            System.err.println("Problem reading flickr properties file.");
            return false;
        }

        // Make connection to the Flickr API.
        flickr = new Flickr(properties.getProperty("apiKey"),
                properties.getProperty("secret"),
                new REST());
        return true;
    }

    private boolean getInfo() {

        //Initialize PhotosInterface object
        PhotosInterface photosInterface = flickr.getPhotosInterface();

        Photo photo = new Photo();
        try {
            photosInterface.getPhoto(contributor);
            //Execute search with photo ID you want to get information
            //Photo photo = photosInterface.getPhoto(contributor);
            System.out.println(photo);
        } catch (FlickrException e) {
            System.err.println("Problem performing flickr query: " + e);
            return false;
        }
        
        return true;
    }

}
