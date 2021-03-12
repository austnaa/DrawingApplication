/*
 * Fall 2020
 * Assignment 4 - DrawingApplication
 */

package actions;

import java.awt.event.ActionEvent;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Objects;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;

import drawing_tools.SavedShape;
import view.DrawingPanel;

/**
 * The LoadAction class is an Action dedicated to loading
 * a previously saved state of the powerpoint application
 * 
 * @author Austn Attaway
 * @version Fall 2020
 */
public class LoadAction extends AbstractAction {

    /** An auto-generated serial version UID for object Serialization */
    private static final long serialVersionUID = -6473457836836472100L;

    /** The name of a LoadAction */
    private static final String NAME = "Load";
    
    /**
     * The DrawingPanel this LoadAction acts upon
     */
    private final DrawingPanel myDrawingPanel;
    
    /**
     * Constructs a LoadAction that can act upon the given DrawingPanel
     * 
     * @param theDrawingPanel the DrawingPanel this LoadAction acts upon
     * @throws NullPointerException if theDrawingPanel is null;
     */
    public LoadAction(final DrawingPanel theDrawingPanel) {
        super(NAME);
        myDrawingPanel = Objects.requireNonNull(theDrawingPanel, 
                "theDrawingPanel can not be null");
       
    }
    
    /**
     * Serializes the list of shapes that are shown on the DrawingPanel
     * 
     * @param theEvent the ActionEvent that occurred (unused)
     */
    @SuppressWarnings("unchecked")
    @Override
    public void actionPerformed(final ActionEvent theEvent) {
        
        // create a dialogue box to allow the user to pick a file to load
        final JFileChooser fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(null);

        try {
            
            // get the input
            final File selectedFile = fileChooser.getSelectedFile();
            final BufferedInputStream bufferedInStream = 
                    new BufferedInputStream(new FileInputStream(selectedFile));
            final ObjectInputStream inStream = 
                    new ObjectInputStream(bufferedInStream);

            // save the list and update the DrawingPanel
            final ArrayList<SavedShape> savedShapeList = new ArrayList<>();
            savedShapeList.addAll((ArrayList<SavedShape>) inStream.readObject());
            myDrawingPanel.setShapeList(savedShapeList);
           
            inStream.close();
            bufferedInStream.close();
            
        } catch (final IOException theException) {
            theException.printStackTrace();
        
        } catch (final ClassNotFoundException theException) {
            theException.printStackTrace();
        }
        
    }

}
