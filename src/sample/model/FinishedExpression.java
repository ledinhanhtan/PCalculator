package sample.model;

import javafx.scene.control.Label;
import javafx.scene.text.Font;
import sample.model.label.SmartLabel;

import java.util.ArrayList;

public class FinishedExpression extends SmartFlowPane {
    FinishedExpression(ArrayList<SmartLabel> labels, Label result) {
        System.out.println("got");
        format();

        labels.add(new SmartLabel(result.getText()));

        for (Label lbl : labels) {
            lbl.setFont(new Font("System", 20));
            lbl.setStyle("-fx-text-fill: #979696");
            this.getChildren().add(lbl);
        }
    }
}
