package org.firstinspires.ftc.teamcode.TopLevel;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Lift {

    private DcMotor lift;
    private DcMotor lift2;

    public Lift(HardwareMap hardwareMap){
        lift = hardwareMap.get(DcMotor.class, "lift");
        lift2 = hardwareMap.get(DcMotor.class, "lift2");
    }

    public class LiftInit implements Action{
        @Override
        public boolean run(@NonNull TelemetryPacket packet){
            lift.setDirection(DcMotorSimple.Direction.FORWARD);
            lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            lift.setTargetPosition(0);
            lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            lift.setPower(0);
            return false;
        }
    }

    public Action liftInit(){ return new LiftInit(); }

    public class LiftInit2 implements Action{
        @Override
        public boolean run(@NonNull TelemetryPacket packet){
            lift2.setDirection(DcMotorSimple.Direction.FORWARD);
            lift2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            lift2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            lift2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            lift2.setTargetPosition(0);
            lift2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            lift2.setPower(0);
            return false;
        }
    }

    public Action liftInit2(){ return new LiftInit(); }

    public class LiftPickUp implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet){
            lift.setTargetPosition(0);
            lift.setPower(0.7);
            lift2.setTargetPosition(0);
            lift2.setPower(0.7);
            // telemetry.addData("lift", lift.getCurrentPosition());
            return false;
        }
    }

    public Action liftPickUp(){
        return new LiftPickUp();
    }

        public class LiftScoring implements Action {
            public boolean inits = false;
            @Override
            public boolean run(@NonNull TelemetryPacket packet){
                if(!inits){
                    lift.setTargetPosition(6300);   // Add 200 more ticks if lift pos is too short
                    lift.setPower(1);
                    lift2.setTargetPosition(6300);  // Add 200 more ticks if lift pos is too short
                    lift2.setPower(1);
                    inits = true;
                }
                if(lift.getCurrentPosition() < 6200){
                    return true;
                }
                else {
                    inits = false;
                    return false;
                }
            }
        }

//    public class LiftScoring implements Action {
//        // public boolean inits = false;
//        @Override
//        public boolean run(@NonNull TelemetryPacket packet){
//            lift.setTargetPosition(6300);   // Add 200 more ticks if lift pos is too short
//            lift.setPower(1);
//            lift2.setTargetPosition(6300);  // Add 200 more ticks if lift pos is too short
//            lift2.setPower(1);
//            //        if(!inits){
//            //
//            //            inits = true;
//            //        }
//            //        if(lift.getCurrentPosition() < 5000){
//            //            return true;
//            //        }
//            //        else {
//            //  inits = false;
//            return false;
//            // }
//        }
//    }

    public Action liftScoring(){ return new LiftScoring(); }

    public class LiftTravel implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet)
        {
            lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            lift.setTargetPosition(0);
            lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            lift.setPower(1.0);
            lift2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            lift2.setTargetPosition(0);
            lift2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            lift2.setPower(1.0);
            return false;
        }
    }

    public Action liftTravel(){ return new LiftTravel(); }

    public class LiftHome implements Action {
        public boolean init = false;
        @Override
        public boolean run(@NonNull TelemetryPacket packet){
            if(!init){
                lift.setTargetPosition(0);
                lift.setPower(.75);
                lift2.setTargetPosition(0);
                lift2.setPower(.75);
                init = true;
            }
            if(lift.getCurrentPosition() > 100){
                return true;
            }
            else {
                init = false;
                return false;
            }
        }
    }

    public Action liftHome(){ return new LiftHome(); }

}
