package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.SleepAction;
import com.acmerobotics.dashboard.canvas.Canvas;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;

public class RobotShim {

    public WristShim wrist;
    public ArmShim arm;
    public IntakeShim intake;

    public LiftShim lift;

    public RobotShim() {
        wrist = new WristShim();
        arm = new ArmShim();
        intake = new IntakeShim();
        lift = new LiftShim();
    }

    public static class WristShim {

        public Action wristInit() {
            return new SleepAction(0.1);
        }
        public Action wristPickup() {
            return new SleepAction(0.1);
        }

        public Action wristScoring() {
            return new SleepAction(0.1);
        }
    }

    public static class ArmShim {

        public Action armInit() {
            return new SleepAction(0.1);
        }
        public Action armPickUp() {
            return new SleepAction(0.1);
        }

        public Action armResting() {
            return new SleepAction(0.1);
        }
    }

    public static class IntakeShim {
        public Action intakeIn() {
            return new SleepAction(0.1);
        }
        public Action intakeSleep() {
            return new SleepAction(0.1);
        }
        public Action intakeStop() {
            return new SleepAction(0.1);
        }
        public Action intakeOut() {
            return new SleepAction(0.1);
        }
    }

    public static class LiftShim {

        public Action liftInit() {
            return new SleepAction(0.1);
        }
        public Action liftInit2() {
            return new SleepAction(0.1);
        }

        public Action liftHome() {
            return new LiftVisual();
        }
        public Action liftPickUp() {
            return new SleepAction(0.1);
        }
        public Action liftScoring() {
            return new SleepAction(0.1);
        }
        public Action liftTravel() {
            return new SleepAction(0.1);
        }

        public static class LiftVisual implements Action {
            public boolean run(TelemetryPacket p) {
                System.out.println("running ");
                return Boolean.FALSE;
            }

            public void preview(Canvas fieldOverlay) {
                System.out.println("Drawing " + fieldOverlay.toString());
            }
        }

    }

}
