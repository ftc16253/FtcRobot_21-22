package org.firstinspires.ftc.teamcode.BEAST_2021;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous
public class  autoRedDuck2 extends LinearOpMode {
    Pushbot2021 robot = new Pushbot2021();
    int t=0;

    @Override
    public void runOpMode() {
        robot.init(hardwareMap, true);

        /** Wait for the game to begin */
        telemetry.addData(">", "Press Play to start op mode");
        telemetry.update();
        waitForStart();

        while (opModeIsActive()) {
            while (t < 1) {
                robot.place = robot.detector.position;
                robot.leftTotal = robot.detector.leftTotal;
                robot.centerTotal = robot.detector.centerTotal;
                telemetry.addData("position = ", robot.place);
                telemetry.addData("leftTotal = ", robot.leftTotal);
                telemetry.addData("centerTotal = ", robot.centerTotal);
                telemetry.update();
                t = t + 1;
            }
            robot.webcam.stopStreaming();

            //sleep(5000);
            //move to away from wall
            robot.MoveForwardInch(7, 1);
            //turn to alliance hub
            robot.turn(27, 1);
            //move to alliance hub
            robot.MoveForwardInch(16, 1);

            robot.turret.setPower(.22);
            sleep(150);
            robot.turret.setPower(0);

            sleep(1000);
            //move pivot to correct level
            if (robot.place == "RIGHT"){
                robot.pivot.setPower(.5);
                robot.linkage.setPosition(.2);
                sleep(500);
                robot.intake.setPower(-.35);
                sleep(1500);
                robot.intake.setPower(0);
                robot.pivot.setPower(0);
                sleep(100);
                robot.linkage.setPosition(1);
            }else if (robot.place == "CENTER"){
                robot.intake.setPower(-.25);
                sleep(1500);
                robot.intake.setPower(0);
            }else if (robot.place == "LEFT"){
                robot.linkage.setPosition(.8);
                robot.bucket.setPosition(1);
                sleep(100);
                robot.intake.setPower(-.25);
                sleep(2000);
                robot.intake.setPower(0);
                robot.linkage.setPosition(1);
            }
            robot.MoveForwardInch(10,-1);
            //turn to duck spinner
            robot.turn(20, 1);

            //move to duck spinner
            robot.MoveForwardInch(31.5, -1);

            //turn carousel
            robot.duckSpinner.setPower(.5);
            sleep(3500);

            //stop carousel
            robot.duckSpinner.setPower(0);
            sleep(250);

            //turn to storage unit
            robot.turn(55,-1);

            //move away from carousel
            robot.MoveForwardInch(22, 1);

            sleep(30000);
        }
    }
}
