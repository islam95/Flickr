package NewProj;

import java.util.*; //import java untility package for collections and properties
import com.flickr4java.flickr.photos.*; //import flickr4java package for flickr photos

/**
 * program that sets photo tags list and displays it randomly
 * @author  Islam Dudaev
 * @version 1.0, 13 December, 2014
 */
public class PhotoTagList
{
    private ArrayList<String[]> games; //stores a list of photo tags
    private String currentGame; //declare changeGame method variable

    public PhotoTagList(){

    }
    /**
     * method sets arrayList of adding photos tags
     */
    public void initGames()
    {
        games = new ArrayList<>();
        games.add(new String[] {"London", "City"});
        games.add(new String[] {"New York", "City"});
        games.add(new String[] {"Paris", "City"});
        games.add(new String[] {"Glasgow", "City"});
        games.add(new String[] {"Tokyo", "City"});
    }

    /**
     * method sets changing photos by choosing one of array element randomly
     * @param photoListQuery to query Flickr with the given game tags
     * @param photoPanel using the query results, we will change the image on the photo panel
     */
    public void changeGame(FlickrPhotoQuery photoListQuery, PhotoPanel photoPanel)
    {
        int idx = new Random().nextInt(games.size());
        String[] tags;
        tags = games.get(idx);
        currentGame = tags[0];
        photoListQuery.doQuery(tags);
        photoPanel.setImage(photoListQuery.getPhotoURLs().get(0));
    }

   /**
    * method gets currentGame
    * @return currentGame
    */
    public String getCurrentGame()
    {
        return currentGame;
    }
}
