package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import sample.model.Calculator;

public class Controller {
    @FXML
    ScrollPane scrollPane;
    @FXML
    AnchorPane anchorPaneForResult;
    @FXML
    ImageView green;
    @FXML
    ImageView red;
    @FXML
    Button allClearButton;

    private Calculator calculator;

    @FXML
    public void initialize() {
        calculator = new Calculator();
        calculator.setup(scrollPane, anchorPaneForResult, green, red);
    }

    @FXML
    public void number(ActionEvent e) {
        Button button = (Button) e.getSource();
        calculator.writeNumber(button.getText());

        allClearButton.setText("C");
    }

    @FXML
    public void dot() {
        calculator.writeDot();
        allClearButton.setText("C");
    }

    @FXML
    public void operator(ActionEvent e) {
        Button button = (Button) e.getSource();
        calculator.writeOperator(button.getText());
    }

    @FXML
    public void percent() { calculator.percent(); }

    @FXML
    public void equal() { calculator.equal(); }

    @FXML
    public void delete() { calculator.delete(); }

    @FXML
    public void allClear() {
        if (allClearButton.getText().equals("C")) {
            calculator.reset();
        } else {
            calculator.allClear();
        }

        allClearButton.setText("AC");
    }

    public void keyEventHandler(KeyEvent keyEvent) {
        String key = keyEvent.getText();

        if (key.matches("[0-9]")) {
            calculator.writeNumber(key);
            allClearButton.setText("C");
        } else if (key.equals(".")) {
            calculator.writeDot();
            allClearButton.setText("C");
        } else if (key.matches("[+\\-]")) {
            calculator.writeOperator(key);
        } else if (key.equals("*")) {
            calculator.writeOperator("x");
        } else if (key.equals("/")) {
            calculator.writeOperator("÷");
        } else if (keyEvent.getCode().equals(KeyCode.BACK_SPACE)) {
            delete();
        } else if (keyEvent.getCode().equals(KeyCode.ESCAPE)) {
            allClear();
        } else if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            equal();
        }
    }
}
