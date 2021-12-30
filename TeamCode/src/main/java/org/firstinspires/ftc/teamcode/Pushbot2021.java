package org.firstinspires.ftc.teamcode;

import android.hardware.Sensor;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.checkerframework.checker.units.qual.degrees;

public class Pushbot2021
{
    /* Public OpMode members. */
    public DcMotor frontLeft, frontRight, backRight, backLeft;
    public DcMotor slide, turret, duckSpinner;
    public Servo grabber, linkage, pivot;
    public DigitalChannel slideSensor;
    double spinDiameter = 1;
    double diameter = 3.6;
    double circumference = diameter * 3.14;
    int tetrixEncoderTics = 1440;
    //int andyMarkEncoderTics = 515;
    int andyMarkEncoderTics = 1120;
    public static final String VUFORIA_KEY =
            "Afctxlz/////AAABmSWf4jOsTUHcsOYa/JfaZlRo+3yiPN8cCUH4BDLpIZ8FAt0tEVLJ/mxWUyd7f0gqd+a7JRTMYP9+A9s1nojOs9B1ZGOFsvr84RZnbVN8cGP7RFKNP4Mg0Pr/6vIUmHGFx/jrOrXz/YJXwVXvPpqr1uDm8xpBZOE4j+CtQcKW2Y2zjVWHWRTkmb6ve/R91k3jfjaH4PErbZMcvD7Xy5IesqSet3/pjeUXWSnlHmPwH7fgUcHSkAf0Fj2nLvZ7zmpT8vh9rSKri9XD3A64WBNRO+6+SGH/C/eS3mWLmdi5ZMbSK66WuvNhAPT0SHCzzqAlAf2P6asrrrAuw+aQ0B2HV0mPtGdNPe62djhu5Afa/rL+";

    /* local OpMode members. */

    HardwareMap hwMap           =  null;
    private ElapsedTime period  = new ElapsedTime();

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;

        // Define and Initialize Motors
        frontLeft  = hwMap.get(DcMotor.class, "frontLeft");
        frontRight = hwMap.get(DcMotor.class, "frontRight");
        backLeft  = hwMap.get(DcMotor.class, "backLeft");
        backRight = hwMap.get(DcMotor.class, "backRight");
        slide = hwMap.get(DcMotor.class, "slide");
        turret = hwMap.get(DcMotor.class, "turret");
        duckSpinner = hwMap.get(DcMotor.class, "duckSpinner");

        frontLeft.setDirection(DcMotor.Direction.FORWARD);
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.REVERSE);
        turret.setDirection(DcMotorSimple.Direction.FORWARD);
        slide.setDirection(DcMotorSimple.Direction.FORWARD);
        duckSpinner.setDirection(DcMotorSimple.Direction.FORWARD);


        // Set all motors to zero power
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
        slide.setPower(0);
        turret.setPower(0);
        duckSpinner.setPower(0);



        //Define Servos
        grabber = hwMap.get(Servo.class, "grabber");
        linkage = hwMap.get(Servo.class, "linkage");
        pivot = hwMap.get(Servo.class, "pivot");



        //set servo positions to zero
        linkage.setPosition(.53);
        grabber.setPosition(0);
        pivot.setPosition(0);

        //set sensors
        slideSensor = hwMap.get(DigitalChannel.class, "slideSensor");

        //set the channel to input
        slideSensor.setMode(DigitalChannel.Mode.INPUT);

/*
        //This section makes the motors drive slowly - Don't use BRAKE
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
*/
    }
    public void turn(double degrees, double power) {

        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        double turnCircumference = 14 * 3.14;
        double totalRotations = turnCircumference / 360 * degrees;
        int rotationDistanceofWheel = (int) (andyMarkEncoderTics * totalRotations);

        /*frontLeft.setTargetPosition((int) (andyMarkEncoderTics / 360 * degrees));
        frontRight.setTargetPosition((int) (-andyMarkEncoderTics / 360 * degrees));
*/


        boolean runRobot = true;
        while (runRobot) {
            if (Math.abs(frontRight.getCurrentPosition()) > Math.abs(rotationDistanceofWheel) || Math.abs(frontLeft.getCurrentPosition()) > Math.abs(rotationDistanceofWheel)
                    || Math.abs(backLeft.getCurrentPosition()) > Math.abs(rotationDistanceofWheel) || Math.abs(backRight.getCurrentPosition()) > Math.abs(rotationDistanceofWheel)) {
                frontLeft.setPower(0);
                backLeft.setPower(0);
                frontRight.setPower(0);
                backRight.setPower(0);
                runRobot = false;
            } else {
                frontLeft.setPower(power);
                frontRight.setPower(-power);
                backLeft.setPower(power);
                backRight.setPower(power);
            }
        }

    }
    public void MoveForwardInch(double Distance, double power) {

        double totalRotations = Distance / circumference;
        int rotationDistanceofWheel = (int) (andyMarkEncoderTics * totalRotations);

        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        while (true) {
            if (Math.abs(frontRight.getCurrentPosition()) > Math.abs(rotationDistanceofWheel) || Math.abs(frontLeft.getCurrentPosition()) > Math.abs(rotationDistanceofWheel)
                    || Math.abs(backLeft.getCurrentPosition()) > Math.abs(rotationDistanceofWheel) || Math.abs(backRight.getCurrentPosition()) > Math.abs(rotationDistanceofWheel)) {
                frontLeft.setPower(0);
                backLeft.setPower(0);
                frontRight.setPower(0);
                backRight.setPower(0);
                break;
            } else {
                if (Distance > 0) {
                    frontLeft.setPower(power);
                    frontRight.setPower(power);
                    backRight.setPower(-power);
                    backLeft.setPower(-power);
                } else if (Distance < 0) {
                    frontLeft.setPower(-power);
                    frontRight.setPower(-power);
                    backRight.setPower(power);
                    backLeft.setPower(power);
                }
            }
        }
    }
    public void depositCube(double height, double power){
        double slideCirc = spinDiameter*3.14;
        double Rotations = height/slideCirc;
        double totalTics = andyMarkEncoderTics * Rotations;
        while (slideSensor.getState() == false) {
            //move linear slide down
            slide.setPower(-1);
        }
        //move linear slide to correct level
        if(slide.getCurrentPosition() > totalTics){
            slide.setPower(0);
        } else if (slide.getCurrentPosition() < totalTics){
          slide.setPower(power);
        }

        //move linkage out above shipping hub
        linkage.setPosition(.5);

        //release starting cube
        grabber.setPosition(0);

        //move linkage into robot
        linkage.setPosition(0);

        while (slideSensor.getState() == false) {
            //move linear slide down
            slide.setPower(-power);
        }
        slide.setPower(0);
    }
    public void pickupCube(double degrees){
        double ticToDegree = andyMarkEncoderTics/360;
        double motorTics = (ticToDegree * degrees);
        int totalTics = (int) (motorTics * (22/7));

        turret.setTargetPosition(0);
        turret.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        turret.setTargetPosition(totalTics);
        turret.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        grabber.setPosition(.5);

        linkage.setPosition(.5);

        pivot.setPosition(.5);

        grabber.setPosition(0);

        pivot.setPosition(0);

        linkage.setPosition(0);

        turret.setTargetPosition(0);
        turret.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
}
