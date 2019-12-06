package sample.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

class OperatorLabel extends Label {
    private SimpleStringProperty operatorProperty;

    OperatorLabel() {
        format();
        setup();
    }

    private void format() {
        this.setFont(new Font("System", 35));
    }

    private void setup() {
        operatorProperty = new SimpleStringProperty();
        this.textProperty().bind(operatorProperty);
    }

    void write(String operator) {
        if (operatorProperty.getValue() == null) {
            operatorProperty.setValue(operator);
        } else {
            replace(operator);
        }
    }

    private void replace(String operator) {
        operatorProperty.setValue(operator);
    }
}
