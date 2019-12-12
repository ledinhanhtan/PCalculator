package sample.model;

import javafx.scene.image.ImageView;

import java.util.Timer;
import java.util.TimerTask;

class Led {
    private ImageView green;
    private ImageView red;
    private Timer timer;

    private boolean disableGreen;

    Led(ImageView green, ImageView red) {
        this.green = green;
        this.red = red;
        setup();
    }

    private void setup() {
        timer = new Timer();
    }

    void peak() {
        if (!disableGreen) {
            green.setVisible(true);
            timer.schedule(new TurnOffLed(), 100);
        }
    }

    void on() {
        green.setVisible(true);
    }

    void off() {
        green.setVisible(false);
    }

    class TurnOffLed extends TimerTask {
        @Override
        public void run() {
            green.setVisible(false);
        }
    }

    void redOn() {
        red.setVisible(true);
        disableGreen = true;
    }

    void redOff() {
        red.setVisible(false);
        disableGreen = false;
    }
}
