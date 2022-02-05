package org.firstinspires.ftc.teamcode.BEAST_2021;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.BEAST_2021.Pushbot2021;

@TeleOp

public class TestBed extends LinearOpMode {
    Pushbot2021 robot = new Pushbot2021();

    public void runOpMode() {
        robot.init(hardwareMap);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            robot.frontRight.setPower(gamepad1.right_stick_y);
            robot.backRight.setPower(-gamepad1.right_stick_y);
            robot.frontLeft.setPower(-gamepad1.left_stick_y);
            robot.backLeft.setPower(-gamepad1.left_stick_y);
            robot.pivot.setPower(gamepad2.right_stick_y);
            robot.turret.setPower(gamepad2.left_stick_x / 8);



            if (gamepad1.left_trigger != 0) {
                robot.bucket.setPosition(0);
            }
            if (gamepad1.right_trigger != 0) {
                robot.bucket.setPosition(1);
            }

            if (gamepad1.a == true) {
                robot.duckSpinner.setPower(.5);
            } else if (gamepad1.b == true){
                robot.duckSpinner.setPower(.75);
            } else if (gamepad1.x){
                robot.duckSpinner.setPower(1);
            } else if (gamepad1.dpad_right){
                robot.duckSpinner.setPower(-.5);
            } else if (gamepad1.dpad_left){
                robot.duckSpinner.setPower(-1);
            } else if (gamepad1.dpad_up){
                robot.duckSpinner.setPower(-.75);
            } else{
                robot.duckSpinner.setPower(0);
            }
            if (gamepad2.right_trigger != 0){
                robot.intake.setPower(gamepad2.right_trigger);
            }
            if (gamepad2.left_trigger != 0){
                robot.intake.setPower(gamepad2.left_trigger / 2);
            }
            if (gamepad2.y == true){
                //linkage open
                robot.linkage.setPosition(0);

            }
            if (gamepad2.a == true){
                robot.pivotMove = true;
                robot.movePivot(0);
            }
            if (gamepad2.b == true){
                robot.pivotMove = true;
                robot.movePivot(7);
            }

            if (gamepad2.x == true){
                //linkage closed - counter clockwise to 1
                robot.linkage.setPosition(1);
            }

            if (robot.turretMove) {
                robot.moveTurret(robot.targetDeg, 0.25);
            }

            double currentDegree = robot.turret.getCurrentPosition() / robot.ticToDegree;

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

}
