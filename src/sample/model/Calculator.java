package sample.model;

import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.text.NumberFormat;
import java.util.Locale;

public class Calculator {
    private Expression expression;
    private Label result;
    private Screen screen;

    private boolean calculated;

    private ScriptEngine engine;

    public void setup(ScrollPane scrollPane, Label result) {
        ScriptEngineManager mgr = new ScriptEngineManager();
        engine = mgr.getEngineByName("JavaScript");

        expression = new Expression();
        expression.setup(scrollPane);

        screen = new Screen();
        screen.setup(expression, result);

        this.result = result;
        result.textProperty().addListener((observable, oldValue, newValue) -> specialCase(newValue));
    }

    public void writeNumber(String number) {
        if (calculated) {
            expression.addFinishedLabel(getPreviousExpressionAndResult());
            clear();
        }

        expression.writeNumber(number);
        calculate();
    }

    public void writeDot() {
        if (calculated) {
            expression.addFinishedLabel(getPreviousExpressionAndResult());
            String ans = result.getText().replaceAll("=", "");
            clear();
            if (!ans.contains(".")) {
               writeNumber(ans + ".");
            }
        }
        expression.writeDot();
    }

    public void writeOperator(String operator) {
        //Ans, write a new expression begin with previous result (ans)
        if (calculated) {
            expression.addFinishedLabel(getPreviousExpressionAndResult());
            String ans = result.getText().replaceAll("=", "");
            clear();
            writeNumber(ans);
        }

        expression.writeOperator(operator);
    }

    private void calculate() {
        if (!expression.isEmpty()) {
            try {
                result.setText("=" + formatNumberForResult(evaluate(
                        expression.getExpression())));
            } catch (ScriptException e) {
                e.printStackTrace();
            }
        }
    }

    private String formatNumberForResult(String result) {
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
        return numberFormat.format(Double.parseDouble(result));
    }

    public void percent() {
        if (calculated) {
            String ans = result.getText().replaceAll("=", "");
            expression.addFinishedLabel(getPreviousExpressionAndResult());
            clear();
            writeNumber(ans);
        }
        expression.percent();
        calculate();
    }

    public void equal() {
        if (!expression.isEmpty()) {
            screen.zoomZoom();
            calculated = true;
        }
    }

    private String evaluate(String expr) throws ScriptException {
        String result;
        try {
            result = Integer.toString((int) engine.eval(expr));
        } catch (ClassCastException e) {
            result = Double.toString((double) engine.eval(expr));
        }
        //Todo: substring overkill
//        if (result.length() > 12) {
//            result = result.substring(0, 15);
//        }
        return result;
    }

    public void delete() {
        if (calculated) {
            calculated = false;
        }
        if (expression.isEmpty()) {
            result.setText("0");
        }

        expression.delete();
        calculate();
        screen.zoomZoomReverse();
    }

    private String getPreviousExpressionAndResult() {
        return expression.getExpression() + result.getText();
    }

    private void specialCase(String newValue) {
        if (newValue.matches("=∞|=-∞")) {
            result.setText("=Can't divide by zero");
        }
    }

    private void clear() {
        expression.clear();
        cl();
    }

    public void allClear() {
        expression.allClear();
        cl();
    }

    private void cl() {
        result.setText("0");
        calculated = false;
        screen.zoomZoomReverse();
    }
}
