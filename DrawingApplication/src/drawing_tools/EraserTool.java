/*
 * Fall 2020
 * Assignment 4 - DrawingApplication
 */

package drawing_tools;

import javax.swing.ImageIcon;

/**
 * The EraserTool class provides functionality dedicated to creating a shape 
 * used to erase with 
 *
 * @author Austn Attaway
 * @version Fall 2020
 */
public class EraserTool extends PencilTool {
    
    /** The name of an Eraser tool */
    private static final String NAME = "Eraser";
    
    /** The Icon for an Eraser tool */
    private static final ImageIcon ICON = new ImageIcon("./images/eraser.gif");
    
    /**
     * Returns the name of this tool
     * 
     * @return the name of this tool
     */
    @Override
    public String getName() {
        return NAME;
    }
   
    /**
     * Returns the Icon for this tool
     * 
     * @return the Icon for this tool
     */
    @Override 
    public ImageIcon getIcon() {
        return ICON;
    }
        
}
