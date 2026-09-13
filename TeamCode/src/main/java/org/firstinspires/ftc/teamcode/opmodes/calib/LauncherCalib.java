package org.firstinspires.ftc.teamcode.opmodes.calib;


import static com.pedropathing.ivy.Scheduler.schedule;
import static com.pedropathing.ivy.commands.Commands.infinite;
import static com.pedropathing.ivy.commands.Commands.instant;

import static dev.nextftc.units.Units.RotationsPerMinute;

import com.pedropathing.ivy.Scheduler;

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
        CommandGamepad gp1 = new CommandGamepad(gamepad1);

        gp1.dpadUp().onTrue(robot.getLauncher().setPollen());
        gp1.dpadDown().onTrue(robot.getLauncher().setNectar());

        gp1.circle().onTrue(instant(()->robot.getLauncher().setTargetVelocity(5000)));
        gp1.square().onTrue(instant(()->robot.getLauncher().setTargetVelocity(10000)));

       /* schedule(infinite(() -> {
            robot.getLauncher().setTargetVelocity(20000);
        }));*/
    }


    @Override
    public void periodic() {
        telemetry.addData("Velo (RPM)", robot.getLauncher().getLauncherMotor().getEncoderVelocity().into(RotationsPerMinute));
        telemetry.update();
    }

    @Override
    public void end() {
        Scheduler.reset();
    }

}
