package org.firstinspires.ftc.teamcode.TopLevel;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Wrist {

    private Servo wrist;

    public Wrist(HardwareMap hardwareMap){
        wrist = hardwareMap.get(Servo.class,"wrist");

    }

    public class WristInit implements Action{
        @Override
        public boolean run(@NonNull TelemetryPacket packet){
            wrist.setDirection(Servo.Direction.FORWARD);
            wrist.setPosition(0.5);
            wrist.scaleRange(0, 1);
            return false;
        }
    }
    public Action wristInit(){
        return new WristInit();
    }
    public class WristPickup implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet){
            wrist.setPosition(0.25);

            return false;
        }
    }
    public Action wristPickup(){
        return new WristPickup();
    }
    public class WristScoring implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet){
            wrist.setPosition(.88);

            return false;
        }
    }
    public Action wristScoring(){
        return new WristScoring();
    }


}
