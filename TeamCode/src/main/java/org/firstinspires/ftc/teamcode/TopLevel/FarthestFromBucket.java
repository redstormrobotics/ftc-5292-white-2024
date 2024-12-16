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



@Autonomous(name = "FarthestFromBucket", preselectTeleOp = "White tele-op improved Dual Motors1")
public final class FarthestFromBucket extends LinearOpMode {
    public Robot robot;


    @Override
    public void runOpMode() throws InterruptedException {
        double offset = 90;
        Pose2d beginPose = new Pose2d(14.5, -61.0, Math.toRadians(offset+2.0));
        Pose2d PoseAlmostPickupSample = new Pose2d(22,-35, Math.toRadians(offset+2.0));
      //  Pose2d PosePickupSample = new Pose2d(53, -55+12, Math.toRadians(offset+2.0));
        MecanumDrive drive = new MecanumDrive(hardwareMap, beginPose);
        //Pose2d Pose2 = new Pose2d(drive.pose.position.x,drive.pose.position.y,drive.pose.heading.image);
        // Initialize the robot with the hardwareMap
        robot = new Robot(hardwareMap);

        //robot.intake.intakeInit();

        //robot.arm.armInit();

        //robot.wrist.wristInit();
        //robot.lift.liftInit();
        Actions.runBlocking(robot.arm.armInit());
        Actions.runBlocking(robot.lift.liftInit());
        Actions.runBlocking(robot.wrist.wristInit());
        Actions.runBlocking(robot.intake.intakeInit());
        telemetry.update();
        waitForStart();
        Action GotoFirstSamplePos;


        GotoFirstSamplePos = drive.actionBuilder(beginPose)
                .setTangent(offset+2.0)
                .strafeToLinearHeading(new Vector2d(30,-24),Math.toRadians(15))
                .build();

        Action Park = drive.actionBuilder(PoseAlmostPickupSample)
                .setTangent(offset+2.0)
                .splineToConstantHeading(new Vector2d(53,-55+12), Math.toRadians(offset+2.0))
                .build();




        Actions.runBlocking(
                new SequentialAction(
                        robot.wrist.wristPickup(),
                        robot.arm.armPickUp(),
                        robot.intake.intakeSleep(),
                        robot.intake.intakeOut(),
                        robot.intake.intakeSleep(),
                        robot.intake.intakeStop(),
                        robot.arm.armResting(),
                        GotoFirstSamplePos,
                        robot.wrist.wristPickup(),
                        robot.arm.armPickUp(),
                        robot.intake.intakeIn(),
                        robot.intake.intakeSleep(),
                        robot.intake.intakeStop(),
                        robot.arm.armResting(),
                        robot.intake.intakeSleep(),
                        robot.wrist.wristScoring(),
                        robot.wrist.wristUp(),
                        Park

                )
        );
    }
}
