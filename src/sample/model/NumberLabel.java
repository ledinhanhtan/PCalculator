package sample.model;

import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class NumberLabel extends Label {
    public NumberLabel(String str) {
        super(str+2);
        format();
    }

    private void format() {
        this.setFont(new Font("System", 35));
    }
}
