package org.firstinspires.ftc.teamcode.opmodes.calib;


import static com.pedropathing.ivy.commands.Commands.instant;

import org.firstinspires.ftc.teamcode.robot.HazmatRobot;

import dev.nextftc.robot.opmode.NextOpMode;
import dev.nextftc.robot.opmode.NextTeleop;
import dev.nextftc.robot.triggers.CommandGamepad;
import dev.nextftc.robot.triggers.Trigger;

@NextTeleop(name = "test", group = "1")
public class LauncherCalib extends NextOpMode {
    private final HazmatRobot robot;

    public LauncherCalib(HazmatRobot robot) {
        super(robot);
        this.robot = robot;
        robot.getFollower();
        Trigger.Companion.getDefaultEventLoop().clear();
        CommandGamepad gp1 = new CommandGamepad(gamepad1);

//        gp1.rightBumper().onTrue(instant(() -> robot.getLauncher().setTargetVelocity(2000)));

    }

    @Override
    public void periodic() {
        telemetry.update();
    }
}
