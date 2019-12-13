package sample.model;

import javafx.geometry.Pos;
import javafx.scene.layout.FlowPane;

public class SmartFlowPane extends FlowPane {
    void format() {
        this.setAlignment(Pos.BOTTOM_RIGHT);
        this.setMaxWidth(230);
        this.setPrefWrapLength(230);
    }
}
