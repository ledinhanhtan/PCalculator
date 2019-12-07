package sample.model;

class NumberLabel extends SmartLabel {
    @Override
    void concatOrReplace(String number) {
        //concat
        if (this.strProperty.getValue().length() < 11) {
            this.strProperty.setValue(this.strProperty.getValue() + number);
        }
    }
}
