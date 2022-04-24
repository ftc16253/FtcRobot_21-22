package org.firstinspires.ftc.teamcode.BEAST_2021;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.BEAST_2021.Pushbot2021;

@TeleOp

public class TestBed extends LinearOpMode {
    Pushbot2021 robot = new Pushbot2021();
    boolean pivotMove = false;
    double andyMark20Tics = 537.6;
    double tictoheight = ((andyMark20Tics*4)/360);

    public void runOpMode() {
        robot.init(hardwareMap, false);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
           /* robot.frontRight.setPower(gamepad1.right_stick_y);
            robot.backRight.setPower(-gamepad1.right_stick_y);
            robot.frontLeft.setPower(-gamepad1.left_stick_y);
            robot.backLeft.setPower(-gamepad1.left_stick_y);
            robot.pivot.setPower(gamepad2.right_stick_y);
            robot.turret.setPower(gamepad2.left_stick_x / 8);



            if (gamepad1.left_bumper = true) {
                robot.movePivot(0);
            }
            if (gamepad1.right_bumper = true) {
                robot.movePivot(10);
            }*/

            /*if (gamepad1.a == true) {
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
            }*/


            if (gamepad2.right_trigger != 0){
                robot.intake.setPower(gamepad2.right_trigger);
            }
            if (gamepad2.left_trigger != 0){
                robot.intake.setPower(-gamepad2.left_trigger / 2);
            }
            if (gamepad2.y == true){
                //linkage out
                robot.linkage.setPosition(0);
            }
            if (gamepad2.x == true){
                //linkage in - counter clockwise to 1
                robot.linkage.setPosition(1);
            }
            if (gamepad2.a == true){
                //robot.pivotMove = true;
                //robot.movePivot(30);
                //telemetry.addData("Pivot position: ", robot.pivot.getCurrentPosition());
                //telemetry.update();
                while (Math.abs(robot.pivot.getCurrentPosition()) > 30) {
                    robot.pivot.setPower(-1);
                }
                robot.pivot.setPower(0);
                double currentHeight = angleWrap(robot.pivot.getCurrentPosition() / tictoheight);
                telemetry.addData("tictoheight: ", tictoheight);
                telemetry.addData("Pivot position: ", robot.pivot.getCurrentPosition());
                telemetry.addData("currentHeight: ", currentHeight);
                //telemetry.addData("degree - currentHeight: ", Math.abs(degree - currentHeight));
                telemetry.update();
            }
            if (gamepad2.b == true){
                robot.pivotMove = true;
                robot.movePivot(90);
                //telemetry.addData("Pivot position: ", robot.pivot.getCurrentPosition());
                //telemetry.update();
            }


          /*  if (robot.turretMove) {
                robot.moveTurret(robot.targetDeg, 0.25);
            }*/

            double currentDegree = robot.turret.getCurrentPosition() / robot.ticToDegree;
            //telemetry.addData("Degrees: ", currentDegree);
            //telemetry.update();
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

    public void movePivot (double degree){
        double currentHeight = angleWrap(robot.pivot.getCurrentPosition() / tictoheight);
        double outputPower;
        telemetry.addData("tictoheight: ", tictoheight);
        telemetry.addData("Pivot position: ", robot.pivot.getCurrentPosition());
        telemetry.addData("currentHeight: ", currentHeight);
        telemetry.addData("degree - currentHeight: ", Math.abs(degree - currentHeight));
        telemetry.update();

        if (Math.abs(degree - currentHeight) < 20.0) {
            outputPower = 0.05;
        } else {
            outputPower = 1;
        }
        if (Math.abs(degree - currentHeight) < 2.0){
            robot.pivot.setPower(0);
            pivotMove = false;
        } else if (degree > currentHeight){
            robot.pivot.setPower(outputPower);
        } else if (degree < currentHeight){
            robot.pivot.setPower(-outputPower);
        }
    }

}


