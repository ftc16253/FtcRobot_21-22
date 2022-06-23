package org.firstinspires.ftc.teamcode.BEAST_2022;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous
public class ThirtyStep extends LinearOpMode {
    Pushbot2022 bot = new Pushbot2022();

    @Override
    public void runOpMode() {
        bot.init(hardwareMap);

        waitForStart();

        while (opModeIsActive()) {
            bot.PIDX(12);
            sleep(100);
            bot.flank(90,1);
            sleep(1000000);
            bot.flank(-180,1);
            sleep(100);
            bot.PIDX(12);
            sleep(100);
            bot.flank(-90,1);
            sleep(100);
            bot.PIDX(12);
            sleep(100);
            bot.flank(90,1);
            sleep(100);
            bot.PIDX(12);
            sleep(100);
            bot.columnRight(90,1);
            sleep(100);
            bot.PIDX(24);
            sleep(100);
            bot.PIDX(-12);
            sleep(100);
            bot.PIDX(24);
            sleep(100);
            bot.columnRight(90,1);
            sleep(100);
            bot.PIDX(15);
            sleep(100);
            bot.flank(-45,1);
            bot.frontRightMec.setPower(1);
            bot.backLeftMec.setPower(1);
            sleep(400);
            bot.frontRightMec.setPower(0);
            bot.backLeftMec.setPower(0);
            bot.flank(45,1);
            sleep(100);
            bot.PIDX(15);
            sleep(100);
            bot.columnRight(90,1);
            sleep(100);
            bot.PIDX(24);
            sleep(400);
            bot.PIDX(24);
            sleep(100);
            bot.columnRight(90,1);
            sleep(100);
            bot.PIDX(20);
            sleep(100);
            bot.flank(90,1);
            sleep(100);
            bot.moveSide(1);
            sleep(200);
            bot.moveSide(0);
            sleep(30000);
        }
    }
}