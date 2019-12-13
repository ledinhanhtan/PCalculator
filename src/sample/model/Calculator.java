package sample.model;

import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import sample.model.flowPane.Expression;
import sample.model.label.Result;

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

    public Calculator() {
        expression = new Expression();
        result = new Result();
        screen = new Screen();

        ScriptEngineManager mgr = new ScriptEngineManager();
        engine = mgr.getEngineByName("JavaScript");
    }

    public void setup(ScrollPane scrollPane, AnchorPane anchorPaneForResult, ImageView green, ImageView red) {
        led = new Led(green, red);

        result.setLed(led);
        anchorPaneForResult.getChildren().add(result);

        screen.setup(expression, result);
        screen.heightProperty().addListener(observable -> {
            scrollPane.setVvalue(1);
            scrollPane.setHvalue(1);
        });
        scrollPane.setContent(screen);
    }

    public void writeNumber(String number) {
        if (calculated) {
            screen.addFinishedExpression(expression, result);
            reset();
        }

        expression.writeNumber(number);
        calculate();

        led.peak();
    }

    public void writeDot() {
        if (calculated) {
            screen.addFinishedExpression(expression, result);

            String ans = result.getText().replaceAll("=", "");

            reset();
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
                screen.addFinishedExpression(expression, result);
                String ans = result.getText().replaceAll("=", "");
                reset();
                writeNumber(ans);
            }
            expression.writeOperator(operator);
        }
        led.peak();
    }

    public void percent() {
        if (calculated) {
            String ans = result.getText().replaceAll("=", "");
            screen.addFinishedExpression(expression, result);

            reset();
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
                e.printStackTrace();
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

    public void reset() {
        expression.reset();
        cl();
    }

    public void allClear() {
        screen.clear();
        cl();
    }

    private void cl() {
        calculated = false;
        result.setText("0");
        screen.zoomZoomReverse();

        led.off();
    }
}
