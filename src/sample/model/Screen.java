package sample.model;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

class Screen extends VBox {
    private Expression expression;
    private Result result;

    Screen() {
        format();
    }

    void setup(Expression expression, Result result) {
        this.expression = expression;
        this.result = result;

        this.getChildren().add(expression);
    }

    private void format() {
        this.setPrefSize(240, 150);
        this.setMaxSize(240, 150);

//        this.setSpacing();

        this.setAlignment(Pos.BOTTOM_RIGHT);

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
