package org.firstinspires.ftc.teamcode.BEAST_2022;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

class Pushbot2022 extends LinearOpMode {

    public void runOpMode() {
        DcMotor frontLeftMec, frontRightMec, backRightMec, backLeftMec;
        frontLeftMec = hardwareMap.get(DcMotor.class, "frontLeftMec");
        frontRightMec = hardwareMap.get(DcMotor.class, "frontRightMec");
        backLeftMec = hardwareMap.get(DcMotor.class, "backLeftMec");
        backRightMec = hardwareMap.get(DcMotor.class, "backRightMec");

        frontLeftMec.setDirection(DcMotor.Direction.REVERSE);
        frontRightMec.setDirection(DcMotor.Direction.FORWARD);
        backLeftMec.setDirection(DcMotor.Direction.FORWARD);
        backRightMec.setDirection(DcMotor.Direction.REVERSE);

    }
    public void moveFowardInch(double distance){

    }
}

