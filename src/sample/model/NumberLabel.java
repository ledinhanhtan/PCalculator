package sample.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

class NumberLabel extends Label {
    private SimpleStringProperty numberProperty;

    NumberLabel() {
        format();
        setup();
    }

    private void format() {
        this.setFont(new Font("System", 35));
    }

    private void setup() {
        numberProperty = new SimpleStringProperty();
        this.textProperty().bind(numberProperty);
    }

    void write(String number) {
        if (numberProperty.getValue() == null) {
            numberProperty.setValue(number);
        } else {
            concat(number);
        }
    }

    private void concat(String number) {
        numberProperty.setValue(numberProperty.getValue() + number);
    }
}
