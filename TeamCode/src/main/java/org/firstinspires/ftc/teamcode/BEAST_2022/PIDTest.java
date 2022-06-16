package org.firstinspires.ftc.teamcode.BEAST_2022;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.PIDCoefficients;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp
public class PIDTest extends LinearOpMode {
    Pushbot2022 bot = new Pushbot2022();

    public static PIDCoefficients pidC = new PIDCoefficients(0, 0, 0);
    public PIDCoefficients pidGain = new PIDCoefficients(0, 0, 0);

    ElapsedTime PIDtimer = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);

    @Override
    public void runOpMode(){

        waitForStart();
        if (opModeIsActive()){

            if (gamepad1.a){
                PIDTot(.9);
            } else if (gamepad1.b){
                PIDTot(.5);
            }else if (gamepad1.x){
                PIDTot(-.5);
            }else {
                PIDTot(0);
            }
            telemetry.update();
        }
    }
    double integral = 0;
    double lastError = 0;
    public void PIDTot (double targetV){
        PIDfl(targetV);
        PIDfr(targetV);
        PIDbl(targetV);
        PIDbr(targetV);
    }
    public void PIDTurn (double targetV){
        PIDfl(targetV);
        PIDfr(-targetV);
        PIDbl(targetV);
        PIDbr(-targetV);
    }
    public void PIDfl(double targetV){
        PIDtimer.reset();

        double currentV = bot.frontLeftMec.getPower();
        double error = targetV - currentV;

        integral += error * PIDtimer.time();
        double deltaError = error - lastError;
        double derivative = deltaError / PIDtimer.time();

        pidGain.p = pidC.p * error;
        pidGain.i = pidC.i * integral;
        pidGain.d = pidC.d * derivative;

        bot.frontLeftMec.setPower(pidGain.p + pidGain.i + pidGain.d + targetV);
        lastError = error;
    }
    public void PIDfr(double targetV){
        PIDtimer.reset();

        double currentV = bot.frontRightMec.getPower();
        double error = targetV - currentV;

        integral += error * PIDtimer.time();
        double deltaError = error - lastError;
        double derivative = deltaError / PIDtimer.time();

        pidGain.p = pidC.p * error;
        pidGain.i = pidC.i * integral;
        pidGain.d = pidC.d * derivative;

        bot.frontRightMec.setPower(pidGain.p + pidGain.i + pidGain.d + targetV);
        lastError = error;
    }
    public void PIDbl(double targetV){
        PIDtimer.reset();

        double currentV = bot.backLeftMec.getPower();
        double error = targetV - currentV;

        integral += error * PIDtimer.time();
        double deltaError = error - lastError;
        double derivative = deltaError / PIDtimer.time();

        pidGain.p = pidC.p * error;
        pidGain.i = pidC.i * integral;
        pidGain.d = pidC.d * derivative;

        bot.backLeftMec.setPower(pidGain.p + pidGain.i + pidGain.d + targetV);
        lastError = error;
    }
    public void PIDbr(double targetV){
        PIDtimer.reset();

        double currentV = bot.backRightMec.getPower();
        double error = targetV - currentV;

        integral += error * PIDtimer.time();
        double deltaError = error - lastError;
        double derivative = deltaError / PIDtimer.time();

        pidGain.p = pidC.p * error;
        pidGain.i = pidC.i * integral;
        pidGain.d = pidC.d * derivative;

        bot.backRightMec.setPower(pidGain.p + pidGain.i + pidGain.d + targetV);
        lastError = error;
    }
}
