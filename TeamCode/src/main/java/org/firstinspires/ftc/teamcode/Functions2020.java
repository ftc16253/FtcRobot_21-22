package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

class Functions2020 {
    PushBot2020 rob = new PushBot2020();

    private double kP = 9000000.0;
    private double kI = 0.0;
    private double kD = 10000.0;

    private double integral = 0.0;
    private double derivative = 0.0;

    private double motorOut = 0.0;
    private final double fTarget = 3.0e-6;  //1.2e-6      2.5e-6
    private double fVelocity = 0.0;
    private double fError = 0.0;
    private double fLastError = 0.0;

    private int fEncoder = 0;
    private int fLastEncoder = 0;

    private long fVelocityTime = 0;
    private long fLastVelocityTime = 0;

    public DcMotor frontLeft, frontRight, backLeft, backRight;
    double diameter = 4;
    double circumference = diameter * 3.14;
    int tetrixEncoderTics = 1440;
    //int andyMarkEncoderTics = 515;
    int andyMarkEncoderTics = 660;

    public void init(HardwareMap ahwMap) {
        rob.init(ahwMap, false);

        frontLeft = rob.frontLeft;
        frontRight = rob.frontRight;
        //backLeft = rob.backLeft;
        //backRight = rob.backRight;
    }

    public void turnLeft(double degrees, double power) {

        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        //backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        //backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        double turnCircumference = 15 * 3.14;
        double totalRotations = turnCircumference / 360 * degrees;
        int rotationDistanceofWheel = (int) (andyMarkEncoderTics * totalRotations);

        /*frontLeft.setTargetPosition((int) (andyMarkEncoderTics / 360 * degrees));
        frontRight.setTargetPosition((int) (-andyMarkEncoderTics / 360 * degrees));
*/


        boolean runRobot = true;
        while (runRobot) {
            if (Math.abs(frontRight.getCurrentPosition()) > Math.abs(rotationDistanceofWheel)) {
                frontLeft.setPower(0);
                //backLeft.setPower(0);
                frontRight.setPower(0);
                //backRight.setPower(0);
                runRobot = false;
            } else {
                frontLeft.setPower(power);
                frontRight.setPower(-power);
                //backLeft.setPower(power);
                //backRight.setPower(-power);
            }
        }


    }

    public void turnRight(double degrees, double power) {

        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        //backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        //backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        double turnCircumference = 15 * 3.14;
        double totalRotations = turnCircumference / 360 * degrees;
        int rotationDistanceofWheel = (int) (andyMarkEncoderTics * totalRotations);

        boolean runRobot = true;
        while (runRobot) {
            if (Math.abs(frontRight.getCurrentPosition()) > Math.abs(rotationDistanceofWheel)) {
                frontLeft.setPower(0);
                //backLeft.setPower(0);
                frontRight.setPower(0);
                //backRight.setPower(0);
                runRobot = false;
            } else {
                frontLeft.setPower(-power);
                frontRight.setPower(power);
                //backLeft.setPower(-power);
                //backRight.setPower(power);
            }
        }

    }

    public void MoveForwardInch(double distance, double power) {

        double totalRotations = distance / circumference;
        int rotationDistanceofWheel = (int) (andyMarkEncoderTics * totalRotations);

        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        while (true) {
            if (Math.abs(frontRight.getCurrentPosition()) > Math.abs(rotationDistanceofWheel)) {
                frontLeft.setPower(0);
                backLeft.setPower(0);
                frontRight.setPower(0);
                backRight.setPower(0);
                break;
            } else {
                if (distance > 0) {
                    //frontLeft.setPower(-power - .05);
                    frontLeft.setPower(-power);
                    frontRight.setPower(-power + .065);
                    backRight.setPower(-power + .065);
                    //backLeft.setPower(-power - .05);
                    backLeft.setPower(-power);
                } else if (distance < 0) {
                    //frontLeft.setPower(power + .05);
                    frontLeft.setPower(power);
                    frontRight.setPower(power - .065);
                    backRight.setPower(power - .065);
                    //backLeft.setPower(power + .05);
                    backLeft.setPower(power);
                }
            }
        }
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

    }

