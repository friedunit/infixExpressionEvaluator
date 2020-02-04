/*
 * Filename: ExpressionEvaluator.java
 * Author: John Kaiser
 * Date: 2/4/2020
 * Purpose: Create an infix expression evaluator for unsigned integers using stacks
 */
package expressionevaluator;

/**
 *
 * @author johnkaiser
 */
import java.util.Stack;
import java.util.StringTokenizer;

public class ExpressionEvaluator {
    private static StringTokenizer st;
    private static String operators = "+-*/";
    private static String delims = "+-*/() ";
    private static int value1;
    private static int value2;
    private static String operator;
    
    //Create stack of integers for operands
    static Stack<Integer> operandStack = new Stack<>();
    //Creat stack of Strings for operators
    static Stack<String> operatorStack = new Stack<>();
    
    public static void tokenize(String input) throws DivideByZero {
        st = new StringTokenizer(input, delims, true);
        while (st.hasMoreTokens()) {
            String token = st.nextToken();
            if (!delims.contains(token)) { //check if token is NOT an operator, must be a number
                int tokenInt = Integer.parseInt(token); //Parse to integer
                operandStack.push(tokenInt); //add integer number to operand stack
            } else if (token.equals("(")) {
                operatorStack.push(token);
            } else if (token.equals(")")) {
                //check top of operator stack to see if it is left parenthesis
                while(!operatorStack.peek().equals("(")) { 
                    setValues();
                    operandStack.push(calculate(value1, value2, operator));
                }
                //If top of operator stack is now a left parenthesis, pop it
                if(operatorStack.peek().equals("("))
                    operatorStack.pop();
            } else if (operators.contains(token)) {
                //while operator stack is not empty and the top of operator stack has higher or equal 
                //precedence than current token, set values and perform calculation
                while((!operatorStack.isEmpty()) && ((getPriority(operatorStack.peek()) >= getPriority(token)))) {
                        setValues();
                        operandStack.push(calculate(value1, value2, operator));
                    }
                //else push token to operator stack
                operatorStack.push(token);
                }   
            }
        //When no more tokens, while operator stack is not empty, there should be 2 operands and 1 operator
        //perform last calculation
        while (!operatorStack.isEmpty()) {
            setValues();
            operandStack.push(calculate(value1, value2, operator));
        }
    }
    
    //pop 2 operands and 1 operator
    private static void setValues() {
        value2 = operandStack.pop();
        value1 = operandStack.pop();
        operator = operatorStack.pop();
    }
    
    //perform calculation
    private static int calculate(int value1, int value2, String operator) throws DivideByZero {
        int result = 0;
        switch(operator) {
            case "+":
                result = value1 + value2;
                break;
            case "-":
                result = value1 - value2;
                break;
            case "*":
                result = value1 * value2;
                break;
            case "/":
                //Check if denominator is 0
                if(value2!=0) {
                result = value1 / value2;
                } else {
                    //Throw exception if denominator is 0
                    throw new DivideByZero();
                }
                break;
        }
        return result;
    }
    
    //get priority of operators
    private static int getPriority(String operator) {
        int priority = 0;
        if(operator.equals("+") || operator.equals("-")) 
            priority = 1;
         else if (operator.equals("*") || operator.equals("/")) 
            priority = 2;
        return priority;
    }  
    
    //get final result
    public static int getFinalResult() {
        return operandStack.pop();
    }
}

