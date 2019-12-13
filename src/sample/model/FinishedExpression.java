package sample.model;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;
import sample.model.label.SmartLabel;

import java.util.ArrayList;

public class FinishedExpression extends FlowPane {
    FinishedExpression(ArrayList<SmartLabel> labels, Label result) {
        format();

        labels.add(new SmartLabel(result.getText()));

        for (Label lbl : labels) {
            lbl.setFont(new Font("System", 20));
            lbl.setStyle("-fx-text-fill: #979696");
            this.getChildren().add(lbl);
        }
    }

    private void format() {
        this.setAlignment(Pos.BOTTOM_RIGHT);
        this.setMaxWidth(230);
        this.setPrefWrapLength(230);
    }
}
