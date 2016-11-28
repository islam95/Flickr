/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package photoViewerApp;

import java.util.*;                 		// For collections and properties.
import java.io.*;                  			// For file handling.

import com.flickr4java.flickr.*;        	// For Flickr4Java classes.
import com.flickr4java.flickr.photos.*; 	// For photos interface.
import com.flickr4java.flickr.people.*; 	// For people interface.

/**
 *
 * @author islam
 */
public class PhotoListQuery {
    
    // --------------------------- Object variables -------------------------
    private Flickr flickr;                  // Object providing link with Flickr.
    private ArrayList<String> photoURLs;    // Stores a list of photo URLs
    private String contributor;             // Name of photo contributor

	// ----------------------------- Constructor ----------------------------
    /**
     * Sets up the Flickr connection and performs a simple query.
     */
    public PhotoListQuery() {
        if (initFlickrConnection()) {
            photoURLs = new ArrayList<String>();    // URLs of queried photos.
            doQuery();
            getPhotoURLs();
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

    
    public ArrayList<String> getPhotoURLs(){
        return new ArrayList<String>(photoURLs);
    }
}
