package com.design.pattern.behavioural;

import java.util.Map;

// AbstractExpression interface
interface AbstractExpression {
    int interpret(Context context);
}

// TerminalExpression class
class TerminalExpression implements AbstractExpression {
    private String variable;

    public TerminalExpression(String variable) {
        this.variable = variable;
    }

    @Override
    public int interpret(Context context) {
        return context.getValue(variable);
    }
}

// NonTerminalExpression for Binary Operations
class BinaryExpression implements AbstractExpression {
    private AbstractExpression leftExpression;
    private AbstractExpression rightExpression;
    private char operator;

    public BinaryExpression(AbstractExpression leftExpression, AbstractExpression rightExpression, char operator) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
        this.operator = operator;
    }

    @Override
    public int interpret(Context context) {
        int leftValue = leftExpression.interpret(context);
        int rightValue = rightExpression.interpret(context);
        switch (operator) {
            case '+':
                return leftValue + rightValue;
            case '-':
                return leftValue - rightValue;
            case '*':
                return leftValue * rightValue;
            case '/':
                return leftValue / rightValue;
            default:
                throw new UnsupportedOperationException("Operator not supported: " + operator);
        }
    }
}

// Context class to store variables and their values
class Context {
    private Map<String, Integer> variableMap;

    public Context(Map<String, Integer> variableMap) {
        this.variableMap = variableMap;
    }

    public int getValue(String variable) {
        if (!variableMap.containsKey(variable)) {
            throw new IllegalArgumentException("Variable not found in context: " + variable);
        }
        return variableMap.get(variable);
    }
}

// Client Code
public class InterpreterClient {
    public static void main(String[] args) {
        // Define context with variable values
        Context context = new Context(Map.of(
            "a", 2,
            "b", 4,
            "c", 8,
            "d", 16
        ));

        // Construct the expression: (a * b) + (c * d)
        AbstractExpression leftExpression = new BinaryExpression(
            new TerminalExpression("a"),
            new TerminalExpression("b"),
            '*'
        );
        AbstractExpression rightExpression = new BinaryExpression(
            new TerminalExpression("c"),
            new TerminalExpression("d"),
            '*'
        );
        AbstractExpression fullExpression = new BinaryExpression(leftExpression, rightExpression, '+');

        // Interpret the expression
        int result = fullExpression.interpret(context);

        // Print the result
        System.out.println("Result of (a * b) + (c * d): " + result); // Output: 136
    }
}

