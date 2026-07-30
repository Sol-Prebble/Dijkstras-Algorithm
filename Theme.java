import java.awt.*;
import java.util.*;
/**
 * Write a description of class Theme here.
 *
 * @author (your name)
 * @version (a version number or a date)
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
    private void setUpColors(Graph graph, Map<String, Color> palette){
        for(Node currentNode : graph.getAllNodes()){
            currentNode.setColor(palette.get("nodeDefault"));
            ArrayList<Edge> currentEdges = currentNode.getEdges();
            for(int e = 0; e < currentEdges.size(); e++){
                Edge currentEdge = currentEdges.get(e);
                currentEdge.setColor(palette.get("edgeDefault"));
            }
        }
    }
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
        Color buttonBackground = Color.DARK_GRAY;
        Color buttonText = Color.WHITE;
        
        darkPalette.put("background", background);
        darkPalette.put("nodeDefault", nodeDefault);
        darkPalette.put("edgeDefault", edgeDefault);
        darkPalette.put("visited", visited);
        darkPalette.put("text", text);
        darkPalette.put("visitedText", visitedText);
        darkPalette.put("selected", selected);
        darkPalette.put("path", path);
        darkPalette.put("buttonBackground", buttonBackground);
        darkPalette.put("buttonText", buttonText);
        
        return(darkPalette);
    }
    private Map<String, Color> defaultPalette(){
        
        Map<String, Color> defaultPalette = new HashMap<>();
        
        Color background = Color.GRAY;
        Color nodeDefault = Color.LIGHT_GRAY;
        Color edgeDefault = nodeDefault;
        Color text = Color.WHITE;
        Color visitedText = Color.BLACK;
        Color selected = Color.YELLOW;
        Color path = Color.GREEN;
        Color buttonBackground = Color.DARK_GRAY;
        Color buttonText = Color.WHITE;
        
        defaultPalette.put("background", background);
        defaultPalette.put("nodeDefault", nodeDefault);
        defaultPalette.put("edgeDefault", edgeDefault);
        defaultPalette.put("text", text);
        defaultPalette.put("visitedText", visitedText);
        defaultPalette.put("selected", selected);
        defaultPalette.put("path", path);
        defaultPalette.put("buttonBackground", buttonBackground);
        defaultPalette.put("buttonText", buttonText);
        return(defaultPalette);
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