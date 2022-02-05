package org.firstinspires.ftc.teamcode.BEAST_2021;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous
public class autoRedDuck2 extends LinearOpMode {
    Pushbot2021 robot = new Pushbot2021();
    int t=0;

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);

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

            robot.bucket.setPosition(0);
           // sleep(5000);
            //move to away from wall
            if (robot.place == "RIGHT"){
                robot.MoveForwardInch(6,1);
            } else {
                robot.MoveForwardInch(7, 1);
            }
            //turn to alliance hub
            robot.turn(31, 1);
            //move to alliance hub
            robot.MoveForwardInch(24, 1);

            if (robot.place == "RIGHT"){
                robot.turn(22, 1);
                robot.turret.setPower(.22);
                sleep(280);
                robot.turret.setPower(0);
            }else {
                //turn to duck spinner
                robot.turn(15, 1);
                robot.turret.setPower(.22);
                sleep(250);
                robot.turret.setPower(0);
            }

            sleep(2000);
            //move pivot to correct level
            if (robot.place == "LEFT") {
               robot.intake.setPower(-.35);
               sleep(1000);
               robot.intake.setPower(0);
            } else if (robot.place == "RIGHT"){
                robot.pivot.setPower(0.5);
                sleep(500);
                robot.intake.setPower(-1);
                sleep(1000);
                robot.intake.setPower(0);
                robot.pivot.setPower(0);
            } else if (robot.place == "CENTER") {
                robot.pivot.setPower(.5);
                sleep(400);
                robot.intake.setPower(-.35);
                sleep(1000);
                robot.intake.setPower(0);
                robot.pivot.setPower(0);
            }
            robot.turret.setPower(-.22);
            sleep(250);
            robot.turret.setPower(0);

            if (robot.place == "RIGHT"){
                robot.turn(12, -1);
            }else {
                robot.turn(8, -1);
            }
           /* //move pivot to correct level
            if (robot.place == "LEFT") {
                robot.pivot.setPower(-0.5);
                sleep(200);
                robot.pivot.setPower(0);
            } else if (robot.place == "RIGHT"){
                robot.pivot.setPower(-0.5);
                sleep(1000);
                robot.pivot.setPower(0);
            } else if (robot.place == "CENTER"){
                robot.pivot.setPower(-0.5);
                sleep(500);
                robot.pivot.setPower(0);
            } */

            //move to duck spinner
            robot.MoveForwardInch(51, -1);

            //turn carousel
            robot.duckSpinner.setPower(.5);
            sleep(2500);

            //stop carousel
            robot.duckSpinner.setPower(0);
            sleep(250);

            //turn to storage unit
            robot.turn(46,-1);

            //move away from carousel
            robot.MoveForwardInch(20, 1);

            sleep(30000);
        }
    }
}
