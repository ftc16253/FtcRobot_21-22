package org.firstinspires.ftc.teamcode.BEAST_2021;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;


public class EricTest extends LinearOpMode {

    Pushbot2021 robot = new Pushbot2021();
    private Object DcMotorController;

    public void runOpMode() {
        robot.init(hardwareMap, false);

        waitForStart();

        while (opModeIsActive()) {

            telemetry.update();

            robot.frontRight.setPower(gamepad1.right_stick_y);
            robot.frontLeft.setPower(gamepad1.left_stick_y);
            robot.backRight.setPower(-gamepad1.right_stick_y);
            robot.backLeft.setPower(-gamepad1.left_stick_y);

        }






}
}
