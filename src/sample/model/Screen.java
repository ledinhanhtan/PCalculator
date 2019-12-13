package sample.model;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import sample.model.flowPane.Expression;
import sample.model.flowPane.FinishedExpression;
import sample.model.label.Result;

class Screen extends VBox {
    private Expression expression;
    private Result result;
    private int index = 1;
    private Pane invisiblePane;

    Screen() {
        format();
    }

    private void format() {
        this.setPrefWidth(240);
        this.setAlignment(Pos.BOTTOM_RIGHT);
        this.setSpacing(10);
    }

    void setup(Expression expression, Result result) {
        invisiblePane = new Pane();
//        invisiblePane.setStyle("-fx-background-color: yellow");
        invisiblePane.setPrefHeight(68);
        invisiblePane.setMaxWidth(220);
        this.getChildren().add(invisiblePane);

        this.expression = expression;
        this.getChildren().add(expression);

        this.result = result;
    }

    void addFinishedExpression(Expression expression, Label result) {
        this.getChildren().add(index, new FinishedExpression(expression.getLabels(), result));
        //Always add to bottom of set of FinishedExpressions
        index++;

        if (index == 2) invisiblePane.setPrefHeight(28);
        if (index == 3) invisiblePane.setPrefHeight(0.1);
    }

    void clear() {
        expression.reset();
        this.getChildren().clear();
        this.getChildren().add(invisiblePane);
        this.getChildren().add(expression);

        index = 1;
        invisiblePane.setPrefHeight(68);
    }

    void zoomZoom() {
        expression.zoom(25);
        expression.setStyleForLabels("#666666");

        result.setFont(new Font("System", 35));
        result.setStyle("-fx-text-fill: #000000");
    }

    void zoomZoomReverse() {
        expression.zoom(35);
        expression.setStyleForLabels("#000000");

        result.setFont(new Font("System", 22));
        result.setStyle("-fx-text-fill: #b3b3b3");
    }
}
