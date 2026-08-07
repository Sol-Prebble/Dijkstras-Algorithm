import java.awt.*;
import java.util.*;
/**
 * This class controls the color palette that the program uses.
 * It contains methods for each variaty and also to add the colors to nodes and edges
 *
 * @author Sol Prebble
 * @version 6-8-26
 */
public class Theme
{
    private Map<String, Color> palette;
    /**
     * Constructor for objects of class Theme
     */
    public Theme()
    {
        this.palette = darkPalette();
    }
    /**
     * The dark version of the color palette
     * Creates a map with all the different colors to be used throughout the program
     * Sets them up with the same name as the variable to be used for calling the color
     * @param null
     * @return darkPalette - the map of the colors
     */
    private Map<String, Color> darkPalette(){
        Map<String, Color> darkPalette = new HashMap<>();

        Color background = Color.BLACK;
        Color nodeDefault = Color.DARK_GRAY;
        Color edgeDefault = nodeDefault;
        Color visited = Color.LIGHT_GRAY;
        Color text = Color.WHITE;
        Color visitedText = Color.BLACK;
        Color selected = Color.MAGENTA;
        Color path = Color.CYAN;
        Color buttonDisabled = Color.DARK_GRAY;
        Color buttonEnabled = new Color(60, 0, 40); // deep magenta color
        Color buttonText = Color.WHITE;

        darkPalette.put("background", background);
        darkPalette.put("nodeDefault", nodeDefault);
        darkPalette.put("edgeDefault", edgeDefault);
        darkPalette.put("visited", visited);
        darkPalette.put("text", text);
        darkPalette.put("visitedText", visitedText);
        darkPalette.put("selected", selected);
        darkPalette.put("path", path);
        darkPalette.put("buttonDisabled", buttonDisabled);
        darkPalette.put("buttonEnabled", buttonEnabled);
        darkPalette.put("buttonText", buttonText);

        return(darkPalette);
    }
    /**
     * The light version of the color palette
     * Creates a map with all the different colors to be used throughout the program
     * Sets them up with the same name as the variable to be used for calling the color
     * @param null
     * @return lightPalette - the map of the colors
     */
    private Map<String, Color> lightPalette(){

        Map<String, Color> lightPalette = new HashMap<>();

        Color background = Color.GRAY;
        Color nodeDefault = Color.LIGHT_GRAY;
        Color edgeDefault = nodeDefault;
        Color text = Color.WHITE;
        Color visitedText = Color.BLACK;
        Color selected = Color.YELLOW;
        Color path = Color.GREEN;
        Color buttonBackground = Color.DARK_GRAY;
        Color buttonText = Color.WHITE;

        lightPalette.put("background", background);
        lightPalette.put("nodeDefault", nodeDefault);
        lightPalette.put("edgeDefault", edgeDefault);
        lightPalette.put("text", text);
        lightPalette.put("visitedText", visitedText);
        lightPalette.put("selected", selected);
        lightPalette.put("path", path);
        lightPalette.put("buttonBackground", buttonBackground);
        lightPalette.put("buttonText", buttonText);
        return(lightPalette);
    }

    /* Getters */
    public Map<String, Color> getPalette(){
        return(this.palette);
    }

    /* Setters */
    public void setPalette(Map<String, Color> palette){
        this.palette = palette;
    }
}