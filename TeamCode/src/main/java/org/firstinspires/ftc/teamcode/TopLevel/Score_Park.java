package org.firstinspires.ftc.teamcode.TopLevel;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.teamcode.MecanumDrive;

import com.acmerobotics.roadrunner.SequentialAction;

import com.acmerobotics.roadrunner.Action;



@Autonomous(name = "Score_Park" )
public final class Score_Park extends LinearOpMode {
    public Robot robot;


    @Override
    public void runOpMode() throws InterruptedException {
        Pose2d beginPose = new Pose2d(0, 0, 0);
        Pose2d Pose2 = new Pose2d(-35, 10, Math.toRadians(90));
        MecanumDrive drive = new MecanumDrive(hardwareMap, beginPose);
        //Pose2d Pose2 = new Pose2d(drive.pose.position.x,drive.pose.position.y,drive.pose.heading.imag);
        // Initialize the robot with the hardwareMap
        robot = new Robot(hardwareMap);

        robot.intake.intakeInit();
         robot.arm.armInit();
        robot.wrist.wristInit();
         robot.lift.liftInit();
         waitForStart();
         Action traj1;


        traj1 = drive.actionBuilder(beginPose)
                .splineTo(new Vector2d(0,10),Math.toRadians(-90))
                .splineTo(new Vector2d(-35,0),Math.toRadians(90))
                .splineToConstantHeading(new Vector2d(-40,-34),Math.toRadians(90))
                //.splineToLinearHeading(new Pose2d(-35, 10, 0), Math.toRadians(90))
                .build();

        Action Traj2 = drive.actionBuilder(Pose2)
                .splineTo(new Vector2d(70,-5),Math.toRadians(0))

                .build();









        Actions.runBlocking(
                new SequentialAction(
                        traj1,
//                        robot.lift.liftScoring(),
//                        robot.arm.armScoring(),
//                        robot.wrist.wristScoring(),
//                        robot.intake.intakeOut(),
//                        robot.arm.armIn(),
//                        robot.lift.liftHome(),
//                        robot.intake.intakeStop(),
                        Traj2
                )
        );
    }
}