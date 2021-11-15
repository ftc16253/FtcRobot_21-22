package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp

public class Drive2021 extends LinearOpMode {
    Pushbot2021 robot = new Pushbot2021();

    public void runOpMode() {
        robot.init(hardwareMap, false);

       
        waitForStart();


        while (opModeIsActive()) {
            double drive = gamepad1.left_stick_y;
            double turn = gamepad1.right_stick_x;
            if (drive != 0){
                robot.frontLeft.setPower(drive);
                robot.frontRight.setPower(drive);
                robot.backRight.setPower(drive);
                robot.backLeft.setPower(drive);
            }

            if (turn != 0){
                //Turn left or right
                robot.frontLeft.setPower(-turn);
                robot.frontRight.setPower(turn);
                robot.backLeft.setPower(-turn);
                robot.backRight.setPower(turn);
            }

            if (drive == 0 && turn == 0) {
                robot.frontLeft.setPower(0);
                robot.frontRight.setPower(0);
                robot.backRight.setPower(0);
                robot.backLeft.setPower(0);
            }
        }
    }
}
