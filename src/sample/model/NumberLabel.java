package sample.model;

class NumberLabel extends SmartLabel {
    @Override
    void concatOrReplace(String number) {
        //When user enter "0" in first index of a number, block user to add any number
        if (!this.strProperty.getValue().equals("0")) {
            //concat
            if (this.strProperty.getValue().length() < 11) {
                concat(number);
            }
        }
    }

    void writeDot() {
        if (!this.strProperty.getValue().contains(".")) {
            concat(".");
        }
    }

    private void concat(String str) {
        this.strProperty.setValue(this.strProperty.getValue() + str);
    }
}
