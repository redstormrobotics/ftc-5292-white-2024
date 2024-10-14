package org.firstinspires.ftc.teamcode.TopLevel;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class Robot {

 public Intake intake;
 private HardwareMap hardwareMap;

 public Robot(HardwareMap hardwareMap){
     this.hardwareMap = hardwareMap;
     intake = new Intake(hardwareMap);
 }
}
