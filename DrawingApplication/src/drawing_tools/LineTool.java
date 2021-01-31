/*
 * TCSS 305 - Fall 2020
 * Assignment 4 - Powerpaint
 */

package drawing_tools;

import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Line2D;
import java.util.Objects;

import javax.swing.ImageIcon;

/**
 * The LineTool class provides functionality dedicated to creating lines
 * 
 * @author Austn Attaway
 * @version Fall 2020
 *
 */
public class LineTool extends AbstractPaintTool {
    
    /** The name of a LineTool */
    private static final String NAME = "Line";
    
    /** The Icon for a LineTool */
    private static final ImageIcon ICON = new ImageIcon("./images/line.gif");
    
    /** The starting Point for the current line being drawn */
    private Point myStartPoint;
    
    /** The ending Point for the current line being drawn */
    private Point myEndPoint;
    
    /**
     * Initializes this LineTool with default values
     */
    public LineTool() {
        super(NAME, ICON);
       
        myStartPoint = NO_POINT;
        myEndPoint = NO_POINT;
    }

    /**
     * Returns the Line to be drawn
     * 
     * @return the Line to be drawn
     */
    @Override
    public Shape getShape() {
        return new Line2D.Double(myStartPoint, myEndPoint);
    }
    
    /**
     * Returns the starting Point for the current line being drawn
     * 
     * @return the starting Point for the current line being drawn
     */
    public Point getStartPoint() {
        return myStartPoint;
    }

    /**
     * Sets the starting point values when this tool starts to draw a new line
     * 
     * @param thePoint the Point where a new line shape will start from
     * @throws NullPointerException if thePoint is null
     */
    @Override
    public void setStartPoint(final Point thePoint) {

        myStartPoint = Objects.requireNonNull(thePoint, 
                "thePoint can not be null");
        myEndPoint = Objects.requireNonNull(thePoint, 
                "thePoint can not be null");
        
    }

    /**
     * Sets the end Point for the current line being drawn
     * 
     * @param thePoint the Point that marks where the current line ends
     * @throws NullPointerException if thePoint is null
     */
    @Override
    public void setEndPoint(final Point thePoint) {
        myEndPoint = Objects.requireNonNull(thePoint, 
                "thePoint can not be null");
    }

    /**
     * Resets the Line tool so it is prepared to draw a new line
     */
    @Override
    public void reset() {
        myStartPoint = AbstractPaintTool.NO_POINT;
        myEndPoint   = AbstractPaintTool.NO_POINT;  
    }
 
}
