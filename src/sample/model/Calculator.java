package sample.model;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Calculator {
    private Expression expression;
    private Label result;

    private SimpleBooleanProperty conditionProperty;

    private ScriptEngine engine;

    public void setup(AnchorPane anchorPane, Label result) {
        conditionProperty = new SimpleBooleanProperty(false);
        expression = new Expression();
        expression.setConditionProperty(conditionProperty);
        anchorPane.getChildren().add(expression);

        this.result = result;

        ScriptEngineManager mgr = new ScriptEngineManager();
        engine = mgr.getEngineByName("JavaScript");
    }

    public void writeNumber(String number) throws ScriptException {
        if (!(expression.isEmpty() && number.equals("0"))) {
            if (!conditionProperty.getValue()) {
                expression.add(new NumberLabel());
                switchCondition();
            }
            expression.getLastLabel().write(number);
            equal();
        }
    }

    public void writeDot() {
        try {
            NumberLabel numberLabel = (NumberLabel) expression.getLastLabel();
            numberLabel.writeDot();
        } catch (ClassCastException exp) {
            //todo auto add 0.
            System.out.println("Last label is a operator");
        }
    }

    public void writeOperator(String operator) {
        if (conditionProperty.getValue()) {
            expression.add(new OperatorLabel());
            switchCondition();
        }
        try {
            expression.getLastLabel().write(operator);
        } catch (Exception ignored) { }
    }

    public void equal() throws ScriptException {
        if (!expression.isEmpty()) {
            result.setText("=" + evaluate(expression.getExpression()));
        }
    }

    private String evaluate(String expr) throws ScriptException {
        return Integer.toString((int) engine.eval(expr));
    }

    public void delete() {
        expression.delete();
    }

    private void switchCondition() {
        conditionProperty.setValue(!conditionProperty.getValue());
    }

    public void allClear() {
        expression.clear();
        result.setText("0");
        conditionProperty.setValue(false);
    }
}
