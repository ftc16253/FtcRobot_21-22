package org.firstinspires.ftc.teamcode.BEAST_2021;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous
public class autoBlueWH2 extends LinearOpMode {
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
            robot.pivot.setPower(.5);
            sleep(500);
            robot.pivot.setPower(0);
            //move away from wall
            robot.MoveForwardInch(6, 1);
            sleep(100);

            //turn to alliance hub
            robot.turn(23,-1);
            //move to alliance hub
            robot.MoveForwardInch(17,1);

            //turn turret to alliance hub
            robot.turret.setPower(-.22);
            sleep(83);
            robot.turret.setPower(0);

            if (robot.place == "RIGHT"){

            }else if (robot.place == "CENTER"){

            }else if (robot.place == "LEFT"){

            }

            robot.turret.setPower(.22);
            sleep(83);
            robot.turret.setPower(0);
            sleep(20000);

            //turn to warehouse
            robot.turn(28,- 1);

            //drive to warehouse
            robot.MoveForwardInch(70, -1);

            robot.turret.setPower(.22);
            sleep(500);
            robot.turret.setPower(.05);
            sleep(300);
            robot.turret.setPower(0);

            sleep(30000);
        }
    }
}
