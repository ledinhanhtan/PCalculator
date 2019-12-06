package sample.model;

import javafx.scene.layout.FlowPane;

public class Calculator {
    private FlowPane flowPane;

    public void setup(FlowPane flowPane) {
        this.flowPane = flowPane;
    }

    private boolean isDeclaredNumber;
    private NumberLabel numberLabel;

    public void writeNumber(String number) {
        if (!isDeclaredNumber) {
            numberLabel = new NumberLabel();
            flowPane.getChildren().add(numberLabel);
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
            flowPane.getChildren().add((operatorLabel));
            isDeclaredOperator = true;
            isDeclaredNumber = false;
        }
        operatorLabel.write(operator);
    }

//    private void switchCondition() {
//        isDeclaredOperator = isDeclaredNumber;
//        isDeclaredNumber = !isDeclaredOperator;
//    }
}
