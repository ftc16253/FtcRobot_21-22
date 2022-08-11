package org.firstinspires.ftc.teamcode.BEAST_2022;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous
public class Auto2022 extends LinearOpMode {
    Pushbot2022 bot = new Pushbot2022();

    @Override
    public void runOpMode(){
        bot.init(hardwareMap);

        waitForStart();

        while (opModeIsActive()){
            bot.PIDX(48);
            break;
        }
        }
    }

