package org.firstinspires.ftc.teamcode.TopLevel;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.teamcode.MecanumDrive;

import com.acmerobotics.roadrunner.SequentialAction;

import com.acmerobotics.roadrunner.Action;



@Autonomous(name = "shortPark_Auto" )
public final class shortPark_Auto extends LinearOpMode {
    public Robot robot;


    @Override
    public void runOpMode() throws InterruptedException {
        Pose2d beginPose = new Pose2d(0, 0, 0);
        Pose2d Pose2 = new Pose2d(86, 0, 0);
        MecanumDrive drive = new MecanumDrive(hardwareMap, beginPose);
        //Pose2d Pose2 = new Pose2d(drive.pose.position.x,drive.pose.position.y,drive.pose.heading.imag);
        // Initialize the robot with the hardwareMap
        robot = new Robot(hardwareMap);


        //robot.intake.intakeInit();
       // robot.arm.armInit();
        //robot.wrist.wristInit();
       // robot.lift.liftInit();
        Actions.runBlocking(robot.lights.lightsInit());
        Actions.runBlocking(robot.lights.lightsRainbow());
        waitForStart();
        Actions.runBlocking(robot.lights.lightsWhite());
        Action trajectoryActionChosen;
        Action trajToPick;
        Action updatePos;
        Action Traj1;
        Action Traj2;
        Action Traj3;
    Traj1 = drive.actionBuilder(beginPose)
            .splineTo(new Vector2d(40,-10),Math.toRadians(90))

            .build();
    Traj2 = drive.actionBuilder(Pose2)
                    .waitSeconds(2)
            .splineTo(new Vector2d(86,2),Math.toRadians(90))
            .build();


        Actions.runBlocking(
                new SequentialAction(
                        Traj1
                )
        );

        Actions.runBlocking(
               drive.actionBuilder(beginPose)
                     .splineTo(new Vector2d(68, 5), Math.toRadians(90))
                   .splineTo(new Vector2d(74, 53), Math.toRadians(0))
                 .build()
           );
    }
}