package sample.model;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;

import java.util.ArrayList;

class Expression extends FlowPane {
    private ArrayList<SmartLabel> labels;
    private ArrayList<EvaluatedExpression> blankLabelList;
    private SimpleBooleanProperty conditionProperty;
    private final String[] operators = {"+", "-", "*", "/"};

    Expression(ScrollPane parent) {
        format();
        labels = new ArrayList<>();

        parent.setContent(this);
        this.heightProperty().addListener(observable-> {
            parent.setVvalue(1);
            parent.setHvalue(1);
        });

        addBlankLabel();
    }

    private void format() {
        this.setAlignment(Pos.BOTTOM_RIGHT);
        this.setMaxWidth(230);
    }

    private void addBlankLabel() {
        blankLabelList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            EvaluatedExpression blankLabel = new EvaluatedExpression("");
            this.getChildren().add(blankLabel);
            blankLabelList.add(blankLabel);
        }
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
        addBlankLabel();
        labels.clear();
    }

    void zoom(int size) {
        for (SmartLabel lbl : labels) {
            lbl.setFont(new Font("System", size));
        }
    }

    void addLabel(String evaluatedExpression) {
        this.getChildren().add(new EvaluatedExpression(evaluatedExpression));

        if (blankLabelList.size() > 0) {
            this.getChildren().remove(blankLabelList.get(blankLabelList.size() - 1));
            blankLabelList.remove(blankLabelList.size() - 1);
        }
    }
}
