package org.firstinspires.ftc.teamcode.BEAST_2021;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous
public class autoRedWH2 extends LinearOpMode {
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
            robot.linkage.setPosition(.2);
            robot.pivot.setPosition(.215);
            sleep(300);
            robot.grabber.setPosition(1);
            sleep(1000);
            robot.pivot.setPosition(.1);
            // }

            //turn to alliance hub
            robot.turn(18, -1);

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
            robot.MoveForwardInch(27,1);

            //deposit cube
            robot.grabber.setPosition(0);

            //move away from alliance hub
           // robot.MoveForwardInch(6,-1);


            //turn to pickup duck
            if (robot.place == "CENTER") {
                robot.turn(21, 1);
                robot.MoveForwardInch(4,-1);
                robot.turn(28,1);
                robot.linkage.setPosition(0.25);
                sleep(200);
                robot.MoveForwardInch(2,-1);
                robot.pivot.setPosition(.2);
                sleep(500);
                robot.grabber.setPosition(1);
                sleep(700);
                robot.pivot.setPosition(0.15);
                sleep(200);
                robot.turret.setPower(0.2);
                sleep(600);
                robot.turret.setPower(0);
                sleep(100);
                robot.grabber.setPosition(0);
                sleep(100);
                robot.turret.setPower(-0.2);
                sleep(600);
                robot.turret.setPower(0);
                robot.turn(15,1);

            } else if (robot.place == "LEFT"){
                robot.turn(40, 1);
                robot.linkage.setPosition(0.25);
                sleep(200);
                robot.pivot.setPosition(.215);
                sleep(500);
                robot.grabber.setPosition(1);
            } else if (robot.place == "RIGHT"){
                robot.turn(40, 1);
                robot.linkage.setPosition(0.25);
                sleep(200);
                robot.pivot.setPosition(.215);
                sleep(500);
                robot.grabber.setPosition(1);
            }
            //sleep(10000);
            //drive to warehouse
            robot.MoveForwardInch(60, 1);
/*
                            //pickup cube
                            robot.pickupCube(0, 8, .5);
                            //drive back to alliance hub
                            robot.MoveForwardInch(48, -1);
                            //deposit cube
                            robot.depositCube(0, 135, 10);
                            //move to warehouse
                            robot.MoveForwardInch(48, 1);
                            //Pickup cube
                            robot.pickupCube(0, 10, .5);
                            //move to allaince hub
                            robot.MoveForwardInch(48, -1);
                            //deposit cube
                            robot.depositCube(0, 135, 5);
                            //move to warehouse
                            robot.MoveForwardInch(48, 1);
*/

            sleep(30000);
        }
    }
}
