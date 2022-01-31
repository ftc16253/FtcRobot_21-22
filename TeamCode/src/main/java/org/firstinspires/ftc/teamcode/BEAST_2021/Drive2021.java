package org.firstinspires.ftc.teamcode.BEAST_2021;
/*
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp

public class Drive2021 extends LinearOpMode {
    Pushbot2021 robot = new Pushbot2021();

    public void runOpMode() {
        robot.init(hardwareMap);

        waitForStart();

        while (opModeIsActive()) {
            double drive = gamepad1.left_stick_y;
            double turn = gamepad1.right_stick_x;
            if (drive != 0){
                robot.frontLeft.setPower(-drive);
                robot.frontRight.setPower(-drive);
                robot.backRight.setPower(drive);
                robot.backLeft.setPower(drive);
            }

            if (turn != 0){
                //Turn left or right
                robot.frontLeft.setPower(turn);
                robot.frontRight.setPower(-turn);
                robot.backLeft.setPower(-turn);
                robot.backRight.setPower(turn);
            }

            if (drive == 0 && turn == 0) {
                robot.frontLeft.setPower(0);
                robot.frontRight.setPower(0);
                robot.backRight.setPower(0);
                robot.backLeft.setPower(0);
            }


            robot.slide.setPower(-gamepad2.right_stick_y / 8);
            telemetry.addData("Slide Encoder: ", robot.slide.getCurrentPosition());
            telemetry.update();
            robot.turret.setPower(-gamepad2.left_stick_x / 20);

            if (gamepad1.a) {
                 robot.duckSpinner.setPower(.7);
            } else if (gamepad1.b){
                robot.duckSpinner.setPower(-.7);
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
            }*
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
                //pivot up
                telemetry.addData("Pivot position: ", robot.pivot.getPosition());
                telemetry.update();
                if (robot.pivot.getPosition() > .05){
                    robot.pivot.setPosition(robot.pivot.getPosition() - .05);
                }
                sleep(100);
            }

            if (gamepad2.b) {
                //pivot down
                telemetry.addData("Old Pivot position: ", robot.pivot.getPosition());
                if (robot.pivot.getPosition() < .25) {
                    robot.pivot.setPosition(robot.pivot.getPosition() + 0.05) ;

                }
                telemetry.addData("New Pivot position: ", robot.pivot.getPosition());
                telemetry.update();
                sleep (100);
            }
        }
    }
}
*/