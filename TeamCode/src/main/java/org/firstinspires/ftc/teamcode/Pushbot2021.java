package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.Map;

public class Pushbot2021 {
    DcMotor Intake;
    HardwareMap hwMap = null;
    public void init(HardwareMap ahwMap) {
        hwMap = ahwMap;

        Intake = hwMap.get(DcMotor.class, "Intake");
        Intake.setPower(0);
    }



}
