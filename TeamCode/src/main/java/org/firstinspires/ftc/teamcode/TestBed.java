package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class TestBed extends LinearOpMode {
    Pushbot2021 robot = new Pushbot2021();
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
            robot.intake.setPower(gamepad2.left_stick_y);
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

            if (gamepad1.right_bumper == true){
                //turn turret to right
                robot.turret.setPower(1);
            }
            if (gamepad1.left_bumper == true){
                //turn turret to left
                robot.turret.setPower(-1);
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
        }

    }
}
