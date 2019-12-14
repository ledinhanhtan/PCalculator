package sample.model.flowPane;

import javafx.scene.control.Label;
import javafx.scene.text.Font;
import sample.model.label.SmartLabel;

import java.util.ArrayList;

public class FinishedExpression extends SmartFlowPane {
    public FinishedExpression(ArrayList<SmartLabel> labels, Label result) {
        format();

        labels.add(new SmartLabel(result.getText()));

        for (SmartLabel lbl : labels) {
            lbl.setFont(new Font("System", 20));
            lbl.setStyle("-fx-text-fill: #979696");
            lbl.setState(3);
            this.getChildren().add(lbl);
        }
    }
}
