package sample.model;

import javafx.scene.layout.AnchorPane;

public class Calculator {
    private Expression expression;

    public void setup(AnchorPane anchorPane) {
        expression = new Expression();
        anchorPane.getChildren().add(expression);
    }

    private boolean isDeclaredNumber;
    private NumberLabel numberLabel;

    public void writeNumber(String number) {
        if (!isDeclaredNumber) {
            numberLabel = new NumberLabel();
            expression.add(numberLabel);
            isDeclaredNumber = true;
            isDeclaredOperator = false;
        }
        numberLabel.write(number);
    }

    private boolean isDeclaredOperator;
    private OperatorLabel operatorLabel;

    public void writeOperator(String operator) {
        if (!isDeclaredOperator) {
            operatorLabel = new OperatorLabel();
            expression.add(operatorLabel);
            isDeclaredOperator = true;
            isDeclaredNumber = false;
        }
        operatorLabel.write(operator);
    }

    public void equal() {
        System.out.println(expression.getExpression());
    }

    private void switchCondition() {
    }
}
