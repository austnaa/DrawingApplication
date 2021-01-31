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
 * The UndoAction class is an Action dedicated to undoing 
 * the previous drawing a user did on a DrawingPanel
 * 
 * Note: this Action does not undo the clearing of a DrawingPanel
 * 
 * @author Austn Attaway
 * @verson Fall 2020
 */
public class UndoAction extends AbstractAction {

    /** An auto-generated serial version UID for object Serialization */
    private static final long serialVersionUID = 7195186476850615927L;
   
    /** The name of this Action */
    private static final String NAME = "Undo";
    
    /**
     * The DrawingPanel this Action acts upon 
     */
    private final DrawingPanel myDrawingPanel;
    
    /**
     * Constructs an UndoAction that can undo the previous drawing
     * on a DrawingPanel
     * 
     * @param theDrawingPanel the DrawingPanel this UndoAction acts upon
     * @throws NullPointerException if theDrawingPanel is null
     */
    public UndoAction(final DrawingPanel theDrawingPanel) {
        super(NAME);
        
        myDrawingPanel = Objects.requireNonNull(theDrawingPanel, 
                "theDrawingPanel can not be null");
    }
    
    /**
     * Deletes the shape that was most recently drawn
     * 
     * @param theEvent the ActionEvent that occurred (unused)
     */
    @Override
    public void actionPerformed(final ActionEvent theEvent) {
        myDrawingPanel.undo();
    }
    
}
