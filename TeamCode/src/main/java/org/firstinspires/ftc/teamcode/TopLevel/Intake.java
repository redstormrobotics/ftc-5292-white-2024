package org.firstinspires.ftc.teamcode.TopLevel;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Intake {


    private CRServo bottomroller;
    private CRServo toproller;


    public Intake(HardwareMap hardwareMap){
        toproller = hardwareMap.get(CRServo.class,"upper roller");
        bottomroller = hardwareMap.get(CRServo.class,"lower roller");

    }

    public class IntakeInit implements Action{
        @Override
        public boolean run(@NonNull TelemetryPacket packet){
            bottomroller.setDirection(CRServo.Direction.REVERSE);
            toproller.setDirection(CRServo.Direction.FORWARD);
            toproller.setPower(0);
            bottomroller.setPower(0);

            return false;
        }
    }
    public Action intakeInit() { return new IntakeInit();}


    public class IntakeIn implements Action {
        public boolean init = false;
        double counter = 0;
        @Override
        public boolean run(@NonNull TelemetryPacket packet){
            toproller.setPower(1);
            bottomroller.setPower(1);
            return false;
//            if(!init) {
//
//                init = true;
//            }
//
//
//            if (counter < 400000){
//                counter = counter + 1;
//                return true;
//            }
//            else{
//
//            }
        }
    }
    public Action intakeIn(){
        return new IntakeIn();
    }
    public class IntakeOut implements Action {
        public boolean init = false;
        double counter = 0;
        @Override

        public boolean run(@NonNull TelemetryPacket packet){
            if(!init){
                toproller.setPower(-1);
                bottomroller.setPower(-1);
                init = true;

            }
            if (counter < 50000){
                counter = counter + 1;
                return true;
            }
            else{

                init =false;
                return false;
            }
        }
    }
    public Action intakeOut(){
        return new IntakeOut();
    }


    public class IntakeStop implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet){
            toproller.setPower(0);
            bottomroller.setPower(0);

            return false;
        }
    }
    public Action intakeStop(){
        return new IntakeStop();
    }


    public class IntakeSleep implements Action {
        public boolean init = false;
        double counter;
        double wait;
        public IntakeSleep(){
            counter = 0;
            wait = 200000;
        }
        public IntakeSleep(double wait){
            counter = 0;
            this.wait = wait;
        }
        @Override
        public boolean run(@NonNull TelemetryPacket packet){
            if(!init){
                init = true;
            }
            if (counter < wait){
                counter = counter + 1;
                return true;
            }
            else{
                init = false;
                return false;
            }
        }
    }
    public Action intakeSleep(){
        return new IntakeSleep();
    }
    public Action intakeSleep(double wait){
        return new IntakeSleep(wait);
    }
    public class IntakeSleepLess implements Action {
        public boolean init = false;
        double counter = 0;
        @Override

        public boolean run(@NonNull TelemetryPacket packet){
            if(!init){
                init = true;
            }
            if (counter < 100000){
                counter = counter + 1;
                return true;
            }
            else{
                init = false;
                return false;
            }
        }
    }
    public Action intakeSleepLess(){
        return new IntakeSleepLess();
    }
}
