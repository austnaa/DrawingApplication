/*
 * TCSS 305 - Fall 2020
 * Assignment 4 - Powerpaint
 */

package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Objects;

import javax.swing.Icon;

/**
 * The ColorMenuIcon class is an Icon that displays the primary or 
 * secondary painting color of a DrawingPanel
 * 
 * @author Austn Attaway
 * @version Fall 2020
 */
public class ColorMenuIcon implements Icon {
    
    /**
     * An enum that helps specify which color to display 
     * 
     * @author Austn Attaway
     * @version Fall 2020
     */
    public enum Type {PRIMARY, SECONDARY};

    /** The outline Color of the Icon */
    public static final Color ICON_OUTLINE_COLOR = Color.BLACK;
    
    /** The width of this Icon */
    private static final int ICON_WIDTH = 11;
    
    /** The height of this Icon */
    private static final int ICON_HEIGHT = 11;
    
    /**
     * The type of DrawingPanel paint color (PRIMARY or SECONDARY) 
     * this Icon displays 
     */
    private final Type myType;
    
    /** 
     * The DrawingPanel this Icon gets its color from 
     */
    private final DrawingPanel myDrawingPanel;
    
    /**
     * Initializes this ColorMenuIcon with the given DrawingPanel and type
     *  
     * @param theDrawingPanel the DrawingPanel this Icon gets its color from
     * @param theType the type of color (primary or secondary) this Icon displays
     * @throws NullPointerException if theType is null
     * @throws NullPointerException if theDrawingPanel is null
     */
    public ColorMenuIcon(final DrawingPanel theDrawingPanel, 
            final Type theType) {
        super();
        
        myType = Objects.requireNonNull(theType, "theType can not be null");
        myDrawingPanel = theDrawingPanel;
    }
    
    /**
     * Returns the width of this Icon
     * 
     * @return the width of this Icon
     */
    @Override
    public int getIconWidth() {  
        return ICON_WIDTH;
    }

    /**
     * Returns the height of this Icon
     * 
     * @return the height of this Icon
     */
    @Override
    public int getIconHeight() {
        return ICON_HEIGHT;
    }

    /**
     * Paints the Icon 
     * 
     * @param theComponent (unused)
     * @param theGraphics the Graphics object used for painting
     * @param theX the x position this Icon should be drawn at
     * @param theY the y position this Icon should be drawn at
     * @throws NullPointerException if theGraphics is null
     */
    @Override
    public void paintIcon(final Component theComponent,
            final Graphics theGraphics, final int theX, final int theY) {
 
        final Graphics2D graphics2d = (Graphics2D) 
                Objects.requireNonNull(theGraphics, "theGraphics can not be null");
      
        // fill the icon with the correct color
        final Color newColor = myType.equals(Type.PRIMARY) ?
                myDrawingPanel.getPrimaryColor() : myDrawingPanel.getSecondaryColor();
        graphics2d.setColor(newColor);
        graphics2d.fillRect(theX, theY, getIconWidth(), getIconHeight());
      
        // draw a black outine around the icon
        graphics2d.setColor(ICON_OUTLINE_COLOR);
        graphics2d.drawRect(theX, theY, getIconWidth(), getIconHeight());
       
    }

}
