/*
 * Fall 2020
 * Assignment 4 - DrawingApplication
 */

package drawing_tools;

import java.awt.Point;
import java.util.Objects;

import javax.swing.Icon;

/**
 * AbstractPaintTool is an abstract class that provides some functionality to
 * child tool classes.
 * 
 * @author Austn Attaway
 * @version Fall 2020
 */
public abstract class AbstractPaintTool implements PaintTool {
   
    /** The default values for a Point before a new shape has been created  */
    public static final Point NO_POINT = new Point(-100, -100);
    
    /** The name of this tool */
    private final String myName;
    
    /** The Icon for this tool */
    private final Icon myIcon;
    
    /**
     * Sets the state of this tool with a name and Icon 
     * 
     * @param theName the name of this tool
     * @param theIcon the Icon for this tool
     * @throws NullPointerException if theIcon is null
     */
    public AbstractPaintTool(final String theName, final Icon theIcon) { 
        
        myName = theName;
        myIcon = Objects.requireNonNull(theIcon, "theIcon can not be null");
        
    }

    /**
     * Returns the name of this tool
     * 
     * @return the name of this tool
     */
    @Override
    public String getName() {
        return myName;
    }

    /**
     * Returns the Icon for this tool
     * 
     * @return the Icon for this tool
     */
    @Override
    public Icon getIcon() {
        return myIcon;
    }
    
}
