/* Copyright (c) 2019 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode.BEAST_2021;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

import java.util.List;

@Autonomous(name = "autoBlueDuck")
public class autoBlueDuck extends LinearOpMode {
    private static final String TFOD_MODEL_ASSET = "FreightFrenzy_BCDM.tflite";
    private static final String[] LABELS = {
            "Ball",
            "Cube",
            "Duck",
            "Marker"
    };
    String place;

    private static final String VUFORIA_KEY =
            "Afctxlz/////AAABmSWf4jOsTUHcsOYa/JfaZlRo+3yiPN8cCUH4BDLpIZ8FAt0tEVLJ/mxWUyd7f0gqd+a7JRTMYP9+A9s1nojOs9B1ZGOFsvr84RZnbVN8cGP7RFKNP4Mg0Pr/6vIUmHGFx/jrOrXz/YJXwVXvPpqr1uDm8xpBZOE4j+CtQcKW2Y2zjVWHWRTkmb6ve/R91k3jfjaH4PErbZMcvD7Xy5IesqSet3/pjeUXWSnlHmPwH7fgUcHSkAf0Fj2nLvZ7zmpT8vh9rSKri9XD3A64WBNRO+6+SGH/C/eS3mWLmdi5ZMbSK66WuvNhAPT0SHCzzqAlAf2P6asrrrAuw+aQ0B2HV0mPtGdNPe62djhu5Afa/rL+";

    /**
     * {@link #vuforia} is the variable we will use to store our instance of the Vuforia
     * localization engine.
     */
    private VuforiaLocalizer vuforia;
    Pushbot2021 robot = new Pushbot2021();

    /**
     * {@link #tfod} is the variable we will use to store our instance of the TensorFlow Object
     * Detection engine.
     */
    private TFObjectDetector tfod;

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        // The TFObjectDetector uses the camera frames from the VuforiaLocalizer, so we create that
        // first.
        initVuforia();
        initTfod();

        /**
         * Activate TensorFlow Object Detection before we wait for the start command.
         * Do it here so that the Camera Stream window will have the TensorFlow annotations visible.
         **/
        if (tfod != null) {
            tfod.activate();
            tfod.setZoom(1.2, 17.0/10.0);
        }


        //robot.slide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.turret.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        /** Wait for the game to begin */
        telemetry.addData(">", "Press Play to start op mode");
        telemetry.update();
        waitForStart();

        if (opModeIsActive()) {
            while (opModeIsActive()) {
                if (tfod != null) {
                    // getUpdatedRecognitions() will return null if no new information is available since
                    // the last time that call was made.
                    List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
                   /* if (updatedRecognitions == null){
                        place = "right";
                    }*/
                    if (updatedRecognitions != null) {
                        telemetry.addData("# Object Detected", updatedRecognitions.size());
                        // step through the list of recognitions and display boundary info.
                        int i = 0;
                        for (Recognition recognition : updatedRecognitions) {
                            telemetry.addData(String.format("label (%d)", i), recognition.getLabel());
                            telemetry.addData(String.format("  left,top (%d)", i), "%.03f , %.03f",
                                    recognition.getLeft(), recognition.getTop());
                            telemetry.addData(String.format("  right,bottom (%d)", i), "%.03f , %.03f",
                                    recognition.getRight(), recognition.getBottom());

                            i++;
                        }
                        if (updatedRecognitions.size() > 0 && (updatedRecognitions.get(0).getLabel() == "Duck" || updatedRecognitions.get(0).getLabel() == "Cube")){
                            place = "left";
                        }
                        else if (updatedRecognitions.size() > 1 && (updatedRecognitions.get(1).getLabel() == "Duck" || updatedRecognitions.get(1).getLabel() == "Cube")){
                            place = "mid";
                        }
                        else {
                            place = "right";
                        }

                        tfod.shutdown();
                        place="left";
                        telemetry.addData("Place = ", place);
                        telemetry.update();

                        //when duck is in left config
                        /*if (place == "left"){

                            //move away from wall
                            robot.MoveForwardInch(14, 1);
                            sleep(100);

                            //backup to pickup cube
                            robot.MoveForwardInch(10,-1);

                            //pickup cube {
                            robot.linkage.setPosition(.2);
                            robot.pivot.setPosition(.24);
                            sleep(200);
                            robot.grabber.setPosition(1);
                            sleep(1000);
                            robot.pivot.setPosition(.1);
                            // }

                            //turn to alliance hub
                            robot.turn(22, -1);

                            //move pivot to place cube on correct level
                            robot.pivot.setPosition(.125);

                            //move to alliance hub
                            robot.MoveForwardInch(30,1);

                            //move turret straight
                            robot.moveTurret(-10,1);

                            //deposit the cube in tower
                            robot.grabber.setPosition(0);

                            //move linkage in
                            robot.linkage.setPosition(.4);

                            //turn to duck spinner
                            robot.turn(18, -1);

                            //move to duck spinner
                            robot.MoveForwardInch(50, -1);

                            //turn to put duckspinner on carousel
                            robot.turn(35,1);

                            //turn carousel
                            robot.duckSpinner.setPower(-.2);
                            sleep(200);
                            robot.duckSpinner.setPower(-.4);
                            sleep(200);
                            robot.duckSpinner.setPower(-.6);
                            sleep(200);
                            robot.duckSpinner.setPower(-.8);
                            sleep(2500);

                            //stop carousel
                            robot.duckSpinner.setPower(0);
                            sleep(250);

                            //turn to storage unit
                            robot.turn(20,1);

                            //move away from carousel
                            robot.MoveForwardInch(30, 1);

                            sleep(30000);
                        }
                        //when duck is in right config
                        else if (place == "right"){
                            //move away from wall
                            robot.MoveForwardInch(14, 1);
                            sleep(100);

                            //backup to pickup cube
                            robot.MoveForwardInch(8,-1);

                            //pickup cube {
                            robot.linkage.setPosition(.2);
                            robot.pivot.setPosition(.235);
                            sleep(200);
                            robot.grabber.setPosition(1);
                            sleep(1000);
                            robot.pivot.setPosition(.1);
                            // }

                            //turn to alliance hub
                            robot.turn(22, -1);

                            //movelinear slide to hold cube above top level
                            robot.slide.setPower(-.1);
                            sleep(1000);
                            robot.slide.setPower(0);

                            //move pivot to clear top level
                            robot.pivot.setPosition(0);
                            sleep(5000);

                            //move to alliance hub
                            robot.MoveForwardInch(26,1);

                            //deposit the cube in tower
                            robot.grabber.setPosition(0);

                            //move linkage in
                            robot.linkage.setPosition(.4);

                            //turn to duck spinner
                            robot.turn(16, -1);

                            //move to duck spinner
                            robot.MoveForwardInch(41, -1);

                            //turn to put duckspinner on carousel
                            robot.turn(35,1);

                            //Move back to hit carousel
                            robot.MoveForwardInch(2, -1);

                            //turn carousel
                            robot.duckSpinner.setPower(-.7);
                            sleep(2500);

                            //stop carousel
                            robot.duckSpinner.setPower(0);
                            sleep(250);

                            //turn to storage unit
                            robot.turn(4,1);

                            //move away from carousel
                            robot.MoveForwardInch(20, 1);

                            sleep(30000);
                        }
                        //when duck is in middle config
                        else if (place == "mid"){

                            //move away from wall
                            robot.MoveForwardInch(14, 1);
                            sleep(100);

                            //backup to pickup cube
                            robot.MoveForwardInch(8,-1);

                            //pickup cube {
                            robot.linkage.setPosition(.2);
                            robot.pivot.setPosition(.235);
                            sleep(200);
                            robot.grabber.setPosition(1);
                            sleep(1000);
                            robot.pivot.setPosition(.1);
                            // }

                            //turn to alliance hub
                            robot.turn(22, -1);

                            //move pivot to correct level
                            robot.pivot.setPosition(.05);

                            //move to alliance hub
                            robot.MoveForwardInch(26,1);

                            //make turret staright
                            robot.moveTurret(0,1);

                            //deposit the cube in tower
                            robot.grabber.setPosition(0);

                            //move linkage in
                            robot.linkage.setPosition(.4);

                            //turn to duck spinner
                            robot.turn(16, -1);

                            //move to duck spinner
                            robot.MoveForwardInch(41, -1);

                            //turn to put duckspinner on carousel
                            robot.turn(35,1);

                            //Move back to hit carousel
                            robot.MoveForwardInch(2, -1);

                            //turn carousel
                            robot.duckSpinner.setPower(-.7);
                            sleep(2500);

                            //stop carousel
                            robot.duckSpinner.setPower(0);
                            sleep(250);

                            //turn to storage unit
                            robot.turn(4,1);

                            //move away from carousel
                            robot.MoveForwardInch(20, 1);

                            sleep(30000);
                        }*/
                    }
                }
            }
        }
    }

    /**
     * Initialize the Vuforia localizat  ion engine.
     */
    private void initVuforia() {
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraName = hardwareMap.get(WebcamName.class, "Webcam 2021");

        //  Instantiate the Vuforia engine
        vuforia = ClassFactory.getInstance().createVuforia(parameters);

        // Loading trackables is not necessary for the TensorFlow Object Detection engine.
    }

    /**
     * Initialize the TensorFlow Object Detection engine.
     */
    private void initTfod() {
        int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
                "tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfodParameters.minResultConfidence = 0.8f;
        tfodParameters.isModelTensorFlow2 = true;
        tfodParameters.inputSize = 350;
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABELS);
    }
}
