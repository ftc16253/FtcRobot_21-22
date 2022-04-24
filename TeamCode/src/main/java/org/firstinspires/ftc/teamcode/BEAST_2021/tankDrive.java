package org.firstinspires.ftc.teamcode.BEAST_2021;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.BEAST_2021.Pushbot2021;

@TeleOp

public class tankDrive extends LinearOpMode {
    Pushbot2021 robot = new Pushbot2021();

    public void runOpMode() {
        robot.init(hardwareMap, false);

        waitForStart();

        while (opModeIsActive()) {
            robot.frontRight.setPower(-gamepad1.right_stick_y);
            robot.backRight.setPower(gamepad1.right_stick_y);
            robot.frontLeft.setPower(-gamepad1.left_stick_y);
            robot.backLeft.setPower(gamepad1.left_stick_y);
            robot.pivot.setPower(gamepad2.right_stick_y);
            robot.turret.setPower(-gamepad2.left_stick_x / 8);

            if (gamepad1.a) {
                robot.duckSpinner.setPower(.2);
                sleep(25);
                robot.duckSpinner.setPower(.4);
                sleep(25);
                robot.duckSpinner.setPower(.6);
                sleep(25);
                robot.duckSpinner.setPower(.7);
                while (gamepad1.a){
                    robot.duckSpinner.setPower(.7);
                }
            } else if (gamepad1.b) {
                robot.duckSpinner.setPower(-.2);
                sleep(25);
                robot.duckSpinner.setPower(-.4);
                sleep(25);
                robot.duckSpinner.setPower(-.6);
                sleep(25);
                robot.duckSpinner.setPower(-.7);
                while (gamepad1.a){
                    robot.duckSpinner.setPower(-.7);
                }
            } else {
                robot.duckSpinner.setPower(0);
            }
            /*if (gamepad2.right_stick_y > 0){
                //linkage out and pivot down
                if (robot.linkage.getPosition() != 1) {
                    robot.linkage.setPosition(1);
                    sleep(200);
                }
                while (gamepad2.right_stick_y > 0){
                    robot.pivot.setPower(gamepad2.right_stick_y/2);
                    telemetry.addData("Pivot position: ", robot.pivot.getCurrentPosition());
                    telemetry.update();
                }
            } else if (gamepad2.right_stick_y < 0){
                //linkage in and pivot up
                //robot.linkage.setPosition(0);
                //sleep(200);
                while (gamepad2.right_stick_y < 0){
                    robot.pivot.setPower(gamepad2.right_stick_y/2);
                }
                robot.linkage.setPosition(0);
                sleep(200);
            } else{
                robot.pivot.setPower(0);
                telemetry.addData("Pivot position: ", robot.pivot.getCurrentPosition());
                telemetry.update();
            }*/

            if (gamepad2.dpad_right){
                robot.linkage.setPosition(.5);
            } else if (gamepad2.dpad_down){
                robot.linkage.setPosition(1);
            } else if (gamepad2.dpad_up){
                robot.linkage.setPosition(0.15);
            }
            /*if (gamepad2.y) {
                //linkage in
                robot.linkage.setPosition(1);
            }

            if (gamepad2.x) {
                //linkage out
                robot.linkage.setPosition(0.15);
            }*/
            if (gamepad2.a){
                robot.bucket.setPosition(0);
            }
            if (gamepad2.b){
                robot.bucket.setPosition(1);
            }
            if (gamepad2.right_bumper) {
                robot.intake.setPower(1);
            } else if (gamepad2.left_bumper) {
                robot.intake.setPower(-.4);
            } else {
                robot.intake.setPower(0);
            }
        }
    }
}

