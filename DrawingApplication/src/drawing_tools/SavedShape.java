/*
 * Fall 2020
 * Assignment 4 - DrawingApplication
 */

package drawing_tools;

import java.awt.Color;
import java.awt.Shape;
import java.io.Serializable;
import java.util.Objects;

/**
 * The SavedShape class is dedicated to saving important information about
 * shapes that are meant to be drawn on a JPanel
 * 
 * @author Austn Attaway
 * @version Fall 2020
 */
public final class SavedShape implements Serializable {

    /** An auto-generated serial version UID for object Serialization */
    private static final long serialVersionUID = -7596996491598240757L;

    /** 
     * The Shape being saved 
     */
    private final Shape myShape;
    
    /**
     * The Color of the Shape
     */
    private final Color myColor;
    
    /**
     * The stroke width of the Shape
     */
    private final int myStrokeWidth;
    
    /**
     * Initializes the Shape, Color, and stroke width for the Shape being saved
     * 
     * @param theShape the Shape to be saved
     * @param theColor the Color of the Shape to be saved
     * @param theStrokeWidth the StrokeWidth of the Shape to be saved
     * @throws NullPointerException if theShape is null
     * @throws NullPointerException if theColor is null
     * @throws IllegalArgumentException if theStrokeWidth is less than 0
     */
    public SavedShape(final Shape theShape, final Color theColor, 
            final int theStrokeWidth) {
        
        myShape = Objects.requireNonNull(theShape, "theShape can not be null");
        myColor = Objects.requireNonNull(theColor, "theColor can not be null");
        
        if (theStrokeWidth < 0) {
            throw new IllegalArgumentException(
                    "theStrokeWidth can not be less than 0");
        } else {
            myStrokeWidth = theStrokeWidth;     
        }
    }
    
    /**
     * Returns the Shape
     * 
     * @return the Shape
     */
    public Shape getShape() {
        return myShape;
    }
    
    /**
     * Returns the Color of the saved Shape
     * 
     * @return the Color of the saved Shape
     */
    public Color getColor() {
        return myColor;
    }
    
    /**
     * Returns the stroke width for the saved Shape
     * 
     * @return the stroke width for the saved Shape
     */
    public int getStrokeWidth() {
        return myStrokeWidth;
    }
  
}
