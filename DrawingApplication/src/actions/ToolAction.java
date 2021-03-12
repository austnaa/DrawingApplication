/*
 * Fall 2020
 * Assignment 4 - DrawingApplication
 */

package actions;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Objects;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import drawing_tools.AbstractPaintTool;
import view.DrawingPanel;

/**
 * The ToolAction class constructs an Action that is used to 
 * select a tool to paint with.
 * 
 * @author Austn Attaway
 * @version Fall 2020
 */
public class ToolAction extends AbstractAction {
    
    /** An auto-generated serial version UID for object Serialization */
    private static final long serialVersionUID = -4463790640139552164L;
    
    /** The width/height of the Icon on the toolbar */
    private static final int TOOLBAR_ICON_SCALE = 15;
   
    /**
     * The tool to select 
     */
    private final AbstractPaintTool myTool;
    
    /**
     * The DrawingPanel that the tool will be drawn on
     */
    private final DrawingPanel myDrawingPanel;
   
    /**
     * Constructs an action with the specified name and icon, 
     * used to select the tool used to paint with.
     * 
     * @param theName the name for this ToolAction
     * @param theIcon the Icon for this ToolAction
     * @param theTool the Tool for this ToolAction
     * @param theDrawingPanel the DrawingPanel to be acted upon
     * @throws NulllPointerException if theIcon is null
     * @throws NulllPointerException if theTool is null
     * @throws NulllPointerException if theDrawingPanel is null
     */
    public ToolAction(
            final AbstractPaintTool theTool, 
            final DrawingPanel theDrawingPanel) {
        
        super(theTool.getName());
        
        myTool = Objects.requireNonNull(theTool, 
                "theTool can not be null");
        myDrawingPanel = Objects.requireNonNull(theDrawingPanel, 
                "theDrawingPanel can not be null");
        
        assignIcons(theTool.getIcon());
        
        // sets the mnemonic to the first letter of the name,
        // if the tool is an erasor, the mnemonic is "a"
        if ("Eraser".equals(theTool.getName())) {
            putValue(Action.MNEMONIC_KEY, KeyEvent.VK_A);
        } else {
            putValue(Action.MNEMONIC_KEY, 
                    KeyEvent.getExtendedKeyCodeForChar(theTool.getName().charAt(0)));
        }
        
    }
    
    /**
     * Assigns Icons for the menu bar and tool bar
     * when this ToolAction is in use
     * 
     * @param theIcon the Icon for this ToolAction
     * @throws NullPointerException if theIcon is null
     */
    private void assignIcons(final Icon theIcon ) {
 
        final Icon originalIcon = Objects.requireNonNull(theIcon, 
                "theIcon can not be null");
        
        // assign small Icon to the menu
        putValue(Action.SMALL_ICON, originalIcon);
        
        // assign larger Icon to tool bar
        final ImageIcon imageIcon = (ImageIcon) originalIcon;
        final Image largeImage = imageIcon.getImage().
                getScaledInstance(TOOLBAR_ICON_SCALE, TOOLBAR_ICON_SCALE, 
                        java.awt.Image.SCALE_SMOOTH);
        final ImageIcon largeImageIcon = new ImageIcon(largeImage);
        putValue(Action.LARGE_ICON_KEY, largeImageIcon);
        
    }
    
    /**
     * Sets the DrawingPanel's paint tool to the 
     * tool associated with this Action
     * 
     * @param theEvent the ActionEvent that occurred (unused)
     */
    @Override
    public void actionPerformed(final ActionEvent theEvent) { 
        myDrawingPanel.setTool(myTool);
        putValue(Action.SELECTED_KEY, Boolean.TRUE); 
    }

}
