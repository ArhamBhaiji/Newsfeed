import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.channels.*;
import java.net.URL;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Fatema Shabbir 19201960
 */

//This class is used to download and parse the news
public class Downloader {
    
    /**
     * This method downloads the news from the URL it is provided and stores it into the given file
     * Code adapted from https://stackoverflow.com/questions/921262/how-to-download-and-save-a-file-from-internet-using-java
     * Accessed 23/10/17
     */
    public void downloadNews(File file, URL website){
        FileOutputStream fos;
        ReadableByteChannel rbc = null;
        
        try {
            rbc = Channels.newChannel(website.openStream());
            file.createNewFile(); // if file already exists will do nothing 
            fos = new FileOutputStream(file, false);
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        } 
        catch (FileNotFoundException ex) 
        {
            JOptionPane.showMessageDialog(null, "Please check the file path and ensure the directory exists!\n" + ex.getMessage());
        }
        catch (IOException ex) 
        {
            JOptionPane.showMessageDialog(null, "IOException: \n" + ex.getMessage());
        }
        finally 
        {
            try 
            {
                if(rbc != null) 
                {
                    rbc.close();
                }
            } 
            catch (IOException ex) 
            {
                JOptionPane.showMessageDialog(null, "Exception Encountered while closing the ReadableByteChannel: \n" + ex.getMessage());
            }               
        }
    }
    
    /**
     * This method is used to parse the html file downloaded by the downloadNews method
     * Code in this method is adapted from: https://aboullaite.me/jsoup-html-parser-tutorial-examples/
     * Accessed 22/10/17
     */
    public List parseFile(String headTag, File file, String url) {
        Document doc;
        List headings = new  ArrayList();
        try 
        {
            doc = Jsoup.parse(file, "UTF-8", url);
            Elements inputElements = doc.select(headTag);  
            
            for (Element inputElement : inputElements) {   
                String value = inputElement.text();  
                String headline = url + ": " + value + " (" + getDateTime() + ")";
                headings.add(headline);
            }   
        } 
        catch (IOException ex) 
        {
            JOptionPane.showMessageDialog(null, "IOException: \n" + ex.getMessage());
        }
        
        return headings;
    }
    
    /**
     * Gets the current date and time to print along with the headline
     */
    public String getDateTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm a");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }
}
