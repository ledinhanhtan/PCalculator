package sample.model;

class OperatorLabel extends SmartLabel {
    @Override
    void concatOrReplace(String operator) {
        //replace
        this.strProperty.setValue(operator);
    }
}
