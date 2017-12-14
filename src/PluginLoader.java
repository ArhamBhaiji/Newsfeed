import java.nio.file.*;

/**
 *
 * @author Fatema Shabbir 19201960
 * Some of this code was written during Lab 3 
 */

public class PluginLoader extends ClassLoader
{
    /**
     * This method loads all the plugins that implement the PluginInterface class from the class files
     */
    public PluginInterface loadPlugin(String fname) throws ClassNotFoundException
    {
        try
        {
            byte[] classData = Files.readAllBytes(Paths.get(fname));
            Class<?> cls = defineClass(null, classData, 0, classData.length);
            return (PluginInterface)cls.newInstance();
        }
        catch(Exception e)
        {
            throw new ClassNotFoundException(String.format("Could not load '%s': %s", fname, e.getMessage()),e);
        }
    }

}