package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.Map;

public class Pushbot2021 extends LinearOpMode {
    DcMotor Intake;
    HardwareMap hwMap = null;
    public void init(HardwareMap ahwMap) {
        hwMap = ahwMap;

    }


    @Override
    public void runOpMode() throws InterruptedException {
        Intake = hwMap.get(DcMotor.class, "Intake");
        Intake.setPower(0);

    }
}
