package org.firstinspires.ftc.teamcode.TopLevel;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Arm {

    private DcMotor arm;


    public Arm(HardwareMap hardwareMap){
        arm = hardwareMap.get(DcMotor.class, "arm");

    }

    public class ArmInt implements Action{
        @Override
        public boolean run(@NonNull TelemetryPacket packet){
            arm.setDirection(DcMotorSimple.Direction.FORWARD);
            arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            arm.setTargetPosition(0);
            arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            arm.setPower(0);
            return false;
        }
    }
    public Action armInit(){
        return new ArmInt();
    }
    public class ArmPickUp implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet){
            arm.setTargetPosition(1000);
            arm.setPower(.5);
            return false;
        }
    }
    public Action intakeIn(){
        return new ArmPickUp();
    }



}
