package sample.model;

import javafx.scene.control.Label;
import javafx.scene.text.Font;

class SmartLabel extends Label {
    SmartLabel() {
        setup();
        format();
    }

    void setup() {    }

    private void format() {
        this.setFont(new Font("System", 35));
    }

    void write(String str) {
        if (this.getText() == null) {
           this.setText(str);
        } else {
            concatOrReplace(str);
        }
    }

    void concatOrReplace(String str) {}

    void deleteLastCharacter() {
        this.setText(this.getText().substring(0, this.getText().length() - 1));
    }
}
