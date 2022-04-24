package org.firstinspires.ftc.teamcode.BEAST_2021;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous
public class autoBlueWH2 extends LinearOpMode {
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
            //move away from wall
            robot.MoveForwardInch(8, 1);
            sleep(100);

            //turn to alliance hub
            robot.turn(25,1);
            //move to alliance hub
            robot.MoveForwardInch(14,1);
            robot.turn(18,-1);
            robot.MoveForwardInch(4,1);

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
                robot.linkage.setPosition(.5);
                robot.bucket.setPosition(.5);
                sleep(100);
                robot.intake.setPower(-.25);
                sleep(2000);
                robot.intake.setPower(0);
                robot.linkage.setPosition(1);
            }
            robot.MoveForwardInch(6,-1);
            robot.turn(20,1);
            robot.MoveForwardInch(9,-1);
            //turn to warehouse
            robot.turn(32,1);
            robot.MoveForwardInch(35,-1);
            robot.turn(18,-1);
            //drive to warehouse
            robot.MoveForwardInch(25, -1);

            sleep(30000);
        }
    }
}
