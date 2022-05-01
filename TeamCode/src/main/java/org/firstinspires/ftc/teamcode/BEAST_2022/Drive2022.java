package org.firstinspires.ftc.teamcode.BEAST_2022;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class Drive2022 extends LinearOpMode {
    Pushbot2022 bot = new Pushbot2022();
    public void runOpMode() {
        bot.init(hardwareMap);

        waitForStart();

        while (opModeIsActive()){
            if (gamepad1.right_stick_y != 0){
                bot.moveForward(gamepad1.right_stick_y);
            }
            if (gamepad1.right_stick_x != 0){
                bot.turn(gamepad1.right_stick_x);
            }
            if (gamepad1.left_stick_x > 0 && gamepad1.left_stick_y > 0) {
                //move forward diagonally to the right
                bot.frontRightMec.setPower(1);
                bot.backLeftMec.setPower(1);
            }else if (gamepad1.left_stick_x < 0 && gamepad1.left_stick_y > 0) {
                //move forward diagonally to the left
                bot.frontLeftMec.setPower(1);
                bot.backRightMec.setPower(1);
            }else if (gamepad1.left_stick_x > 0 && gamepad1.left_stick_y < 0) {
                //move backward diagonally to the right
                bot.frontLeftMec.setPower(-1);
                bot.backRightMec.setPower(-1);
            }else if (gamepad1.left_stick_x < 0 && gamepad1.left_stick_y < 0) {
                //move backward diagonally to the left
                bot.frontRightMec.setPower(-1);
                bot.backLeftMec.setPower(-1);
            }else if (gamepad1.left_stick_x != 0){
                bot.moveSide(gamepad1.left_stick_x);
            }
            else{
                bot.frontRightMec.setPower(0);
                bot.frontLeftMec.setPower(0);
                bot.backLeftMec.setPower(0);
                bot.backRightMec.setPower(0);
            }
        }
    }
}