    public void DriveAndShoot() {
        calculatePID(1);
        PIDloopDrive2(26, -.5);
        //PIDloopDrive2(20, -.6);
        //PIDloopDrive2(1, -.6);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
        }

        frontLeft.setPower(-0.3);
        try {
            Thread.sleep(385);
        } catch (InterruptedException e) {
        }
        frontLeft.setPower(0);

        //turnRight(0.1,.3);
        //turnLeft(.141,-.7);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
        }
        frontRight.setPower(0);
        //turnLeft(1.75,.7);
        //turnRight(.25, .2);
        //calculatePID(1);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
        }
        rob.feeder.setPower(1);
        try {
            Thread.sleep(600);
        } catch (InterruptedException e) {
        }
        rob.feeder.setPower(0);
        //calculatePID(.9);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        rob.feeder.setPower(1);
        rob.intake.setPower(1);
        try {
            Thread.sleep(750);
        } catch (InterruptedException e) {
        }
        rob.feeder.setPower(0);
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
        }
        rob.feeder.setPower(1);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {

        }
        calculatePID(0);
        rob.feeder.setPower(0);
        rob.intake.setPower(0);
        turnLeft(.35, .7);
    }

    public void DriveB() {
        MoveForwardInch(-92, 0.3);
    }

    public void CameraRings() {

    }

    public void PIDloopDrive(double distance, double power) {
        if (frontLeft.getCurrentPosition() != distance) {
            while (frontRight.getCurrentPosition() != distance) {
                if (frontRight.getCurrentPosition() - 3 > frontLeft.getCurrentPosition()) {
                    while (frontRight.getCurrentPosition() != frontLeft.getCurrentPosition()) {
                        frontRight.setPower(power - .05);
                        frontLeft.setPower(power);
                        //backRight.setPower(power - .05);
                        //backLeft.setPower(power);
                    }
                } else if (frontRight.getCurrentPosition() + 3 < frontLeft.getCurrentPosition()) {
                    while (frontRight.getCurrentPosition() != frontLeft.getCurrentPosition()) {
                        frontRight.setPower(power);
                        frontLeft.setPower(power - .05);
                        //backRight.setPower(power);
                        //backLeft.setPower(power - .05);
                    }
                } else {
                    frontRight.setPower(power);
                    frontLeft.setPower(power);
                    //backRight.setPower(power);
                    //backLeft.setPower(power);
                }
            }
        }
    }

    public void PIDloopDrive2(double distance, double power) {
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        //backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        //backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        double totalRotations = distance / circumference;
        int TicDistance = (int) (andyMarkEncoderTics * totalRotations);

        while (Math.abs(frontRight.getCurrentPosition()) < TicDistance) {
            frontRight.setPower(power+.0175);
            //frontLeft.setPower(power-0.05);
            frontLeft.setPower(power);
            //backRight.setPower(power);
            //backLeft.setPower(power-0.05);
            //backLeft.setPower(power);
        }

        frontRight.setPower(0);
        frontLeft.setPower(0);
        //backRight.setPower(0);
        //backLeft.setPower(0);
        //backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void calculatePID(double shooter_power) {
        fVelocityTime = System.nanoTime();
        fEncoder = rob.shooterFront.getCurrentPosition();
        fVelocity = (double) (fEncoder - fLastEncoder) / (fVelocityTime - fLastVelocityTime);
        fError = fTarget - fVelocity;

        integral += fError;
        if (fError == 0) {
            integral = 0;
        }

        if (Math.abs(fError) > 50) {
            integral = 0;
        }

        derivative = fError - fLastError;

        fLastError = fError;
        fLastEncoder = fEncoder;
        fLastVelocityTime = fVelocityTime;

        motorOut = (kP * fError) + (kI * integral) + (kD * derivative);

        motorOut = Range.clip(motorOut, shooter_power-.1, shooter_power);

        //Log.wtf(TAG, String.valueOf(fError));

        setFPower(motorOut);
    }

    private void setFPower(double power) {
        rob.shooterFront.setPower(power);
    }

}

