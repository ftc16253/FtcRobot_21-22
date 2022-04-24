package org.firstinspires.ftc.teamcode.BEAST_2021;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous
public class autoRedWH2 extends LinearOpMode {
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

            //sleep(6000);
            //move to alliance hub
            robot.MoveForwardInch(8,1);

            //turn to alliance hub
            robot.turn(33, -1);

            //move to alliance hub
            robot.MoveForwardInch(17, 1);
            //turn to deposit cube
            robot.turn(27,1);
            robot.MoveForwardInch(3,1);

            if (robot.place == "RIGHT"){
                robot.pivot.setPower(.5);
                sleep(200);
                robot.linkage.setPosition(.2);
                robot.bucket.setPosition(.6);
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
                robot.linkage.setPosition(.5);
                robot.bucket.setPosition(.5);
                sleep(100);
                robot.intake.setPower(-.25);
                sleep(2000);
                robot.intake.setPower(0);
                robot.linkage.setPosition(1);
            }

            robot.MoveForwardInch(4,-1);
            robot.turn(24,-1);
            robot.MoveForwardInch(9,-1);
            //turn to warehouse
            robot.turn(30,-1);
            robot.MoveForwardInch(30,-1);
            robot.turn(18,1);
            //drive to warehousewe
            robot.MoveForwardInch(30, -1);

            sleep(30000);
        }
    }
}
