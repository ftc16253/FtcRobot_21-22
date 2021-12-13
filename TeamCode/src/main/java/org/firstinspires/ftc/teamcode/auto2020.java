package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;

@Autonomous(name="auto2020", group="Linear Opmode")
@Disabled
public class auto2020 extends LinearOpMode {
    Pushbot2021 robot = new Pushbot2021();
    Functions2020 Util = new Functions2020();
    int t=0;
    private OpenCvCamera webcam;
    private RingDetector detector = new RingDetector();
    private String numberOfRings;
    private double bottomTotal;
    private double topTotal;

    @Override
    public void runOpMode() {
        //Initialize motors
        robot.init(hardwareMap);
        Util.init(hardwareMap);
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        webcam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);

        webcam.openCameraDevice();
        webcam.setPipeline(detector);
        webcam.startStreaming(320, 240, OpenCvCameraRotation.UPRIGHT);
        robot.grabber.setPosition(0);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            telemetry.addData("Status", "Running");
            telemetry.update();

            while (t<1) {
                numberOfRings = detector.numberOfRings;
                bottomTotal = detector.bottomTotal;
                topTotal = detector.topTotal;
                telemetry.addData("numberOfRings = ", numberOfRings);
                telemetry.addData("bottomTotal = ", bottomTotal);
                telemetry.addData("topTotal = ", topTotal);
                telemetry.update();
                t=t+1;
            }

            //numberOfRings = "QUAD";

            Util.DriveAndShoot();
            //sleep(1000);
            //sleep(30000);

            if (numberOfRings == "NONE"){
                //Drive to A
                //Util.PIDloopDrive2(46, -.6);
                sleep(500);
                Util.turnLeft(3,.7);
                sleep(250);
                Util.PIDloopDrive2(4,.6);

                //Drop the wobble
                robot.linkage.setPosition(.65);
                sleep(1250);
                robot.grabber.setPosition(.6);
                sleep(750);

                //Drive forward, lift the grabber, and turn left onto the line
                Util.PIDloopDrive2(2, -.5);
                sleep(500);
                //Util.turnRight(2.1, .7);
                //sleep(250);
                //Util.PIDloopDrive2(12, .5);
                //sleep(30000);
                //robot.grabberSetPosition(0);
                //sleep(750);
                //robot.linkage.setPosition(.25);
                robot.linkage.setPosition(0);
                sleep(250);
                Util.turnLeft(2,.7);
                sleep(250);
            }

            else if (numberOfRings == "SINGLE") {
                //Drive forward to square B and position the robot to drop wobble
                //Util.PIDloopDrive2(72, -.6);
                Util.PIDloopDrive2(17, -.5);
                sleep(500);
                Util.turnLeft(3.5, .7);
                sleep(250);
                Util.PIDloopDrive2(5, -.6);
                sleep(500);

                //Drop the wobble
                robot.linkage.setPosition(.65);
                sleep(1250);
                robot.grabber.setPosition(.6);
                sleep(750);

                //Drive forward, lift the grabber, and turn left
                Util.PIDloopDrive2(1, -.5);
                sleep(500);
                robot.linkage.setPosition(0);
                sleep(250);
                Util.turnLeft(1.75, .7);
                sleep(250);

                //Drive forward onto the line
                Util.PIDloopDrive2(9, -.6);
            }
            else {
                //Drive forward to square C and position the robot to drop wobble
                Util.PIDloopDrive2(25, -.5);
                sleep(500);
                Util.turnLeft(4,.7);
                sleep(250);
                //back up a little
                Util.PIDloopDrive2(5, .6);
                sleep(500);

                //Drop the wobble
                robot.linkage.setPosition(.65);
                sleep(1250);
                robot.grabber.setPosition(.6);
                sleep(750);

                //Drive forward, lift the grabber, and turn left
                Util.PIDloopDrive2(2, -.6);
                sleep(500);
                robot.linkage.setPosition(0);
                sleep(250);
                Util.turnLeft(1.5,.7);
                sleep(250);

                //Drive forward onto the line
                Util.PIDloopDrive2(19, -.5);
            }

            robot.linkage.setPosition(.65);

            telemetry.addData("Left Motor Position at end", robot.frontLeft.getCurrentPosition());
            telemetry.addData("Right Motor Position at end", robot.frontRight.getCurrentPosition());
            telemetry.update();

            sleep(30000);
        }
    }
}


