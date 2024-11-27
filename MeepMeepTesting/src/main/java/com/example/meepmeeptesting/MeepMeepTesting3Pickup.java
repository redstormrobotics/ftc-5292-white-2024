package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TranslationalVelConstraint;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.DriveShim;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class        MeepMeepTesting3Pickup {
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
                .setConstraints(10, 60, Math.toRadians(30), Math.toRadians(30), 15)
                .build();

        // Initialize the robot with the hardwareMap
        robot = new RobotShim();
        drive = myBot.getDrive();



        myBot.runAction(getAction());

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_OFFICIAL)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }

    static
    private SequentialAction getAction() {

        double offset = 90.0;
        Pose2d beginPose = new Pose2d(-38.0, -61.0, Math.toRadians(offset+2.0));
        //Pose2d intakeFirstSample = new Pose2d(20, -6, 50);
        Pose2d intakeFirstSample = new Pose2d(beginPose.position.plus(new Vector2d(6.0,20.0)), Math.toRadians(offset+30.0));
        //Pose2d scorePosition = new Pose2d(0, 3, 0);
        Pose2d scorePositionP1 = new Pose2d(beginPose.position.plus(new Vector2d(-4.0, 0.0)), Math.toRadians(offset));
        Pose2d scorePositionP2 = new Pose2d(beginPose.position.plus(new Vector2d(-10.0, 0.0)), Math.toRadians(offset));
        //Pose2d intakeSecondSample = new Pose2d(16, 13, 0);
        Pose2d intakeSecondSample = new Pose2d(intakeFirstSample.position.plus(new Vector2d(-10.0, 0.0)), Math.toRadians(offset+30.0));
        //Pose2d Pose2 = new Pose2d(drive.pose.position.x,drive.pose.position.y,drive.pose.heading.image);

        Action driveBeginToIntakeFirst = drive.actionBuilder(beginPose)
                .splineToLinearHeading(intakeFirstSample,intakeFirstSample.heading)
                .build();

        Action driveIntakeFirstToScoreP1 = drive.actionBuilder(intakeFirstSample)
                .splineToLinearHeading(scorePositionP1,scorePositionP1.heading)
                .build();
        Action driveScoreP1ToScoreP2 = drive.actionBuilder(scorePositionP1)
                .splineToLinearHeading(scorePositionP2,scorePositionP2.heading)
                .build();

        Action driveScoreP2ToScoreP1 = drive.actionBuilder(scorePositionP2)
                .splineToLinearHeading(scorePositionP1,scorePositionP1.heading)
                .build();

        Action driveScoreP2ToIntakeSecond = drive.actionBuilder(scorePositionP2)
                .splineToLinearHeading(intakeSecondSample,intakeSecondSample.heading)
                .build();

        Action driveIntakeSecondToScoreP1 = drive.actionBuilder(intakeSecondSample)
                .splineToLinearHeading(scorePositionP1, scorePositionP1.heading)
                .build();

        return new SequentialAction(
                new com.acmerobotics.roadrunner.SleepAction(2.0),
                robot.lift.liftHome(),
                robot.wrist.wristPickup(),
                robot.arm.armPickUp(),
                robot.intake.intakeIn(),
                driveBeginToIntakeFirst,
                robot.intake.intakeSleep(),
                robot.intake.intakeStop(),
                robot.arm.armResting(),
                robot.wrist.wristPickup(),
                robot.intake.intakeIn(),
                driveIntakeFirstToScoreP1,
                driveScoreP1ToScoreP2,
                robot.intake.intakeOut(),
                robot.intake.intakeStop(),
                robot.wrist.wristPickup(),
                driveScoreP2ToScoreP1,
                robot.arm.armPickUp(),
                robot.intake.intakeIn(),
                driveScoreP2ToIntakeSecond,
                robot.intake.intakeSleep(),
                robot.intake.intakeStop(),
                robot.arm.armResting(),
                driveIntakeSecondToScoreP1,
                driveScoreP1ToScoreP2,
                robot.intake.intakeOut(),
                robot.intake.intakeStop(),
                driveScoreP2ToScoreP1
        );
    }
}