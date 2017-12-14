import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import javax.swing.JOptionPane;
/**
 *
 * @author Fatema Shabbir 19201960
 */
public class Arstechnica implements PluginInterface, Callable<List<String>>{
    
    private final long updateFrequency = 20; //sets frequency to 20 minutes
    private URL website = null;
    File file = new File("html/arstechnica.html"); //sets the file name for this plugin to download to
    Downloader downloader = new Downloader();;
    List headings = new  ArrayList();
    String url = "https://arstechnica.com/"; //sets to url
    String pluginName = "Arstechnica"; //sets the proper plugin name
    
    public Arstechnica() {
        try {
            website = new URL(url);
        }
        catch (MalformedURLException e) {
            JOptionPane.showMessageDialog(null, "Incorrect URL!\n" + e.getMessage());
        }
    }
    
    /**
     * Returns the URL of the source
     * @return 
     */
    @Override
    public String getName() {
        return pluginName;
    }
    
    /**
     * Calls the downloader so that it can download and parse the given url to the given file
     * @return List of headlines
     */
    @Override
    public List getHeadlines() {
        downloader.downloadNews(file, website);
        headings = downloader.parseFile("h1.heading", file, url);
        return headings;
    }

    /**
     * Gives the update frequency to schedule this source
     * @return 
     */
    @Override
    public long getUpdateFrequency() {
        return updateFrequency;
    }    
    
    /**
     * Method from the callable interface used by the timer task to schedule downloads from this source
     * @return List of all headlines
     * @throws Exception 
     */
    @Override
    public List<String> call() throws Exception {
        return getHeadlines();
    }
}
