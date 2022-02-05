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

            //sleep(5000);
            //robot.pivot.setPower(.5);
            //sleep(500);
            //robot.pivot.setPower(0);
            //move away from wall
            robot.MoveForwardInch(6, 1);
            sleep(100);

            //turn to alliance hub
            robot.turn(7,-1);
            //move to alliance hub
            robot.MoveForwardInch(14,1);
            robot.turn(10, 1);
            robot.MoveForwardInch(18,1);


            //turn turret to alliance hub
            robot.turret.setPower(.22);
            sleep(220);
            robot.turret.setPower(0);
            robot.bucket.setPosition(0);

            sleep(2000);

            if (robot.place == "RIGHT"){
                robot.pivot.setPower(.5);
                sleep(400);
                robot.intake.setPower(-.5);
                sleep(1000);
                robot.intake.setPower(0);
                robot.pivot.setPower(0);
            }else if (robot.place == "CENTER"){
                robot.pivot.setPower(.5);
                sleep(200);
                robot.intake.setPower(-.37);
                sleep(1000);
                robot.intake.setPower(0);
                robot.pivot.setPower(0);
            }else if (robot.place == "LEFT"){
                robot.linkage.setPosition(.8);
                robot.intake.setPower(-.35);
                sleep(1000);
                robot.intake.setPower(0);
            }
            sleep(3000);

            robot.MoveForwardInch(8, -1);
            //turn to warehouse
            robot.turn(35,- 1);

            //drive to warehouse
            robot.MoveForwardInch(55, -1);

            sleep(30000);
        }
    }
}
