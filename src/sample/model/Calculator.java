package sample.model;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Calculator {
    private Expression expression;
    private Label result;

    private boolean switcher;
    private NumberLabel numberLabel;
    private OperatorLabel operatorLabel;

    private ScriptEngine engine;

    public void setup(AnchorPane anchorPane, Label result) {
        expression = new Expression();
        anchorPane.getChildren().add(expression);

        this.result = result;

        ScriptEngineManager mgr = new ScriptEngineManager();
        engine = mgr.getEngineByName("JavaScript");
    }

    public void writeNumber(String number) throws ScriptException {
        if (!switcher) {
            numberLabel = new NumberLabel();
            expression.add(numberLabel);

            switcher = true;
        }
        numberLabel.write(number);
        equal();
    }

    public void writeOperator(String operator) {
        if (switcher) {
            operatorLabel = new OperatorLabel();
            expression.add(operatorLabel);

            switcher = false;
        }
        if (operatorLabel != null) { operatorLabel.write(operator); }
    }

    public void equal() throws ScriptException {
        result.setText("=" + evaluate(expression.getExpression()));
    }

    private String evaluate(String expr) throws ScriptException {
        return Integer.toString((int) engine.eval(expr));
    }

    public void delete() {

    }
}
