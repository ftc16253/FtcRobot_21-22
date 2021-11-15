package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

@TeleOp

public class Drive2020 extends LinearOpMode {
    public DcMotor frontLeft, backLeft, frontRight, backRight, intake, slide, turret;//, shooterBack;
    public Servo grabber, linkage;


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

    Pushbot2021 robot = new Pushbot2021();
    private void calcVelocity() {
        fVelocityTime = System.nanoTime();
        fEncoder = turret.getCurrentPosition();
        fVelocity = (double) (fEncoder - fLastEncoder) / (fVelocityTime - fLastVelocityTime);

        fLastEncoder = fEncoder;
        fLastVelocityTime = fVelocityTime;
    }

    private void calculatePID(double shooter_power) {
        fVelocityTime = System.nanoTime();
        fEncoder = turret.getCurrentPosition();
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
        turret.setPower(power);
    }

    public void runOpMode() {
        robot.init(hardwareMap, false);

        frontLeft = robot.frontLeft;
        frontRight = robot.frontRight;
        backLeft = robot.backLeft;
        backRight = robot.backRight;
        intake = robot.intake;
        slide = robot.slide;
        linkage = robot.linkage;
        turret = robot.turret;
        ///shooterBack = robot.shooterBack;

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            double drive = gamepad1.left_stick_y;
            double turn = gamepad1.right_stick_x;

            // Drive forward or backward
            if (drive != 0){
                frontLeft.setPower(drive-.1);
                frontRight.setPower(drive - .165);
                //backRight.setPower(drive - .115);
                //backLeft.setPower(drive-.05);
            }

            if (turn != 0){
                //Turn left or right
                frontLeft.setPower(-turn);
                frontRight.setPower(turn);
                //backLeft.setPower(-turn);
                //backRight.setPower(turn);
            }

            if (drive == 0 && turn == 0) {
                frontLeft.setPower(0);
                frontRight.setPower(0);
                //backRight.setPower(0);
                //backLeft.setPower(0);
            }

            //Turn left or right

            intake.setPower(gamepad2.left_stick_y);

            if (gamepad2.a == true) {
                //intake.setPower(1);
                slide.setPower(1);
            } else if(gamepad2.b == true){
                slide.setPower(-1);
            } else {
                //intake.setPower(0);
                slide.setPower(0);
            }

            //Right trigger - wobble rotator goes down
            if (gamepad1.a == true){
                telemetry.addData("status","right trigger pressed");
                telemetry.update();
                linkage.setPosition(0);
            }

            //Left trigger - claw closes and wobble rotator goes up
            if (gamepad1.right_bumper == true) {
                telemetry.addData("status", "Right Bumper pressed");
                telemetry.update();
                //Close claw first
                robot.grabberSetPosition(0);
                sleep(750);
                //This is for the two servos
                linkage.setPosition(.25);
            }

            //left bumper - wobble rotator goes down and claw opens
            if (gamepad1.left_bumper == true){
                //Move two servos down
                linkage.setPosition(.55);
                sleep(500);
                //Open claw
                robot.grabberSetPosition(.6);
            }

            if(gamepad1.b == true){
                linkage.setPosition(.75);
                sleep(250);
                robot.grabberSetPosition(.6);
            }

            //Button A - wobble arm to initial position
            /*if (gamepad1.a == true){
                linkage.setPosition(0);
            }*/

            if (gamepad2.left_trigger != 0) {
                calculatePID(0);
                sleep(100);
                //shooterBack.setPower(1);
            } else if (gamepad2.right_trigger != 0) {
                calculatePID(.95);
                sleep(100);
                //shooterBack.setPower(1);
            }

            if (gamepad2.left_bumper == true){
                calculatePID(0);
                sleep(100);
            }else if (gamepad2.right_bumper == true){
                calculatePID(.8);
                sleep(100);
            }


            telemetry.addData("encoder: ", fEncoder);
            telemetry.update();

        }
        //telemetry.addData("Current Right Motor Power", robot.frontRight.getPower());
    }
}

