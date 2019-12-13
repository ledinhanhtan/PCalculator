package sample.model.label;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.text.Font;

public class SmartLabel extends Label {
    public SmartLabel(String init) {
        super(init);
        format();
    }

    SmartLabel() {
        setup();
        format();
    }

    void setup() {    }

    private void format() {
        this.setFont(new Font("System", 35));

        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent content = new ClipboardContent();

        ContextMenu contextMenu = new ContextMenu();
        MenuItem copy = new MenuItem("Copy");
        copy.setOnAction(event -> {
            content.putString(this.getText().replaceAll(",", ""));
            clipboard.setContent(content);
        });
        contextMenu.getItems().add(copy);

        this.setOnMouseEntered(event -> this.setStyle(
                "-fx-background-color: #e6f3ff;" +
                "-fx-background-radius: 5px;" +
                "-fx-border-color: #0084ff;" +
                "-fx-border-radius: 5px;" +
                "-fx-border-width: 0.6px;"));

        this.setOnMouseExited(event -> {
            this.setStyle("-fx-background-color: transparent;" +
                    "-fx-border-color: transparent");
        });

        this.setOnContextMenuRequested(event ->
                contextMenu.show(this, event.getScreenX(), event.getScreenY()));
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
        this.setText(this.getText().
                substring(0, this.getText().length() - 1));
    }
}
