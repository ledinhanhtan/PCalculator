package sample.model;

class NumberLabel extends SmartLabel {
    @Override
    void concatOrReplace(String number) {
        this.strProperty.setValue(this.strProperty.getValue() + number);
    }
}
