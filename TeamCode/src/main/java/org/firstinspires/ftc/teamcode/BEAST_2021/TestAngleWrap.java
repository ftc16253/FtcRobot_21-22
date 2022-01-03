package org.firstinspires.ftc.teamcode.BEAST_2021;

public class TestAngleWrap {

    public static void main(String[] args) {
        System.out.println(angleWrap(370));
    }

    public static double angleWrap(double currentAngle) {
        while (currentAngle < 0) {
            currentAngle += 360;
        }
        while (currentAngle > 360) {
            currentAngle -= 360;
        }

        return currentAngle;
    }
}
