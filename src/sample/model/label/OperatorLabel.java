package sample.model.label;

public class OperatorLabel extends SmartLabel {
    @Override
    void concatOrReplace(String operator) {
        //replace
        this.setText(operator);
    }
}
