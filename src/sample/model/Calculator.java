package sample.model;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.text.NumberFormat;
import java.util.Locale;

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
        result.textProperty().addListener((observable, oldValue, newValue) -> specialCase(newValue));

        ScriptEngineManager mgr = new ScriptEngineManager();
        engine = mgr.getEngineByName("JavaScript");
    }

    private void specialCase(String newValue) {
        if (newValue.matches("=Infinity|=-Infinity")) {
            result.setText("=Can't divide by zero");
        }
    }

    public void writeNumber(String number) {
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
        } catch (ClassCastException | ArrayIndexOutOfBoundsException exp) {
            writeNumber("0.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeOperator(String operator) {
        if (conditionProperty.getValue()) {
            expression.add(new OperatorLabel());
            switchCondition();
        }
        try {
            expression.getLastLabel().write(operator);
        } catch (ArrayIndexOutOfBoundsException ignored) {
            System.out.println("First character can't not be a operator");
        }
    }

    public void equal() {
        if (!expression.isEmpty()) {
            try {
                result.setText("=" + formatNumberForResult(evaluate(expression.getExpression())));
            } catch (ScriptException e) {
                e.printStackTrace();
            }
        }
    }

    private String evaluate(String expr) throws ScriptException {
        String result;
        try {
            result = Integer.toString((int) engine.eval(expr));
        } catch (ClassCastException e) {
            result = Double.toString((double) engine.eval(expr));
        }
        if (result.length() > 12) {
            result = result.substring(0, 15);
        }
        return result;
    }

    private String formatNumberForResult(String result) {
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
        result = numberFormat.format(Double.parseDouble(result));
        return result;
    }

    public void delete() {
        expression.delete();
        equal();

        if (expression.isEmpty()) {
            result.setText("0");
        }
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
