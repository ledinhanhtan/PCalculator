package sample.model;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

class EvaluatedExpression extends Label {
    EvaluatedExpression(String init) {
        super(init);
        format();
    }

    private void format() {
        this.setMinWidth(260);
        this.setAlignment(Pos.CENTER_RIGHT);
        this.setFont(new Font("System", 20));
        this.setStyle("-fx-text-fill: #979696");
    }
}
