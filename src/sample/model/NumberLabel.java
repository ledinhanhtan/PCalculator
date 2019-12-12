package sample.model;

import java.text.NumberFormat;
import java.util.Locale;

class NumberLabel extends SmartLabel {
    private NumberFormat numberFormat;

    @Override
    void setup() {
        numberFormat = NumberFormat.getNumberInstance(Locale.US);
        this.textProperty().addListener((observable, oldValue, newValue) -> autoComma(newValue));
    }

    @Override
    void concatOrReplace(String number) {
        //When user enter "0" in first index of a number, block user to add any number
        if (!this.getText().equals("0")) {
            //concat
            if (this.getText().length() <= 15) {
                this.setText(this.getText() + number);
            }
        }
    }

    //Todo in here!
    private void autoComma(String newValue) {
        //Remove all comma to use format method
        newValue = newValue.replaceAll(",", "");

        if (newValue.length() > 3) {
            try {
                //Add comma by formatting the number
                this.setText(numberFormat.format(Integer.parseInt(
                        newValue)));
            } catch (NumberFormatException e) {
                //In case number are too large > 1B, format decimal. If number are < 1B,
                //do not format number, so user can still write double number
                if (Double.parseDouble(newValue) > 999999999) {
                    this.setText(numberFormat.format(Double.parseDouble(
                        newValue)));
                }
            }
            //Delete comma when number change to 3 digits
        } else if (newValue.length() == 3) {
            this.setText(this.getText().replaceAll(",", ""));
        }
    }



    void writeDot() {
        if (!this.getText().contains(".")) {
            this.setText(this.getText() + ".");
        }
    }
}
