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



@Autonomous(name = "pickupPosition", preselectTeleOp = "White tele-op improved Dual Motors")
public final class pickupPosition extends LinearOpMode {
    public Robot robot;


    @Override
    public void runOpMode() throws InterruptedException {
        double offset = 90;
        Pose2d beginPose = new Pose2d(-38.0, -61.0, Math.toRadians(offset+2.0));
        Pose2d PoseAutonScore = new Pose2d(-53,-61, Math.toRadians(offset+2.0));
        Pose2d PoseExtakeSample = new Pose2d(-53+12-7, -61+14, Math.toRadians(offset+2.0));
        Pose2d PoseIntakeFirstSample = new Pose2d(-53-4,-61+14, Math.toRadians(offset+2.0));
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
                .setTangent(offset+2.0)
                .splineToLinearHeading(new Pose2d(-53,-61,Math.toRadians(offset+2.0)),Math.toRadians(offset+2.0))
                .build();

        Action Traj2 = drive.actionBuilder(PoseAutonScore)
                .setTangent(offset+2.0)
                .splineToLinearHeading(new Pose2d(-53+12,-61+20,Math.toRadians(offset+2.0)),Math.toRadians(offset+2.0))
                .build();

        Action Traj3 = drive.actionBuilder(PoseExtakeSample)
                .setReversed(true)
                .setTangent(offset+30)
                .splineToLinearHeading(new Pose2d(-53,-61,Math.toRadians(offset+2.0)),Math.toRadians(offset+2.0))
                .build();

        Action Traj4 = drive.actionBuilder(PoseAutonScore)
                .setTangent(offset+2.0)
                .splineToLinearHeading(new Pose2d(-53+15,-61+14,Math.toRadians(offset+30)),Math.toRadians(offset+2.0))
                .build();

        Action Traj5 = drive.actionBuilder(PoseIntakeFirstSample)
                .splineToConstantHeading(new Vector2d(0,30),Math.toRadians(90), new TranslationalVelConstraint(30.0))
                .build();




        Actions.runBlocking(
                new SequentialAction(
                        traj1,
                   //       robot.lift.liftScoring(),
                   //       robot.arm.armScoring(),
                   //       robot.wrist.wristScoring(),
                        Traj2,
                   //       robot.intake.intakeOut(),
                    //      robot.intake.intakeStop(),
                        Traj3,
                  //        robot.arm.armResting(),
                    //      robot.wrist.wristPickup(),
                   //       robot.lift.liftTravel(),
                          robot.wrist.wristPickup(),
                        Traj4,
                          robot.arm.armPickUp(),
                          robot.intake.intakeIn(),
                          robot.intake.intakeStop(),
                          robot.arm.armResting()
                   //     Traj5
                    //      robot.lift.liftScoring(),
                     //     robot.arm.armScoring(),
                  //        robot.wrist.wristScoring(),
                    //      robot.intake.intakeOut()

                )
        );
    }
}