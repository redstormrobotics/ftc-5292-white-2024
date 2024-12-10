package org.firstinspires.ftc.teamcode.TopLevel;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import com.acmerobotics.roadrunner.ftc.Actions;
import androidx.annotation.NonNull;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import org.firstinspires.ftc.teamcode.MecanumDrive;

import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;


public class Wrist {

    private Servo wrist;

    public Wrist(HardwareMap hardwareMap){
        wrist = hardwareMap.get(Servo.class,"wrist");

    }

    public class WristInit implements Action{
        @Override
        public boolean run(@NonNull TelemetryPacket packet){
            wrist.setDirection(Servo.Direction.FORWARD);
            wrist.setPosition(0.7);
            wrist.scaleRange(0, 1);
            //telemetry.addData("wrist", wrist.getPosition());

            return false;
        }
    }
    public Action wristInit(){
//        wrist.setDirection(Servo.Direction.FORWARD);
//        wrist.setPosition(0.5);
//        wrist.scaleRange(0, 1);
       // telemetry.addData("wrist", wrist.getPosition());
        return new WristInit();
    }
    public class WristPickup implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet){
            wrist.setPosition(0);

            return false;
        }
    }
    public Action wristPickup(){
        return new WristPickup();
    }
    public class WristScoring implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet){
            wrist.setPosition(.64);

            return false;
        }
    }
    public Action wristScoring(){
        return new WristScoring();
    }

    public class WristUp implements Action{
        @Override
        public boolean run(@NonNull TelemetryPacket packet){
            wrist.setDirection(Servo.Direction.FORWARD);
            wrist.setPosition(0.21);
            wrist.scaleRange(0, 1);

            return false;
        }
    }

    public Action wristUp(){
        return new WristScoring();
    }


}
