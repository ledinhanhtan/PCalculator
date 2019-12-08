package sample.model;

class NumberLabel extends SmartLabel {
    @Override
    void concatOrReplace(String number) {
        //When user enter "0" in first index of a number, block user to add any number
        if (!this.strProperty.getValue().equals("0")) {
            //concat
            if (this.strProperty.getValue().length() < 11) {
                this.strProperty.setValue(this.strProperty.getValue() + number);
            }
        }
    }

    void writeDot() {
        if (!this.strProperty.getValue().contains(".")) {
            this.strProperty.setValue(strProperty.getValue() + ".");
        }
    }
}
