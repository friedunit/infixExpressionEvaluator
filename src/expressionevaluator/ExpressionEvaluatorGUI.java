/*
 * Filename: ExpressionEvaluatorGUI.java
 * Author: John Kaiser
 * Date: 2/4/2020
 * Purpose: GUI for ExpressionEvaluator.java to calculate infix expressions of 
 * unsigned integers using two stacks.
 */
package expressionevaluator;

/**
 *
 * @author johnkaiser
 */
import java.awt.event.ActionEvent;
import javax.swing.*;

public class ExpressionEvaluatorGUI extends JFrame {
    //Declare private JFrame components
    private JLabel enterLabel = new JLabel("Enter Infix Expression");
    private JTextField inputTextField = new JTextField("");
    private JButton evaluateButton = new JButton("Evaluate");
    private JLabel resultLabel = new JLabel("Result");
    private JTextField resultTextField = new JTextField("");
    
    private static String input;
    
    public ExpressionEvaluatorGUI() {
        super("Infix Expression Evaluator");
        //Set resultTextField as uneditable
        resultTextField.setEditable(false);
        setLayout();
        getInput();
    }
    
    private void setLayout() {
        //set the layout using group layout
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addContainerGap(20, 20)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(enterLabel)
                        .addComponent(resultLabel))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(inputTextField)
                        .addComponent(evaluateButton)
                        .addComponent(resultTextField))
                .addContainerGap(20, 20));
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addContainerGap(20, 20)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(enterLabel)
                        .addComponent(inputTextField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(evaluateButton))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(resultLabel)
                        .addComponent(resultTextField))
                .addContainerGap(20, 20));
        pack();
    }
    
    
    private void getInput() {
        //Add actionListener to evaluateButton
        evaluateButton.addActionListener((ActionEvent a) -> {
            try {
            //Get user's input expression
            input = inputTextField.getText();
            //Tokenize user's expression
            ExpressionEvaluator.tokenize(input);
        
            //Display result
            resultTextField.setText(String.valueOf(ExpressionEvaluator.getFinalResult()));
            } catch (NumberFormatException num) {
                JOptionPane.showMessageDialog(null, "Please enter only integers.");
            } catch (DivideByZero ex) {
            }
        });
    }
    
    private void display() {
        setVisible(true);
        //Centers the frame on the screen
        setLocationRelativeTo(null);
        //Sets the window to be closeable
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400,150);
    }

    public static void main(String[] args) {
        ExpressionEvaluatorGUI test = new ExpressionEvaluatorGUI();
        test.display();       
    }    
}

