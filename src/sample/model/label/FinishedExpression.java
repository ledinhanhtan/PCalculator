package sample.model.label;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class FinishedExpression extends Label {
    public FinishedExpression(String init) {
        super(init);
        format();
    }

    private void format() {
        this.setMinWidth(230);
        this.setAlignment(Pos.CENTER_RIGHT);
        this.setFont(new Font("System", 20));
        this.setStyle("-fx-text-fill: #979696");
    }
}
