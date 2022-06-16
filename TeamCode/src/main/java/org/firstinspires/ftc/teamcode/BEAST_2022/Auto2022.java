package org.firstinspires.ftc.teamcode.BEAST_2022;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous
public class Auto2022 extends LinearOpMode {
    Pushbot2022 bot = new Pushbot2022();

    @Override
    public void runOpMode(){
        bot.init(hardwareMap);

        waitForStart();

        while (opModeIsActive()){
            PIDX(600);
            break;
        }
    }
    public void PIDX(double targetX) {
        bot.PIDtimer.reset();


        //substitute camera value for getPower
        double currentX = Math.abs((bot.frontLeftMec.getCurrentPosition() / bot.yellowJacketTics) * bot.circumference);
        double error = targetX - currentX;
/*
        bot.integral = error / bot.PIDtimer.time();
        double deltaError = error - bot.lastError;
        double derivative = deltaError * bot.PIDtimer.time();

        bot.pidGain.p = bot.pidC.p * error;

        double rcw = bot.pidGain.p + derivative + bot.integral;
*/
        while (Math.abs(error) > 2) {
            /*bot.frontLeftMec.setPower(rcw);
            bot.frontRightMec.setPower(rcw);
            bot.backLeftMec.setPower(rcw);
            bot.backRightMec.setPower(rcw);*/

            bot.frontLeftMec.setPower(.03*error);
            bot.frontRightMec.setPower(.03*error);
            bot.backLeftMec.setPower(.03*error);
            bot.backRightMec.setPower(.03*error);
            /*bot.integral = error / bot.PIDtimer.time();
            deltaError = error - bot.lastError;
            derivative = deltaError * bot.PIDtimer.time();

            bot.pidGain.p = bot.pidC.p * error;

            rcw = bot.pidGain.p + derivative + bot.integral;*/
            currentX = ((bot.frontLeftMec.getCurrentPosition() / bot.yellowJacketTics) * bot.circumference);
            error = targetX - currentX;
            telemetry.addData("targetX: ", targetX);
            telemetry.addData("currentX: ", currentX);
            telemetry.addData("error: ", error);
            telemetry.update();
        }
        bot.frontLeftMec.setPower(0);
        bot.frontRightMec.setPower(0);
        bot.backLeftMec.setPower(0);
        bot.backRightMec.setPower(0);

        //bot.lastError = error;
        currentX = ((bot.frontLeftMec.getCurrentPosition() / bot.yellowJacketTics) * bot.circumference);
        error = targetX - currentX;
        telemetry.addData("Current Tics = ", bot.frontLeftMec.getCurrentPosition());
        telemetry.addData("Distance = ", currentX);
        telemetry.addData("Proportional = ", bot.pidGain.p);
        telemetry.addData("Integral = ", bot.pidGain.i);
        telemetry.addData("Derivative = ",bot.pidGain.d);
        telemetry.addData("targetX: ", targetX);
        telemetry.addData("currentX: ", currentX);
        telemetry.addData("error: ", error);
        telemetry.update();

        sleep(30000);
    }
}
