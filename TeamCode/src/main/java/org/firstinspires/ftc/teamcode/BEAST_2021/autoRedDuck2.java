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

            sleep(5000);
            //move away from wall
            robot.MoveForwardInch(14, 1);
            sleep(100);
            //backup to pickup cube
            robot.MoveForwardInch(10,-1);
            //pickup cube {
            robot.linkage.setPosition(.15);
            robot.pivot.setPosition(.215);
            sleep(200);
            robot.grabber.setPosition(1);
            sleep(1000);
            robot.pivot.setPosition(.1);
            // }
            //turn to alliance hub
            robot.turn(13, 1);

            //move pivot to correct level
            if (robot.place == "LEFT") {
                robot.pivot.setPosition(.125);
            } else if (robot.place == "RIGHT"){
                robot.pivot.setPosition(0);
                /* //move slide to above top level
                robot.slide.setPower(.05);
                sleep(500);
                robot.slide.setPower(0);*/
            } else if (robot.place == "CENTER"){
                robot.pivot.setPosition(0.02);
            }

            //move to alliance hub
            robot.MoveForwardInch(20,1);

            //deposit cube in tower
            robot.grabber.setPosition(0);

            //move linkage in
            robot.linkage.setPosition(.4);

            robot.turret.setPower(.2);
            sleep(600);
            robot.turret.setPower(0);
            //robot.moveTurret(5,.75);

            robot.linkage.setPosition(0);
            sleep(500);

            robot.pivot.setPosition(1);
            sleep(10000);
            //turn to duck spinner
            robot.turn(22, 1);

            //move to duck spinner
            robot.MoveForwardInch(41, -1);

            //turn carousel
            robot.duckSpinner.setPower(.5);
            sleep(2500);

            //stop carousel
            robot.duckSpinner.setPower(0);
            sleep(250);

            //turn to storage unit
            robot.turn(35,-1);

            //move away from carousel
            robot.MoveForwardInch(20, 1);

            sleep(30000);
        }
    }
}
