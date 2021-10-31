package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class TestBed<hardwareMap> extends LinearOpMode {
    public DcMotor Intake, Motor1, Motor2;
    public Servo Grabber;
    public void runOpMode() {
        Intake = hardwareMap.get(DcMotor.class, "Intake");
        Motor1 = hardwareMap.get(DcMotor.class, "Motor1");
        Motor2 = hardwareMap.get(DcMotor.class, "Motor2");
        Intake.setPower(0);
        Motor1.setPower(0);
        Motor2.setPower(0);


        Grabber = hardwareMap.get(Servo.class, "grabber");




        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            //Intake.setPower(gamepad1.right_stick_y);
            Motor1.setPower(gamepad1.left_stick_y);
            Motor2.setPower(gamepad1.left_stick_y);

            //For continuous servo on grabber
            if (gamepad1.a==true)
            {
                //Close grabber
                Grabber.setPosition(1);
            }
            else if (gamepad1.b == true)
            {
                //Open grabber
                Grabber.setPosition(0);
            }
            else
            {
                //Stop grabber
                Grabber.setPosition(.5);
            }
        }

    }
}
