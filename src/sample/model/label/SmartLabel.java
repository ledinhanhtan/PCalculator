package sample.model.label;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.text.Font;

public class SmartLabel extends Label {
    private final String BLACK = "#000000";
    private final String GREY = "#666666";
    private final String WHITE_GREY = "#979696";
    private final String SELECTED =
            "-fx-background-color: #e6f3ff;" +
            "-fx-background-radius: 5px;" +
            "-fx-border-color: #0084ff;" +
            "-fx-border-radius: 5px;" +
            "-fx-border-width: 0.6px;";

    //state == 1: BLACK   //state == 2: GREY   //state == 3: WHITE_GREY
    private int state = 1;

    public SmartLabel(String init) {
        super(init);
        setup();
        format();
    }

    public SmartLabel() {
        setup();
        format();
    }

    void setup() {
        this.setOnMouseEntered(event -> this.setStyle(SELECTED));

        this.setOnMouseExited(event -> {
            if (state == 1) this.setStyle("-fx-text-fill: " + BLACK);
            if (state == 2) this.setStyle("-fx-text-fill: " + GREY);
            if (state == 3) this.setStyle("-fx-text-fill: " + WHITE_GREY);
        });

        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent content = new ClipboardContent();
        ContextMenu contextMenu = new ContextMenu();
        MenuItem copy = new MenuItem("Copy");

        copy.setOnAction(event -> {
            content.putString(this.getText().
                    replaceAll(",", "").replaceAll("=", ""));
            clipboard.setContent(content);
        });
        contextMenu.getItems().add(copy);

        this.setOnContextMenuRequested(event ->
                contextMenu.show(this, event.getScreenX(), event.getScreenY()));
    }

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

    public void setState(int state) {
        this.state = state;
    }

    public void deleteLastCharacter() {
        this.setText(this.getText().
                substring(0, this.getText().length() - 1));
    }
}
