package sample.model.flowPane;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.text.Font;
import sample.model.label.NumberLabel;
import sample.model.label.OperatorLabel;
import sample.model.label.SmartLabel;

import java.util.ArrayList;

public class Expression extends SmartFlowPane {
    private final String[] operators = {"+", "-", "x", "÷"};

    private boolean condition;
    private ArrayList<SmartLabel> labels;

    //Use for auto resize
    private SimpleStringProperty expressionProperty;

    public Expression() {
        setup();
        format();
    }

    private void setup() {
        labels = new ArrayList<>();

        expressionProperty = new SimpleStringProperty("");
        expressionProperty.addListener((observable, oldValue, newValue) ->
                autoResize(newValue.replaceAll("[,.]", "").length()));
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

    public void writeNumber(String number) {
        if (!condition) {
            add(new NumberLabel());
            switchCondition();
        }
        getLastLabel().write(number);
        updateProperty();
    }

    public void writeDot() {
        try {
            NumberLabel numberLabel = (NumberLabel) getLastLabel();
            numberLabel.writeDot();
        } catch (ClassCastException | ArrayIndexOutOfBoundsException exp) {
            writeNumber("0.");
        }
        updateProperty();
    }

    public void writeOperator(String operator) {
        if (condition) {
            add(new OperatorLabel());
            switchCondition();
        }
        try {
            getLastLabel().write(operator);
            updateProperty();
        } catch (ArrayIndexOutOfBoundsException ignored) {
            System.out.println("First character can't be an operator");
        }
    }

    public void percent() {
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

    public void delete() {
        if (labels.size() > 0) {
            SmartLabel lastLabel = labels.get(labels.size() - 1);
            lastLabel.deleteLastCharacter();
            deleteProperty();
            if (lastLabel.getText().equals("")) {
                remove(lastLabel);
            }
        }
    }

    private void remove(SmartLabel lastLabel) {
        labels.remove(lastLabel);
        this.getChildren().remove(lastLabel);

        switchCondition();
    }

    private void switchCondition() {
        condition = !condition;
    }

    //-------------------

    private void add(SmartLabel label) {
        this.getChildren().add(label);
        labels.add(label);
    }

    public void reset() {
        this.getChildren().clear();
        labels.clear();
        condition = false;
        expressionProperty.setValue("");
    }

    private SmartLabel getLastLabel() {
        return labels.get(labels.size() - 1);
    }

    public String getExpression() {
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

    public boolean isEmpty() {
        return labels.size() == 0;
    }

    public void zoom(int size) {
        for (SmartLabel lbl : labels) {
            lbl.setFont(new Font("System", size));
        }
    }

    public void setStyleForLabels(String hexColor) {
        int state = 1;
        if (hexColor.equals("#666666")) state = 2;
        for (SmartLabel lbl : labels) {
            lbl.setStyle("-fx-text-fill: " + hexColor);
            lbl.setState(state);
        }
    }

    public ArrayList<SmartLabel> getLabels() {
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
