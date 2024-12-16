package org.firstinspires.ftc.teamcode.TopLevel;

import static java.lang.Math.toRadians;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.TranslationalVelConstraint;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.VelConstraint;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.MecanumDrive;

import com.acmerobotics.roadrunner.SequentialAction;

import com.acmerobotics.roadrunner.Action;



@Autonomous(name = "CloserToBucketsImproved", preselectTeleOp = "White tele-op improved Dual Motors1")
public final class CloserToBucketsImproved extends LinearOpMode {
    public Robot robot;


    @Override
    public void runOpMode() throws InterruptedException {
        double offset = 90;
        Pose2d beginPose = new Pose2d(-38.0, -61.0, toRadians(offset+2.0));
        Pose2d PoseAutonScore = new Pose2d(-56-2,-67, toRadians(offset+2.0));
        Pose2d PoseExtakeSample = new Pose2d(-43.0, -44, toRadians(offset+2.0));
        Pose2d PoseScoreFirstSample = new Pose2d(-53-3-3, -61, toRadians(offset));
        Pose2d PoseIntakeFirstSample = new Pose2d(-53,-61, toRadians(offset+2.0));
        Pose2d AngleFirstPickup = new Pose2d(-46+5+7+3.5+2,-44+3.5-13-2+19,toRadians(offset+30.0));
        Pose2d PoseIntakeSecondSample = new Pose2d(AngleFirstPickup.position.plus(new Vector2d(-10, 0)), AngleFirstPickup.heading);;
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
        Action FirstScore;

        FirstScore = drive.actionBuilder(beginPose)
                .setTangent(offset+2.0)
                .splineToLinearHeading(PoseAutonScore, Math.toRadians(offset+2.0), new TranslationalVelConstraint(30.0))
                //new Pose2d(-56-2,-67,Math.toRadians(offset+2.0)),Math.toRadians(offset+2.0)
                .build();


        Action IntakeFirstSample = drive.actionBuilder(PoseAutonScore)
                .setTangent(offset+2.0)
                .strafeToLinearHeading(AngleFirstPickup.position,AngleFirstPickup.heading)

                .build();

        Action ScoreSecondSample = drive.actionBuilder(AngleFirstPickup)
                .strafeToLinearHeading(PoseScoreFirstSample.position, PoseScoreFirstSample.heading, new TranslationalVelConstraint(30.0))
                //new Pose2d(-53-3-3,-61,Math.toRadians(offset+2.0)),Math.toRadians(offset+2.0)
                .build();

        Action IntakeSecondSample = drive.actionBuilder(PoseScoreFirstSample)
                .setTangent(offset+2.0)
                .splineToLinearHeading(PoseIntakeSecondSample, PoseIntakeSecondSample.heading)
                //new Pose2d(-46+5,-44+3.5,Math.toRadians(offset+30)),Math.toRadians(offset+2.0), new TranslationalVelConstraint(20.0)
                .build();

        Action ScoreThirdSample = drive.actionBuilder(PoseIntakeSecondSample)
                .strafeToLinearHeading(PoseScoreFirstSample.position, PoseScoreFirstSample.heading, new TranslationalVelConstraint(30.0))
                .build();




        Actions.runBlocking(
                new SequentialAction(
                        robot.lift.liftScoring(),
                        FirstScore,
                        robot.arm.armScoring(),
                        robot.wrist.wristScoring(),
                        robot.intake.intakeOut(),
                        robot.intake.intakeStop(),
                        robot.arm.armResting(),
                        robot.wrist.wristPickup(),
                        robot.lift.liftTravel(),
                        robot.arm.armPickUp(),
                        robot.wrist.wristPickup(),
                        robot.intake.intakeIn(),
                        IntakeFirstSample,
                        robot.intake.intakeSleep(150000.0),
                        robot.wrist.wristPickup(),
                        robot.arm.armResting(),
                        robot.intake.intakeStop(),
                        robot.lift.liftScoring(),
                        ScoreSecondSample,
                        robot.arm.armScoring(),
                        robot.wrist.wristScoring(),
                        robot.intake.intakeSleep(50000.0),
                        robot.intake.intakeOut(),
                        robot.intake.intakeStop(),
                        robot.arm.armResting(),
                        robot.wrist.wristPickup(),
                        robot.lift.liftTravel(),
                        robot.arm.armPickUp(),
                        robot.wrist.wristPickup(),
                        robot.intake.intakeIn(),
                        IntakeSecondSample,
                        robot.intake.intakeSleep(150000.0),
                        robot.intake.intakeStop(),
                        robot.arm.armResting(),
                        robot.wrist.wristScoring(),
                        robot.lift.liftScoring(),
                        robot.arm.armScoring(),
                        ScoreThirdSample,
                        robot.intake.intakeOut(),
                        robot.lift.liftTravel(),
                        robot.arm.armResting()
                )
        );
    }
}