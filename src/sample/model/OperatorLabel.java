package sample.model;

class OperatorLabel extends SmartLabel {
    @Override
    void concatOrReplace(String operator) {
        //replace
        this.setText(operator);
    }
}
