package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import sample.model.Calculator;
import sample.model.NumberLabel;


public class Controller {
    @FXML
    Label expression;
    @FXML
    Label result;
    @FXML
    AnchorPane root;
    @FXML
    FlowPane flowPane;

    private Calculator calculator;

    public void number(ActionEvent e) {
        Button button = (Button) e.getSource();
        NumberLabel lbl = new NumberLabel(button.getText());
        flowPane.getChildren().add(lbl);
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
