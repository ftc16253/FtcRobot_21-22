package org.firstinspires.ftc.teamcode.BEAST_2022;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.BEAST_2021.DuckDetector;
import org.openftc.easyopencv.OpenCvCamera;

class Pushbot2022{
    /* Public OpMode members. */
    public DcMotor frontLeftMec, frontRightMec, backRightMec, backLeftMec;
    double spinDiameter = 1;
    double diameter = 3.6;
    double circumference = diameter * 3.14;
    double andyMark40Tics = 1120;
    double andyMark20Tics = 537.6;
    public static final String VUFORIA_KEY =
            "Afctxlz/////AAABmSWf4jOsTUHcsOYa/JfaZlRo+3yiPN8cCUH4BDLpIZ8FAt0tEVLJ/mxWUyd7f0gqd+a7JRTMYP9+A9s1nojOs9B1ZGOFsvr84RZnbVN8cGP7RFKNP4Mg0Pr/6vIUmHGFx/jrOrXz/YJXwVXvPpqr1uDm8xpBZOE4j+CtQcKW2Y2zjVWHWRTkmb6ve/R91k3jfjaH4PErbZMcvD7Xy5IesqSet3/pjeUXWSnlHmPwH7fgUcHSkAf0Fj2nLvZ7zmpT8vh9rSKri9XD3A64WBNRO+6+SGH/C/eS3mWLmdi5ZMbSK66WuvNhAPT0SHCzzqAlAf2P6asrrrAuw+aQ0B2HV0mPtGdNPe62djhu5Afa/rL+";


    /* local OpMode members. */

    HardwareMap hwMap           =  null;
    private ElapsedTime period  = new ElapsedTime();

    public void init(HardwareMap ahwMap) {
        hwMap = ahwMap;
        //DcMotor frontLeftMec, frontRightMec, backRightMec, backLeftMec;
        frontLeftMec = hwMap.get(DcMotor.class, "frontLeftMec");
        frontRightMec = hwMap.get(DcMotor.class, "frontRightMec");
        backLeftMec = hwMap.get(DcMotor.class, "backLeftMec");
        backRightMec = hwMap.get(DcMotor.class, "backRightMec");

        frontLeftMec.setDirection(DcMotor.Direction.FORWARD);
        frontRightMec.setDirection(DcMotor.Direction.REVERSE);
        backLeftMec.setDirection(DcMotor.Direction.FORWARD);
        backRightMec.setDirection(DcMotor.Direction.REVERSE);

        //set power to zero
        frontLeftMec.setPower(0);
        frontRightMec.setPower(0);
        backLeftMec.setPower(0);
        backRightMec.setPower(0);
    }
    public void moveForward(double power){
        frontLeftMec.setPower(power);
        frontRightMec.setPower(power);
        backLeftMec.setPower(power);
        backRightMec.setPower(power);
    }
    public void turn(double power){
        frontRightMec.setPower(power);
        frontLeftMec.setPower(-power);
        backRightMec.setPower(power);
        backLeftMec.setPower(-power);
    }
    public void moveSide(double power){
        frontLeftMec.setPower(-power);
        frontRightMec.setPower(power);
        backLeftMec.setPower(power);
        backRightMec.setPower(-power);
    }
    public void moveFowardInch(double distance, double power){
    frontLeftMec.setPower(power);
    }

}

