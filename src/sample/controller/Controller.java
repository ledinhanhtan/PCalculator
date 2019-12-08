package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import sample.model.Calculator;

import javax.script.ScriptException;


public class Controller {
    @FXML
    AnchorPane anchorPane;
    @FXML
    Label result;

    private Calculator calculator;

    @FXML
    public void initialize() {
        calculator = new Calculator();
        calculator.setup(anchorPane, result);
    }

    @FXML
    public void number(ActionEvent e) throws ScriptException {
        Button button = (Button) e.getSource();
        calculator.writeNumber(button.getText());
    }

    @FXML
    public void dot() {
        calculator.writeDot();
    }

    @FXML
    public void operator(ActionEvent e) {
        Button button = (Button) e.getSource();
        calculator.writeOperator(button.getText());
    }

    @FXML
    public void equal() throws ScriptException {
        calculator.equal();
    }

    @FXML
    public void delete() {
        calculator.delete();
    }

    @FXML
    public void allClear() {
        calculator.allClear();
    }

//    public void keyEventHandler(KeyEvent keyEvent) throws ScriptException {
//        String key = keyEvent.getText();
//
//        if (key.matches("[0-9]")) {
//            calculator.writeNumber(key);
//        } else if (key.matches("[+\\-*/]")) {
//            calculator.writeOperator(key);
//        } else if (key.equals(".")) {
//            calculator.writeDot();
//        } else if (keyEvent.getCode().equals(KeyCode.BACK_SPACE)) {
//            delete();
//        } else if (keyEvent.getCode().equals(KeyCode.ESCAPE)) {
//            allClear();
//        } else if (keyEvent.getCode().equals(KeyCode.ENTER)) {
//            equal();
//        }
//    }
}
