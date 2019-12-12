package sample.model;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class Result extends Label {
    private boolean isSpecialCase;
    private NumberFormat localeFormatter;
    private NumberFormat decimalFormatter;

    Result() {
        format();
        setup();
    }

    private void format() {
        this.setText("0");
        this.setAlignment(Pos.CENTER_RIGHT);
        this.setMinSize(240,50);
        this.setFont(new Font("System", 22));
        this.setPadding(new Insets(0, 10, 0, 10));

        this.setStyle("-fx-text-fill: #b3b3b3");
    }

    private void setup() {
        localeFormatter = NumberFormat.getNumberInstance(Locale.US);
        decimalFormatter = new DecimalFormat("0.#####E0");

        this.textProperty().addListener((observable, oldValue, newValue) ->
                specialCase(newValue));
    }

    void setResult(Number number) {
        String result;
        try {
            result = Integer.toString((int) number);
        } catch (ClassCastException e) {
            result = Double.toString((double) number);
            if ((double) number < 1000 && result.length() > 12) {
                result = result.substring(0, 12);
            }
        }
//.replace("E", "e"
        if (Double.parseDouble(result) > 999999999) {
            result = decimalFormatter.format(Double.parseDouble(result)).replace("E", "e");
        } else {
            result = localeFormatter.format(Double.parseDouble(result));
        }
        this.setText("=" + result);
    }

    private void specialCase(String newValue) {
        if (newValue.matches("=∞|=-∞")) {
            this.setText("=Can't divide by zero");
            isSpecialCase = true;
        } else {
            isSpecialCase = false;
        }
    }

    boolean isSpecialCase() {
        return !isSpecialCase;
    }
}
