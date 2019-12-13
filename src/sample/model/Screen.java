package sample.model;

import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

public class Screen extends ScrollPane {
    private Expression expression;
    private Result result;

    public Screen() {
        format();
    }

    public void setup(Expression expression, Result result) {
        this.expression = expression;
        this.result = result;

        this.setContent(expression);
        this.heightProperty().addListener(observable-> {
            this.setVvalue(1);
            this.setHvalue(1);
        });
    }

    private void format() {
        this.setPadding(new Insets(0, 10, 0, 10));
        AnchorPane.setTopAnchor(this, 0.0);
        AnchorPane.setRightAnchor(this, 0.0);
        AnchorPane.setBottomAnchor(this, 0.0);
        AnchorPane.setLeftAnchor(this, 0.0);

        this.setVbarPolicy(ScrollBarPolicy.NEVER);
        this.setHbarPolicy(ScrollBarPolicy.NEVER);
        this.setPannable(true);

        this.setStyle("-fx-background-color: transparent");
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
}
