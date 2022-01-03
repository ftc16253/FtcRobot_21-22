
package org.firstinspires.ftc.teamcode.BEAST_2020;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class PushBot2020
{
    /* Public OpMode members. */
    public DcMotor frontLeft;
    public DcMotor frontRight;
    public DcMotor backRight;
    public DcMotor backLeft;
    public DcMotor intake, feeder;
    public DcMotor shooterFront, encoderTest;
    public Servo grabber, wobbleRotate;
    public static final String VUFORIA_KEY =
            "Afctxlz/////AAABmSWf4jOsTUHcsOYa/JfaZlRo+3yiPN8cCUH4BDLpIZ8FAt0tEVLJ/mxWUyd7f0gqd+a7JRTMYP9+A9s1nojOs9B1ZGOFsvr84RZnbVN8cGP7RFKNP4Mg0Pr/6vIUmHGFx/jrOrXz/YJXwVXvPpqr1uDm8xpBZOE4j+CtQcKW2Y2zjVWHWRTkmb6ve/R91k3jfjaH4PErbZMcvD7Xy5IesqSet3/pjeUXWSnlHmPwH7fgUcHSkAf0Fj2nLvZ7zmpT8vh9rSKri9XD3A64WBNRO+6+SGH/C/eS3mWLmdi5ZMbSK66WuvNhAPT0SHCzzqAlAf2P6asrrrAuw+aQ0B2HV0mPtGdNPe62djhu5Afa/rL+";

    /* local OpMode members. */

    HardwareMap hwMap           =  null;
    private ElapsedTime period  = new ElapsedTime();

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap, boolean isAuto) {
        // Save reference to Hardware map
        hwMap = ahwMap;

        // Define and Initialize Motors
        frontLeft  = hwMap.get(DcMotor.class, "left");
        frontRight = hwMap.get(DcMotor.class, "right");
        //backLeft  = hwMap.get(DcMotor.class, "backLeft");
        //backRight = hwMap.get(DcMotor.class, "backRight");
        intake = hwMap.get(DcMotor.class, "intake");
        feeder = hwMap.get(DcMotor.class, "feeder");
        shooterFront = hwMap.get(DcMotor.class, "shooterFront");
        encoderTest  = hwMap.get(DcMotor.class, "encoderTest");

        frontLeft.setDirection(DcMotor.Direction.FORWARD);
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        //backLeft.setDirection(DcMotor.Direction.FORWARD);
        //backRight.setDirection(DcMotor.Direction.REVERSE);
        intake.setDirection(DcMotor.Direction.REVERSE);
        shooterFront.setDirection(DcMotorSimple.Direction.REVERSE);

        shooterFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        shooterFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        // Set all motors to zero power
        frontLeft.setPower(0);
        frontRight.setPower(0);
        //backLeft.setPower(0);
        //backRight.setPower(0);
        intake.setPower(0);
        feeder.setPower(0);
        shooterFront.setPower(0);



        //Define Servos
        grabber = hwMap.get(Servo.class, "grabber");
        wobbleRotate = hwMap.get(Servo.class, "wobbleRotate");


        if (isAuto == false){
            //Start position for two servos
            wobbleRotate.setPosition(0);

            //Start position for claw
            grabberSetPosition(.8);
        }

        else{
            wobbleRotate.setPosition(.4);
            wobbleRotate.setPosition(.2);
            wobbleRotate.setPosition(.05);
            grabberSetPosition(0);
        }


/*
        //This section makes the motors drive slowly - Don't use BRAKE
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
*/
    }
    public void grabberSetPosition(double position){
        grabber.setPosition(position);
    }
}

