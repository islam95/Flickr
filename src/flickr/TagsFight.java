package flickr;

import com.flickr4java.flickr.*;
import com.flickr4java.flickr.photos.*;
import java.io.*;
import java.util.*;

/**
 *
 * @author Islam Dudaev
 */
public class TagsFight{
    

    // --------------------------- Object variables -------------------------
    protected Flickr flickr;              // Object providing link with Flickr.
    
    // ----------------------------- Constructor ----------------------------

    /**
     * Sets up the Flickr connection and performs a simple queries.
     */
    public TagsFight() {
        if (initFlickrConnection()) {
          fight();
            
        }
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

        flickr = new Flickr(properties.getProperty("apiKey"),
                properties.getProperty("secret"),
                new REST());
        return true;
    }
    
    /**
     * Tag fight. To find who has the most tags.
     * @return 
     */
    public boolean fight(){
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
            System.out.println("Tag '" + tag2 + "' wins!");
        } else if(num1 > num2){
            System.out.println("Tag '" + tag1 + "' wins!");
        } else if(num1 == num2){
            System.out.println("It is a draw!");
        }
        
        return true;
    }
    
    public void time(){
    
        // Provides access to Flickr's 'photos' API calls.
        PhotosInterface photos = flickr.getPhotosInterface();
        
        SearchParameters criteria = new SearchParameters();
        
        // Set temporal bounds of query.
        Calendar calendar = Calendar.getInstance();
        criteria.setMaxTakenDate(calendar.getTime());       // Now

        calendar.add(Calendar.YEAR, -1);
        criteria.setMinTakenDate(calendar.getTime());       // One year ago.
        
    }
    
    
}
