package org.firstinspires.ftc.teamcode.TopLevel;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Lift {

    private DcMotor lift;


    public Lift(HardwareMap hardwareMap){
        lift = hardwareMap.get(DcMotor.class, "lift");

    }

    public class LiftInt implements Action{
        @Override
        public boolean run(@NonNull TelemetryPacket packet){
            lift.setDirection(DcMotorSimple.Direction.FORWARD);
            lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            lift.setTargetPosition(0);
            lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            lift.setPower(0);
            return false;
        }
    }
    public Action liftInit(){
        return new LiftInt();
    }
    public class LiftPickUp implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet){
            lift.setTargetPosition(1000);
            lift.setPower(.5);
            return false;
        }
    }
    public Action intakeIn(){
        return new LiftPickUp();
    }

    public class LiftScoring implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet){
            lift.setTargetPosition(1000);
            lift.setPower(.5);
            return false;
        }
    }

}





