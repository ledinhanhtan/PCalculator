package sample.model;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;

import java.util.ArrayList;

class Expression extends FlowPane {
    private ArrayList<SmartLabel> labels;
    private SimpleBooleanProperty conditionProperty;
    private final String[] operators = {"+", "-", "*", "/"};

    Expression() {
        format();
        labels = new ArrayList<>();
    }

    private void format() {
        this.setAlignment(Pos.BOTTOM_RIGHT);
        this.setMaxWidth(240);
        this.setPrefWrapLength(240);
        AnchorPane.setBottomAnchor(this, 0.0);
        AnchorPane.setRightAnchor(this, 0.0);
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
        StringBuilder stringBuilder = new StringBuilder();
        for (SmartLabel label : labels) {
            stringBuilder.append(label.getText());
        }
        return validExpression(stringBuilder.toString());
    }

    private String validExpression(String expr) {
        if (isLastCharacterAOperator(expr)) {
            expr = expr.substring(0, expr.length() - 1);
        }
        if (expr.contains(",")) {
            expr = expr.replaceAll(",", "");
        }
        return expr;
    }

    private boolean isLastCharacterAOperator(String expr) {
        boolean result = false;
        for (String operator : operators) {
            if (getLastCharacter(expr).equals(operator)) {
                result = true;
                break;
            }
        }
        return result;
    }

    private String getLastCharacter(String expr) {
        return String.valueOf(expr.charAt(expr.length() - 1));
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
        for (Label lbl : labels) {
            this.getChildren().remove(lbl);
        }
        labels.clear();
    }

    void allClear() {
        this.getChildren().clear();
        labels.clear();
    }

    void zoom(int size) {
        for (SmartLabel lbl : labels) {
            lbl.setFont(new Font("System", size));
        }
    }

    void addLabel(String evaluatedExpression) {
        this.getChildren().add(new EvaluatedExpression(evaluatedExpression));
    }
}
