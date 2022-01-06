package org.firstinspires.ftc.teamcode.BEAST_2021;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.BEAST_2021.Pushbot2021;

@TeleOp

public class testTankDrive extends LinearOpMode {
    Pushbot2021 robot = new Pushbot2021();

    public void runOpMode() {
        robot.init(hardwareMap);

        waitForStart();

        while (opModeIsActive()) {
            robot.frontRight.setPower(gamepad1.right_stick_y);
            robot.backRight.setPower(gamepad1.right_stick_y);
            robot.frontLeft.setPower(gamepad1.left_stick_y);
            robot.backLeft.setPower(gamepad1.left_stick_y);


            robot.slide.setPower(gamepad2.left_stick_y / 4);

            if (gamepad1.a) {
                robot.duckSpinner.setPower(.7);
            } else if (gamepad1.b) {
                robot.duckSpinner.setPower(-.7);
            } else {
                robot.duckSpinner.setPower(0);
            }

            if (gamepad2.right_bumper) {
                //turn turret to right
                robot.turret.setPower(-1);
            }

            if (gamepad2.left_bumper) {
                //turn turret to left
                robot.turret.setPower(1);
            }

            if (gamepad2.left_trigger != 0) {
                //close grabber
                robot.grabber.setPosition(1);
            } else if (gamepad2.right_trigger != 0) {
                //Open grabber
                robot.grabber.setPosition(0);
            } /*else {
                //Stop grabber
                robot.grabber.setPosition(.5);
            }*/

            if (gamepad2.y) {
                //linkage open
                robot.linkage.setPosition(0);
            }

            if (gamepad2.x) {
                //linkage closed - counter clockwise to 1
                robot.linkage.setPosition(.53);
            }

            if (gamepad2.a) {
                robot.pivot.setPosition(0);
            }

            if (gamepad2.b) {
                robot.pivot.setPosition(.2);
            }

            while (robot.slideSensor.isPressed()) {
                robot.slide.setPower(0);
                sleep(500);
                robot.slide.setPower(Math.abs(gamepad2.left_stick_y));
            }
        }
    }
}