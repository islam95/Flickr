package listPhotoURLs;

import java.util.*;                 		// For collections and properties.
import java.io.*;                  			// For file handling.

import com.flickr4java.flickr.*;        	// For Flickr4Java classes.
import com.flickr4java.flickr.photos.*; 	// For photos interface.
import com.flickr4java.flickr.people.*; 	// For people interface.

//  ***************************************************************************
/**
 * Class that links to the Flickr service and creates a simple web page
 * containing a set of photos from a given contributor.
 *
 * @author Islam Dudaev
 * @version 2.0, 25th October, 2014
 */
//  *************************************************************************
public class PhotoWebPageExample {
    // ---------------------------- Starter method --------------------------

    /**
     * Runs the program as an application.
     *
     * @param args Command line arguments (ignored).
     */
    public static void main(String[] args) {
        new PhotoWebPageExample();
    }

	// --------------------------- Object variables -------------------------
    private Flickr flickr;                  // Object providing link with Flickr.
    private ArrayList<String> photoURLs;    // Stores a list of photo URLs
    private String contributor;             // Name of photo contributor

	// ----------------------------- Constructor ----------------------------
    /**
     * Sets up the Flickr connection and performs a simple query.
     */
    public PhotoWebPageExample() {
        if (initFlickrConnection()) {
            photoURLs = new ArrayList<String>();    // URLs of queried photos.
            doQuery();
            createWebPage("photos.html");
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

    /**
     * Performs a simple non-authenticated query of the Flickr service.
     *
     * @return True if query performed successfully.
     */
    private boolean doQuery() {
        // Provides access to Flickr's 'photos' API calls.
        PhotosInterface photos = flickr.getPhotosInterface();

        // Handles a collection of photos returned by a query.
        PhotoList<Photo> photoList = null;

        SearchParameters criteria = new SearchParameters();
        criteria.setSort(SearchParameters.DATE_TAKEN_DESC); // Order query results by date.
        criteria.setUserId("7539598@N04");          		// User's id whose photos to display.

        try {
            // Find details about the user.
            PeopleInterface people = flickr.getPeopleInterface();
            User user = people.getInfo(criteria.getUserId());

            contributor = user.getRealName();
            if (contributor == null) {
                // If no real name provided, try their user name.
                contributor = user.getUsername();
            }
            if (contributor == null) {
                // If no username provided, use their ID
                contributor = "User " + user.getId();
            }

            // Extract details about the first 20 photos that match search criteria.
            photoList = photos.search(criteria, 20, 1);
        } catch (FlickrException e) {
            System.err.println("Problem performing flickr query: " + e);
            return false;
        }

        // Now we have a list of photos, extract the photo URL from each photo in turn.
        for (Photo photo : photoList) {
            // Store the URL pointing to the small version of each photo.
            photoURLs.add(photo.getSmallUrl());
        }
        return true;
    }

    /**
     * Creates a simple web page displaying the photos previously queried.
     *
     * @param pageName Name of web page to create.
     */
    private void createWebPage(String pageName) {
        int photoNumber = 0;

        try {
            PrintWriter webPageFile = new PrintWriter(pageName);

            // Write HTML page header.
            webPageFile.println("<!DOCTYPE html>");
            webPageFile.println("<html><head><title>Flickr Catalogue</title></head><body>");
            webPageFile.println("<h3 style=\"font-family:sans-serif\">" + contributor + "'s Photos</h3>");
            webPageFile.println("<table cellspacing=\"0\" cellpadding=\"1\" border=\"0\">");
            webPageFile.println("<tr>");

            // Write out table containing photos.
            for (String url : photoURLs) {
                if (photoNumber % 4 == 0) {
                    webPageFile.println("</tr>\n<tr>");
                }
                webPageFile.println(" <td align=\"center\"><img src=\"" + url + "\"</td>");
                photoNumber++;
            }

            // Write HTML page footer.
            webPageFile.println("</tr></table></body></html>");
            webPageFile.close();

        } catch (FileNotFoundException e) {
            System.err.println("Problem writing photo web page: " + pageName);
        }
    }
}
