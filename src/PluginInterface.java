import java.util.List;

/**
 *
 * @author Fatema Shabbir 19201960
 */

/**
 * This interface is implemented by all the plugins
 */
public interface PluginInterface{
    /**
     * Returns the URL of the source
     */
    public String getName();
    
    /**
     * Gives the update frequency to schedule this source
     */
    public long getUpdateFrequency();
    
    /**
     * Calls the downloader so that it can download and parse the given url to the given file
     * @return List of headlines
     */
    public List<String> getHeadlines();
}
