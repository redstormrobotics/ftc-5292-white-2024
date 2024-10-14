package org.firstinspires.ftc.teamcode.TopLevel;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Intake {

    private Servo toproller;
    private Servo bottomroller;


    public Intake(HardwareMap hardwareMap){
        toproller = hardwareMap.get(Servo.class,"Top Roller");
        bottomroller = hardwareMap.get(Servo.class,"Bottom Roller");

    }

    public class IntakeInit implements Action{
        @Override
        public boolean run(@NonNull TelemetryPacket packet){
            bottomroller.setDirection(Servo.Direction.FORWARD);
            toproller.setDirection(Servo.Direction.REVERSE);
            toproller.setPosition(0);
            bottomroller.setPosition(0);

            return false;
        }
    }
    public Action intakeInit(){
        return new IntakeInit();
    }
    public class IntakeIn implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet){
            toproller.setPosition(.5);
            bottomroller.setPosition(.5);

            return false;
        }
    }
    public Action intakeIn(){
        return new IntakeIn();
    }



}
