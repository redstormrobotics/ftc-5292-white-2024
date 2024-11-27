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



@Autonomous(name = "pickUpThreeSamples", preselectTeleOp = "White tele-op improved Dual Motors")
public final class pickUpThreeSamples extends LinearOpMode {
    public Robot robot;


    @Override
    public void runOpMode() throws InterruptedException {
        Pose2d beginPose = new Pose2d(0, 0, 0);
        Pose2d intakeFirstSample = new Pose2d(14, -6, 50);
        Pose2d scorePosition = new Pose2d(0, 3, 0);
        Pose2d intakeSecondSample = new Pose2d(16, 13, 0);
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
                .splineTo(new Vector2d(20,-6),Math.toRadians(50))
                .build();

        Action Traj2 = drive.actionBuilder(intakeFirstSample)
                .splineTo(new Vector2d(0,3),Math.toRadians(0))
                .build();

        Action Traj3 = drive.actionBuilder(scorePosition)
                .splineTo(new Vector2d(16,13),Math.toRadians(0))
                .build();

        Action Traj4 = drive.actionBuilder(intakeSecondSample)
                .splineToConstantHeading(new Vector2d(0,3),Math.toRadians(0), new TranslationalVelConstraint(30.0))
                .build();



        Actions.runBlocking(
                new SequentialAction(
                            robot.wrist.wristPickup(),
                            robot.arm.armPickUp(),
                            robot.intake.intakeIn(),
                        traj1,
                            robot.intake.intakeSleep(),
                            robot.intake.intakeStop(),
                            robot.arm.armResting(),
                            robot.wrist.wristPickup(),
                            robot.intake.intakeIn(),
                        Traj2,
                            robot.intake.intakeOut(),
                            robot.intake.intakeStop(),
                            robot.wrist.wristPickup(),
                            robot.arm.armPickUp(),
                            robot.intake.intakeIn(),
                        Traj3,
                            robot.intake.intakeSleep(),
                            robot.intake.intakeStop(),
                            robot.arm.armResting(),
                        Traj4,
                            robot.intake.intakeOut(),
                            robot.intake.intakeStop()


                )
        );
    }
}