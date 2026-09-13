package org.firstinspires.ftc.teamcode.opmodes.calib;

import static com.pedropathing.ivy.commands.Commands.instant;

import org.firstinspires.ftc.teamcode.robot.HazmatRobot;

import dev.nextftc.robot.opmode.NextOpMode;
import dev.nextftc.robot.opmode.NextTeleop;
import dev.nextftc.robot.triggers.CommandGamepad;
import dev.nextftc.robot.triggers.Trigger;

@NextTeleop(name = "Mecanum")
public class MecanumCalib extends NextOpMode {
    HazmatRobot robot;
    public MecanumCalib(HazmatRobot robot){
        super(robot);
        this.robot = robot;
        Trigger.Companion.getDefaultEventLoop().clear();
    }

    @Override
    public void start() {
        CommandGamepad gp1 = new CommandGamepad(gamepad1);

//        gp1.rightBumper().onTrue(robot.get);

    }
}
