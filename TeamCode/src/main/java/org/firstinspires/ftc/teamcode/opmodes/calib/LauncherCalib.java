package org.firstinspires.ftc.teamcode.opmodes.calib;


import static com.pedropathing.ivy.Scheduler.schedule;
import static com.pedropathing.ivy.commands.Commands.instant;

import static dev.nextftc.units.Units.RotationsPerMinute;

import org.firstinspires.ftc.teamcode.robot.HazmatRobot;

import dev.nextftc.robot.opmode.NextOpMode;
import dev.nextftc.robot.opmode.NextTeleop;
import dev.nextftc.robot.triggers.CommandGamepad;
import dev.nextftc.robot.triggers.Trigger;

@NextTeleop(name = "Launcher Calib", group = "1")
public class LauncherCalib extends NextOpMode {
    private final HazmatRobot robot;

    public LauncherCalib(HazmatRobot robot) {
        super(robot);
        this.robot = robot;
        Trigger.Companion.getDefaultEventLoop().clear();


    }
    @Override
    public void start(){
        schedule(instant(() -> {
            robot.getLauncher().getLauncherMotor().setThrottle(1.0);
        }));
    }


    @Override
    public void periodic() {
        telemetry.addData("Velo (RPM)", robot.getLauncher().getLauncherMotor().getEncoderVelocity().into(RotationsPerMinute));
        telemetry.update();
    }
}
