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

        expression.getExpressionProperty().addListener((observable, oldValue, newValue) ->
                autoResize(newValue.replaceAll("[,.]", "").length()));
    }

    public void zoomZoom() {
        expression.zoom(25);
        expression.setStyleForLabels("#666666");

        result.setFont(new Font("System", 35));
        result.setStyle("-fx-text-fill: #000000");
    }

    public void zoomZoomReverse() {
        expression.zoom(35);
        expression.setStyleForLabels("#000000");

        result.setFont(new Font("System", 22));
        result.setStyle("-fx-text-fill: #b3b3b3");
    }

    //Todo switch
    private void autoResize(int length) {
        if (length == 8) {
            setSize(35);
        }
        if (length == 9) {
            setSize(33);
        }
        if (length == 10) {
            setSize(31);
        }
        if (length == 11) {
            setSize(29);
        }
        if (length == 12) {
            setSize(27);
        }
        if (length >= 13) {
            setSize(25);
        }
    }

    private void setSize(int size) {
        for (SmartLabel lbl : labels) {
            lbl.setFont(new Font("System", size));
        }
    }
}
