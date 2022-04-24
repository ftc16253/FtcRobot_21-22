package org.firstinspires.ftc.teamcode.BEAST_2021;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous
public class autoBlueDuck2 extends LinearOpMode {
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
            robot.turn(36, -1);

            //move to alliance hub
            robot.MoveForwardInch(18, 1);
            //turn to deposit cube
            robot.turn(21,1);

            //deposit cube
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
                robot.linkage.setPosition(.8);
                sleep(100);
                robot.intake.setPower(-.25);
                sleep(1500);
                robot.intake.setPower(0);
            }else if (robot.place == "LEFT"){
                robot.bucket.setPosition(1);
                sleep(100);
                robot.intake.setPower(-.35);
                sleep(2000);
                robot.intake.setPower(0);
            }
            //turn to carousel
            robot.turn(20,-1);

            //move to duck spinner
            robot.MoveForwardInch(42, -1);

            //turn to place duckspinner on carousel
            robot.turn(25,1);

            //turn carousel
            robot.duckSpinner.setPower(-.5);
            sleep(3000);

            //stop carousel
            robot.duckSpinner.setPower(0);
            sleep(250);

            //move away from carousel
            robot.MoveForwardInch(4 , 1);

            robot.turn(24, 1);

            robot.MoveForwardInch(20, 1);

            sleep(30000);
        }
    }
}
