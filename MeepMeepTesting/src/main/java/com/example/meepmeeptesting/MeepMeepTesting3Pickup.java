package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TranslationalVelConstraint;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting3Pickup {
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
                .setConstraints(10, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .build();


        Pose2d beginPose = new Pose2d(0, 0, 0);
        Pose2d intakeFirstSample = new Pose2d(20, -6, 50);
        Pose2d scorePosition = new Pose2d(0, 3, 0);
        Pose2d intakeSecondSample = new Pose2d(16, 13, 0);
        //Pose2d Pose2 = new Pose2d(drive.pose.position.x,drive.pose.position.y,drive.pose.heading.image);
        // Initialize the robot with the hardwareMap

        Action traj1;


        traj1 = myBot.getDrive().actionBuilder(beginPose)
                .splineTo(new Vector2d(20,-6),Math.toRadians(50))
                .build();

        Action Traj2 = myBot.getDrive().actionBuilder(intakeFirstSample)
                .splineTo(new Vector2d(0,3),Math.toRadians(0))
                .build();

        Action Traj3 = myBot.getDrive().actionBuilder(scorePosition)
                .splineTo(new Vector2d(16,13),Math.toRadians(0))
                .build();

        Action Traj4 = myBot.getDrive().actionBuilder(intakeSecondSample)
                .splineToConstantHeading(new Vector2d(0,3),Math.toRadians(0), new TranslationalVelConstraint(30.0))
                .build();

        myBot.runAction(
                        new SequentialAction(
                                //robot.wrist.wristPickup(),
                                //robot.arm.armPickUp(),
                                //robot.intake.intakeIn(),
                                traj1,
                                //robot.intake.intakeSleep(),
                                ///robot.intake.intakeStop(),
                                //robot.arm.armResting(),
                                ///robot.wrist.wristPickup(),
                                //robot.intake.intakeIn(),
                                Traj2,
                                //robot.intake.intakeOut(),
                                //robot.intake.intakeStop(),
                                //robot.wrist.wristPickup(),
                                //robot.arm.armPickUp(),
                                //robot.intake.intakeIn(),
                                Traj3,
                                //robot.intake.intakeSleep(),
                                //robot.intake.intakeStop(),
                                //robot.arm.armResting(),
                                Traj4
                                //robot.intake.intakeOut(),
                                //robot.intake.intakeStop()


                        )
        );

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_OFFICIAL)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}