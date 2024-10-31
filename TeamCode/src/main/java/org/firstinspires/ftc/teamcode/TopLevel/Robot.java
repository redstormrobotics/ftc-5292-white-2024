package org.firstinspires.ftc.teamcode.TopLevel;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.qualcomm.robotcore.hardware.HardwareMap;

//import com.acmerobotics.roadrunner.geometry.Pose2d;
//import com.acmerobotics.roadrunner.geometry.Vector2d;
//import com.acmerobotics.roadrunner.trajectory.Trajectory;
//import com.qualcomm.robotcore.eventloop.opmode.Autonomous;


public class Robot {


 public Arm arm;
 public Lift lift;
 public Intake intake;
 public Wrist wrist;
 private HardwareMap hardwareMap;

 public Robot(HardwareMap hardwareMap){
     this.hardwareMap = hardwareMap;
     intake = new Intake(hardwareMap);
     arm = new Arm(hardwareMap);
     lift = new Lift(hardwareMap);
     wrist = new Wrist(hardwareMap);
//     public void test(){
//
//         sleep(1000);
//     }
 }

}
