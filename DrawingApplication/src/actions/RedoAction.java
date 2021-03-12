/*
 * Fall 2020
 * Assignment 4 - DrawingApplication
 */

package actions;

import java.awt.event.ActionEvent;
import java.util.Objects;

import javax.swing.AbstractAction;

import view.DrawingPanel;

/**
 * The RedoAction class is an Action dedicated to redrawing the shape 
 * that was previously undone 
 * 
 * @author Austn Attaway
 * @version Fall 2020
 */
public class RedoAction extends AbstractAction {
   
    /** An auto-generated serial version UID for object Serialization */
    private static final long serialVersionUID = -2698040343995309985L;
    
    /** The name of this RedoAction */
    private static final String NAME = "Redo";

    /** 
     * The DrawingPanel this RedoAction acts upon 
     */
    private final DrawingPanel myDrawingPanel;
    
    /**
     * Constructs a RedoAction that can be used to redo previously
     * undone shapes
     * @param theDrawingPanel the DrawingPanel this RedoAction acts upon
     * @throws NullPointerException if theDrawingPanel is null
     */
    public RedoAction(final DrawingPanel theDrawingPanel) {
        super(NAME);
        myDrawingPanel = Objects.requireNonNull(theDrawingPanel,
                "theDrawingPanel can not be null");
    }
    
    /**
     * Redraws the shape that was previously undone if such shape exists
     * 
     * @param theEvent the ActionEvent that occurred (unused)
     */
    @Override
    public void actionPerformed(final ActionEvent theEvent) {
        myDrawingPanel.redo();
    }
    
}
