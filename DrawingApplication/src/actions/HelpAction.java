/*
 * Fall 2020
 * Assignment 4 - DrawingApplication
 */

package actions;

import java.awt.Component;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.util.Objects;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import view.Frame;

/**
 * The HelpAction class creates an Action object that when invoked, a window 
 * pops up containing information about this project. 
 * 
 * @author Austn Attaway
 * @version Fall 2020
 */
public class HelpAction extends AbstractAction {

    /** An auto-generated serial version UID for object Serialization */
    private static final long serialVersionUID = -8024531792314956268L;
    
    /** The width of the ImageIcon shown on th JOptionPane */
    private static final int IMAGE_WIDTH = 50;
    
    /** The height of the ImageIcon shown on th JOptionPane */
    private static final int IMAGE_HEIGHT = 40;

    /** The name of this Action */
    private static final String NAME = "About"; 
    
    /** The message shown on the popup window when this action is activated */
    private static final String MESSAGE = "Austn Attaway\nAutumn 2020\n"
            + "TCSS 305 Assignment 4";
    
    /** 
     * The parent component to the JOptionPane this Action creates
     */
    private final Component myParentComponent;

    /**
     * Constructs an Action that will create a pop up window when it is
     * invoked
     * 
     * @param theParentComponent the parent component to the JOptionPane
     *        this Action creates
     * @throws NullPointerException if theParentComponent is null
     */
    public HelpAction(final Component theParentComponent) {
        super(NAME);
        myParentComponent = Objects.requireNonNull(theParentComponent);
    }

    /**
     * Runs whenever a component assigned this Action is interacted with, 
     * providing a JOptionPane with information about this project.
     * 
     * {@inheritDoc}
     */
    @Override
    public void actionPerformed(final ActionEvent theEvent) {
           
        // rescale the ImageIcon from the main window 
        // for the JOptionPane
        final Image iconImage = Frame.FRAME_ICON.getImage(); 
        final Image newImage = iconImage.getScaledInstance(IMAGE_WIDTH, 
                IMAGE_HEIGHT, java.awt.Image.SCALE_SMOOTH);   
        final ImageIcon imageIcon = new ImageIcon(newImage);  

        // create the JOptionPane pop up window
        JOptionPane.showMessageDialog(myParentComponent, MESSAGE, NAME,
                JOptionPane.INFORMATION_MESSAGE, imageIcon); 
    }
   
}
