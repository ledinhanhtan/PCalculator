package sample.model;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.FlowPane;

import java.util.ArrayList;

class Expression extends FlowPane {
    private ArrayList<SmartLabel> labels;
    private SimpleBooleanProperty conditionProperty;

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

    void setConditionProperty(SimpleBooleanProperty conditionProperty) {
        this.conditionProperty = conditionProperty;
    }

    void add(SmartLabel label) {
        this.getChildren().add(label);
        labels.add(label);
    }

    SmartLabel getLastLabel() {
        return labels.get(labels.size() - 1);
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

        switchCondition();
    }

    private void switchCondition() {
        conditionProperty.setValue(!conditionProperty.getValue());
    }

    boolean isEmpty() {
        return labels.size() == 0;
    }

    void clear() {
        this.getChildren().clear();
        labels.clear();
    }
}
