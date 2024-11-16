package org.firstinspires.ftc.teamcode.TopLevel;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.TranslationalVelConstraint;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.MecanumDrive;

import com.acmerobotics.roadrunner.SequentialAction;

import com.acmerobotics.roadrunner.Action;



@Autonomous(name = "pickupPosition", preselectTeleOp = "White tele-op")
public final class pickupPosition extends LinearOpMode {
    public Robot robot;


    @Override
    public void runOpMode() throws InterruptedException {
        Pose2d beginPose = new Pose2d(0, 0, 0);
        Pose2d PoseAutonScore = new Pose2d(0, 30, Math.toRadians(0));
        Pose2d PoseExtakeSample = new Pose2d(0, 36, Math.toRadians(0));
        Pose2d PoseIntakeFirstSample = new Pose2d(10, 30, Math.toRadians(0));
        MecanumDrive drive = new MecanumDrive(hardwareMap, beginPose);
        //Pose2d Pose2 = new Pose2d(drive.pose.position.x,drive.pose.position.y,drive.pose.heading.image);
        // Initialize the robot with the hardwareMap
        robot = new Robot(hardwareMap);

        robot.intake.intakeInit();
        Actions.runBlocking(robot.intake.intakeInit());
        robot.arm.armInit();
        Actions.runBlocking(robot.arm.armInit());
        robot.wrist.wristInit();
        robot.lift.liftInit();
        Actions.runBlocking(robot.lift.liftInit());
        Actions.runBlocking(robot.wrist.wristInit());
        telemetry.update();
        waitForStart();
        Action traj1;


        traj1 = drive.actionBuilder(beginPose)
                .splineToConstantHeading(new Vector2d(0,30),Math.toRadians(0))
                .build();

        Action Traj2 = drive.actionBuilder(PoseAutonScore)
                .splineToConstantHeading(new Vector2d(0,36),Math.toRadians(0))
                .build();

        Action Traj3 = drive.actionBuilder(PoseExtakeSample)
                .splineToConstantHeading(new Vector2d(0,30),Math.toRadians(0))
                .build();

        Action Traj4 = drive.actionBuilder(PoseAutonScore)
                .splineToConstantHeading(new Vector2d(10,30),Math.toRadians(0))
                .build();
        Action Traj5 = drive.actionBuilder(PoseIntakeFirstSample)
                .splineToConstantHeading(new Vector2d(0,30),Math.toRadians(90))
                .build();



        Actions.runBlocking(
                new SequentialAction(
                        traj1,
                 //         robot.lift.liftScoring(),
                   //       robot.arm.armScoring(),
                   //       robot.wrist.wristScoring(),
                        Traj2,
                   //       robot.intake.intakeOut(),
                    //      robot.intake.intakeStop(),
                        Traj3,
                 //         robot.arm.armResting(),
                  //        robot.wrist.wristPickup(),
                  //        robot.lift.liftHome(),
                 //         robot.wrist.wristScoring(),
                        Traj4,
                          robot.arm.armPickUp(),
                          robot.intake.intakeIn(),
                          robot.intake.intakeStop(),
                          robot.arm.armResting(),
                        Traj5

                )
        );
    }
}