package sample.model;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Calculator {
    private Expression expression;
    private Result result;
    private Screen screen;

    private boolean calculated;

    private ScriptEngine engine;

    public void setup(ScrollPane scrollPane, AnchorPane anchorPane) {
        ScriptEngineManager mgr = new ScriptEngineManager();
        engine = mgr.getEngineByName("JavaScript");

        expression = new Expression();
        expression.setup(scrollPane);


        result = new Result();
        anchorPane.getChildren().add(result);

        screen = new Screen();
        screen.setup(expression, result);
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

    private void calculate() {
        if (!expression.isEmpty()) {
            try {
                result.setResult(evaluate(expression.getExpression()));
            } catch (ScriptException e) {
                e.printStackTrace();
            }
        }
    }

    private Number evaluate(String expr) throws ScriptException {
        return (Number) engine.eval(expr);
    }

    public void equal() {
        if (!expression.isEmpty()) {
            if (!result.isSpecialCase()) {
                screen.zoomZoom();
                calculated = true;
            }
        }
    }

    private String getPreviousExpressionAndResult() {
        return expression.getExpression() + result.getText();
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
