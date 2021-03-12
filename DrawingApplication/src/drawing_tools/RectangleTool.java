/*
 * Fall 2020
 * Assignment 4 - DrawingApplication
 */

package drawing_tools;

import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.util.Objects;

import javax.swing.ImageIcon;

/**
 * The RectangleTool class provides functionality dedicated to 
 * creating rectangles
 * 
 * @author Austn Attaway
 * @version Fall 2020
 */
public class RectangleTool extends AbstractPaintTool {

    /** The name of a Rectangle tool */
    private static final String NAME = "Rectangle";
    
    /** The Icon for a Rectangle tool */
    private static final ImageIcon ICON = 
            new ImageIcon("./images/rectangle.gif");
    
    /**
     * The Point that is initially set for a new Rectangle
     */
    private Point myOriginPoint;
    
    /**
     * The Point that is set after the origin Point is 
     * set for a Rectangle being created
     */
    private Point mySecondaryPoint;
 
    /**
     * Initializes this RectangleTool with default values
     */
    public RectangleTool() {
        super(NAME, ICON);
        
        myOriginPoint    = AbstractPaintTool.NO_POINT;
        mySecondaryPoint = AbstractPaintTool.NO_POINT;
    }

    /**
     * Returns the Rectangle2D shape that is currently saved
     */
    @Override
    public Shape getShape() {
        final Rectangle2D rect = new Rectangle2D.Double();
        rect.setRect(getShapeX(), getShapeY(), getWidth(), getHeight());  
        return rect;
    }

    /**
     * Sets the starting Point for the currently drawn Rectangle
     * 
     * @param thePoint the Point
     * @throws NullPointerException if thePoint is null
     */
    @Override
    public void setStartPoint(final Point thePoint) {
        myOriginPoint = Objects.requireNonNull(thePoint,
                "thePoint can not be null");
        mySecondaryPoint = Objects.requireNonNull(thePoint,
                "thePoint can not be null");
        
    }

    /**
     * Sets the ending Point for the currently drawn Rectangle
     * 
     * @param thePoint the Point
     * @throws NullPointerException if thePoint is null
     */
    @Override
    public void setEndPoint(final Point thePoint) {
        mySecondaryPoint = Objects.requireNonNull(thePoint, 
                "thePoint can not be null");
        
    }

    /**
     * Resets this tool for future use
     */
    @Override
    public void reset() {
        myOriginPoint    = AbstractPaintTool.NO_POINT;
        mySecondaryPoint = AbstractPaintTool.NO_POINT;
     
    }
    
    /**
     * Returns the width of the current Rectangle being drawn
     * 
     * @return the width of the current Rectangle being drawn
     */
    protected double getWidth() {
        return Math.abs(mySecondaryPoint.getX() - myOriginPoint.getX()); 
    }
    
    /**
     * Returns the height of the current Rectangle being drawn
     * 
     * @return the height of the current Rectangle being drawn
     */
    protected double getHeight() {
        return Math.abs(mySecondaryPoint.getY() - myOriginPoint.getY()); 
    }
    
    /**
     * Returns the upper-left x position of the current Rectangle being drawn
     * 
     * @return the upper-left x position of the current Rectabgle being drawn
     */
    protected double getShapeX() {
        return myOriginPoint.getX() < mySecondaryPoint.getX() ? 
                myOriginPoint.getX() : mySecondaryPoint.getX();
    }
    
    /**
     * Returns the upper-left y position of the current Rectangle being draw

     * @return the upper-left y position of the current Rectabgle being drawn
     */
    protected double getShapeY() {
        return myOriginPoint.getY() < mySecondaryPoint.getY() ? 
                myOriginPoint.getY() : mySecondaryPoint.getY();
    }

}
