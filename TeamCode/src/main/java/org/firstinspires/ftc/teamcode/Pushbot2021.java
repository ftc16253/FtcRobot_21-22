package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import java.util.Map;

public class Pushbot2021 {

    private HardwareMap hwMap;
    public DcMotor Intake;
    public Servo Grabber;

    public void init(HardwareMap ahwMap) {
        hwMap = ahwMap;


        Intake = hwMap.get(DcMotor.class, "Intake");
        Intake.setPower(0);

        Grabber = hwMap.get(Servo.class, "grabber");

        Intake = hwMap.get(DcMotor.class, "Intake");
        Intake.setPower(0);
    }
}
