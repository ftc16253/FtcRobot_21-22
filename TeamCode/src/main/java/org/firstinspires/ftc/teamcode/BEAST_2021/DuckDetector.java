package org.firstinspires.ftc.teamcode.BEAST_2021;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;
@Disabled
public class DuckDetector extends OpenCvPipeline {
    private Mat workingMatrix = new Mat();
    public String position = "";
    public double leftTotal, centerTotal, rightTotal;
    public DuckDetector(){

    }

    public final  Mat processFrame(Mat input){
        input.copyTo(workingMatrix);
        if (workingMatrix.empty()){
            return input;
        }
        Imgproc.cvtColor(workingMatrix, workingMatrix, Imgproc.COLOR_RGB2YCrCb);

        //Mat matLeft = workingMatrix.submat(120,150,10, 50);
        //Mat matCenter = workingMatrix.submat(120, 150, 80, 120);
        //Mat matRight = workingMatrix.submat(120, 150, 150, 190);
        Mat matLeft = workingMatrix.submat(100,150,10, 60);
        Mat matCenter = workingMatrix.submat(100, 150, 205, 255);
        //Mat matRight = workingMatrix.submat(120, 150, 150, 190);

        Imgproc.rectangle(workingMatrix, new Rect(10,100, 50, 50), new Scalar(0,255,0));
        Imgproc.rectangle(workingMatrix, new Rect(205,100, 50, 50), new Scalar(0,255,0));
        //Imgproc.rectangle(workingMatrix, new Rect(150,120, 40, 30), new Scalar(0,255,0));

        leftTotal = Core.sumElems(matLeft).val[2];
        centerTotal = Core.sumElems(matCenter).val[2];
        //rightTotal = Core.sumElems(matRight).val[2];

        if (leftTotal < centerTotal){
                //left is Duck
                position = "LEFT";
        } else {
            if (leftTotal - centerTotal > 10000.0){
                //center is Duck
                position = "CENTER";
            } else {
                //right is Duck
                position = "RIGHT";
            }
        }

        return workingMatrix;
    }
}
