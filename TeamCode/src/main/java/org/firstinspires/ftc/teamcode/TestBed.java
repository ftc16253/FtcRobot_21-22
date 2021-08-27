package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

@TeleOp
public class TestBed<hardwareMap> extends LinearOpMode {
    public DcMotor Intake;
    hardwareMap hwMap;

    public void runOpMode() {
        Intake = hardwareMap.get(DcMotor.class, "Intake");
        Intake.setPower(0);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            Intake.setPower(gamepad1.right_stick_y);

        }

    }
}
