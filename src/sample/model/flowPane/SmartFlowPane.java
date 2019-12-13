package sample.model.flowPane;

import javafx.geometry.Pos;
import javafx.scene.layout.FlowPane;

public class SmartFlowPane extends FlowPane {
    void format() {
        this.setAlignment(Pos.BOTTOM_RIGHT);
        this.setMaxWidth(215);
        this.setPrefWrapLength(215);
    }
}
