/*
 * TCSS 305 - Fall 2020
 * Assignment 4 - Powerpaint
 */

package view;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSlider;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;

import actions.ClearAction;
import actions.ColorAction;
import actions.HelpAction;
import actions.LoadAction;
import actions.RedoAction;
import actions.SaveAction;
import actions.ToolAction;
import actions.UndoAction;
import drawing_tools.EllipseTool;
import drawing_tools.EraserTool;
import drawing_tools.LineTool;
import drawing_tools.PencilTool;
import drawing_tools.RectangleTool;

/**
 * The MenuBar class is a JMenuBar meant to be used with a DrawingPanel
 *
 * @author Austn Attaway
 * @version Fall 2020
 *
 */
public class MenuBar extends JMenuBar {
   
    /** An auto-generated serial version UID for object Serialization */
    private static final long serialVersionUID = 7458140994211382630L;
    
    // file menu titles
    /** The name of the File menu */
    private static final String FILE_TITLE = "File";
    
    // edit menu titles
    /** The name of the Edit menu */
    private static final String EDIT_TITLE = "Edit";
    
    /** The key press required to activate the UndoAction */
    private static final KeyStroke UNDO_ACCELERATOR = 
            KeyStroke.getKeyStroke(KeyEvent.VK_Z, KeyEvent.CTRL_DOWN_MASK);
    
    /** The key press required to activate the RedoAction */
    private static final KeyStroke REDO_ACCELERATOR =
            KeyStroke.getKeyStroke(KeyEvent.VK_Y, KeyEvent.CTRL_DOWN_MASK);
    
    // options menu titles
    /** The name of the Options menu */
    private static final String OPTIONS_TITLE = "Options";
    
    /** The name of the Thickness menu item */
    private static final String THICKNESS_TITLE = "Thickness";
    
    /** The name of the primary color chooser menu item */
    private static final String PRIMARY_COLOR_ITEM_TITLE = "Primary Color...";
    
    /** The name of the secondary color chooser menu item */
    private static final String SECONDARY_COLOR_ITEM_TITLE =
            "Secondary Color...";
    
    /** The name of the Clear menu item */
    private static final String CLEAR_TITLE = "Clear";
    
    // the tools menu title
    /** The name of the Tools menu */
    private static final String TOOLS_TITLE = "Tools";
    
    // the help menu titles
    /** The name of the Help menu */
    private static final String HELP_TITLE = "Help";
    
    /** The mame of the about menu item */
    private static final String ABOUT_TITLE = "About";

    // thickness slider settings
    /** The minimum value for the thickness slider */
    private static final int SLIDER_MIN_VALUE = 0;
    
    /** The maximum value for the thickness slider */
    private static final int SLIDER_MAX_VALUE = 20;
    
    /** The thickness slider major tick spacing */
    private static final int MAJOR_TICK_SPACING = 5;
    
    /** The thickness slider minor tick spacing */
    private static final int MINOR_TICK_SPACING = 1;
    
    /**
     * The DrawingPanel that is acted upon
     */
    private final DrawingPanel myDrawingPanel;
    
    /**
     * A List of ToolActions that are used in the menu and tool bar
     */
    private final List<ToolAction> myToolActions;
    
    /**
     * The Options JMenu 
     */
    private final JMenu myOptionsMenu;
    
    /** 
     * The JMenuItem used for clearing the DrawingPanel
     */
    private JMenuItem myClearItem;
    
    /** 
     * The JMenuItem used for deleting the previously drawn shape
     */
    private JMenuItem myUndoItem;
    
    /** 
     * The JMenuItem used for repainting the previously undone shape
     */
    private JMenuItem myRedoItem;
    
    /**
     * Initializes this MenuBar
     * 
     * @param theDrawingPanel the DrawingPanel some 
     *        Actions in the menu bar act upon
     * @param theFrame the Frame that contains theDrawingPanel
     * @throws NullPointerException if theDrawingPanel is null
     * @throws NullPointerException if theFrame is null
     */
    public MenuBar(final DrawingPanel theDrawingPanel) {
        super();
      
        myDrawingPanel = Objects.requireNonNull(theDrawingPanel,
                "theDrawingPanel can not be null");
                
        myOptionsMenu = new JMenu(OPTIONS_TITLE);
        
        myToolActions = new ArrayList<ToolAction>();
        
        setUpFileMenu();
        setUpEditMenu();
        setUpOptionsMenu();
        setUpToolActions();
        setUpToolsMenu();
        setUpHelpMenu();
        
    }
    
