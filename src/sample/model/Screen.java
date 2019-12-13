package sample.model;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

//import java.util.ArrayList;

class Screen extends VBox {
//    private ArrayList<Expression> expressionList;
    private Expression expression;
    private Result result;

    Screen() {
        format();
    }

    void setup(Result result) {
//        expressionList = new ArrayList<>();
        this.result = result;
    }

    private void format() {
        this.setPrefWidth(240);
        this.setAlignment(Pos.BOTTOM_RIGHT);
        this.setSpacing(10);
    }

    void add(Expression expression) {
        this.expression = expression;
//        expressionList.add(expression);
        this.getChildren().add(expression);
    }

    void addFinishedExpression(Expression expression, Label result) {
        this.getChildren().add(0, new FinishedExpression(expression.getLabels(), result));
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
