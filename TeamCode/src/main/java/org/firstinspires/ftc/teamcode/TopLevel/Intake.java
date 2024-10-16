package org.firstinspires.ftc.teamcode.TopLevel;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Intake {


    private CRServo bottomroller;
    private CRServo toproller;


    public Intake(HardwareMap hardwareMap){
        toproller = hardwareMap.get(CRServo.class,"upper roller");
        bottomroller = hardwareMap.get(CRServo.class,"lower roller");

    }

    public class IntakeInit implements Action{
        @Override
        public boolean run(@NonNull TelemetryPacket packet){
            bottomroller.setDirection(CRServo.Direction.FORWARD);
            toproller.setDirection(CRServo.Direction.REVERSE);
            toproller.setPower(0);
            bottomroller.setPower(0);

            return false;
        }
    }
    public Action intakeInit(){
        return new IntakeInit();
    }


    public class IntakeIn implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet){
            toproller.setPower(0.5);
            bottomroller.setPower(0.5);

            return false;
        }
    }
    public Action intakeIn(){
        return new IntakeIn();
    }
    public class IntakeOut implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet){
            toproller.setPower(-0.5);
            bottomroller.setPower(-0.5);

            return false;
        }
    }
    public Action intakeOut(){
        return new IntakeOut();
    }


    public class IntakeStop implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet){
            toproller.setPower(0);
            bottomroller.setPower(0);

            return false;
        }
    }
    public Action intakeStop(){
        return new IntakeStop();
    }
}
