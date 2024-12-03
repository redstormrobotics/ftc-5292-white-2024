package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TranslationalVelConstraint;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

import sun.font.TrueTypeFont;

public class MeepMeepTesting2 {
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
        double offset = 90;
        Pose2d beginPose = new Pose2d(-38.0, -61.0, Math.toRadians(offset+2.0));
        Pose2d PoseAutonScore = new Pose2d(-53,-61, Math.toRadians(offset+2.0));
        Pose2d PoseExtakeSample = new Pose2d(-53+12-7, -61+14, Math.toRadians(offset+2.0));
        Pose2d PoseIntakeFirstSample = new Pose2d(-53-4,-61+14, Math.toRadians(offset+2.0));
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
                .setTangent(offset+2.0)
                .splineToLinearHeading(new Pose2d(-53,-61,Math.toRadians(offset+2.0)),Math.toRadians(offset+2.0))
                .build();

        Action Traj2 = myBot.getDrive().actionBuilder(PoseAutonScore)
//              //constant heading
//                .splineToConstantHeading(new Vector2d(-53+12,-61),Math.toRadians(offset+2.0))
//                .splineToConstantHeading(new Vector2d(-53+12-7, -61+14),Math.toRadians(offset+2.0))
                //linear heading
                .setTangent(offset+2.0)
                .splineToLinearHeading(new Pose2d(-53+12,-61+20,Math.toRadians(offset+2.0)),Math.toRadians(offset+2.0))
                .build();
//
        Action Traj3 = myBot.getDrive().actionBuilder(PoseExtakeSample)
                //constant
                //.splineToConstantHeading(new Vector2d(-53,-61),Math.toRadians(offset+2.0))
                .setReversed(true)
                .setTangent(offset+30)
                .splineToLinearHeading(new Pose2d(-53,-61,Math.toRadians(offset+2.0)),Math.toRadians(offset+2.0))
                .build();
//
        Action Traj4 = myBot.getDrive().actionBuilder(PoseAutonScore)
                .setTangent(offset+2.0)
                .splineToLinearHeading(new Pose2d(-53+15,-61+14,Math.toRadians(offset+30)),Math.toRadians(offset+2.0))
//                .splineToConstantHeading(new Vector2d(-53-4,-61+14),Math.toRadians(offset+2.0))
                .build();

//        Action Traj5 = myBot.getDrive().actionBuilder(PoseIntakeFirstSample)
//
//                .setTangent(Math.toRadians(offset+2.0))
//                .splineToLinearHeading(new Pose2d(50, -32, Math.toRadians(-90)), Math.toRadians(offset+2.0))
////                .splineTo(new Vector2d(-40,-40),Math.toRadians(0))
////                .splineTo(new Vector2d(50,-32),Math.toRadians(0))
//
////                .splineToConstantHeading(new Vector2d(0,30),Math.toRadians(90))
//                .build();



        myBot.runAction(
                new SequentialAction(
                        traj1,
                        //         robot.lift.liftScoring(),
                        //       robot.arm.armScoring(),
                        //       robot.wrist.wristScoring(),
                        Traj2
                        //       robot.intake.intakeOut(),
                        //      robot.intake.intakeStop(),
                     //  Traj3
                        //         robot.arm.armResting(),
                        //        robot.wrist.wristPickup(),
                        //        robot.lift.liftHome(),
                        //         robot.wrist.wristScoring(),
                       // Traj4
                      //  robot.arm.armPickUp(),
                  //      robot.intake.intakeIn(),
                  //      robot.intake.intakeStop(),
                   //     robot.arm.armResting(),
                      // Traj5
                )
        );

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_OFFICIAL)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}