    /**
     * Enables/disables the Clear menu item depending on the 
     * passed true/false value
     * 
     * @param theMenuIsEnabled whether or not the clear menu item is enabled
     */
    public void setClearEnabled(final boolean theMenuItemIsEnabled) {
        myClearItem.setEnabled(theMenuItemIsEnabled);
    }
    
    /**
     * Enables/disables the Undo menu item depending on the 
     * passed true/false value
     * 
     * @param theMenuIsEnabled whether or not the Undo menu item is enabled
     */
    public void setUndoEnabled(final boolean theMenuItemIsEnabled) {
        myUndoItem.setEnabled(theMenuItemIsEnabled);
    }
    
    /**
     * Enables/disables the Redo menu item depending on the 
     * passed true/false value
     * 
     * @param theMenuIsEnabled whether or not the Redo menu item is enabled
     */
    public void setRedoEnabled(final boolean theMenuItemIsEnabled) {
        myRedoItem.setEnabled(theMenuItemIsEnabled);
    }
    
    /**
     * Helper method that adds the File menu to this MenuBar
     */
    private void setUpFileMenu() {
        final JMenu fileMenu = new JMenu(FILE_TITLE);
        fileMenu.setMnemonic(KeyEvent.VK_F);
        
        final JMenuItem loadItem = 
                new JMenuItem(new LoadAction(myDrawingPanel));
        loadItem.setMnemonic(KeyEvent.VK_L);
        
        final JMenuItem saveItem = 
                new JMenuItem(new SaveAction(myDrawingPanel));
        saveItem.setMnemonic(KeyEvent.VK_S);
        
        fileMenu.add(loadItem);
        fileMenu.add(saveItem);
        
        add(fileMenu);
    }
    
    /**
     * Helper method that adds the Edit menu to this MenuBar
     */
    private void setUpEditMenu() {
        final JMenu editMenu = new JMenu(EDIT_TITLE);
        editMenu.setMnemonic(KeyEvent.VK_D);
        
        myUndoItem = new JMenuItem(new UndoAction(myDrawingPanel));
        myUndoItem.setMnemonic(KeyEvent.VK_U);
        myUndoItem.setAccelerator(UNDO_ACCELERATOR);
                
        myRedoItem =
                new JMenuItem(new RedoAction(myDrawingPanel));
        myRedoItem.setMnemonic(KeyEvent.VK_R);
        myRedoItem.setAccelerator(REDO_ACCELERATOR);
        
        editMenu.add(myUndoItem);
        editMenu.add(myRedoItem);
        
        add(editMenu);
        
    }
    
   
    /**
     * Helper method that adds the Options menu to this MenuBar
     */
    private void setUpOptionsMenu() {
        
        myOptionsMenu.setMnemonic(KeyEvent.VK_O); 
        
        // stroke thickness menu
        final JMenu thicknessMenu = new JMenu(THICKNESS_TITLE);
        thicknessMenu.setMnemonic(KeyEvent.VK_T);
        thicknessMenu.add(createThicknessSlider());

        // primary color chooser item
        final JMenuItem primaryColorItem = new JMenuItem(
                new ColorAction(PRIMARY_COLOR_ITEM_TITLE, myDrawingPanel));
        primaryColorItem.setMnemonic(KeyEvent.VK_P);
        primaryColorItem.setIcon(new ColorMenuIcon(myDrawingPanel, 
                ColorMenuIcon.Type.PRIMARY));

        // secondary color chooser item
        final JMenuItem secondaryColorItem = new JMenuItem(
                new ColorAction(SECONDARY_COLOR_ITEM_TITLE, myDrawingPanel));
        secondaryColorItem.setMnemonic(KeyEvent.VK_S);
        secondaryColorItem.setIcon(new ColorMenuIcon(myDrawingPanel, 
                ColorMenuIcon.Type.SECONDARY));
        
        // clear item 
        myClearItem = new JMenuItem(CLEAR_TITLE);
        myClearItem.setAction(new ClearAction(myDrawingPanel));
        myClearItem.setMnemonic(KeyEvent.VK_C);
        
        myOptionsMenu.add(thicknessMenu);
        myOptionsMenu.addSeparator();
        myOptionsMenu.add(primaryColorItem);
        myOptionsMenu.add(secondaryColorItem);
        myOptionsMenu.addSeparator();
        myOptionsMenu.add(myClearItem);
        
        this.add(myOptionsMenu);
        
    }
    
