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
            arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            arm.setTargetPosition(0);
            arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            return false;
        }
    }
    public Action armInit(){
        return new ArmInt();
    }
    public class ArmPickUp implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet){
            arm.setTargetPosition(2500);
            arm.setPower(.35);
            return false;
        }
    }
    public Action armPickUp(){return new ArmPickUp();
    }
    public class ArmScoring implements Action {
        public boolean init = false;
        @Override
        public boolean run(@NonNull TelemetryPacket packet){
            if(!init){
                arm.setPower(.35);
                arm.setTargetPosition(-250);
                init = true;
            }
            if (arm.isBusy()){
                return true;
            }
            else{
                return  false;
            }
        }
    }
    public Action armScoring(){return new ArmScoring();
    }

    public class ArmResting implements Action {
        public boolean init = false;
        @Override
        public boolean run(@NonNull TelemetryPacket packet){
            if (!init){
                arm.setTargetPosition(0);
                arm.setPower(.35);
            }
            if (arm.isBusy()){
                return true;
            }
            else {
                return false;
            }
        }
    }
    public Action armResting(){return new ArmResting();
    }
















}
