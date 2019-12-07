package sample.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

class SmartLabel extends Label {
    SimpleStringProperty strProperty;

    SmartLabel() {
        format();
        setup();
    }

    private void format() {
        this.setFont(new Font("System", 35));
    }

    private void setup() {
        strProperty = new SimpleStringProperty();
        this.textProperty().bind(strProperty);
    }

    void write(String str) {
        if (strProperty.getValue() == null) {
            strProperty.setValue(str);
        } else {
            concatOrReplace(str);
        }
    }

    void concatOrReplace(String str) {    }

    void deleteLastCharacter() {
        strProperty.setValue(strProperty.getValue()
                .substring(0, strProperty.getValue().length() - 1));
    }
}
