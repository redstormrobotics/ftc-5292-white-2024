package org.firstinspires.ftc.teamcode.TopLevel;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class Robot {

 public Intake intake;
 public Arm arm;
 public Lift lift;
 public Wrist wrist;
 private HardwareMap hardwareMap;

 public Robot(HardwareMap hardwareMap){
     this.hardwareMap = hardwareMap;
     intake = new Intake(hardwareMap);
     arm = new Arm(hardwareMap);
     lift = new Lift(hardwareMap);
     wrist = new Wrist(hardwareMap);
 }
}
