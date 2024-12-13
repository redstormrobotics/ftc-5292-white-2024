package com.example.meepmeeptesting;

import static java.lang.Math.toRadians;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TranslationalVelConstraint;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.DriveShim;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

import sun.font.TrueTypeFont;

public class MeepMeepTesting4 {
    public static void main(String[] args) {
        //run_demo();
        run_Score_Park_Test();
    }

    static DriveShim drive = null;
    static RobotShim robot = null;
    static void run_demo() {
        {
            MeepMeep meepMeep = new MeepMeep(800);

            RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                    // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                    .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                    .build();

            myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(0, 0, 0))
                    .lineToX(30)
                    .turn(Math.toRadians(90))
                    .lineToY(30)
                    .turn(Math.toRadians(90))
                    .lineToX(0)
                    .turn(Math.toRadians(90))
                    .lineToY(0)
                    .turn(Math.toRadians(90))
                    .build());


            meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_OFFICIAL)
                    .setDarkMode(true)
                    .setBackgroundAlpha(0.95f)
                    .addEntity(myBot)
                    .start();
        }
    }

    static void run_Score_Park_Test (){

        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .build();
        double offset = 90;
        Pose2d beginPose = new Pose2d(-38.0, -61.0, toRadians(offset+2.0));
        Pose2d PoseAutonScore = new Pose2d(-56-2,-67, toRadians(offset+2.0));
        Pose2d PoseExtakeSample = new Pose2d(-43.0, -44, toRadians(offset+2.0));
        Pose2d PoseScoreFirstSample = new Pose2d(-53-3-3, -61, toRadians(offset));
        Pose2d PoseIntakeFirstSample = new Pose2d(-53,-61, toRadians(offset+2.0));
        Pose2d AngleFirstPickup = new Pose2d(-46+5+7+3.5+2,-44+3.5-13-2+20,toRadians(offset+30.0));
        Pose2d PoseIntakeSecondSample = new Pose2d(AngleFirstPickup.position.plus(new Vector2d(-10, 0)), AngleFirstPickup.heading);;

        robot = new RobotShim();
        drive = myBot.getDrive();
        //MecanumDrive drive = new MecanumDrive(hardwareMap, beginPose);
        //Pose2d Pose2 = new Pose2d(drive.pose.position.x,drive.pose.position.y,drive.pose.heading.imag);
        // Initialize the robot with the hardwareMap
        //robot = new Robot(hardwareMap);

        Action FirstScore = drive.actionBuilder(beginPose)
                .setTangent(offset+2.0)
                .splineToLinearHeading(PoseAutonScore, Math.toRadians(offset+2.0))
                //new Pose2d(-56-2,-67,Math.toRadians(offset+2.0)),Math.toRadians(offset+2.0)
                .build();


        Action IntakeFirstSample = drive.actionBuilder(PoseAutonScore)
                .strafeToLinearHeading(AngleFirstPickup.position,AngleFirstPickup.heading)
                .build();

        Action ScoreSecondSample = drive.actionBuilder(AngleFirstPickup)
                .strafeToLinearHeading(PoseScoreFirstSample.position, PoseScoreFirstSample.heading)
                //new Pose2d(-53-3-3,-61,Math.toRadians(offset+2.0)),Math.toRadians(offset+2.0)
                .build();

        Action IntakeSecondSample = drive.actionBuilder(PoseScoreFirstSample)
                .setTangent(offset+2.0)
                .splineToLinearHeading(PoseIntakeSecondSample, PoseIntakeSecondSample.heading)
                //new Pose2d(-46+5,-44+3.5,Math.toRadians(offset+30)),Math.toRadians(offset+2.0), new TranslationalVelConstraint(20.0)
                .build();

        Action ScoreThirdSample = drive.actionBuilder(PoseIntakeSecondSample)
                .splineToConstantHeading(new Vector2d(-53-3-3,-61),Math.toRadians(offset+2.0), new TranslationalVelConstraint(30.0))
                .build();




        myBot.runAction(
                new SequentialAction(
                        robot.lift.liftScoring(),
                        FirstScore,
                        //robot.arm.armScoring(),
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
//                        robot.intake.intakeSleep(),
//                        robot.wrist.wristPickup(),
//                        robot.arm.armResting(),
//                        robot.intake.intakeStop(),
//                        robot.lift.liftScoring(),
                        ScoreSecondSample,
//                        robot.arm.armScoring(),
//                        robot.wrist.wristScoring(),
//                        robot.intake.intakeSleep(200000.0),
//                        robot.intake.intakeOut(),
//                        robot.intake.intakeStop(),
//                        robot.arm.armResting(),
//                        robot.wrist.wristPickup(),
//                        robot.lift.liftTravel(),
//                        robot.arm.armPickUp(),
//                        robot.wrist.wristPickup(),
//                        robot.intake.intakeIn(),
//                        IntakeSecondSample,
//                        robot.intake.intakeSleep(),
//                        robot.intake.intakeStop(),
//                        robot.arm.armResting(),
//                        robot.wrist.wristScoring(),
//                        robot.intake.intakeSleep(),
                       robot.intake.intakeSleep()

                )
        );

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_OFFICIAL)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}