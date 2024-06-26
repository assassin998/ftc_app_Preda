package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class verstappen_drive extends LinearOpMode {
    private DcMotor FL;
    private DcMotor FR;
    private DcMotor BL;
    private DcMotor BR;

    @Override
    public void runOpMode() throws InterruptedException {

        FL = hardwareMap.get(DcMotor.class, "FL");
        FR = hardwareMap.get(DcMotor.class, "FR");
        BL = hardwareMap.get(DcMotor.class, "BL");
        BR = hardwareMap.get(DcMotor.class, "BR");
        FL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        FR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        waitForStart();

        while(opModeIsActive()) {
            float front; float rotate;
            front = gamepad1.left_stick_y;
            rotate = gamepad1.right_stick_x;

            if(!gamepad1.right_bumper) {
                front *= 0.6f;
            }

            float divider;
            divider = Math.max( Math.max(front - rotate, front + rotate), Math.max(-front + rotate, -front - rotate));

            if(divider <= 1) {
                divider = 1;
            }

            //normalizer
            FL.setPower((front + rotate) / divider);
            FR.setPower((front - rotate) / divider);
            BL.setPower((front + rotate) / divider);
            BR.setPower((front - rotate) / divider);

        }
    }
}
