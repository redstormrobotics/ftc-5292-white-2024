package org.firstinspires.ftc.teamcode.TopLevel;

import android.graphics.Color;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.w8wjb.ftc.AdafruitNeoDriver;

public class Lights {
    private static final int NUM_PIXELS = 7;
    private HardwareMap hardwareMap;
    AdafruitNeoDriver neopixels;
    public Lights(HardwareMap hardwareMap){
        neopixels = null;
        this.hardwareMap = hardwareMap;
    }

    public void init() {

        neopixels = hardwareMap.get(AdafruitNeoDriver.class, "neoleds");
        neopixels.resetDeviceConfigurationForOpMode();
        neopixels.setNumberOfPixels(NUM_PIXELS);
        neopixels.fill("#220011");
        neopixels.show();
    }
    public class LightsInt implements Action{
        @Override
        public boolean run(@NonNull TelemetryPacket packet){
            init();
            return false;
        }
    }
    public Action lightsInit(){
        return new Lights.LightsInt();
    }
    public void setWhite() {
//        int[] colors = new int[NUM_PIXELS];
//        for (int i=0; i < colors.length; i++) {
//            colors[i] = Color.HSVToColor(new float[] { , 1.0f, .05f });
//        }
//        neopixels.setPixelColors(colors);
        neopixels.fill("#ff0000");
        neopixels.show();
    }
    public Action lightsWhite(){ return new Lights.LightColorWhite();}
    public class LightColorWhite implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet){
            setWhite();
            return false;
        }
    }
}
