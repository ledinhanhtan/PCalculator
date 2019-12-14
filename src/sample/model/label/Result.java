package sample.model.label;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import sample.model.Led;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class Result extends SmartLabel {
    private boolean isError;
    private NumberFormat localeFormatter;
    private NumberFormat decimalFormatter;

    private Led led;

    public Result() {
        setup();
        format();
    }

    @Override
    void setup() {
        super.setup();
        this.state = 3;
        localeFormatter = NumberFormat.getNumberInstance(Locale.US);
        decimalFormatter = new DecimalFormat("0.#####E0");
    }

    private void format() {
        this.setText("0");
        this.setAlignment(Pos.CENTER_RIGHT);
//        this.setMinSize(240,50);
        this.setMinHeight(50);
        AnchorPane.setRightAnchor(this, 0.);
        this.setFont(new Font("System", 22));
        this.setPadding(new Insets(0, 10, 0, 10));
        this.setStyle("-fx-text-fill: #979696");
    }

    public void setLed(Led led) {
        this.led = led;
    }

    public void setResult(Number number) {
        if (!isError(number)) {
            String result;
            try {
                result = Integer.toString((int) number);
            } catch (ClassCastException e) {
                result = Double.toString((double) number);
                if ((double) number < 1000 && result.length() > 12) {
                    result = result.substring(0, 12);
                }
            }
            if (Double.parseDouble(result) > 999999999) {
                result = decimalFormatter.format(Double.parseDouble(result)).replace("E", "e");
            } else {
                result = localeFormatter.format(Double.parseDouble(result));
            }
            this.setText("=" + result);
        }
    }

    //Neu trong bieu thuc co dau / va result la ∞, giu nguyen dau vo cuc
    private boolean isError(Number number) {
        double num = Double.parseDouble(number.toString().replace("=", ""));
        if (Double.isInfinite(num) || Double.isNaN(num)) {
            this.setText("=Can't divide by zero");
            led.redOn();
            isError = true;
            return true;
        } else {
            led.redOff();

            isError = false;
            return false;
        }
    }

    public boolean isNonError() {
        return !isError;
    }
}
