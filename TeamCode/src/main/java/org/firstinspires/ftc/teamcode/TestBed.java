package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.checkerframework.checker.units.qual.degrees;

@TeleOp
public class TestBed extends LinearOpMode {
    Pushbot2021 robot = new Pushbot2021();

    double ticToDegree = (537.6*(20.0/7.0))/360;

    boolean test = false;
    double targetDeg = 0;

    public void runOpMode() {
        robot.init(hardwareMap);

/*        robot.frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        robot.backRight = hardwareMap.get(DcMotor.class, "backRight");
        robot.frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        robot.backLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        robot.intake = hardwareMap.get(DcMotor.class, "intake");
        robot.frontRight.setPower(0);
        robot.backRight.setPower(0);
        robot.frontLeft.setPower(0);
        robot.backLeft.setPower(0);
        robot.intake.setPower(0);
        robot.slide.setPower(0);
        robot.duckSpinner.setPower(0);
        robot.turret.setPower(0);
 

        robot.grabber = hardwareMap.get(Servo.class, "grabber");
        robot.linkage = hardwareMap.get(Servo.class, "linkage");
*/


        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            robot.frontRight.setPower(gamepad1.right_stick_y);
            robot.backRight.setPower(-gamepad1.right_stick_y);
            robot.frontLeft.setPower(-gamepad1.left_stick_y);
            robot.backLeft.setPower(-gamepad1.left_stick_y);
            robot.slide.setPower(gamepad2.right_stick_y);

            if (gamepad1.a == true) {
                //ramp up duck spinner
                //robot.duckSpinner.setPower(.2);
                //robot.duckSpinner.setPower(.4);
                //robot.duckSpinner.setPower(.6);
                robot.duckSpinner.setPower(.7);
                //robot.duckSpinner.setPower(1);
            } else {
                robot.duckSpinner.setPower(0);
            }

            if (gamepad1.right_bumper == true){
                //test MoveForwardInch
                robot.MoveForwardInch(3, 1);
            }

            if (gamepad1.a == true){  
                //turn turret to right
                test = true;
                targetDeg = 10.0;
            }
            if (gamepad1.b == true){
                //turn turret to left
                test = true;
                targetDeg = 270.0;
            }

            if (gamepad2.left_bumper == true){
                //close grabber
                robot.grabber.setPosition(1);
            }
            else if (gamepad2.right_bumper == true)
            {
                //Open grabber
                robot.grabber.setPosition(0);
            }
            else
            {
                //Stop grabber
                robot.grabber.setPosition(.5);
            }

            if (gamepad2.y == true){
                //linkage open
                robot.linkage.setPosition(0);

            }
            if (gamepad2.a == true){
                robot.pivot.setPosition(0);
            }
            if (gamepad2.b == true){
                robot.pivot.setPosition(.35);
            }

            if (gamepad2.x == true){
                //linkage closed - counter clockwise to 1
                robot.linkage.setPosition(.6);
            }
            if (gamepad1.x == true){
                test = true;
                targetDeg = 90;
            }

            if (test) {
                moveTurret2(targetDeg, 0.25);
            }

            double currentDegree = robot.turret.getCurrentPosition() / ticToDegree;

            telemetry.addData("Degrees: ", currentDegree);
            telemetry.update();
           /* if (robot.slideSensor.getState() == true){
                robot.backRight.setPower(0);
            }
            else if (robot.slideSensor.getState() == false){
                robot.backRight.setPower(1);
            }*/
        }
    }

    public double angleWrap(double currentAngle) {
        while (currentAngle < 0) {
            currentAngle += 360;
        }
        while (currentAngle > 360) {
            currentAngle -= 360;
        }

        return currentAngle;
    }

    public void moveTurret2(double degrees, double power) {
        //double motorTics = (ticToDegree * degrees);
        int i = 0;
        //double startingPosition = robot.turret.getCurrentPosition();

        double currentDegree = angleWrap(robot.turret.getCurrentPosition() / ticToDegree);

        double outputPower;

        if (Math.abs(degrees - currentDegree) < 30.0) {
            outputPower = 0.05;
        } else {
            outputPower = power;
        }

        if (Math.abs(degrees - currentDegree) < 2.0){
            robot.turret.setPower(0);
            test = false;
        } else if (currentDegree < degrees) {
            robot.turret.setPower(outputPower);
        } else if (currentDegree > degrees){
            robot.turret.setPower(-outputPower);
        }

        //telemetry.addData("starting Position: ", startingPosition);
        telemetry.addData("Motor position: ", robot.turret.getCurrentPosition());
        //telemetry.addData("Total Tics: ", motorTics);
        telemetry.update();
    }

    public void moveTurret(double degrees, double power){
        double ticToDegree = (537.6*(20.0/7.0))/360;
        double motorTics = (ticToDegree * degrees);
        int i = 0;
        double startingPosition = robot.turret.getCurrentPosition();

        while (true) {
            if (Math.abs(robot.turret.getCurrentPosition()) > (Math.abs(motorTics)-20) && Math.abs(robot.turret.getCurrentPosition()) < (Math.abs(motorTics)+20)){
                robot.turret.setPower(0);
                sleep(200);
                return;
            } else if (Math.abs(robot.turret.getCurrentPosition()) < Math.abs(motorTics)) {
                robot.turret.setPower(power);
            } else if (Math.abs(robot.turret.getCurrentPosition()) > Math.abs(motorTics)){
                robot.turret.setPower(-power);
            }

            telemetry.addData("starting Position: ", startingPosition);
            telemetry.addData("Motor position: ", robot.turret.getCurrentPosition());
            telemetry.addData("Total Tics: ", motorTics);
            telemetry.update();
        }
    }
}
