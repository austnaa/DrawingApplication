/*
 * Fall 2020
 * Assignment 4 - DrawingApplication
 */

package actions;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Objects;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;

import view.DrawingPanel;

/**
 * The SaveAction class is an Action dedicated to saving the current state
 * of the Powerpaint application
 * 
 * @author Austn Attaway
 * @version Fall 2020
 */
public class SaveAction extends AbstractAction {

    /** The extension used in the saved file name */
    public static final String EXTENSION = ".shps";
    
    /** An auto-generated serial version UID for object Serialization */
    private static final long serialVersionUID = 921404807204308036L;

    /** The name of a LoadAction */
    private static final String NAME = "Save";
    
    /**
     * The DrawingPanel this SaveAction acts upon
     */
    private final DrawingPanel myDrawingPanel;
    
    /**
     * Constructs a SaveAction that can act upon the given DrawingPanel
     * 
     * @param theDrawingPanel the DrawingPanel this SaveAction acts upon
     * @throws NullPointerException if theDrawingPanel is null;
     */
    public SaveAction(final DrawingPanel theDrawingPanel) {
        super(NAME);
        
        myDrawingPanel = Objects.requireNonNull(theDrawingPanel, 
                "theDrawingPanel can not be null");
    }
    
    /**
     * Saves the shapes that are drawn on the DrawingPanel
     * 
     * @param theEvent the ActionEvent that occurred (unused)
     */
    @Override
    public void actionPerformed(final ActionEvent theEvent) {
        
        final JFileChooser fileSaver = new JFileChooser();
        fileSaver.showSaveDialog(null);

        try {
            final File fileToSave = fileSaver.getSelectedFile();
            if (fileToSave == null) return;
            final FileOutputStream file = 
                    new FileOutputStream(fileToSave.getPath() + EXTENSION);
            final ObjectOutputStream outStream = new ObjectOutputStream(file);
            
            outStream.writeObject(myDrawingPanel.getShapeList());
            
            outStream.close();
            file.close();
               
        } catch (final IOException theException) {
            theException.printStackTrace();
        }
    }

    
}
