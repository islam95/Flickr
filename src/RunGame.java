
import com.flickr4java.flickr.*;
import com.flickr4java.flickr.photos.*;
import java.io.*;
import java.util.*;
/**
 * This calss is responsible for running the app as an application
 *
 * @author Islam 
 * @version v1.0, 17/12/2014
 */
public class RunGame
{
    /**
     * Runs the program as an application.
     */
    public static void main(String[] args) {
        FlickrTagsFight flicker = new FlickrTagsFight();
        flicker.initFlickrConnection();
        flicker.tags();
        // code for reading input

}
}
