import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Fatema Shabbir 19201960
 */
public class MapCreator {
    
    /**
     * This method takes all the command line arguments and creates a HashMap with them after loading the plugins
     * @param args
     * @return 
     */
    public HashMap<String, PluginInterface> createHashMap(String args[]){
        
        PluginLoader loader;
        Map<String, PluginInterface> data;
        loader = new PluginLoader();
        data = new HashMap<String, PluginInterface>();
        
        //runs through all the args and loads the plugins via the loader
        for(int x=0; x<args.length;x++){
            try { 
                data.put(args[x], loader.loadPlugin(args[x]));
            } catch (ClassNotFoundException ex) {
                System.out.println("ClassNotFoundException caught at arg " + x + ": " + args[x]);
            }
        }

        return (HashMap<String, PluginInterface>) data;
    }
    
}
