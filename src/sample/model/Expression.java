package sample.model;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.FlowPane;

import java.util.ArrayList;

class Expression extends FlowPane {
    private ArrayList<SmartLabel> labels;

    Expression() {
        format();
        labels = new ArrayList<>();
    }

    private void format() {
        this.setAlignment(Pos.BOTTOM_RIGHT);
        this.setPrefSize(240,150);
        this.setMaxSize(240,150);
        this.setPadding(new Insets(10));
    }

    void add(SmartLabel label) {
        this.getChildren().add(label);
        labels.add(label);
    }

    String getExpression() {
        StringBuilder expr = new StringBuilder();
        for (SmartLabel label : labels) {
            expr.append(label.getText());
        }
        return expr.toString();
    }

    void delete() {
        if (labels.size() > 0) {
            SmartLabel lastLabel = labels.get(labels.size() - 1);
            lastLabel.deleteLastCharacter();
            if (lastLabel.getText().equals("")) {
                remove(lastLabel);
            }
        }
    }

    private void remove(SmartLabel lastLabel) {
        labels.remove(lastLabel);
        this.getChildren().remove(lastLabel);
        lastLabel = null;
    }
}
