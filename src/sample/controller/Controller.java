package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import sample.model.Calculator;

public class Controller {
    @FXML
    Label expression;
    @FXML
    Label result;
    @FXML
    AnchorPane root;

    private Calculator calculator;

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
