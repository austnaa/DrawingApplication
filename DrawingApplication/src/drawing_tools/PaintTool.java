/*
 * Fall 2020
 * Assignment 4 - DrawingApplication
 */

package drawing_tools;

import java.awt.Point;
import java.awt.Shape;

import javax.swing.Icon;

/**
 * Provides method stubs for the methods a PaintTool must implement
 * 
 * @author Austn Attaway
 * @version Fall 2020
 */
public interface PaintTool {
    
    /**
     * Returns the name of this tool
     * 
     * @return the name of this tool
     */
    String getName();
    
    /**
     * Returns the Icon for this tool
     * 
     * @return the Icon for this tool
     */
    Icon getIcon();
    
    /**
     * Returns the Shape this tool draws
     * 
     * @return the Shape this tool draws
     */
    Shape getShape();
    
    /**
     * Sets the starting point for the current Shape being drawn
     * 
     * @throws NullPointerException if thePoint is null
     */
    void setStartPoint(final Point thePoint);
    
    /**
     * Sets the endPoint for this shape
     * 
     * @throws NullPointerException if thePoint is null
     */
    void setEndPoint(final Point thePoint);
    
    /**
     * Resets this tool to default values
     */
    void reset();
  
}
