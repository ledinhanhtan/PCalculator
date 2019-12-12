package sample.model;

import javafx.scene.text.Font;

import java.util.ArrayList;

public class Screen {
    private Expression expression;
    private Result result;

    private ArrayList<SmartLabel> labels;

    public void setup(Expression expression, Result result) {
        this.expression = expression;
        this.result = result;

        this.labels = expression.getLabels();

        expression.getExpressionProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > 10) {
                zoom(20);
                zoom(30);
            }
        });
    }

    public void zoomZoom() {
        expression.zoom(25);
        expression.setStyleForLabels("#666666");

        result.setFont(new Font("System", 35));
        result.setStyle("-fx-text-fill: #000000");
    }

    public void zoomZoomReverse() {
        expression.zoom(35);

        result.setFont(new Font("System", 22));
        result.setStyle("-fx-text-fill: #b3b3b3");
    }

    private void zoom(int size) {
        for (SmartLabel lbl : labels) {
            lbl.setFont(new Font("System", size));
        }
    }
}
