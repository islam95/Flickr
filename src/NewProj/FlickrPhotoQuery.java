package NewProj;

//import java package for collections, properties, and file handling
import java.util.*;
import java.io.*;

//import flickr4java package for its classes, photos and people interface
import com.flickr4java.flickr.*;
import com.flickr4java.flickr.photos.*;
import com.flickr4java.flickr.people.*;

/**
 * program gets a photo from Flickr by using search criteria 'photo tag'
 * @author Islam Dudaev
 * @version 1.0 13 December, 2014
 */
public class FlickrPhotoQuery
{
    //object variables
    private Flickr flickr; //object providing link with Flickr.
    private ArrayList<String> photoURLs; //store a list of photo URLs
    private ArrayList<Photo> photos; //store a list of photo tags

    /**
     * constructor sets up the Flickr connection and performs a simple query
     */
    public FlickrPhotoQuery()
    {
        initFlickrConnection();

    }

    /**
     * private method initialises the connection with the Flickr service by using API key and secret stored in a local properties file
     * @return true, the Flickr object
     */
    private boolean initFlickrConnection()
    {
        //extract the API key and secret from a local properties file
        Properties properties = null;
        try
        {
            properties = new Properties();
            properties.load(new FileInputStream("flickr.properties"));
        }
        catch (IOException e)
        {
            System.err.println("Problem reading flickr properties file.");
            return false;
        }

        //make connection to the Flickr API
        flickr = new Flickr(properties.getProperty("apiKey"), properties.getProperty("secret"), new REST());
        return true;
    }

    /**
     * method performs a simple non-authenticated query of the Flickr service
     * @param tags
     * @return true, if query performed successfully
     */
    public boolean doQuery(String[] tags)
    {
       //object arrayList photoURLs initalises
       photoURLs = new ArrayList<>();

       //provides access to Flickr's 'photos' API calls
       PhotosInterface photosInterface = flickr.getPhotosInterface();

       //handles a collection of photos returned by a query
       photos =  null;

       //object searching criteria initalises
       SearchParameters criteria = new SearchParameters();
       criteria.setSort(SearchParameters.RELEVANCE); //order query results by relevance
       criteria.setTags(tags); //photo tags to display

       try
        {
            photos = photosInterface.search(criteria, 250, 1);
        }
        catch (FlickrException e)
        {
            System.err.println("Problem performing flickr query: " + e);
            return false;
        }

       //a list of photos, using for loop to extract the photo URL from each photo in turn
       for (Photo photo : photos)
        {
            //store the URL pointing to the small version of each photo
            photoURLs.add(photo.getSmallUrl());
        }

        return true;
    }

    /**
     * method gets the list of URLs. return a copy so that it's read-only
     * @return a list of URLs
     */
    public ArrayList<String> getPhotoURLs()
    {
        return new ArrayList<>(photoURLs);
    }

    /**
     * method gets a photo from the Flickr search result. The Photo object contains information like URL, title and description.
     * @param index
     * @return photo object, if it is given a valid index
     */

    public Photo getPhoto(int index)
    {
       if (index >= 0 && index < photos.size())
        {
            return photos.get(index);
        }
       else
        {
            return null;
        }
    }
}
