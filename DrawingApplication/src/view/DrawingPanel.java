/*
 * TCSS 305 - Fall 2020
 * Assignment 4 - DrawingApplication
 */

package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import drawing_tools.AbstractPaintTool;
import drawing_tools.EraserTool;
import drawing_tools.LineTool;
import drawing_tools.SavedShape;

/**
 * The DrawingPanel class is a JPanel that can be used to draw shapes
 * 
 * @author Austn Attaway
 * @version Fall 2020
 */
public class DrawingPanel extends JPanel {

    /** The default stroke width for drawing */
    public static final int DEFAULT_STROKE_WIDTH = 10;
    
    /** An auto-generated serial version UID for object Serialization */
    private static final long serialVersionUID = 8444575580975344636L;
    
    /** The default primary color (UW Purple) */
    private static final Color DEFAULT_PRIMARY_COLOR = new Color(51, 0, 111);
    
    /** The default secondary color (UW Gold) */
    private static final Color DEFAULT_SECONDARY_COLOR = 
            new Color(232, 211, 162);
     
    /** The default width for this Panel */
    private static final int DEFAULT_WIDTH = 500;
    
    /** The default height for this Panel */
    private static final int DEFAULT_HEIGHT = 300;
    
    /** The default dimension for this Panel */
    private static final Dimension DEFAULT_DIMENSION = 
            new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    
    /** The background color for this Panel */
    private static final Color BACKGROUND_COLOR = Color.WHITE;
    
    /**
     * The menu bar that should be used with a DrawingPanel
     */
    private final MenuBar myMenuBar;
    
    /**
     * The primary color for drawing 
     */
    private Color myPrimaryColor;
    
    /**
     * The secondary color for drawing 
     */
    private Color mySecondaryColor;
    
    /**
     * The Color that is currently being used to draw new shapes
     */
    private Color myCurrentColor;
    
    /**
     * The current paint tool being used
     */
    private AbstractPaintTool myCurrentTool;
    
    /**
     * The current stroke width used to draw shapes
     */
    private int myCurrentStrokeWidth;
    
    /**
     * The List of SavedShapes already drawn on the panel
     */
    private List<SavedShape> myShapeList;
    
