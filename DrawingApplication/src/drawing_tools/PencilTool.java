/*
 * TCSS 305 - Fall 2020
 * Assignment 4 - Powerpaint
 */

package drawing_tools;

import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.swing.ImageIcon;

/**
 * The PencilTool class provides functionality dedicated to creating path shapes 
 * similar to a pencil
 * 
 * @author Austn Attaway
 * @version Fall 2020
 *
 */
public class PencilTool extends AbstractPaintTool {

    /** The name of a Pencil */
    private static final String NAME = "Pencil";
    
    /** The Icon for a Pencil */
    private static final ImageIcon ICON = new ImageIcon("./images/pencil.gif");
    
    /**
     * The List of Point objects storing data for the current drawing
     */
    private final List<Point> myPointList;
    
    /**
     * Constructs a Pencil tool that can be used to draw like 
     * a pencil on a DrawingPanel
     */
    public PencilTool() {
        super(NAME, ICON);
        myPointList = new ArrayList<Point>();
        
    }

    /**
     * Returns the Path2D object that is currently being drawn 
     */
    @Override
    public Shape getShape() {
        final Path2D path = new Path2D.Double();
        
        if (!myPointList.isEmpty()) {
            
            // set start point
            final Point startPoint = myPointList.get(0);
            path.moveTo(startPoint.getX(), startPoint.getY());
            
            // add the rest of the lines
            for (int i = 1; i < myPointList.size(); i++) {
                final Point currPoint = myPointList.get(i);
                path.lineTo(currPoint.getX(), currPoint.getY());
            } 
        }
        
        return path;
    }

    /**
     * Sets the start point of this drawing
     * 
     * @param thePoint the starting Point
     * @throws NullPointerException if thePoint is null
     */
    @Override
    public void setStartPoint(final Point thePoint) {

        myPointList.add(Objects.requireNonNull(
                thePoint, "thePoint can not be null"));   
    }

    /**
     * Sets the next point for the current drawing
     * 
     * @param thePoint the next Point
     * @throws NullPointerException if thePoint is null
     */
    @Override
    public void setEndPoint(final Point thePoint) {
        myPointList.add(Objects.requireNonNull(thePoint));
        
    }

    /**
     * Resets the current drawing 
     */
    @Override
    public void reset() {
        myPointList.clear();
    }

}
