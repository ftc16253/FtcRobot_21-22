package org.firstinspires.ftc.teamcode.BEAST_2021;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

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


            robot.slide.setPower(-gamepad2.right_stick_y / 8);
            telemetry.addData("Slide Encoder: ", robot.slide.getCurrentPosition());
            telemetry.update();
            robot.turret.setPower(-gamepad2.left_stick_x / 8);

            if (gamepad1.a) {
                robot.duckSpinner.setPower(.2);
                robot.duckSpinner.setPower(.4);
                robot.duckSpinner.setPower(.6);
                robot.duckSpinner.setPower(.8);
            } else if (gamepad1.b){
                robot.duckSpinner.setPower(-.2);
                robot.duckSpinner.setPower(-.4);
                robot.duckSpinner.setPower(-.6);
                robot.duckSpinner.setPower(-.8);
            } else{
                robot.duckSpinner.setPower(0);
            }


/*            if (gamepad2.left_trigger != 0){
                    //close grabber
                    robot.grabber.setPosition(.8);
            } else if (gamepad2.right_trigger != 0) {
                //Open grabber
                robot.grabber.setPosition(0.6);
            } else {
                //Stop grabber
                robot.grabber.setPosition(.5);
            }*/
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


            if (gamepad2.y){
                //linkage out
                robot.linkage.setPosition(0);
            }

            if (gamepad2.x){
                //linkage in
                robot.linkage.setPosition(.3);
            }

            if (gamepad2.a){
                telemetry.addData("Pivot position: ", robot.pivot.getPosition());
                telemetry.update();
                if (robot.pivot.getPosition() > 0){
                    robot.pivot.setPosition(robot.pivot.getPosition() - .025);
                }
                sleep(100);
            }

            if (gamepad2.b) {
                telemetry.addData("Old Pivot position: ", robot.pivot.getPosition());
                if (robot.pivot.getPosition() < 0.275) {
                    robot.pivot.setPosition(robot.pivot.getPosition() + 0.025);

                }
                telemetry.addData("New Pivot position: ", robot.pivot.getPosition());
                telemetry.update();
                sleep (100);
            }


            while(robot.slideSensor.isPressed()){
                robot.slide.setPower(0);
                sleep(500);
                robot.slide.setPower(Math.abs(gamepad2.left_stick_y));
            }
        }
    }
}
