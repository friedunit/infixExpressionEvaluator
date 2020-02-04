/*
 * Filename: DivideByZero.java
 * Author: John Kaiser
 * Date: 2/4/2020
 * Purpose: Exception to be thrown when attempting to divide by zero
 */
package expressionevaluator;

/**
 *
 * @author johnkaiser
 */
import javax.swing.JOptionPane;

public class DivideByZero extends Exception {
    
    public DivideByZero() {
        JOptionPane.showMessageDialog(null, "Cannot divide by zero!");
    }
}