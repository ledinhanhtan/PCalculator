package sample.model;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.text.NumberFormat;
import java.util.Locale;

public class Calculator {
    private ScrollPane scrollPane;
    private Expression expression;
    private Label result;
    private Screen screen;

    private SimpleBooleanProperty conditionProperty;
    private boolean calculated;

    private ScriptEngine engine;

    public void setup(ScrollPane scrollPane, Label result) {
        conditionProperty = new SimpleBooleanProperty(false);
        ScriptEngineManager mgr = new ScriptEngineManager();
        engine = mgr.getEngineByName("JavaScript");

        expression = new Expression();
        expression.setConditionProperty(conditionProperty);
        this.scrollPane = scrollPane;
        scrollPane.setContent(expression);
        expression.heightProperty().addListener(observable-> scrollPane.setVvalue(1));

        this.result = result;
        result.textProperty().addListener((observable, oldValue, newValue) -> specialCase(newValue));

        screen = new Screen();
        screen.setup(expression, result);
    }

    public void writeNumber(String number) {
        //After hit equal button, everything reset if user continue enter a number
        //a fresh calculator
        if (calculated) {
            expression.addLabel(getPreviousExpressionAndResult());
            clear();
        }

        if (!(expression.isEmpty() && number.equals("0"))) {
            if (!conditionProperty.getValue()) {
                expression.add(new NumberLabel());
                switchCondition();
            }
            expression.getLastLabel().write(number);
            calculate();
        }
    }

    public void writeDot() {
        if (calculated) {
            expression.addLabel(getPreviousExpressionAndResult());
            clear();
        }

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
        //Ans, write a new expression begin with previous result (ans)
        if (calculated) {
            expression.addLabel(getPreviousExpressionAndResult());
            String ans = result.getText().replaceAll("=", "");
            clear();
            writeNumber(ans);
        }

        if (conditionProperty.getValue()) {
            expression.add(new OperatorLabel());
            switchCondition();
        }
        try {
            expression.getLastLabel().write(operator);
        } catch (ArrayIndexOutOfBoundsException ignored) {
            System.out.println("First character can't not be an operator");
        }
    }

    private void calculate() {
        if (!expression.isEmpty()) {
            try {
                result.setText("=" + formatNumberForResult(evaluate(expression.getExpression())));
            } catch (ScriptException e) {
                e.printStackTrace();
            }
        }
    }

    public void percent() {
        if (calculated) {
            String ans = result.getText().replaceAll("=", "");
            clear();
            writeNumber(ans);
        }
        try {
            NumberLabel lbl = (NumberLabel) expression.getLastLabel();
            double number = Double.parseDouble(lbl.getText());
            lbl.setText(Double.toString(number/100));
        } catch (ClassCastException clExp) {
            System.out.println("Last label is a operator");
        } catch (ArrayIndexOutOfBoundsException arExp) {
            System.out.println("Empty");
        }
        calculate();
    }

    public void equal() {
        screen.zoomZoom();
        calculated = true;
    }

    private String formatNumberForResult(String result) {
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
        return numberFormat.format(Double.parseDouble(result));
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

    public void delete() {
        expression.delete();
        calculate();

        if (expression.isEmpty()) {
            result.setText("0");
        }

        screen.zoomZoomReverse();
    }

    private void switchCondition() {
        conditionProperty.setValue(!conditionProperty.getValue());
    }

    private String getPreviousExpressionAndResult() {
        return expression.getExpression() + result.getText();
    }

    private void specialCase(String newValue) {
        if (newValue.matches("=Infinity|=-Infinity")) {
            result.setText("=Can't divide by zero");
        }
    }

    private void clear() {
        expression.clear();
        result.setText("0");
        conditionProperty.setValue(false);
        calculated = false;

        screen.zoomZoomReverse();
    }

    public void allClear() {
        expression.allClear();
        result.setText("0");
        conditionProperty.setValue(false);
        calculated = false;

        screen.zoomZoomReverse();
    }
}
