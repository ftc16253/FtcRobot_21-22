package org.firstinspires.ftc.teamcode.BEAST_2021;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.BEAST_2021.Pushbot2021;

@TeleOp
@Disabled
public class TestBed extends LinearOpMode {
    Pushbot2021 robot = new Pushbot2021();

    public void runOpMode() {
        robot.init(hardwareMap, false);

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
            robot.slide.setPower(gamepad2.right_stick_y / 2 );

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
                //test drive encoders
                robot.frontLeft.setPower(1);
                robot.frontRight.setPower(1);
                robot.backLeft.setPower(-1);
                robot.backRight.setPower(-1);

                telemetry.addData("left front: ", robot.frontLeft.getCurrentPosition());
                telemetry.addData("right front: ", robot.frontRight.getCurrentPosition());
                telemetry.addData("left back: ", robot.backLeft.getCurrentPosition());
                telemetry.addData("right back: ", robot.backRight.getCurrentPosition());
                telemetry.update();
            }

            if (gamepad1.a == true){  
                //turn turret to right
                robot.slideMove = true;
                robot.targetHeight = 10.0;
            }
            if (gamepad1.b == true){
                //turn turret to left
                robot.slideMove = true;
                robot.targetHeight = 15.0;
            }
            if(gamepad2.dpad_up){
                robot.slideMove = true;
                robot.targetHeight = 5.0;
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
                robot.turretMove = true;
                robot.targetDeg = 90;
            }

            if (robot.turretMove) {
                robot.moveTurret(robot.targetDeg, 0.25);
            }

            if (robot.slideMove){
                robot.moveSlide(robot.targetHeight, .25);
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
