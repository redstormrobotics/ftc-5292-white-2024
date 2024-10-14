package org.firstinspires.ftc.teamcode.tuning;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.w8wjb.ftc.AdafruitNeoDriver;

import org.firstinspires.ftc.teamcode.util.Timer;

@TeleOp(name = "LED Test")
public class LEDTest extends OpMode {

    private static final int NUM_PIXELS = 7;
    AdafruitNeoDriver neopixels;

    Timer timer = new Timer();

    private int redStart = 0;
    private int hueGap = 0;

    @Override
    public void init() {

        neopixels = hardwareMap.get(AdafruitNeoDriver.class, "neoleds");
        neopixels.resetDeviceConfigurationForOpMode();
        neopixels.setNumberOfPixels(NUM_PIXELS);
        neopixels.fill("#220011");
        neopixels.show();
        timer.reset();
        hueGap = 360 / NUM_PIXELS;
    }

    @Override
    public void loop() {
        if (timer.hasElapsed(0.1)) {
            int[] colors = new int[NUM_PIXELS];
            for (int i=0; i < colors.length; i++) {
                float hue = (float) ((redStart + i) * hueGap) % 360;
                        int color = Color.HSVToColor(new float[] { hue, 1.0f, .05f });
                colors[i] = color;
            }
            neopixels.setPixelColors(colors);
            neopixels.show();
            timer.reset();
            redStart = (redStart + 1) % (NUM_PIXELS*3);
        }
    }
}
