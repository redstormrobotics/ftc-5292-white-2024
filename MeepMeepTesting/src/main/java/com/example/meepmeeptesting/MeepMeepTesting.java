package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TranslationalVelConstraint;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
    public static void main(String[] args) {
        //run_demo();
        run_Score_Park_Test();
    }

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
        Pose2d beginPose = new Pose2d(0, 0, 0);
        Pose2d Pose2 = new Pose2d(-20, -14, Math.toRadians(90));
        Pose2d Pose3 = new Pose2d(-28, -14, Math.toRadians(90));
        //MecanumDrive drive = new MecanumDrive(hardwareMap, beginPose);
        //Pose2d Pose2 = new Pose2d(drive.pose.position.x,drive.pose.position.y,drive.pose.heading.imag);
        // Initialize the robot with the hardwareMap
        //robot = new Robot(hardwareMap);

        /*robot.intake.intakeInit();
        Actions.runBlocking(robot.intake.intakeInit());
        robot.arm.armInit();
        Actions.runBlocking(robot.arm.armInit());
        robot.wrist.wristInit();
        robot.lift.liftInit();
        Actions.runBlocking(robot.lift.liftInit());
        telemetry.addData("Test",8);
        Actions.runBlocking(robot.wrist.wristInit());
        telemetry.update();
        waitForStart();*/
        Action traj1;


        traj1 = myBot.getDrive().actionBuilder(beginPose)
                .splineTo(new Vector2d(-20,20),Math.toRadians(90))
                //.splineTo(new Vector2d(-20,-6),Math.toRadians(90))
                .splineToConstantHeading(new Vector2d(-20,-14),Math.toRadians(90))
                //.splineToLinearHeading(new Pose2d(-35, 10, 0), Math.toRadians(90))
                .build();

        Action Traj2 = myBot.getDrive().actionBuilder(Pose2)
                .splineTo(new Vector2d(30,20),Math.toRadians(0), new TranslationalVelConstraint(30.0))
                .splineTo(new Vector2d( 60,20),Math.toRadians(0), new TranslationalVelConstraint(30.0))
                .splineTo(new Vector2d(86,0),Math.toRadians(0),  new TranslationalVelConstraint(30.0))

                .build();

        Action Traj3 = myBot.getDrive().actionBuilder(Pose2)
                .splineToConstantHeading(new Vector2d(-28,-14),Math.toRadians(90))
                .build();

        Action Traj4 = myBot.getDrive().actionBuilder(Pose3)
                .splineToConstantHeading(new Vector2d(-20,-14),Math.toRadians(90))
                .build();

        

        myBot.runAction(
                new SequentialAction(
                        traj1,
                        //robot.lift.liftScoring(),
                        //robot.arm.armScoring(),
                        //robot.wrist.wristScoring(),
                        Traj3,
                        //robot.intake.intakeOut(),
                        //robot.intake.intakeStop(),
                        Traj4,
                        //robot.arm.armResting(),
                        //robot.wrist.wristPickup(),
                        //robot.lift.liftHome(),
                        //robot.wrist.wristScoring(),
                        Traj2
                )
        );

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_OFFICIAL)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}