package sample.model;

import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class Screen {
    private Expression expression;
    private Label result;

    public void setup(Expression expression, Label result) {
        this.expression = expression;
        this.result = result;
    }

    public void zoomZoom() {
        expression.zoom(22);
        result.setFont(new Font("System", 35));
    }

    public void zoomZoomReverse() {
        expression.zoom(35);
        result.setFont(new Font("System", 22));
    }
}
