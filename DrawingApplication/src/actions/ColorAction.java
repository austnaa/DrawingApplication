/*
 * Fall 2020
 * Assignment 4 - DrawingApplication
 */

package actions;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.Objects;

import javax.swing.AbstractAction;
import javax.swing.JColorChooser;

import view.DrawingPanel;

/**
 * Provides the ability to select and set the color of the DrawingPanel paint
 * tool (primary or secondary color)
 * 
 * @author Austn Attaway
 * @version Fall 2020
 *
 */
public class ColorAction extends AbstractAction {

    /** An auto-generated serial version UID for object Serialization */
    private static final long serialVersionUID = -3009707853186042839L;
    
    /** The title of the pop up JColorChooser */
    private static final String COLOR_CHOOSER_TITLE = "Color Chooser";
    
    /** The String that identifies that the primary color should be changed */
    private static final String PRIMARY_COLOR_KEY = "Primary";
    
    /**
     * The DrawingPanel this ColorAction acts upon
     */
    private final DrawingPanel myDrawingPanel;

    /**
     * Determines if this ColorAction changes the Primary color(true), 
     * or secondary color(false)
     */
    private final boolean myPrimaryColorStatus;
    
    /**
     * Constructs a ColorAction with the specified name and DrawingPanel that
     * will allow the setting of the primary or secondary drawing color.
     * 
     * To ensure this ColorAction changes the primary color, theName should 
     * contain "Primary", otherwise, this ColorAction will change the 
     * secondary color
     * 
     * @param theName the name of this Action
     * @param theDrawingPanel the DrawingPanel that the new colors
     *        will be assigned to
     * @throws NullPointerException if theDrawingPanel is null
     */
    public ColorAction(final String theName,
            final DrawingPanel theDrawingPanel) {
        
        super(theName);
        myPrimaryColorStatus = theName.contains(PRIMARY_COLOR_KEY);
        myDrawingPanel = Objects.requireNonNull(theDrawingPanel, 
                "theDrawingPanel can not be null"); 
    }
    
    /**
     * Creates a pop up color chooser, and assigns the new color 
     * to the primary or secondary color. 
     * 
     * If the user hits cancel, the color does not change.
     * 
     * @param theEvent the ActionEvent for this Action (unused)
     */
    @Override
    public void actionPerformed(final ActionEvent theEvent) {
        
        // get the new color from a pop up color chooser
        Color newColor = JColorChooser.showDialog(null, 
                COLOR_CHOOSER_TITLE, null);
        
        // sets the color to the original color if the user hits "cancel" 
        // on the color chooser
        if (newColor == null) {
            newColor = myPrimaryColorStatus ? 
                    myDrawingPanel.getPrimaryColor() :
                    myDrawingPanel.getSecondaryColor();
        }
        
        // set the new color
        if (myPrimaryColorStatus) {
            myDrawingPanel.setPrimaryColor(newColor);
        } else {
            myDrawingPanel.setSecondaryColor(newColor);
        }
    }
    
}
