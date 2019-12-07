package sample.model;

class OperatorLabel extends SmartLabel {
    @Override
    void concatOrReplace(String operator) {
        this.strProperty.setValue(operator);
    }
}
