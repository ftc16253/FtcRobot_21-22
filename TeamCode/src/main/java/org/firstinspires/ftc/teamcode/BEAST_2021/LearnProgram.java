package org.firstinspires.ftc.teamcode.BEAST_2021;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Hardware;

import kotlin.coroutines.jvm.internal.DebugMetadata;

@TeleOp
@Disabled
public class LearnProgram extends LinearOpMode {

    String hello = "yo whatup";


    Pushbot2021 robot = new Pushbot2021();

    public void runOpMode() {
        robot.init(hardwareMap, false);

        waitForStart();

        while (opModeIsActive()) {
            telemetry.addData("phrase:", hello);
            telemetry.update();

            robot.frontRight.setPower(gamepad1.right_stick_y);
            robot.frontLeft.setPower(gamepad1.left_stick_y);
            robot.backRight.setPower(-gamepad1.right_stick_y);
            robot.backLeft.setPower(-gamepad1.left_stick_y);

            if (gamepad1.a == true) {

                Eric("eric");
                robot.turret.setPower(1);
                sleep(1000);
                robot.turret.setPower(0);
            }
            else if (gamepad1.b == true) {

                robot.turret.setPower(-1);
                sleep(1000);
                robot.turret.setPower(0);
            }
        }
    }
    public void Eric(String name) {
        if (name == "a") {
            telemetry.addData("hey ", name, ", yo mama so ugly the whole world faked a virus just to make her wear a mask, got em!");

        } else if (name == "e") {
            telemetry.addData("hey ", name, ", yo mama so fat, I took a picture of her last year and it's still printing!");
        }
        telemetry.update();
    }
}

