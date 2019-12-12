package sample.model.label;

import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class SmartLabel extends Label {
    SmartLabel() {
        setup();
        format();
    }

    void setup() {    }

    private void format() {
        this.setFont(new Font("System", 35));
    }

    public void write(String str) {
        if (this.getText().equals("")) {
            this.setText(str);
        } else {
            concatOrReplace(str);
        }
    }

    void concatOrReplace(String str) {}

    public void deleteLastCharacter() {
        this.setText(this.getText().substring(0, this.getText().length() - 1));
    }
}