    /**
     * The List of shapes that have been deleted using "undo"
     */
    private final List<SavedShape> myUndoShapeList;
    
    
    /**
     * Initializes this DrawingPanel with a default state
     */
    public DrawingPanel() {
        super();
        
        // Panel settings
        setBackground(BACKGROUND_COLOR);
        setPreferredSize(DEFAULT_DIMENSION);
        setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));

        myMenuBar = new MenuBar(this);
        myCurrentTool = new LineTool();
        myCurrentStrokeWidth = DEFAULT_STROKE_WIDTH;
        myPrimaryColor = DEFAULT_PRIMARY_COLOR;
        mySecondaryColor = DEFAULT_SECONDARY_COLOR;
       
        myShapeList = new ArrayList<SavedShape>();
        myUndoShapeList = new ArrayList<SavedShape>();
        
        // handle mouse input
        final MyMouseInputAdapter mouseAdapter = new MyMouseInputAdapter();
        addMouseListener(mouseAdapter);
        addMouseMotionListener(mouseAdapter);

    }
    
    /**
     * Returns the Menubar that belongs to this DrawingPanel
     * @return the MenuBar the belongs to this DrawingPanel
     */
    public MenuBar getMenuBar() {
        return myMenuBar;
    }

    /**
     * Sets the current tool for drawing on this Panel
     * 
     * @param theTool the new tool to be used
     * @throws NullPointerException if theTool is null
     */
    public void setTool(final AbstractPaintTool theTool) {
        myCurrentTool = 
                Objects.requireNonNull(theTool, "theTool is can not be null");
    }
    
    
    /**
     * Sets the list of shapes that are shown on the panel
     * 
     * @param theNewList the new List of shapes to be displayed on the panel
     * @throws NullPointerException if theNewList is null
     */
    public void setShapeList(final List<SavedShape> theNewList) {
        myShapeList = Objects.requireNonNull(theNewList,
                "theNewList can not ben null");
        repaint();
    }
    
    /**
     * Returns the list of SavedShapes that are displayed on the panel
     * 
     * @return the list of SavedShapes that are displayed on the panel
     */
    public List<SavedShape> getShapeList() {
        return myShapeList;
    }
    
    /**
     * Clears the List of SavedShapes from this DrawingPanel
     */
    public void clearPanel() {
        myShapeList.clear();
        repaint();
    }
   
    /**
     * Deletes the previously drawn shape
     */
    public void undo() {
        if (!myShapeList.isEmpty()) {
            // remove the most recently drawn shape
            final SavedShape undoShape = myShapeList.remove(myShapeList.size() - 1);
            
            // add the removed shape to the undo shape list
            myUndoShapeList.add(undoShape);
            
            repaint();
        }  
    }
    
    /**
     * Repaints the shape that was previously undone
     */
    public void redo() {
        if (!myUndoShapeList.isEmpty()) {
            // remove the most recentlt added shape from the undo shape list
            final SavedShape redoShape = 
                    myUndoShapeList.remove(myUndoShapeList.size() - 1);
            
            // add the redo shape to the regular shape list
            myShapeList.add(redoShape);
            
            repaint();
        } 
    }
    
    /**
     * Sets the primary Color for this DrawingPanel
     * 
     * @param thePrimaryColor the new primary Color
     * @throws NullPointerException if thePrimaryColor is null
     */
    public void setPrimaryColor(final Color thePrimaryColor) {
        myPrimaryColor = Objects.requireNonNull(thePrimaryColor, 
                "the new primary color can not be null");
    }
    
    /**
     * Sets the secondary Color for this DrawingPanel
     * 
     * @param theSecondaryColor the new secondary Color
     * @throws NullPointerException if theSecondaryColor is null
     */
    public void setSecondaryColor(final Color theSecondaryColor) {
        mySecondaryColor = theSecondaryColor;
    }
    
    /**
     * Returns the primary paint color
     * 
     * @return the primary paint color
     */
    public Color getPrimaryColor() {
        return myPrimaryColor;
    }
    
    /**
     * Returns the secondary paint color
     * 
     * @return the secondary paint color
     */
    public Color getSecondaryColor() {
        return mySecondaryColor;
    }
    
    /**
     * Sets the current stroke width for drawing on this Panel
     * 
     * @param theWidth the new stroke width to be used
     */
    protected void setStrokeWidth(final int theWidth) {
        myCurrentStrokeWidth = theWidth;
    }
    
    /**
     * Sets up the Graphics object for painting 
     * and paints the shapes on the panel
     * 
     * @param theGraphics the Graphics object used to paint on this panel
     * @throws NullPointerException if theGraphics is null
     */
    @Override
    protected void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        
        // set up new Graphics2D object for better graphics display
        final Graphics2D graphics2D = (Graphics2D) 
                Objects.requireNonNull(theGraphics, "theGraphics can not be null");
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
       
        drawShapes(graphics2D);
        updateMenuItems();
    
    }
    
    /** 
     * Draws the shapes on this panel
     * @param theGraphics
     */
    private void drawShapes(final Graphics2D theGraphics) {
        Objects.requireNonNull(theGraphics, "theGraphics can not be null");
        
        // paint the shapes that are already saved
        for (final SavedShape currShape : myShapeList) {
            
            theGraphics.setColor(currShape.getColor());
            theGraphics.setStroke(new BasicStroke(currShape.getStrokeWidth()));
            theGraphics.draw(currShape.getShape());
           
        }
        
        theGraphics.setColor(myCurrentColor);
        theGraphics.setStroke(new BasicStroke(myCurrentStrokeWidth));
        theGraphics.draw(myCurrentTool.getShape());
        
    }
    
    /**
     * Helper method that enables/disables the Clear, Undo, and Redo menu items
     * depending on the state of this DrawingPanel
     */
    private void updateMenuItems() {
        final boolean clearAndUndoEnabled = !(myShapeList.isEmpty());
        myMenuBar.setClearEnabled(clearAndUndoEnabled);  
        myMenuBar.setUndoEnabled(clearAndUndoEnabled);
        myMenuBar.setRedoEnabled(!myUndoShapeList.isEmpty());
       
    }
    
    /**
     * Sets the current drawing color to either the primary 
     * or secondary color depending on what mouse buttons are 
     * pressed.
     * 
     * If the current tool is the Eraser, the color will be set to the 
     * background color
     * 
     * @param theEvent the MouseEvent that indicates which button was pressed
     * @throws NullPointerException if theEvent is null
     */
    private void setCurrentColor(final MouseEvent theEvent) {
        
        Objects.requireNonNull(theEvent, "theEvent can not be null");
        
        //set current color to background color if the 
        // current tool is an erasor
        if (myCurrentTool instanceof EraserTool) {
            myCurrentColor = BACKGROUND_COLOR;
        } 
        
        // if the left mouse button is clicked, 
        // set the current color to the primary color
        else if (SwingUtilities.isLeftMouseButton(theEvent)) {
            myCurrentColor = myPrimaryColor;
        }
        
        // if the right mouse button is clicked
        // set the current color to the secondary color
        else if (SwingUtilities.isRightMouseButton(theEvent)) {
            myCurrentColor = mySecondaryColor;
        }
        
    }

    /** 
     * The MyMouseAdapter class is a class used to handle
     * various inputs from the mouse.
     * 
     * @author Austn Attaway
     * @version Fall 2020
     */
    private class MyMouseInputAdapter extends MouseAdapter {
        
        /**
         * When the mouse is initially pressed, set the current color and
         * start creating a new shape.  
         *
         * @param theEvent the MouseEvent that just occurred
         * @throws NullPointerException if theEvent is null
         */
        @Override
        public void mousePressed(final MouseEvent theEvent) {
            Objects.requireNonNull(theEvent, "theEvent can not be null");
            
            if (myCurrentStrokeWidth != 0) {
                setCurrentColor(theEvent);
                final Point startPoint =
                        new Point(theEvent.getX(), theEvent.getY());
                myCurrentTool.setStartPoint(startPoint);
            }
            
        }
        
        /**
         * When the mouse is released, save the shape currently being
         * drawn and reset the paint tool
         * 
         * @param theEvent the MouseEvent that just occurred
         * @throws NullPointerException if theEvent is null
         */
        @Override
        public void mouseReleased(final MouseEvent theEvent) {
            if (myCurrentStrokeWidth != 0) {
                 
                setCurrentColor(Objects.requireNonNull(theEvent,
                        "theEvent can not be null"));
                final SavedShape savedShape = 
                        new SavedShape(myCurrentTool.getShape(),
                        myCurrentColor, myCurrentStrokeWidth);
                
                myShapeList.add(savedShape);
                myCurrentTool.reset();

                repaint();
 
            }
        }
        
        /**
         * Whenever the mouse is dragged (moved while pressed), update the
         * current shape that is being drawn
         * 
         * @param theEvent the MouseEvent that just occurred
         * @throws NullPointerException if theEvent is null
         */
        @Override
        public void mouseDragged(final MouseEvent theEvent) {
            if (myCurrentStrokeWidth != 0) {
                Objects.requireNonNull(theEvent, "theEvent can not be null");
               
                final int xPosition = theEvent.getX();
                final int yPosition = theEvent.getY();
                myCurrentTool.setEndPoint(new Point(xPosition, yPosition));
                
                repaint();
            
            }  
        }
        
    }  
    
}
