package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class TestBed<hardwareMap> extends LinearOpMode {
    public DcMotor Intake;
    public Servo Grabber;
    public void runOpMode() {
        Intake = hardwareMap.get(DcMotor.class, "Intake");
        Intake.setPower(0);

        Grabber = hardwareMap.get(Servo.class, "grabber");




        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            Intake.setPower(gamepad1.right_stick_y);
            if (gamepad1.a==true)
            {
                Grabber.setPosition(1);
            }
            if (gamepad1.b == true)
            {
                Grabber.setPosition(0);
            }
        }

    }
}
