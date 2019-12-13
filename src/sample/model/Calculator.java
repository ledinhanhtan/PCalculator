package sample.model;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Calculator {
    private Screen screen;
    private Expression expression;
    private Result result;
    private Led led;

    private boolean calculated;
    private ScriptEngine engine;

    public void setup(AnchorPane anchorPaneForScreen, AnchorPane anchorPaneForResult, ImageView green, ImageView red) {
        ScriptEngineManager mgr = new ScriptEngineManager();
        engine = mgr.getEngineByName("JavaScript");
        led = new Led(green, red);


        result = new Result();
        result.setLed(led);
        anchorPaneForResult.getChildren().add(result);

        expression = new Expression();
        screen = new Screen();
        screen.setup(expression, result);
        anchorPaneForScreen.getChildren().add(screen);
    }

    public void writeNumber(String number) {
        if (calculated) {
            expression.addFinishedLabel(getPreviousExpressionAndResult());
            clear();
        }

        expression.writeNumber(number);
        calculate();

        led.peak();
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

        led.peak();
    }

    public void writeOperator(String operator) {
        //Block user enter any operator after a special case
        if (result.isNonError()) {
            //Ans, write a new expression begin with previous result (ans)
            if (calculated) {
                expression.addFinishedLabel(getPreviousExpressionAndResult());
                String ans = result.getText().replaceAll("=", "");
                clear();
                writeNumber(ans);
            }
            expression.writeOperator(operator);
        }
        led.peak();
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

        led.peak();
    }

    public void delete() {
        if (calculated) {
            calculated = false;
            screen.zoomZoomReverse();
        }

        expression.delete();
        if (expression.isEmpty()) { result.setText("0"); }

        calculate();

        led.peak();
    }

    private void calculate() {
        if (!expression.isEmpty()) {
            try {
                result.setResult(evaluate(expression.getExpression()));
            } catch (ScriptException e) {
                //Freeze all method, later
                e.printStackTrace();
//                result.setText("=Error");
            }
        }
    }

    private Number evaluate(String expr) throws ScriptException {
        return (Number) engine.eval(expr);
    }

    public void equal() {
        if (!expression.isEmpty()) {
            if (result.isNonError()) {
                screen.zoomZoom();
                calculated = true;

                led.on();
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

        led.off();
    }
}
