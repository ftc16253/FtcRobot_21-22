package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;
@Disabled
public class RingDetector extends OpenCvPipeline {
    private Mat workingMatrix = new Mat();
    public String numberOfRings = "NONE";
    public double bottomTotal;
    public double topTotal;
    public RingDetector(){ }

    public final  Mat processFrame(Mat input){
        input.copyTo(workingMatrix);
        if (workingMatrix.empty()){
            return input;
        }
        Imgproc.cvtColor(workingMatrix, workingMatrix, Imgproc.COLOR_RGB2YCrCb);
        Mat matBottom = workingMatrix.submat(120,135,120, 160);
        Mat matTop = workingMatrix.submat(95, 110, 120, 160);

        Imgproc.rectangle(workingMatrix, new Rect(120,120, 40, 15), new Scalar(0,255,0));
        Imgproc.rectangle(workingMatrix, new Rect(120,100, 40, 15), new Scalar(0,255,0));

        bottomTotal = Core.sumElems(matBottom).val[2];
        topTotal = Core.sumElems(matTop).val[2];

        if (bottomTotal < 57000){
            if (topTotal < 64000) {
                numberOfRings = "QUAD";
            }else {
                numberOfRings = "SINGLE";
            }
        } else {
            numberOfRings = "NONE";
        }

        /* //Settings for when it's dark
        if (bottomTotal < 54000){
            if (topTotal < 62000) {
                numberOfRings = "QUAD";
            }else {
                numberOfRings = "SINGLE";
            }
        } else {
            numberOfRings = "NONE";
        }

         */
        return workingMatrix;
    }
}

