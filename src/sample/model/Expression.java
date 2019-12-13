package sample.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;
import sample.model.label.FinishedExpression;
import sample.model.label.NumberLabel;
import sample.model.label.OperatorLabel;
import sample.model.label.SmartLabel;

import java.util.ArrayList;

class Expression extends FlowPane {
    private final String[] operators = {"+", "-", "x", "÷"};
    private ArrayList<SmartLabel> labels;
    private ArrayList<FinishedExpression> blankLabelList;

    private boolean condition;
    private SimpleStringProperty expressionProperty;

    Expression() {
        setup();
        format();
    }

    private void format() {
        this.setAlignment(Pos.BOTTOM_RIGHT);
        this.setMaxWidth(230);
        this.setPrefWrapLength(230);
    }

    private void setup() {
        labels = new ArrayList<>();

        expressionProperty = new SimpleStringProperty("");
        expressionProperty.addListener((observable, oldValue, newValue) ->
                autoResize(newValue.replaceAll("[,.]", "").length()));

        addBlankLabel();
    }

    private void autoResize(int length) {
        if (length == 8) {
            setSize(35);
        }
        if (length == 9) {
            setSize(33);
        }
        if (length == 10) {
            setSize(31);
        }
        if (length == 11) {
            setSize(29);
        }
        if (length == 12) {
            setSize(27);
        }
        if (length >= 13) {
            setSize(25);
        }
    }

    private void setSize(int size) {
        for (SmartLabel lbl : labels) {
            lbl.setFont(new Font("System", size));
        }
    }

    private void addBlankLabel() {
        blankLabelList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            FinishedExpression blankLabel = new FinishedExpression("");
            this.getChildren().add(blankLabel);
            blankLabelList.add(blankLabel);
        }
    }

    void writeNumber(String number) {
        if (!(labels.size() == 0) || !number.equals("0")) {
            if (!condition) {
                add(new NumberLabel());
                switchCondition();
            }
            getLastLabel().write(number);
            updateProperty();
        }
    }

    void writeDot() {
        try {
            NumberLabel numberLabel = (NumberLabel) getLastLabel();
            numberLabel.writeDot();
        } catch (ClassCastException | ArrayIndexOutOfBoundsException exp) {
            writeNumber("0.");
        }
        updateProperty();
    }

    void writeOperator(String operator) {
        if (condition) {
            add(new OperatorLabel());
            switchCondition();
        }
        try {
            getLastLabel().write(operator);
            updateProperty();
        } catch (ArrayIndexOutOfBoundsException ignored) {
            System.out.println("First character can't not be an operator");
        }
    }

    void percent() {
        try {
            NumberLabel lbl = (NumberLabel) getLastLabel();
            double number = Double.parseDouble(lbl.getText().replaceAll(",", ""));
            lbl.setText(Double.toString(number/100));
            updateProperty();
        } catch (ClassCastException clExp) {
            System.out.println("Last label is a operator");
        } catch (ArrayIndexOutOfBoundsException arExp) {
            System.out.println("Empty");
        }
    }

    void delete() {
        if (labels.size() > 0) {
            SmartLabel lastLabel = labels.get(labels.size() - 1);
            lastLabel.deleteLastCharacter();
            deleteProperty();
            if (lastLabel.getText().equals("")) {
                remove(lastLabel);
            }
        }
    }

    private void add(SmartLabel label) {
        this.getChildren().add(label);
        labels.add(label);
    }

    private SmartLabel getLastLabel() {
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
        if (expr.contains("x")) {
            expr = expr.replaceAll("x", "*");
        }
        if (expr.contains("÷")) {
            expr = expr.replaceAll("÷", "/");
        }
        //Fix later
        if (getLastCharacter(expr).equals("e")) {
            expr = expr.substring(0, expr.length() - 1);
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

    private void remove(SmartLabel lastLabel) {
        labels.remove(lastLabel);
        this.getChildren().remove(lastLabel);

        switchCondition();
    }

    private void switchCondition() {
        condition = !condition;
    }

    boolean isEmpty() {
        return labels.size() == 0;
    }

    void clear() {
        for (Label lbl : labels) {
            this.getChildren().remove(lbl);
        }
        labels.clear();
        condition = false;
    }

    void allClear() {
        this.getChildren().clear();
        addBlankLabel();
        labels.clear();
        condition = false;
        expressionProperty.setValue("");
    }

    void zoom(int size) {
        for (SmartLabel lbl : labels) {
            lbl.setFont(new Font("System", size));
        }
    }

    void setStyleForLabels(String hexColor) {
        for (SmartLabel lbl : labels) {
            lbl.setStyle("-fx-text-fill: " + hexColor);
        }
    }

    void addFinishedLabel(String evaluatedExpression) {
        this.getChildren().add(new FinishedExpression(evaluatedExpression));

        if (blankLabelList.size() > 0) {
            this.getChildren().remove(blankLabelList.get(0));
            blankLabelList.remove(0);
        }
    }

    SimpleStringProperty getExpressionProperty() {
        return expressionProperty;
    }

    ArrayList<SmartLabel> getLabels() {
        return labels;
    }

    private void updateProperty() {
        StringBuilder stringBuilder = new StringBuilder();
        for (SmartLabel label : labels) {
            stringBuilder.append(label.getText());
        }
        expressionProperty.setValue(stringBuilder.toString());
    }

    private void deleteProperty() {
        expressionProperty.setValue(expressionProperty.getValue().
                substring(0, expressionProperty.getValue().length() - 1));
    }
}
