/*
 * Fall 2020
 * Assignment 4 - DrawingApplication
 */

package view;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JToolBar;

/**
 * The Frame class is a JFrame that contains contents related 
 * to the PowerPaint application
 * 
 * @author Austn Attaway
 * @version Fall 2020
 */
public class Frame extends JFrame {
    
    /** The Icon for the Frame */
    public static final ImageIcon FRAME_ICON = new ImageIcon("./resources/w.gif");
    
    /** The title for the Frame **/
    private static final String FRAME_TITLE = "Power Paint";
   
    /** An auto-generated serial version UID for object Serialization */
    private static final long serialVersionUID = 2678550713804682656L;
    
    /**
     * Instantiates the GUI and its componenets, then displays it
     */
    public Frame() {
        
        super(FRAME_TITLE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(FRAME_ICON.getImage());
        setFocusable(true);
        
        final DrawingPanel drawingPanel = new DrawingPanel();
        final MenuBar menuBar = drawingPanel.getMenuBar();
        final JToolBar toolBar = menuBar.createToolBar();
        
        setJMenuBar(menuBar);
        add(drawingPanel, BorderLayout.CENTER);
        add(toolBar, BorderLayout.SOUTH); 

        pack();
        setVisible(true);
           
    } 
    
}



