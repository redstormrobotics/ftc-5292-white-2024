package org.firstinspires.ftc.teamcode.TopLevel;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.PoseVelocity2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.teamcode.MecanumDrive;

import com.acmerobotics.roadrunner.SequentialAction;

import com.acmerobotics.roadrunner.Action;



@Autonomous(name = "TestAuto" )
public final class TestAuto extends LinearOpMode {
    public Robot robot;


    @Override
    public void runOpMode() throws InterruptedException {
        Pose2d beginPose = new Pose2d(0, 0, 0);
        Pose2d Pose2 = new Pose2d(74, 53, 0);
        MecanumDrive drive = new MecanumDrive(hardwareMap, beginPose);
        //Pose2d Pose2 = new Pose2d(drive.pose.position.x,drive.pose.position.y,drive.pose.heading.imag);
        // Initialize the robot with the hardwareMap
        robot = new Robot(hardwareMap);

        robot.intake.intakeInit();
        robot.arm.armInit();
        robot.wrist.wristInit();
        robot.lift.liftInit();
        waitForStart();
        Action trajectoryActionChosen;
        Action trajToPick;
        Action updatePos;


        trajectoryActionChosen =             drive.actionBuilder(beginPose)
                .splineTo(new Vector2d(68, 5), Math.toRadians(90))
                .splineTo(new Vector2d(74, 53), Math.toRadians(0))
                .build();
        trajToPick = drive.actionBuilder(Pose2)
                .splineTo(new Vector2d(80, 60), Math.toRadians(90))
                .splineTo(new Vector2d(90, 70), Math.toRadians(0))
                .build();
        Actions.runBlocking(
                new SequentialAction(
                        robot.intake.intakeIn(),
                        trajectoryActionChosen,
                        trajToPick,
                        robot.arm.armInit()
                )
        );

        //Actions.runBlocking(
        //       drive.actionBuilder(beginPose)
        //             .splineTo(new Vector2d(68, 5), Math.toRadians(90))
        //           .splineTo(new Vector2d(74, 53), Math.toRadians(0))
        //         .build()
        //   );
    }
}