package org.firstinspires.ftc.teamcode.TopLevel;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.TranslationalVelConstraint;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.teamcode.MecanumDrive;

import com.acmerobotics.roadrunner.SequentialAction;

import com.acmerobotics.roadrunner.Action;



@Autonomous(name = "Score_Park", preselectTeleOp = "White tele-op")
public final class Score_Park extends LinearOpMode {
    public Robot robot;


    @Override
    public void runOpMode() throws InterruptedException {
        Pose2d beginPose = new Pose2d(0, 0, 0);
        Pose2d Pose2 = new Pose2d(-20, -14, Math.toRadians(90));
        Pose2d Pose3 = new Pose2d(-28, -14, Math.toRadians(90));
        MecanumDrive drive = new MecanumDrive(hardwareMap, beginPose);
        //Pose2d Pose2 = new Pose2d(drive.pose.position.x,drive.pose.position.y,drive.pose.heading.imag);
        // Initialize the robot with the hardwareMap
        robot = new Robot(hardwareMap);

        robot.intake.intakeInit();
        Actions.runBlocking(robot.intake.intakeInit());
         robot.arm.armInit();
        Actions.runBlocking(robot.arm.armInit());
        robot.wrist.wristInit();
         robot.lift.liftInit();
        Actions.runBlocking(robot.lift.liftInit());
         telemetry.addData("Test",8);
        Actions.runBlocking(robot.wrist.wristInit());
         telemetry.update();
         waitForStart();
         Action traj1;


        traj1 = drive.actionBuilder(beginPose)
                .splineTo(new Vector2d(-20,20),Math.toRadians(90))
                //.splineTo(new Vector2d(-20,-6),Math.toRadians(90))
                .splineToConstantHeading(new Vector2d(-20,-14),Math.toRadians(90))
                //.splineToLinearHeading(new Pose2d(-35, 10, 0), Math.toRadians(90))
                .build();

        Action Traj2 = drive.actionBuilder(Pose2)
                .splineTo(new Vector2d(30,20),Math.toRadians(0), new TranslationalVelConstraint(30.0))
                .splineTo(new Vector2d( 60,20),Math.toRadians(0), new TranslationalVelConstraint(30.0))
                .splineTo(new Vector2d(86,0),Math.toRadians(0),  new TranslationalVelConstraint(30.0))

                .build();

        Action Traj3 = drive.actionBuilder(Pose2)
                .splineToConstantHeading(new Vector2d(-28,-14),Math.toRadians(90))
                        .build();

        Action Traj4 = drive.actionBuilder(Pose3)
                .splineToConstantHeading(new Vector2d(-20,-14),Math.toRadians(90))
                .build();







        Actions.runBlocking(
                new SequentialAction(
                        traj1,
                          robot.lift.liftScoring(),
                        robot.arm.armScoring(),
                        robot.wrist.wristScoring(),
                        Traj3,
                        robot.intake.intakeOut(),
                        robot.intake.intakeStop(),
                        Traj4,
                        robot.arm.armResting(),
                        robot.wrist.wristPickup(),
                        robot.lift.liftHome(),
                        robot.wrist.wristScoring(),
                        Traj2
                )
        );
    }
}