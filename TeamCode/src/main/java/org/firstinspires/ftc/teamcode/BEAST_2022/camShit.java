package org.firstinspires.ftc.teamcode.BEAST_2022;


class camShit {
    RealSenseCamera camera = new RealSenseCamera(this);

    void setup() {
        // check if a camera is available
        boolean a = camera.isDeviceAvailable();

        // check how many cameras are available
        int c = camera.getDeviceCount();
    }
}
