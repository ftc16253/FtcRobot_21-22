package org.firstinspires.ftc.teamcode.BEAST_2021;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.BEAST_2020.RingDetector;
import org.opencv.core.Mat;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;

@Autonomous(name = "testDuckOpenCV")
public class testDuckOpenCV extends LinearOpMode {
    Pushbot2021 robot = new Pushbot2021();
    String place;
    int t=0;
    private OpenCvCamera webcam;
    private DuckDetector detector = new DuckDetector();
    double leftTotal, centerTotal, rightTotal;

    public void runOpMode() {
        robot.init(hardwareMap, true);
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        webcam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 2021"), cameraMonitorViewId);
        Mat duckInput = new Mat();
        webcam.openCameraDevice();
        webcam.setPipeline(detector);
        webcam.startStreaming(320, 240, OpenCvCameraRotation.UPRIGHT);


        /** Wait for the game to begin */
        telemetry.addData(">", "Press Play to start op mode");
        telemetry.update();

        waitForStart();


        while (opModeIsActive()) {
            while (t<1) {
                //detector.processFrame(duckInput);
                //sleep(2000);
                place = detector.position;
                leftTotal = detector.leftTotal;
                centerTotal = detector.centerTotal;
                rightTotal = detector.rightTotal;
                telemetry.addData("position = ", place);
                telemetry.addData("leftTotal = ", leftTotal);
                telemetry.addData("centerTotal = ", centerTotal);
                //telemetry.addData("rightTotal = ", rightTotal);
                telemetry.update();
                t=t+1;
            }
            webcam.stopStreaming();

            sleep(30000);
        }
    }
}