    /**
     * Helper method that returns a JSlider used for the thickness menu
     * 
     * @return the JSlider used in the thickness menu
     */
    private JSlider createThicknessSlider() {
       
        final JSlider slider = new JSlider(SLIDER_MIN_VALUE, SLIDER_MAX_VALUE);
        slider.setMajorTickSpacing(MAJOR_TICK_SPACING);
        slider.setMinorTickSpacing(MINOR_TICK_SPACING);
        slider.setValue(DrawingPanel.DEFAULT_STROKE_WIDTH);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        
        slider.addChangeListener(theEvent -> {
            myDrawingPanel.setStrokeWidth(slider.getValue());
        });
           
        return slider;
    }
    
    /**
     * Helper method that sets up the Tool actions for future use
     */
    private void setUpToolActions() {
        
        // create the Actions
        final ToolAction pencilAction = 
                new ToolAction(new PencilTool(), myDrawingPanel);
        final ToolAction lineAction = 
                new ToolAction(new LineTool(), myDrawingPanel);    
        final ToolAction rectangleAction =
                new ToolAction(new RectangleTool(), myDrawingPanel);
        final ToolAction ellipseAction =
                new ToolAction(new EllipseTool(), myDrawingPanel);
        final ToolAction erasorAction = 
                new ToolAction(new EraserTool(), myDrawingPanel);
       
        // add the Actions to the list
        myToolActions.add(pencilAction);
        myToolActions.add(lineAction);
        myToolActions.add(rectangleAction);
        myToolActions.add(ellipseAction);
        myToolActions.add(erasorAction);
        
        // sets the Line tool to the default tool
        lineAction.actionPerformed(null);

    }
    
    /**
     * Helper method that adds the Tools menu to this MenuBar
     */
    private void setUpToolsMenu() {
        
        // set up the Tools menu
        final JMenu toolsMenu = new JMenu(TOOLS_TITLE);
        final ButtonGroup buttonGroup = new ButtonGroup();
        
        toolsMenu.setMnemonic(KeyEvent.VK_T);
        
        // add all of the tools to the Tools menu
        for (final ToolAction currAction : myToolActions) {
            final JRadioButtonMenuItem tempItem = 
                    new JRadioButtonMenuItem(currAction);     
            buttonGroup.add(tempItem);
            toolsMenu.add(tempItem); 
        }
        
        add(toolsMenu);
    
    }
    
    /**
     * Helper method that adds the Help menu to this MenuBar
     */
    private void setUpHelpMenu() {
        
        final JMenu helpMenu = new JMenu(HELP_TITLE);
        helpMenu.setMnemonic(KeyEvent.VK_H);
        
        final JMenuItem aboutMenuItem = new JMenuItem(ABOUT_TITLE);
        aboutMenuItem.setAction(new HelpAction(this));
        aboutMenuItem.setMnemonic(KeyEvent.VK_A);
        
        helpMenu.add(aboutMenuItem);
       
        add(helpMenu);

    }

    /**
     * Returns a fully-stocked tool bar
     *
     * @return a fully-stocked tool bar
     */
    public JToolBar createToolBar() {
        
        final JToolBar toolBar = new JToolBar();
        final ButtonGroup buttonGroup = new ButtonGroup();
        
        for (final ToolAction currAction : myToolActions) {
            final JToggleButton toggleButton = new JToggleButton(currAction);
            buttonGroup.add(toggleButton);
            toolBar.add(toggleButton); 
        }
        
        return toolBar;
    }
   
}
