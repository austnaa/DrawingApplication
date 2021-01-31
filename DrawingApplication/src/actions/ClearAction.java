/*
 * TCSS 305 - Fall 2020
 * Assignment 4 - Powerpaint
 */

package actions;

import java.awt.event.ActionEvent;
import java.util.Objects;

import javax.swing.AbstractAction;

import view.DrawingPanel;

/**
 * The ClearAction class is an Action dedicated to clearing a DrawingPanel of 
 * its shapes
 * 
 * @author Austn Attaway
 * @version Fall 2020
 */
public class ClearAction extends AbstractAction {

    /** An auto-generated serial version UID for object Serialization */
    private static final long serialVersionUID = -2598010851356903032L;
    
    /** The name of a ClearAction */
    private static final String NAME = "Clear"; 

    /** 
     * The DrawingPanel that this ClearAction acts upon
     */
    private final DrawingPanel myDrawingPanel;
    
    /**
     * Constructs a ClearAction that can clear the shapes 
     * from the given DrawingPanel 
     * 
     * @param theDrawingPanel the DrawingPanel this ClearAction acts upon
     * @throws NullPointerException if theDrawingPanel is null
     */
    public ClearAction(final DrawingPanel theDrawingPanel) {
        super(NAME);
        myDrawingPanel = Objects.requireNonNull(theDrawingPanel, 
                "theDrawingPanel can not be null");
    }
    
    /**
     * Clears the DrawingPanel of all drawn shapes.
     * 
     * @param theEvent the ActionEvent that occurred (unused)
     */
    @Override
    public void actionPerformed(final ActionEvent theEvent) {
        myDrawingPanel.clearPanel(); 
    }

}