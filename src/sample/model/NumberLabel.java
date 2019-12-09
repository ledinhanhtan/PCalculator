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
            if (this.getText().length() < 11) {
                concat(number);
            }
        }
    }

    private void autoComma(String newValue) {
        if (newValue.length() > 3) {
            if (newValue.contains(",")) {
                newValue = newValue.replaceAll(",", "");
            }
            try {
                this.setText(numberFormat.format(Integer.parseInt(newValue)));
            } catch (NumberFormatException e) {
                System.out.println("Trying to cast (int) to (double). Fix later");
            }
        } else if (newValue.length() == 3){
            this.setText(this.getText().replaceAll(",", ""));
        }
    }

    void writeDot() {
        if (!this.getText().contains(".")) {
            concat(".");
        }
    }

    private void concat(String str) {
        this.setText(this.getText() + str);
    }
}
