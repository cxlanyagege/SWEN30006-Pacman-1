//package src;
//
//import src.utility.GameCallback;
//import src.utility.PropertiesLoader;
//
//import java.util.Properties;
//
//public class Driver {
//    public static final String DEFAULT_PROPERTIES_PATH = "pacman/properties/test1.properties";
//
//    /**
//     * Starting point
//     * @param args the command line arguments
//     */
//
//    public static void main(String args[]) {
//        String propertiesPath = DEFAULT_PROPERTIES_PATH;
//        if (args.length > 0) {
//            propertiesPath = args[0];
//        }
//        final Properties properties = PropertiesLoader.loadPropertiesFile(propertiesPath);
//        GameCallback gameCallback = new GameCallback();
//        new Game(gameCallback, properties);
//    }
//}
package src;

import src.utility.GameCallback;
import src.utility.PropertiesLoader;

import java.util.Properties;

public class Driver {
    public static final String DEFAULT_PROPERTIES_PATH = "pacman/properties/test5.properties";

    /**
     * Starting point
     * @param args the command line arguments
     */

    public static void main(String args[]) {
        String propertiesPath = DEFAULT_PROPERTIES_PATH;
        if (args.length > 0) {
            propertiesPath = args[0];
        }
        final Properties properties = PropertiesLoader.loadPropertiesFile(propertiesPath);
        GameCallback gameCallback = new GameCallback() {
            @Override
            public void endOfGame(String message) {
                // Handle end of game logic here
            }
        };

        // read version in properties file
        String gameVersion = properties.getProperty("version");

        if (gameVersion == null) {
            throw new RuntimeException("Game version not specified in properties file.");
        }


        // load simple/multiverse game according to version
        if (gameVersion.equals("simple")) {
             new SimpleGame(gameCallback, properties);
        } else if (gameVersion.equals("multiverse")) {
             new MultiverseGame(gameCallback, properties);
        } else {
            throw new RuntimeException("Invalid game version specified in properties file.");
        }
    }
}
