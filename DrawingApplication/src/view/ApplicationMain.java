/*
 * Fall 2020
 * Assignment 4 - DrawingApplication
 */

package view;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * The Application class contains the entry point for
 * the PowerPoint application 
 * 
 * @author Austn Attaway
 * @version Fall 2020
 */
public final class ApplicationMain {

    /**
     * Private empty constructor to ensure 
     * Application class is not instantiated 
     */
    private ApplicationMain() { }
    
    /**
     * Starts the PowerPaint program
     * 
     * @param theArgs the command line arguments (ignored)
     */
    public static void main(final String[] theArgs) {
        
        // Set the Look and Feel for the program
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (final UnsupportedLookAndFeelException theException) {
            theException.printStackTrace();
        }
        catch (final IllegalAccessException theException) {
            theException.printStackTrace();
        }
        catch (final InstantiationException theException) {
            theException.printStackTrace();
        }
        catch (final ClassNotFoundException theException) {
            theException.printStackTrace();
        }
        
        // create the GUI
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Frame();
            }
        });
    }
    
}
