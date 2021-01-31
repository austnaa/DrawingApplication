/*
 * TCSS 305 - Fall 2020
 * Assignment 4 - Powerpaint
 */

package drawing_tools;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import javax.swing.ImageIcon;

/**
 * The EllipseTool class provides functionality dedicated to creating ellipses
 * 
 * @author Austn Attaway
 * @version Fall 2020
 */
public class EllipseTool extends RectangleTool {
    
    /** The name of an EllipseTool */
    private static final String NAME = "Ellipse";
   
    /** The Icon for an EllipseTool */
    private static final ImageIcon ICON =  new ImageIcon("./images/ellipse.gif");
    
    /**
     * Returns the Ellipse2D Shape that is currently saved
     */
    @Override
    public Shape getShape() {
        final Ellipse2D ellipse = new Ellipse2D.Double();
        ellipse.setFrame(getShapeX(), getShapeY(), getWidth(), getHeight());
        return ellipse;
    }
    
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
