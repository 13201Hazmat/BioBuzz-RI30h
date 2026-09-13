package org.firstinspires.ftc.teamcode.opmodes.calib;

import com.pedropathing.ivy.Scheduler;

import org.firstinspires.ftc.teamcode.robot.HazmatRobot;

import dev.nextftc.robot.opmode.NextOpMode;
import dev.nextftc.robot.opmode.NextUtility;
import dev.nextftc.robot.triggers.CommandGamepad;
import dev.nextftc.robot.triggers.Trigger;

@NextUtility(name = "Servo Calib")
public class ServoCalib extends NextOpMode {
    private final HazmatRobot robot;

    public ServoCalib(HazmatRobot robot) {
        super(robot);
        this.robot = robot;
        Scheduler.reset();
    }

    @Override
    public void disabledPeriodic() {
//        int chose = 0;
//        telemetry.addLine("Pick a servo to calibrate");
//        telemetry.addLine("Right bumper for Launcher Gate Servo");
//        telemetry.addLine("Left bumper for Compression Servo");
//        telemetry.addLine("");
//
//        if(gamepad1.rightBumperWasPressed()){
//            chose = 1;
//        }
//        else if (gamepad1.leftBumperWasPressed()){
//            chose = 2;
//        }
    }

    @Override
    public void start() {
        Trigger.Companion.getDefaultEventLoop().clear();
        CommandGamepad gp1 = new CommandGamepad(gamepad1);
        robot.startDrive(gamepad1);

        gp1.rightBumper().onTrue(robot.getTransfer().deltaUp());
        gp1.leftBumper().onTrue(robot.getTransfer().deltaDown());

        gp1.dpadUp().onTrue(robot.getLift().deltaUp());
        gp1.dpadDown().onTrue(robot.getLift().deltaDown());

        gp1.dpadLeft().onTrue(robot.getLauncher().deltaUp());
        gp1.dpadRight().onTrue(robot.getLauncher().deltaDown());
    }

    @Override
    public void periodic() {
        super.periodic();
    }

    @Override
    public void end() {
        Scheduler.reset();
    }

}
